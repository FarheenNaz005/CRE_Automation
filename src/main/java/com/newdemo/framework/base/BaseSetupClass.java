
package com.newdemo.framework.base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/*import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;*/

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.xmlbeans.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
/*import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;*/

import com.ifountain.opsgenie.client.OpsGenieClient;
import com.ifountain.opsgenie.client.swagger.ApiException;
import com.ifountain.opsgenie.client.swagger.api.AlertApi;
import com.ifountain.opsgenie.client.swagger.model.BaseAlert;
import com.ifountain.opsgenie.client.swagger.model.CloseAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.CreateAlertRequest;
import com.ifountain.opsgenie.client.swagger.model.CreateAlertRequest.PriorityEnum;
import com.ifountain.opsgenie.client.swagger.model.ListAlertsRequest;
import com.ifountain.opsgenie.client.swagger.model.ListAlertsResponse;
import com.ifountain.opsgenie.client.swagger.model.SuccessResponse;
import com.newdemo.framework.controller.ApplicationController;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseSetupClass {
    public static Properties objProperties = new Properties();

    {
        try {
            objProperties.load(new FileReader(System.getProperty("user.dir")
                    + "/Config/CONFIG.properties"));
            System.setProperty("atu.reporter.config",
                    System.getProperty("user.dir") + "/Config/ATU.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ApplicationController trepp = null;
    public String strDTParametersNValues = "";
    public String parameterNValue = null;
    public static String strMainParametersNValues = "";
    public static String videourl;
    public Utilites commonFunctions = new Utilites();
    protected static SessionId session = null;

    // ==========================BROWSER
    // VARIABLES============================================
    public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
    public static final String IE_DRIVER_KEY = "webdriver.ie.driver";
    public static final String IE_DRIVER_EXE = "./Lib/IEDriverServer.exe";
    public static final String JAVA_TEMP_DIR = "java.io.tmpdir";
    private PropertiesConfiguration context;

    public static ThreadLocal<WebDriver> wdriver = new ThreadLocal<WebDriver>();
    public String browser = null;

    WebDriver localDriver = null;
    protected static String env = null;
    private static String enviornment = null;
    private static String app = null;
    private static String opsgeniealert = null;
    protected static String priority = "0";
    public static String executionEnv = null;
    public static String configFile = "Config/CONFIG.properties";
    public Properties config;

    public static String downloadFilepath = null;
    public static String inputFilepath = null;

    public Configuration getContext() {
        return (Configuration) this.context;
    }

    public String getString(String key) {
        return this.context.getString(key);
    }

    public String getString(String key, String defVal) {
        return this.context.getString(key, defVal);
    }

    private HashMap<String, String> getCapabilitiesfromParams(String params) {
        HashMap<String, String> cap = new HashMap<String, String>();

        for (String pair : params.split(";")) {
            String[] entry = pair.split(":");
            cap.put(entry[0], entry[1]);
        }

        return cap;
    }

    public static Map<Integer, String[]> priorityntestname = new HashMap<Integer, String[]>();

    public enum BROWSER {
        firefox, ie, microsoftedge, chrome, safari
    }

    @SuppressWarnings("checkstyle:EmptyBlock")
    @Parameters({"remoteurl", "Execute", "capabilities", "environment",
            "application", "opsgenieAlert"})

    @BeforeTest

    public synchronized void launchBrowser(String remoteurl, String execute,
                                           String capabilities, String environment, String application,
                                           String opsgenie) throws Exception {

        System.setProperty("ExectionPlatform", execute);

        env = SystemProperties.getProperty("environment", environment);
        app = SystemProperties.getProperty("application", application);
        opsgeniealert = SystemProperties.getProperty("application", opsgenie);
        BaseSetupClass.inputFilepath = objProperties
                .getProperty("InputDatasheetPath");
        setEnviornment(
                BaseSetupClass.inputFilepath + "Datasheet_" + env + ".xls");

        BaseSetupClass.executionEnv = execute.toLowerCase();

        HashMap<String, String> capMap = this
                .getCapabilitiesfromParams(capabilities);

        BROWSER selectedBrowser = null;
        selectedBrowser = BROWSER
                .valueOf(capMap.get("browserName").toLowerCase());

        // WebDriver localDriver = null;
        BaseSetupClass.downloadFilepath = Paths
                .get(System.getProperty("user.dir"), "Downloads")
                .toString();
        File directory = new File(BaseSetupClass.downloadFilepath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        System.out.println(BaseSetupClass.downloadFilepath);
        if (execute.equalsIgnoreCase("cloud")) {
            DesiredCapabilities caps = new DesiredCapabilities(capMap);
            caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            caps.setCapability("timezone", "UTC-08:00");
            String url = remoteurl;
            if (selectedBrowser == BROWSER.ie) {
                caps.setCapability(
                        InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,
                        true);
                caps.setCapability(
                        InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,
                        "accept");
            }
            localDriver = new RemoteWebDriver(new URL(url), caps);

        } else {

            if (selectedBrowser == null) {
                throw new RuntimeException(
                        "Unknown browser variable specified!!!!!!");
            }

            try {

                switch (selectedBrowser) {
                    case firefox: {
                        System.setProperty("webdriver.gecko.driver",
                                objProperties.getProperty("FirefoxDriverPath"));
                        localDriver = new FirefoxDriver();
                        break;
                    }

                    case chrome: {

                        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                        chromePrefs.put("profile.default_content_settings.popups",
                                0);
                        chromePrefs.put("download.default_directory",
                                BaseSetupClass.downloadFilepath);
                        ChromeOptions options = new ChromeOptions();
                        options.setExperimentalOption("prefs", chromePrefs);
                        options.addArguments("--remote-allow-origins=*");
                        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
                                true);
                        options.setCapability("chrome.switches",
                                Arrays.asList("--ignore-certificate-errors"));
                        DesiredCapabilities cap = new DesiredCapabilities();
                        options.merge(cap);
                        cap.setCapability(ChromeOptions.CAPABILITY, options);
                        ChromeDriverService service = new ChromeDriverService.Builder()
                                .usingDriverExecutable(new File(objProperties
                                        .getProperty("ChromeDriverPath")))
                                .usingAnyFreePort().build();
                        System.setProperty(CHROME_DRIVER_KEY,
                                objProperties.getProperty("ChromeDriverPath"));
                        localDriver = new ChromeDriver(options);
                        break;

                    }
                    case ie: {
                        System.setProperty(IE_DRIVER_KEY, IE_DRIVER_EXE);
                        localDriver = new InternetExplorerDriver();
                        break;
                    }

                    default: {
                        localDriver = new FirefoxDriver();
                    }
                }
            } catch (Throwable exp) {
                Reporter.log("Exception in browser initialization!!! : "
                        + exp.getMessage());
            }

            String os = System.getProperty("os.name").toLowerCase();
            if (os.indexOf("win") >= 0) {
                localDriver.manage().window().maximize();
            }
            if (os.indexOf("mac") >= 0) {
                System.out.print(" block for full screen");
                // localDriver.manage().window().fullscreen();
            }
        }

        setDriver(localDriver);
        session = ((RemoteWebDriver) wdriver.get()).getSessionId();
        System.out.println("Session id: " + session.toString());

    }

    public static void setDriver(WebDriver wdriver) {
        BaseSetupClass.wdriver.set(wdriver);
    }

    public static WebDriver getDriver() {
        return wdriver.get();
    }

    public ApplicationController trepp() {
        if (trepp == null) {
            trepp = new ApplicationController(getDriver());
        }
        trepp.strParametersNValues = strDTParametersNValues;
        if (trepp.strMainParametersNValues == "") {
            trepp.strMainParametersNValues = this.parameterNValue;
        }
        return trepp;
    }

    @AfterMethod
    public void afterMethod() {
        this.trepp = null;
    }

    @AfterTest
    public synchronized void tearDown() throws Exception {
        wdriver.get().quit();
        // results ();
        if (executionEnv.equalsIgnoreCase("cloud")) {
            videourl();
        }
//if (opsgeniealert.equalsIgnoreCase("yes")) {
//createAlert();
//}
    }

    public static String getEnviornment() {
        return enviornment;
    }

    public void setEnviornment(String enviornment) {
        BaseSetupClass.enviornment = enviornment;
    }

    private static int getminpriority() {
        int minValue = 5;
        for (Integer key : priorityntestname.keySet()) {
            // String value = priorityntestname.get(key)[0];
            if (key < minValue) {
                minValue = key;
            }
            return minValue;
        }
        minValue = 0;
        return minValue;
    }

    private static List<String> getclassname() {
        List<String> cname = new ArrayList<>();
        for (Entry<Integer, String[]> entry : priorityntestname.entrySet()) {
            cname.add(entry.getValue()[0]);
            System.out.println(cname);
        }
        Collections.replaceAll(cname, "[", " ");
        return cname;
    }

    private static PriorityEnum getPriority() {

        if (1 == getminpriority())
            return CreateAlertRequest.PriorityEnum.P1;
        else if (2 == getminpriority())
            return CreateAlertRequest.PriorityEnum.P2;
        else if (3 == getminpriority())
            return CreateAlertRequest.PriorityEnum.P3;
        else if (4 == getminpriority())
            return CreateAlertRequest.PriorityEnum.P4;
        else if (5 == getminpriority())
            return CreateAlertRequest.PriorityEnum.P5;
        else
            return CreateAlertRequest.PriorityEnum.P5;

    }

    @AfterSuite
    public static void createAlert()
            throws ApiException, IOException, URISyntaxException {

        if (opsgeniealert.equalsIgnoreCase("Yes")) {
            if (getminpriority() != 0) {
                AlertApi client = new OpsGenieClient().alertV2();
                client.getApiClient()
                        .setApiKey(System.getenv("API_TOKEN_GLOBAL_OPSGENIE"));
                // client.getApiClient().setApiKey(objProperties.getProperty("opsgenieapikey"));
                CreateAlertRequest request = new CreateAlertRequest();
                String msg = "Github actions Test - " + app + ": "
                        + getclassname();
                request.setAlias("Gitbuh actions P" + getminpriority() + "-"
                        + app + "-" + "-"
                        + Utilites.getDateInFormat("dd/MM/yyyy", 0));
                request.setMessage(msg);
                request.setDescription("Video URL" + " " + " : "
                        + getvideourl(
                        priorityntestname.get(getminpriority())[1])
                        + " " + "Error message:" + " "
                        + ((ReuseableFunctions.message == null) ? getclassname()
                        : ReuseableFunctions.message));

                if (getPriority().toString().equalsIgnoreCase("P1")) {
                    request.setTeams(
                            Utilites.getOpsgenieRecipient("opsgeniegroup1"));
                    request.visibleTo(
                            Utilites.getOpsgenieRecipient("opsgeniegroup1"));
                } else {
                    request.setTeams(
                            Utilites.getOpsgenieRecipient("opsgeniegroup2"));
                    request.visibleTo(
                            Utilites.getOpsgenieRecipient("opsgeniegroup2"));
                }
                request.setActions(Arrays.asList("ping", "check Auth service"));
                request.setTags(Arrays.asList(app));
                request.setEntity(app);
                // request.setPriority(CreateAlertRequest.PriorityEnum.fromValue(priority));
                request.setPriority(getPriority());
                request.setUser("anish_ohri@trepp.com");
                request.setNote("Alert created");

                SuccessResponse response = client.createAlert(request);
                Float took = response.getTook();
                String requestId = response.getRequestId();
                String message = response.getResult();

                System.out.println(took);
                System.out.println(requestId);
                System.out.println(message);
                // results ();
            } else {

                System.out.println(
                        "No Alert Created, Checking for any open Alert");
                AlertApi client = new OpsGenieClient().alertV2();
                client.getApiClient()
                        .setApiKey(System.getenv("API_TOKEN_GLOBAL_OPSGENIE"));
                ListAlertsRequest request = new ListAlertsRequest();
                request.setQuery("status: open");
                request.setLimit(10);
                request.setOrder(ListAlertsRequest.OrderEnum.DESC);
                request.setSort(ListAlertsRequest.SortEnum.CREATEDAT);

                ListAlertsResponse response = client.listAlerts(request);
                String requestId = response.getRequestId();
                BaseAlert data = response.getData().get(0);
                List<BaseAlert> alerts = client.listAlerts(request).getData();
                int count = alerts.size();
                for (int i = 0; i <= count - 1; i++) {
                    System.out.println(alerts.get(i).getMessage());
                    String alertDsc = alerts.get(i).getMessage();
                    if (alertDsc.contains("Github actions Test - " + app)) {
                        String alerttiny = alerts.get(i).getTinyId();
                        String alertId = alerts.get(i).getId();

                        CloseAlertRequest closerequest = new CloseAlertRequest();
                        closerequest.setUser("anish_ohri@trepp.com");
                        closerequest.setNote(
                                "Unable to reproduce, closed by System");
                        closerequest.setSource("System");

                        SuccessResponse sucessresponse = client
                                .closeAlert(alerttiny, "tiny", closerequest);
                        String requestIdclose = sucessresponse.getRequestId();
                        System.out.println("Alert Closed");
                    }

                }
                System.out.println("No open alert found : " + " " + app);
            }

        }
    }

    public void videourl() throws IOException, URISyntaxException {

        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();
        Response video = request.accept(ContentType.JSON).header(
                        "Authorization",
                        "Basic YW5pc2hfb2hyaTprZTBjTmRPaXBLTlZHS0s2ZlpwTmFSUzJtWmdXVjhZcThSQjhOMUhTT0RaQmZFcm8xZw==")
                .contentType(ContentType.JSON)
                .get("https://api.lambdatest.com/automation/api/v1/sessions/"
                        + session.toString() + "/video");
        // System.out.println(video.getStatusCode());
        // ResponseBody body = video.getBody();
        // System.out.println("Response Body is: " + body.asString());
        videourl = video.jsonPath().get("view_video_url");
        URI uri = new URI(videourl);
        URL url = uri.toURL();
        System.out.println("Test Execution Video URL:" + url);
    }

    public static String getvideourl(String sessionID)
            throws IOException, URISyntaxException {

        RequestSpecification request = RestAssured.given();
        Response video = request.accept(ContentType.JSON).header(
                        "Authorization",
                        "Basic YW5pc2hfb2hyaTprZTBjTmRPaXBLTlZHS0s2ZlpwTmFSUzJtWmdXVjhZcThSQjhOMUhTT0RaQmZFcm8xZw==")
                .contentType(ContentType.JSON)
                .get("https://api.lambdatest.com/automation/api/v1/sessions/"
                        + sessionID.toString() + "/video");

        return video.jsonPath().get("view_video_url");
    }
    
    
    
    
   
    /*
     * public static void results() { String path = System.getProperty("user.dir") +
     * "/target/surefire-reports/testng-results.xml"; File testNgResultXmlFile = new
     * File(path);
     *
     * // Get Document Builder DocumentBuilderFactory factory =
     * DocumentBuilderFactory.newInstance(); DocumentBuilder builder = null; try {
     * builder = factory.newDocumentBuilder(); } catch (ParserConfigurationException
     * e) { e.printStackTrace(); }
     *
     * // Build Document Document document = null; try { document =
     * builder.parse(testNgResultXmlFile); } catch (SAXException e) {
     * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
     *
     * // Normalize the XML Structure; document.getDocumentElement().normalize();
     *
     * NodeList tMethods = document.getElementsByTagName("test-method");
     *
     * for (int temp = 0; temp < tMethods.getLength(); temp++) { Node node =
     * tMethods.item(temp); if (node.getNodeType() == Node.ELEMENT_NODE) { Element
     * eElement = (Element) node; // Get parent element to capture suite name
     * Element suiteElement = (Element) eElement.getParentNode(); String methodname
     * = eElement.getAttribute("name"); System.out.println(methodname);
     *
     * // Get test case name System.out.println("Name: " +
     * eElement.getAttribute("name"));
     *
     * // Get exception message if exist Node eNode =
     * eElement.getElementsByTagName("exception").item(0); Element exceptionNode =
     * (Element) eNode; if (exceptionNode != null) {
     * System.out.println("Error Message: " + exceptionNode.getAttribute("class"));
     * } else { System.out.println("Error Message: "); }
     *
     * } }
     *
     * }
     */
}
