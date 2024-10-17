package com.newdemo.steps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.result.UpdateResult;
import com.newdemo.framework.base.Utilites;
import com.newdemo.framework.controller.CherreAPIController;
import com.newdemo.framework.model.CherreBatchResult;
import com.newdemo.framework.model.LoansPinIDandAddress;
import io.restassured.RestAssured;
import org.apache.xmlbeans.SystemProperties;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class CacheBuilder {
    static final String ENDPOINT = "/loanuniverse/search";
    static final String ENV = SystemProperties.getProperty("env", "alpha");

    static final String CSV_FILENAME = SystemProperties.getProperty("filename", "DISTINCT_pinid_address.csv");

    List<LoansPinIDandAddress> loans = new ArrayList<>();
    List<LoansPinIDandAddress> errLoans = new ArrayList<>();

    CherreBatchResult cbh;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    /**
     * @throws IOException
     */
    @Test(description = "cache all loan's cherre data to trepploan", invocationCount = 20)
    public void cherreCacheBuilder() throws IOException {

        int limit = 100;
        int currentCount = 0;
        int hardLoopCount = 100;

        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));


        // Reading csv file into list of pojo class
        ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(System.getProperty("user.dir") + "/Input/cherre/" + CSV_FILENAME), CsvPreference.STANDARD_PREFERENCE);
        final String[] columns = beanReader.getHeader(true);

        LoansPinIDandAddress loan;
        while ((loan = beanReader.read(LoansPinIDandAddress.class, columns)) != null) {
            loans.add(loan);
        }
        beanReader.close();

        System.out.println("total record: " + loans.size());


        for (int i = 0; i < hardLoopCount; i++) {
            ICsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter(System.getProperty("user.dir") + "/Input/cherre/error_pinids.csv", true), CsvPreference.STANDARD_PREFERENCE);
            Utilites.startMongoDBconnection(ENV);
            Utilites.connectToMongoDB("trepploanAlpha", pojoCodecRegistry);
            try {
                cbh = getBatchResult();
                cbh.setStart(ZonedDateTime.now().format(df));

                System.out.println("start time: " + cbh.toString());

                currentCount = cbh.getLoanCached();
                if (!(loans.size() > currentCount)) break;

                List<LoansPinIDandAddress> l1 = loans.subList(currentCount, currentCount + limit);

                try {
                    proccessforLoans(l1);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                int cachedLoans = currentCount + l1.size();


                for (LoansPinIDandAddress el : errLoans) {
                    beanWriter.write(el, columns);
                }
                cbh.setErrorCount(cbh.getErrorCount() + errLoans.size());
                errLoans.clear();

                cbh.setEnd(ZonedDateTime.now().format(df));
                cbh.setLoanCached(cachedLoans);
                cbh.setLastPinId(loans.get(l1.size() - 1).getPinid());
                cbh.setCacheBuilderStatus("completed");
                setBatchResult(cbh);
                System.out.println("end : " + cbh.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                beanWriter.close();
                Utilites.mongoClient.close();
            }
        }


    }

    /**
     * get batch result if null then add initial entry to mongo
     * also update batch status to inprogress
     *
     * @return CherreBatchResult
     */
    public CherreBatchResult getBatchResult() {
        CherreBatchResult cbh = Utilites.getFromMongoDB("cherreOwnerData", eq("name", "cherreCacheJob"), CherreBatchResult.class);

        if (cbh == null) {
            cbh = new CherreBatchResult("cherreCacheJob", ZonedDateTime.now().format(df), null, 0, "", "inProgress", 0);
            Utilites.addToMongoDB("cherreOwnerData", CherreBatchResult.class, cbh);
        } else {
            cbh.setEnd("");
            cbh.setCacheBuilderStatus("inProgress");
            setBatchResult(cbh);

        }
        return cbh;
    }

    /**
     * update batch result to mongo
     *
     * @param chb
     */
    public void setBatchResult(CherreBatchResult chb) {
        Utilites.replaceInMongoDB("cherreOwnerData", eq("name", "cherreCacheJob"), CherreBatchResult.class, chb);
    }


    /**
     * Process a list of loans fetch data from cherre for each pinid and add them to mongo
     *
     * @param loans
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void proccessforLoans(List<LoansPinIDandAddress> loans) throws InterruptedException, ExecutionException {
        ForkJoinPool customThreadPool = new ForkJoinPool(16);
        customThreadPool.submit(() -> {
            loans.stream().parallel().forEach(loan -> {
                // System.out.println(loan.pinid+" :: "+loan.getAdress());
                try {
                    String oneLinerAddress = CherreAPIController.getOneLinerAddress(loan.getAdress());
                    JsonArray cherreData = CherreAPIController.getCherreData(oneLinerAddress);
                    if (cherreData.size() > 0) {
                        writeToDB(loan.pinid, cherreData.get(0).getAsJsonObject());
                    }

                } catch (Exception e) {
                    errLoans.add(loan);
                    e.printStackTrace();
                }
            });
        }).get();
    }

    public void writeToDB(String pinid, JsonObject cherreData) throws Exception {
        String model = "{\r\n" + "  \"pinid\" : \"\",\r\n" + "  \"cachedDate\" : \"\",\r\n" + "  \"data\" : \"\"\r\n" + "}";

        JsonObject data = new JsonParser().parse(model).getAsJsonObject();
        data.addProperty("cachedDate", ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")));
        data.addProperty("pinid", pinid);
        data.add("data", cherreData);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // System.out.println(gson.toJson(data));

        //InsertOneResult result =  Utilites.addToMongoDB("cherreOwnerData", Document.class, Document.parse(gson.toJson(data)));
        UpdateResult result = Utilites.replaceInMongoDB("cherreOwnerData", eq("pinid", pinid), Document.class, Document.parse(gson.toJson(data)));
        if (!result.wasAcknowledged()) throw new Exception("Write operation to cherreOwnerData was not successful");
    }

}
