package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class TenantReportTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"regression"})
    public void verify1TenantReport() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickSwitchTenantsButton();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstTenantReport();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyTenantReportLoad();
        trepp().companyExposure().verifyTenantOutstandingLoanMap();
        trepp().companyExposure().verifyTenantDeliquencySummary();
        trepp().companyExposure().verifyTenantRecentTransactionTable();
        trepp().companyExposure().verifyTenantOutstandingDebtSummary();
        trepp().companyExposure().verifyTenantCMBSDealExposure();

    }

    /*@Test(groups = {"regression"})
    public void verify2gotoPropertyList() throws Exception {
        trepp().companyExposure().verifyPropertyListLinkTenantReport();
        trepp().companyExposure().clickPropertyListTenantReport();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        BaseSetupClass.getDriver().navigate().back();
        trepp().crehomepage().waitForLoading();
    }*/

    @Test(groups = {"regression"})
    public void verify3DownloadTenantReport() throws Exception {
        trepp().companyExposure().verifyDownloadTenantReport("Tenant Report - Government.xlsx");
        /*
         * if(BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
         * trepp().companyExposure().deleteFile(BaseSetupClass.downloadFilepath, "*"); }
         */
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickSwitchTenantsButton();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstTenantReport();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().runAssessibilityfunction();
    }
    
    @Test(groups = {"regression"})
    public void verifyTenantReportClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickSwitchTenantsButton();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstTenantReport();
        trepp().crehomepage().waitForLoading();
        //Property List 
        trepp().companyExposure().clickPropertyList1();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        //Delinquency Summary
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickSwitchTenantsButton();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstTenantReport();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickDelinquencySummaryGraph();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        //Property Name
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickSwitchTenantsButton();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstTenantReport();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstPropertyNameOnHotelReport();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");  
        trepp().softAssert.assertAll();

    }
}
