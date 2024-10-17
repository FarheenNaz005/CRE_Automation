package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class CompanyExposureTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Stats | Company Exposure Validation";

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"regression"})
    public void verify1CompnyExposureTab() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyCompanyExposureLink();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().companyExposure().verifyDownloadButton();
        trepp().companyExposure().verifyExposureTable();
        trepp().companyExposure().selectViewByHotel("Franchises");
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyHotelSearch("Marriott");
        trepp().companyExposure().clickSwitchTenantsButton();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyExposureTable();
        trepp().companyExposure().verifyTenantSearch("Government");
    }

    @Test(groups = {"regression"})
    public void verify2gotoPropertyListingFromExposureTable() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyExposureTable();
        trepp().companyExposure().verifyPropertyListLink();
        trepp().companyExposure().clickPropertyList();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        BaseSetupClass.getDriver().navigate().back();
        trepp().crehomepage().waitForLoading();
    }

    @Test(groups = {"regression"})
    public void verify3NavigateToHotelSnapshot() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstHotelLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyHotelSnapshotLoad();
    }

    @Test(groups = {"regression"})
    public void verify4NavigateToTenantReport() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().companyExposure().clickSwitchTenantsButton();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstTenantReport();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyTenantReportLoad();
    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().runAssessibilityfunction();
    }
    
    
    @Test(groups = { "regression" })
    public void verifyHotelReportVerifyClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstHotelLink();
        trepp().crehomepage().waitForLoading();
        //Property List 
        trepp().companyExposure().clickPropertyList1();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        //Property Name
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstHotelLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstPropertyNameOnHotelReport();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");       
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = { "regression" })
    public void verifyHotelReportOperatingPerformanceTableAndChart() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstHotelLink();
        trepp().crehomepage().waitForLoading();  
        trepp().companyExposure().selectOperatingPerformanceYear();
        trepp().companyExposure().verifyselectOperatingPerformanceTable("W Avg Room Revenues $/Room");
        trepp().companyExposure().verifyTogglePresent();
        trepp().companyExposure().verifyRoomTogglePopulateData();
        //trepp().companyExposure().verifyRevTogglePopulateData("Room Revenues % of Revenue");
        trepp().companyExposure().verifyCompareToFeature("Hilton");
        trepp().softAssert.assertAll();
    }
}
