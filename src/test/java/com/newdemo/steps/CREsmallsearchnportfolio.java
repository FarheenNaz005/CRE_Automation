package com.newdemo.steps;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.data.LoadData;

@Test
public class CREsmallsearchnportfolio extends BaseSetupClass {

    String portfolioName;


    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Portfolio Validation";
        portfolioName = "regressionPortfolio";
        this.strDTParametersNValues = parameterNValue;
        BaseAPI.configClient();
        BaseAPI.setBaseURI(new LoadData(this.strDTParametersNValues).strDTURL);
    }

    @BeforeMethod
    public void beforeTestMethod(Method method) {
        System.err.println("Testmethod: " + method.getName());
    }

    @Test(groups = {"regression"})
    public void creatportfolio() throws Exception {

        trepp().crehomepage().goToHome();

        //getting session cookies for apis
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", ContentType.JSON.toString());

        BaseAPI.setCookies(trepp().login().getSessionCookies());
        //fetching all the portfolios
        Response output = BaseAPI.get("/wsapi/loanPortfolios", headers);
        System.out.println(output.getBody().asString());
        //check for portfolio exist
        List<String> portfolios = output.jsonPath().getList("findAll{it -> it.portfolioName=='" + portfolioName + "'}.loanPortfolioID");
        if (portfolios.size() > 0) {
            //deleting portfolio
            BaseAPI.delete("/wsapi/loanPortfolios/" + String.valueOf(portfolios.get(0)), headers);
        }

        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().clickModalSearch();
        trepp().newListingPage().verifyMapViewnData();
        trepp().newListingPage().clickList();
        trepp().newListingPage().waitForLoading();
        trepp().newListingPage().selectProperties(5);
        trepp().newListingPage().createPortfolio(portfolioName);
        trepp().newListingPage().clickviewPortfolio();
        trepp().newListingPage().verifysamerecords();
        //Trepp().PortfolioPage().DeleteRecordfromPortfolio();

    }

    @Test(groups = {"regression"})
    public void verifyPortfolioPage() throws Exception {

        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().newListingPage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(portfolioName);
        trepp().newListingPage().waitForLoading();
        trepp().portfolioPage().verifyCommentsLink();
        trepp().portfolioPage().verifyCalculatorLink();
    }

    @Test(groups = {"regression"})
    public void searchNaddPortfolio() throws Exception {
        trepp().crehomepage().closeAnyOpenModal();
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().clickModalSearch();
        trepp().newListingPage().verifyMapViewnData();
        trepp().newListingPage().clickList();
        trepp().newListingPage().waitForLoading();
        trepp().newListingPage().selectProperties(5);
        trepp().newListingPage().clickaddPortfolio(portfolioName);
        trepp().newListingPage().clickviewPortfolio();
        trepp().newListingPage().verifysamerecords();
        //Trepp().PortfolioPage().DeleteRecordfromPortfolio();

    }

    @Test(groups = {"regression"})
    public void verify1DownloadPortfolio() throws Exception {

        trepp().newListingPage().verifyLoanListingDownloadAll("Portfolio Report.xlsx");
        trepp().newListingPage().clickMapview();
        trepp().newListingPage().verifyLoanListingDownloadAll("Portfolio Report (1).xlsx");

        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }

        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify2PortfolioComment() throws Exception {

        String newComment = "sanity test comment";
        String editComment = "edited comment";
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().newListingPage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(portfolioName);
        trepp().newListingPage().waitForLoading();
        trepp().portfolioPage().clickStratsLink();
        trepp().newListingPage().waitForLoading();
        trepp().portfolioPage().clickCommentLink();
        trepp().portfolioPage().waitForLoading();
        trepp().portfolioPage().verifyNewCommentInput();
        trepp().portfolioPage().verifysaveCommentButton();
        Thread.sleep(2000);
        trepp().portfolioPage().addPortfolioComment(newComment);
        Thread.sleep(2000);
        trepp().portfolioPage().verifyPortfolioComment(newComment);
        Thread.sleep(2000);
        trepp().portfolioPage().editPortfolioComment(newComment, editComment);
        Thread.sleep(2000);
        trepp().portfolioPage().verifyPortfolioComment(editComment);
        Thread.sleep(2000);
        trepp().portfolioPage().deletePortfolioComment(editComment);
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify3PortfolioStrats() throws Exception {

        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().newListingPage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(portfolioName);
        trepp().portfolioPage().clickStratsLink();
        trepp().portfolioPage().verifyStratsPortfolioSummary();
        trepp().portfolioPage().verifyStratSelection();
        trepp().portfolioPage().verifyStratsHeaderButton();
        trepp().portfolioPage().verifyStratsSidePanel();
        trepp().portfolioPage().verifyStratsTableAndChart();
        trepp().portfolioPage().clickStratsRanges();
        trepp().portfolioPage().verifyStratsRangesButtons();
        trepp().portfolioPage().verifyStratsRangesList();
        trepp().portfolioPage().verifyStratsRangesReadNotes();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify4PortfolioLoanDetailUpdate() throws Exception {

        HashMap<String, String> loanDetail = new HashMap<String, String>();
        loanDetail.put("originalBal", "1234567890");
        loanDetail.put("yearBuilt", "2000");
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().newListingPage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(portfolioName);
        trepp().newListingPage().waitForLoading();
        trepp().newListingPage().clickList();
        String propertyName = trepp().portfolioPage().clickFirstPropertypotfoliolistingpage();
        trepp().portfolioLoanDetailPage().verifyportfolioName(portfolioName);
        trepp().portfolioLoanDetailPage().verifypropertyName(propertyName);
        trepp().newListingPage().waitForLoading();
        trepp().portfolioLoanDetailPage().verifyTopButton();
        trepp().portfolioLoanDetailPage().updateLoanDetail(loanDetail);
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify5DeleteRecords() throws Exception {

        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().newListingPage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(portfolioName);
        trepp().newListingPage().waitForLoading();
        trepp().portfolioPage().deleteRecordfromPortfolio();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verifyRankingpage() throws Exception {


        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().newListingPage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(portfolioName);
        trepp().listingPage().waitForLoading();
        trepp().rankingpage().clickonranking();
        trepp().rankingpage().verifyRankingsSummary();
        trepp().rankingpage().clickondetaileddata();
        trepp().rankingpage().verifydetaileddata();
        trepp().rankingpage().verifyExcelDownload();
        trepp().rankingpage().verifyPDFDownload();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verifysnapshotpage() throws Exception {

        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().managePortfolioPage().openPortfolio(portfolioName);
        trepp().listingPage().waitForLoading();
        trepp().snapshotpage().clickonsnapshot();
        trepp().snapshotpage().verifyTables();
        trepp().snapshotpage().verifydownloads();
        trepp().snapshotpage().verifyprint();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        /*
         * trepp().managePortfolioPage().openPortfolio("regressionPortfolio");
         * trepp().listingPage().waitForLoading();
         * trepp().listingPage().runAssessibilityfunction();
         */
    }
} 
