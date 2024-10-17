package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class HotelSnapshotTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Stats | Company Exposure | Hotel Snapshot Validation";

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"regression"})
    public void verify1hotelSpashot() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickCompanyExposureLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().clickFirstHotelLink();
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyHotelSnapshotLoad();
        trepp().companyExposure().verifyHotelOverviewPanel();
        trepp().companyExposure().verifyHotelPerformanceSummaryPanel();
        trepp().companyExposure().verifyHotelRecentTransactionTable();
        trepp().companyExposure().verifyHotelUnderWritingPanel();
        trepp().companyExposure().verifyHotelCMBSDealExposurePanel();
        trepp().companyExposure().verifyHotelOperatingPerformancePanel();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify2hotelSnapshotAfterbrandSelect() throws InterruptedException, Exception {
        trepp().companyExposure().verifyBrandDropdownHotelSnapshot();
        trepp().companyExposure().selectBrandHotelSnapshot("Aloft");
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyMarketDropdownHotelSnapshot();
        trepp().companyExposure().selectMarketHotelSnapshot("IL-IN-WI");
        trepp().crehomepage().waitForLoading();
        trepp().companyExposure().verifyHotelOverviewPanel();
        trepp().companyExposure().verifyHotelPerformanceSummaryPanel();
        trepp().companyExposure().verifyHotelRecentTransactionTable();
        trepp().companyExposure().verifyHotelUnderWritingPanel();
        trepp().companyExposure().verifyHotelCMBSDealExposurePanel();
        trepp().companyExposure().verifyHotelOperatingPerformancePanel();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify3DownloadSnapshot() throws Exception {
        trepp().companyExposure().verifyDownloadSnapshot("Hotel Report - Aloft.xlsx");
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().companyExposure().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();
    }

}
