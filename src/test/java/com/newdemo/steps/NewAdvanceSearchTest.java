package com.newdemo.steps;

import com.newdemo.framework.base.BaseSetupClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NewAdvanceSearchTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        String strDescription = "New Advance Search Validation";

        this.strDTParametersNValues = parameterNValue;
    }

    @Test(groups = {"sanity", "regression"})
    public void newAdvanceSearchTest1() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().toggleByText("All");
        //Thread.sleep(2000);
        trepp().newAdvanceSearch().selectPropertyType("Office");
        trepp().newAdvanceSearch().selectPropertyState("California");
        trepp().newAdvanceSearch().selectTransactionDate("01-01-2020");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();
        //Thread.sleep(3000);
        trepp().newListingPage().verifyMapViewnData();
        //trepp().crehomepage().waitForLoading();
        trepp().newAdvanceSearch().verifyNewListingPage();
        //Thread.sleep(3000);
        trepp().softAssert.assertAll();

    }

    @Test(groups = {"sanity", "regression"})
    public void newAdvanceSearchTest2() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().selectPropertyType("Retail");
        trepp().newAdvanceSearch().toggleByText("Loan");
        trepp().newAdvanceSearch().selectDelinquencyStatus("Current");
        trepp().newAdvanceSearch().selectPropertyCity("Los Angeles");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();
        //Thread.sleep(3000);
        trepp().newListingPage().verifyMapViewnData();
        //trepp().crehomepage().waitForLoading();
        trepp().newAdvanceSearch().verifyNewListingPage();
        //Thread.sleep(3000);
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"sanity", "regression"})
    public void newAdvanceSearchTest3() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().selectPropertyCity("Tampa");
        trepp().newAdvanceSearch().toggleByText("Loan");
        trepp().newAdvanceSearch().selectRemTermMax("24");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();
        //Thread.sleep(3000);
        //trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        trepp().newAdvanceSearch().verifyNewListingPage();
        //Thread.sleep(3000);
        trepp().softAssert.assertAll();

    }
    
    @Test(groups = {"regression"})
    public void verifyEditSearchLoan() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().selectPropertyState("Texas");
        trepp().newAdvanceSearch().toggleByText("Loan");
        trepp().newAdvanceSearch().selectDelinquencyStatus("90+ Days");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();   
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        trepp().newListingPage().clickEditSearchBtn();
        trepp().newAdvanceSearch().selectPropertyState("New York");
        trepp().newAdvanceSearch().toggleByText("Loan");
        trepp().newAdvanceSearch().selectDelinquencyStatus("30 Days");
        trepp().newAdvanceSearch().selectDataLoanSources("Fannie Mae");
        trepp().newAdvanceSearch().selectDataLoanSources("Freddie Mac");
        trepp().newAdvanceSearch().selectDataLoanSources("Ginnie Mae");
        trepp().newAdvanceSearch().selectDataLoanSources("FHA");
        trepp().newAdvanceSearch().selectMaturityYear("01/01/2023");
        trepp().newAdvanceSearch().selectOriginalBalance("500000");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch(); 
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();     
        trepp().softAssert.assertAll();

    }
    
    @Test(groups = {"regression"})
    public void verifyEditSearchProperty() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().selectPropertyCity("New York");
        trepp().newAdvanceSearch().selectPropertyType("Multi-");
        trepp().newAdvanceSearch().selectYearBuilt("1990");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();   
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        trepp().newListingPage().clickEditSearchBtn();
        trepp().newAdvanceSearch().toggleByText("All");
        trepp().newAdvanceSearch().selectYearBuilt("2000");
        trepp().newAdvanceSearch().selectPropertyCity("Los Angeles");
        trepp().newAdvanceSearch().selectPropertySubType("Apartments/Coops");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();   
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();       
        trepp().softAssert.assertAll();

    }
    
    @Test(groups = {"regression"})
    public void verifySearchScenario1() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().selectPropertyByZipCode("10036");
        trepp().newAdvanceSearch().selectPropertyByZipCode("10011");
        trepp().newAdvanceSearch().selectPropertySumOfUnits("50");
        trepp().newAdvanceSearch().selectOperatingStatementAvailable();
        trepp().newAdvanceSearch().selectAppraisalValue("10000000");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();   
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();       
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = {"regression"})
    public void verifySearchScenario2() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().toggleByText("All");
        trepp().newAdvanceSearch().selectMSA("San Francisco-Oakland-Berkeley");
        trepp().newAdvanceSearch().enterTransactionDate("01-24-2018");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();   
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();       
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = {"regression"})
    public void verifySearchScenario3() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().toggleByText("Loan");
        trepp().newAdvanceSearch().clickUncheckAllLoanDataSource();
        trepp().newAdvanceSearch().selectDataLoanSources("CMBS");
        trepp().newAdvanceSearch().selectDataLoanSources("CRE CLO");
        trepp().newAdvanceSearch().selectAmortizationType("Interest Only");
        //trepp().newAdvanceSearch().selectSpecialServiced();
        trepp().newAdvanceSearch().selectLTV("70");
        trepp().newAdvanceSearch().verifySearchCount();
        trepp().newAdvanceSearch().clickSearch();   
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();       
        trepp().softAssert.assertAll();
    }


}
