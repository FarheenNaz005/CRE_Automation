package com.newdemo.steps;

import com.newdemo.framework.base.BaseSetupClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class StatsPageTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Stats | Company Exposure Validation";

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"regression"})
    public void verify1StatsPage() throws Exception {
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().waitForHomepageLoan();
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyTrendsLink();
        trepp().statsPage().verifyTreppWireLink();
        trepp().statsPage().verifyPerformanceLink();
        trepp().statsPage().verifyNewsLink();
        trepp().statsPage().verifyNewIssuanceLink();
        trepp().statsPage().verifyCompanyExposureLink();
    }

    @Test(groups = {"regression"})
    public void verifyStatsNewsTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().waitForHomepageLoan();
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyNewsLink();
        trepp().statsPage().clickNewsLink();
        trepp().crehomepage().waitForLoading();
        trepp().statsNewsPage().verifyArtical();
        trepp().statsNewsPage().clickOndateCheckbox();
        trepp().crehomepage().waitForLoading();
        trepp().statsNewsPage().filterarticalbydaterange("01/01/2021", "01/05/2021");
        trepp().crehomepage().waitForLoading();
        trepp().statsNewsPage().verifydateinrange("01/01/21", "01/05/21");
        trepp().statsNewsPage().clickOndateCheckbox();
        trepp().statsNewsPage().clickOndealCheckBox();
        trepp().statsNewsPage().filterbydealname("FREMF 2015-KPLB");
        trepp().softAssert.assertAll();


    }

    @Test(groups = {"regression"})
    public void verifyNewsTabClickThrough() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().statsPage().clickNewsLink();
        trepp().statsNewsPage().waitForNewsLoading();
        trepp().statsNewsPage().clickOnFirstNewsLink();
        trepp().statsNewsPage().verifyNewsModel();
        trepp().softAssert.assertAll();

    }

    @Test(groups = {"regression"})
    public void verifyMarketSnapshotPrintableView() throws Exception {
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().waitForHomepageLoan();
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyTrendsLink();
        trepp().statsPage().clickMarketSnapshotPrintableButton();
        trepp().crehomepage().waitForLoading();
        trepp().statsPage().clickMarketSnapshotExportButton("Market Snapshot.pdf");
        trepp().crehomepage().waitForLoading();


    }

    @Test(groups = {"regression"})
    public void verifyStatsPerformanceTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().waitForHomepageLoan();
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyPerformanceLink();
        trepp().statsPage().clickPerformanceLink();
        trepp().crehomepage().waitForLoading();
        trepp().statsPage().verifyPerformanceTable();
        trepp().statsPage().selectCollateralUniverse("Standard");
        trepp().statsPage().selectReportType("Loan Performance By Origination Year");
        trepp().statsPage().selectPeriod("Latest Data");
        trepp().statsPage().clickDisplayButton();
        trepp().statsPage().verifyPerformanceTable();
        trepp().statsPage().verifyExcelDownload();
        trepp().statsPage().verifyPDFDownload();
        trepp().softAssert.assertAll();

    }
}
