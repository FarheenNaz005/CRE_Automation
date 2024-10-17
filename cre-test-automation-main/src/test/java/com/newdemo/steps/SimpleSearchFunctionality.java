package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newdemo.framework.base.BaseSetupClass;

public class SimpleSearchFunctionality extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        this.strDTParametersNValues = parameterNValue;
    }

    @Test(groups = {"sanity", "regression"})
    public void searchByPropertyName() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata("477 Madison Avenue");
        trepp().crehomepage().verifySearchdata("477 Madison Avenue");
    }

    @Test(groups = {"sanity", "regression"})
    public void searchByCityName() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata("Chicago");
        trepp().crehomepage().verifySearchdata("Chicago");
    }

    @Test(groups = {"sanity", "regression"})
    public void searchByAddress() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata("230 Southpark Circle");
        trepp().crehomepage().verifySearchdata("230 Southpark Circle");
    }

    @Test(groups = {"sanity", "regression"})
    public void searchByZipcode() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata("474331");
        trepp().crehomepage().verifySearchdata("474331");
    }

    @Test(groups = {"sanity", "regression"})
    public void searchByState() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata("Alabama");
        trepp().crehomepage().verifySearchdata("Alabama");
    }

    @Test(groups = {"sanity", "regression"})
    public void searchByDealName() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata("GSMS");
        trepp().crehomepage().verifySearchdata("GSMS");
    }

    @Test(groups = "regression")
    public void quickSearchResultVerification() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchdata2("Avalon");
        trepp().crehomepage().verifySectionsInQuickSearch("Property");
        trepp().crehomepage().verifySectionsInQuickSearch("Portfolio Loans");
        trepp().crehomepage().verifySectionsInQuickSearch("Market Search");
        trepp().crehomepage().verifyModal();
        trepp().softAssert.assertAll();
    }


}
