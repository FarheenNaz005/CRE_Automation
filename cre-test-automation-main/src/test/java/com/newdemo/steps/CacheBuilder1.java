package com.newdemo.steps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.Utilites;
import com.newdemo.framework.controller.CherreAPIController;
import com.newdemo.framework.model.CherreBatchResult;
import com.newdemo.framework.model.LoansPinIDandAddress;
import io.restassured.response.Response;
import org.apache.xmlbeans.SystemProperties;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.hamcrest.Matchers.hasKey;

public class CacheBuilder1 extends BaseAPI {
    String endpoint = "/loanuniverse/search";
    String env = SystemProperties.getProperty("env", "alpha");
    CherreBatchResult cbh;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");


    @Test(description = "get all loan with PIN id and address")
    public void cherreCacheBuilder() throws IOException {

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        Utilites.startMongoDBconnection(env);
        Utilites.connectToMongoDB("trepploanAlpha", pojoCodecRegistry);

        int totalCoutnt = 0;
        int limit = 100;
        int offsets = 0;
        int nextoffset = 1;
        int currentCount = 0;
        String lastPinId = "";
        int hardLoopCount = 5;

        int count = 0;
        do {
            cbh = getBatchResult();
            cbh.setStart(ZonedDateTime.now().format(df));
            System.out.println("start time: " + cbh.toString());
            String body = "";
            if (cbh.getLastPinId().equals("")) {
                body = "{\r\n" + "    \"fields\": [\"pinid\", \"address\", \"city\", \"state\"],\r\n" + "    \"offset\": " + nextoffset + ",\r\n" + "    \"limit\": \"" + limit + "\",\r\n" + "    \"sort\": {\r\n" + "        \"pinid\": \"asc\"\r\n" + "    }\r\n" + "}";
            } else {
                body = "{\r\n" + "    \"fields\": [\"pinid\", \"address\", \"city\", \"state\"],\r\n" + "\"filters\": [{\r\n" + "            \"pinid\": {\r\n" + "                \"gt\": \"" + cbh.getLastPinId() + "\"\r\n" + "            }\r\n" + "        }]," + "    \"offset\": " + nextoffset + ",\r\n" + "    \"limit\": \"" + limit + "\",\r\n" + "    \"sort\": {\r\n" + "        \"pinid\": \"asc\"\r\n" + "    }\r\n" + "}";
            }


            Response output = getloans(body);

            totalCoutnt = output.jsonPath().getInt("match");
            //nextoffset = output.jsonPath().getInt("offset") + 1;
            offsets = output.jsonPath().getInt("offsets");
            List<LoansPinIDandAddress> loans = output.jsonPath().getList("data", LoansPinIDandAddress.class);

            currentCount = loans.size();
            try {
                proccessforLoans(loans);
            } catch (InterruptedException | ExecutionException e) {

                e.printStackTrace();
            }

            cbh.setEnd(ZonedDateTime.now().format(df));
            cbh.setLoanCached(cbh.getLoanCached() + loans.size());
            cbh.setLastPinId(loans.get(loans.size() - 1).pinid);
            cbh.setCacheBuilderStatus("completed");
            setBatchResult(cbh);
            System.out.println("end : " + cbh.toString());
            count++;

        } while ((totalCoutnt > cbh.getLoanCached()) && (count < hardLoopCount));

        Utilites.mongoClient.close();
    }


    public CherreBatchResult getBatchResult() {
        CherreBatchResult cbh = Utilites.getFromMongoDB("cherreOwnerData", eq("name", "cherreCacheJob"), CherreBatchResult.class);

        if (cbh == null) {
            cbh = new CherreBatchResult("cherreCacheJob", ZonedDateTime.now().format(df), null, 0, "", "inProgress", 0);
            Utilites.addToMongoDB("cherreOwnerData", CherreBatchResult.class, cbh);
        }
        return cbh;
    }

    public void setBatchResult(CherreBatchResult chb) {
        Utilites.replaceInMongoDB("cherreOwnerData", eq("name", "cherreCacheJob"), CherreBatchResult.class, chb);
    }


    public Response getloans(String body) {
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200).body("$", hasKey("total")).body("$", hasKey("offset")).body("$", hasKey("offsets")).body("$", hasKey("data"));

        return output;
    }

    public void proccessforLoans(List<LoansPinIDandAddress> loans) throws InterruptedException, ExecutionException {
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        customThreadPool.submit(() -> {
            loans.stream().parallel().forEach(loan -> {
                //System.out.println(loan.pinid+" :: "+loan.getAdress());
                try {
                    String oneLinerAddress = CherreAPIController.getOneLinerAddress(loan.getAdress());
                    JsonArray cherreData = CherreAPIController.getCherreData(oneLinerAddress);
                    if (cherreData.size() > 0) {
                        writeToDB(loan.pinid, cherreData.get(0).getAsJsonObject());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }).get();
    }

    public void writeToDB(String pinid, JsonObject cherreData) throws FileNotFoundException, IOException {
        String model = "{\r\n" + "  \"pinid\" : \"\",\r\n" + "  \"cachedDate\" : \"\",\r\n" + "  \"data\" : \"\"\r\n" + "}";

        JsonObject data = new JsonParser().parse(model).getAsJsonObject();
        data.addProperty("cachedDate", ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")));
        data.addProperty("pinid", pinid);
        data.add("data", cherreData);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(data));


        Utilites.addToMongoDB("cherreOwnerData", Document.class, Document.parse(gson.toJson(data)));
    }


}
