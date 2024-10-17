package com.newdemo.framework.base;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import com.ifountain.opsgenie.client.swagger.model.TeamRecipient;
import com.opencsv.CSVReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.MongoClient;

/**
 * A class contain generic utility methods that can be used across the framework
 */
public class Utilites {

    public static MongoClient mongoClient;
    public static MongoDatabase mongoDB;

    public HashMap<String, String> splitNStoreParamsInHashMap(String strParameters) {
        HashMap<String, String> objHashMap = new HashMap<String, String>();
        if (strParameters != "" && strParameters != null) {
            String[] arrKeysNValues = strParameters.split("--");

            for (int intArrElt = 0; intArrElt < arrKeysNValues.length; intArrElt++) {
                if (arrKeysNValues[intArrElt] != "" && arrKeysNValues[intArrElt] != null) {
                    String[] arrCurrKeyNValue = arrKeysNValues[intArrElt].split("=>");
                    objHashMap.put(arrCurrKeyNValue[0], arrCurrKeyNValue[1]);
                }
            }
        }
        return objHashMap;
    }

    public static int getRowCount(String fileName, String sheet) throws IOException {
        int numOFRows = 0;
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            ZipSecureFile.setMinInflateRatio(-1.0d);
            @SuppressWarnings("resource")
            Workbook wb = new XSSFWorkbook(fileInputStream);
            numOFRows = wb.getSheet(sheet).getLastRowNum();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (numOFRows);
    }

    public static String convertCsvToXls(String xlsFileLocation, String csvFilePath, String xlsFileName) {
        SXSSFSheet sheet = null;
        CSVReader reader = null;
        Workbook workBook = new SXSSFWorkbook();
        String generatedXlsFilePath = "";
        FileOutputStream fileOutputStream = null;


        try {

            /**** Get the CSVReader Instance & Specify The Delimiter To Be Used ****/
            String[] nextLine;
            reader = new CSVReader(new FileReader(xlsFileLocation + csvFilePath), ',');


            sheet = (SXSSFSheet) workBook.createSheet("Sheet");

            int rowNum = 0;
            System.out.println("Creating New .Xls File From The Already Generated .Csv File");
            while ((nextLine = reader.readNext()) != null) {
                Row currentRow = sheet.createRow(rowNum++);
                for (int i = 0; i < nextLine.length; i++) {
                    if (NumberUtils.isDigits(nextLine[i])) {
                        currentRow.createCell(i).setCellValue(Long.parseLong(nextLine[i]));
                    } else {
                        currentRow.createCell(i).setCellValue(nextLine[i]);
                    }
                }
            }

            generatedXlsFilePath = xlsFileLocation + xlsFileName + ".xlsx";
            System.out.println("The File Is Generated At The Following Location?= " + generatedXlsFilePath);

            fileOutputStream = new FileOutputStream(generatedXlsFilePath.trim());
            workBook.write(fileOutputStream);
        } catch (Exception exObj) {
            System.err.println("Exception In convertCsvToXls() Method?=  " + exObj);
            exObj.printStackTrace();
        } finally {
            try {

                /**** Closing The Excel Workbook Object ****/
                workBook.close();

                /**** Closing The File-Writer Object ****/
                fileOutputStream.close();

                /**** Closing The CSV File-ReaderObject ****/
                reader.close();
            } catch (IOException ioExObj) {
                System.err.println("Exception While Closing I/O Objects In convertCsvToXls() Method?=  " + ioExObj);
            }
        }

        return generatedXlsFilePath;
    }

