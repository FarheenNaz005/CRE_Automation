package com.newdemo.steps;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.data.LoadData;

@Test
public class CalculatorFunctionalityTest extends BaseSetupClass {
    String portfolioName;
    String overrideName;

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Stats | Company Exposure Validation";
        this.strDTParametersNValues = parameterNValue;
        BaseAPI.configClient();
        BaseAPI.setBaseURI(new LoadData(this.strDTParametersNValues).strDTURL);
        this.strDTParametersNValues = parameterNValue;
        this.portfolioName = "calculator_portfolio";
        this.overrideName = "override1";


        //getting session cookies for apis
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", ContentType.JSON.toString());

        BaseAPI.setCookies(trepp().login().getSessionCookies());
        //fetching all the portfolios
        Response output = BaseAPI.get("/wsapi/loanPortfolios", headers);
        //check for portfolio exist
        List<String> portfolios = output.jsonPath().getList("findAll{it -> it.portfolioName=='" + portfolioName + "'}.loanPortfolioID");
        if (portfolios.size() > 0) {
            //deleting portfolio
            Response deletePortfolio = BaseAPI.delete("/wsapi/loanPortfolios/" + String.valueOf(portfolios.get(0)), headers);
        }


        //upload a portfolio for calculation purpose
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAddNewPortfolio();
        trepp().crehomepage().addNewPortfolio(this.portfolioName, "calculator_functionality/Calculator_Portfolio.xlsx");
    }


    @AfterClass
    public void afterClass() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().verifyPortfolioVisible(this.portfolioName);
        trepp().managePortfolioPage().deletePortfolio(this.portfolioName);
    }

    @Test(groups = {"regression"})
    public void verify1OverrideUpload() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(this.portfolioName);
        trepp().portfolioPage().clickCalculatorLink();
        trepp().portfolioPage().clickCalcLoanOverridesLink();
        trepp().listingPage().waitForLoading();
        Thread.sleep(3000);
        trepp().loanOverridesPage().uploadOverride(overrideName, "calculator_functionality/Portfolio_Override.csv");
        trepp().loanOverridesPage().verifyOverride(overrideName);
        trepp().loanOverridesPage().closeLoanOverridePage();
    }

    @Test(groups = {"regression"})
    public void verify2LoanByLoanCalculation() throws Exception {
        trepp().portfolioPage().clickCalculatorLink();
        trepp().portfolioPage().clickCalcLoanByLoanLink();
        trepp().listingPage().waitForLoading();
        trepp().loanByLoanPage().verifyPortfolioSelected(this.portfolioName);
        trepp().loanByLoanPage().selectOverride(overrideName);
        trepp().loanByLoanPage().clickCalculateButton();
        trepp().loanByLoanPage().verifyCalculatedTables();
        trepp().loanByLoanPage().closeloanByLoanPage();
    }
}
