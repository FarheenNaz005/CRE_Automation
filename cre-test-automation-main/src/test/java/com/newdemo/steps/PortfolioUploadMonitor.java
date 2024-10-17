package com.newdemo.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;

@Test
public class PortfolioUploadMonitor extends BaseSetupClass {
    List<String> properties = new ArrayList<String>();
    String portfolioName;

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "Portfolio upload monitor";
        portfolioName = "testupload";
        this.strDTParametersNValues = parameterNValue;

        BaseAPI.configClient();
        BaseAPI.setBaseURI(new LoadData(this.strDTParametersNValues).strDTURL);
    }


    @Test(groups = {"sanity", "regression"})
    public void uploadPortfolio() throws Exception {
        try {
            //getting session cookies for apis
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", ContentType.JSON.toString());

            BaseAPI.setCookies(trepp().login().getSessionCookies());
            //fetching all the portfolios
            Response output = BaseAPI.get("/wsapi/loanPortfolios", headers);
            //System.out.println(output.getBody().asString());
            //check for portfolio exist
            List<String> portfolios = output.jsonPath().getList("findAll{it -> it.portfolioName=='" + portfolioName + "'}.loanPortfolioID");
            if (portfolios.size() > 0) {
                //deleting portfolio
                BaseAPI.delete("/wsapi/loanPortfolios/" + String.valueOf(portfolios.get(0)), headers);
            }
            trepp().crehomepage().clickMyWork();
            trepp().crehomepage().clickAddNewPortfolio();
            long time = trepp().crehomepage().addNewPortfolio(portfolioName, "Trepp_records_500.xlsx"); //Portfolio_Upload_Demo.xlsx
            System.out.println(time + "ms taken in portfolio upload of 50 records");
        } catch (Exception e) {
            // BaseSetupClass.priorityntestname.put(2, new String[]{this.getClass().getSimpleName(), BaseSetupClass.session.toString()});
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            //  BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }
    }

    @Test(groups = {"sanity", "regression"})
    public void createPortfoliofromportfoliopage() throws Exception {
        try {
            //getting session cookies for apis
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", ContentType.JSON.toString());

            BaseAPI.setCookies(trepp().login().getSessionCookies());
            //fetching all the portfolios
            Response output = BaseAPI.get("/wsapi/loanPortfolios", headers);
            //System.out.println(output.getBody().asString());
            //check for portfolio exist
            List<String> portfolios = output.jsonPath().getList("findAll{it -> it.portfolioName=='" + portfolioName + "'}.loanPortfolioID");
            if (portfolios.size() > 0) {
                //deleting portfolio
                BaseAPI.delete("/wsapi/loanPortfolios/" + String.valueOf(portfolios.get(0)), headers);
            }
            trepp().crehomepage().clickMyWork();
            trepp().crehomepage().clickPortfolioButton();
            trepp().managePortfolioPage().clickOnAddPortfoliobutton();
            long time = trepp().crehomepage().addNewPortfolio(portfolioName, "Trepp_records_500.xlsx"); //Portfolio_Upload_Demo.xlsx
            System.out.println(time + "ms taken in portfolio upload of 50 records");
        } catch (Exception e) {
            // BaseSetupClass.priorityntestname.put(2, new String[]{this.getClass().getSimpleName(), BaseSetupClass.session.toString()});
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            //  BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }
    }

    public void createPortfoliofromHomePage() throws Exception {
        //getting session cookies for apis
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", ContentType.JSON.toString());

        BaseAPI.setCookies(trepp().login().getSessionCookies());
        //fetching all the portfolios
        Response output = BaseAPI.get("/wsapi/loanPortfolios", headers);
        //System.out.println(output.getBody().asString());
        //check for portfolio exist
        List<String> portfolios = output.jsonPath().getList("findAll{it -> it.portfolioName=='" + portfolioName + "'}.loanPortfolioID");
        if (portfolios.size() > 0) {
            //deleting portfolio
            BaseAPI.delete("/wsapi/loanPortfolios/" + String.valueOf(portfolios.get(0)), headers);
        }
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata("477 Madison Avenue");
        trepp().crehomepage().clickOnProftfolioshortcut();
        trepp().crehomepage().clickOnNewPortfolioAdd(portfolioName);


    }

    public void createPortfoliofromlistingPage() throws Exception {
        //getting session cookies for apis
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", ContentType.JSON.toString());

        BaseAPI.setCookies(trepp().login().getSessionCookies());
        //fetching all the portfolios
        Response output = BaseAPI.get("/wsapi/loanPortfolios", headers);
        //System.out.println(output.getBody().asString());
        //check for portfolio exist
        List<String> portfolios = output.jsonPath().getList("findAll{it -> it.portfolioName=='" + portfolioName + "'}.loanPortfolioID");
        if (portfolios.size() > 0) {
            //deleting portfolio
            BaseAPI.delete("/wsapi/loanPortfolios/" + String.valueOf(portfolios.get(0)), headers);
        }
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata("477 Madison Avenue");
        trepp().crehomepage().clickOnProftfolioshortcut();
        trepp().crehomepage().clickOnNewPortfolioAdd(portfolioName);


    }
}