    public static String readTextFileAndGetAsString(String strFilePath) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(strFilePath));

            String txtFileLines = "";
            String strCurrentLine = "";
            while ((strCurrentLine = bufferedReader.readLine()) != null) {
                txtFileLines = txtFileLines + strCurrentLine;
            }
            return txtFileLines;
        } catch (Exception e) {
            System.out.println("Exception while reading the file " + strFilePath + "\n" + e.toString());
            return null;
        }
    }

    public int findColumnNoInExcel(HSSFSheet objSH, String strColumnName, int intRowNum) throws Exception {
        intRowNum = intRowNum - 1;
        HSSFRow objRow = objSH.getRow(intRowNum);

        int intLastCellNum;
        String strAvailCellValue;

        try {
            intLastCellNum = objRow.getLastCellNum();
            for (int intCell = 0; intCell < intLastCellNum; intCell++) {
                strAvailCellValue = objSH.getRow(intRowNum).getCell(intCell).getStringCellValue();
                if (strAvailCellValue.equalsIgnoreCase(strColumnName)) {
                    return intCell;
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

    public String getCellValueForRowNum(HSSFSheet objSH, String strColumnName, int intRowNum) throws Exception {

        intRowNum = intRowNum - 1;
        int intXLColumn;
        intXLColumn = this.findColumnNoInExcel(objSH, strColumnName, 1);

        String strAvailCellValue = null;
        Cell objXLCell;
        try {
            objXLCell = objSH.getRow(intRowNum).getCell(intXLColumn);
            objXLCell.setCellType(CellType.STRING); // to convert cell type to string
            strAvailCellValue = objXLCell.getStringCellValue();
            return strAvailCellValue.trim();
        } catch (Exception e) {
            // System.out.println("Exception " + e);
            return "";
        }
    }

    public Boolean setCellValueForRowNum(HSSFSheet objSH, String strColumnName, int intRowNum, String strValue)
            throws Exception {
        int intRowCount;
        int intColumnCount;
        int intColumnNum;
        intRowCount = objSH.getLastRowNum();

        HSSFRow objRow = null;

        intColumnNum = this.findColumnNoInExcel(objSH, strColumnName, 1);

        if (intRowCount >= intRowNum) {
            if (objSH.getRow(intRowNum) != null) {
                objRow = objSH.getRow(intRowNum);
            } else {
                objRow = objSH.createRow(intRowNum);
            }

            if (objRow.getCell(intColumnNum) != null) {
                objRow.getCell(intColumnNum).setCellValue(strValue);
            } else {
                objRow.createCell(intColumnNum).setCellValue(strValue);
            }
            return true;
        } else {
            if (objSH.getRow(intRowNum) != null) {
                objRow = objSH.getRow(intRowNum);
            } else {
                objRow = objSH.createRow(intRowNum);
            }
            objRow.createCell(intColumnNum).setCellValue(strValue);
            return true;
        }
    }

    public String getCellValueInExcel(HSSFSheet objSH, int intRow, int intColumn) {
        String strCellValue;
        try {
            strCellValue = objSH.getRow(intRow).getCell(intColumn).getStringCellValue().trim();
        } catch (Exception e) {
            strCellValue = "";
        }
        return strCellValue;
    }

    public static String getExceptionNDisplay(Exception objException, boolean blnIsDisplay) throws Exception {
        System.err.println(objException.getMessage());
        String strException = objException.getMessage();
        if (strException != null) {
            String[] arrException = strException.split("\n");
            if (blnIsDisplay) {
                System.out.println("Exception occurred " + arrException[0]);
            }
            return arrException[0];
        } else {
            return "No specific error message thrown from driver for the current step. Check error message in previous steps";
        }
    }

    // ==========================CONNECT TO
    // DB=======================================================
    public void connectDB() throws Exception {

        Properties objProperties = new Properties();
        objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
        String url1 = objProperties.getProperty("DBURL");
        String db = "";
        String username = objProperties.getProperty("Username");
        String password = objProperties.getProperty("Password");
        String query = objProperties.getProperty("Query");

        Class.forName(db).newInstance();
        Connection con = DriverManager.getConnection(url1, username, password);
        Statement stmt = (Statement) con.createStatement();
        ResultSet raw = (ResultSet) stmt.executeQuery(query);
        String raw1 = raw.toString();
        String[] result = raw1.split("@");
        System.out.println(result[1]);

    }

    /*connect to mongo db*/
    public static void startMongoDBconnection(String env) throws FileNotFoundException, IOException {
        Properties objProperties = new Properties();
        objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
        String dBurl = objProperties.getProperty(env + "MongoDB");
//String user = objProperties.getProperty(env+"MongoUser");
//String pwd = objProperties.getProperty(env+"MongoPwd");
//String source = objProperties.getProperty("Mongosource");
//String auth = objProperties.getProperty("MongoAuth");
//Builder build =  MongoClientSettings.builder()
//.applyToClusterSettings(builder ->
//builder.hosts(Arrays.asList(new ServerAddress(DBurl, 27017))));
//if(user!=null && pwd!=null) {
//MongoCredential credential = MongoCredential.createCredential(user, source, pwd.toCharArray());
//build = build.credential(credential);
//}
        mongoClient = MongoClients.create(dBurl);
    }

    public static void connectToMongoDB(String db, CodecRegistry pojoCodecRegistry) {
        if (mongoClient != null) {
            mongoDB = mongoClient.getDatabase(db).withCodecRegistry(pojoCodecRegistry);
        }
    }

    public static <T> InsertOneResult addToMongoDB(String collection, Class<T> type, T data) {
        MongoCollection<T> col = mongoDB.getCollection(collection, type);
        return col.insertOne(data);
    }

    public static <T> UpdateResult replaceInMongoDB(String collection, Bson filter, Class<T> type, T data) {
        MongoCollection<T> col = mongoDB.getCollection(collection, type);
        return col.replaceOne(filter, data);
    }

    public static <T> T getFromMongoDB(String collection, Bson filter, Class<T> type) {
        MongoCollection<T> col = mongoDB.getCollection(collection, type);
        return col.find(filter).first();
    }

    /*enf mongo db utils*/

    public static String getDateInFormat(String format, int differenceInDay) {
        String d = null;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, differenceInDay);
        Date date = cal.getTime();
        d = new SimpleDateFormat(format).format(date);

        return d;
    }

    public static String getLambdaTestVideourl(String sessionId) throws IOException, URISyntaxException {

        RequestSpecification request = RestAssured.given();
        Response video = request.accept(ContentType.JSON)
                .header("Authorization",
                        "Basic YW5pc2hfb2hyaTprZTBjTmRPaXBLTlZHS0s2ZlpwTmFSUzJtWmdXVjhZcThSQjhOMUhTT0RaQmZFcm8xZw==")
                .contentType(ContentType.JSON)
                .get("https://api.lambdatest.com/automation/api/v1/sessions/" + sessionId.toString() + "/video");
        return video.jsonPath().get("view_video_url");
    }

    /**
     * OpsGinnie alert creation
     **/
    public static <T> List<T> getOpsgenieRecipient(String groupname) {
        List<T> list = new ArrayList<>();
        String[] team1 = BaseSetupClass.objProperties.getProperty(groupname).split(",");
        for (String t : team1) {
            list.add((T) new TeamRecipient().name(t));
        }
        return list;
    }

    /**
     * create an Opsginnie alert
     *
     * @param {String} title
     * @param {String} description
     * @param {String} alias
     * @throws ApiException
     */
//public static void createOpsGinnieAlert(String message, String description, String alias,PriorityEnum priority) throws ApiException {
//AlertApi client = new OpsGenieClient().alertV2();
//client.getApiClient().setApiKey(BaseTest.objProperties.getProperty("opsgenieapikey"));
//CreateAlertRequest request = new CreateAlertRequest();
//request.setAlias(alias);
//request.setMessage(message);
//request.setDescription(description);
//request.setTeams(getRecipient(TeamRecipient.class));
//request.visibleTo(getRecipient(Recipient.class));
//request.setActions(Arrays.asList("ping", "check Auth service"));
//request.setTags(Arrays.asList(BaseTest.app));
//request.setEntity(BaseTest.app);
//request.setPriority(priority);
//request.setUser("anish_ohri@trepp.com");
//request.setNote("Alert created");
//SuccessResponse response = client.createAlert(request);
//System.out.println( response.getTook());
//System.out.println(response.getRequestId());
//System.out.println(response.getResult());
//}
    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static void main(String[] args) throws Exception {

    }

    public static boolean isWithinRange(Date testDate, Date startDate, Date endDate) {

        // it works, alternatives
        /*boolean result = false;
        if ((testDate.equals(startDate) || testDate.equals(endDate)) ||
                (testDate.after(startDate) && testDate.before(endDate))) {
            result = true;
        }
        return result;*/

        // compare date and time, inclusive of startDate and endDate
        // getTime() returns number of milliseconds since January 1, 1970, 00:00:00 GMT
        return testDate.getTime() >= startDate.getTime()
                &&
                testDate.getTime() <= endDate.getTime();
    }

    public static boolean checkdateinrange(String startdate, String enddate, String testdate) throws ParseException {
        boolean status;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

        Date startDate = sdf.parse(startdate);
        Date endDate = sdf.parse(enddate);

        System.out.println("startDate : " + sdf.format(startDate));
        System.out.println("endDate : " + sdf.format(endDate));

        //DateRangeValidator checker = new DateRangeValidator(startDate, endDate);

        Date testDate = sdf.parse(testdate);
        System.out.println("testDate : " + sdf.format(testDate));

        if (isWithinRange(testDate, startDate, endDate)) {
            status = true;
            System.out.println("testDate is within the date range.");
        } else {
            status = false;
            System.out.println("testDate is NOT within the date range.");
        }
        return status;

    }


}
