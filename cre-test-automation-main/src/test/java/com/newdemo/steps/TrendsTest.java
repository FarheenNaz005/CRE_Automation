package com.newdemo.steps;

import com.newdemo.framework.base.BaseSetupClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class TrendsTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"sanity", "regression"})
    public void verify1TrendsTab() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyTrendsLink();
        trepp().statsPage().clickTrendsLink();
        trepp().trendsPage().verifyTopButtons();
        trepp().trendsPage().verifyMap();
        trepp().trendsPage().verifyRankList("MSA", 25, "WatchList");
        trepp().trendsPage().verifyRankListValue();

    }

    @Test(groups = {"sanity", "regression"})
    public void verify2SwitchStateAndMSA() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().selectMarket("State");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyRankList("State", 25, "WatchList");
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"sanity", "regression"})
    public void verify3SwitchtopCount() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().trendsPage().selectRankListCountSwitch("Top 10");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyselectedRankListCountSwitch("Top 10");
        trepp().trendsPage().verifyRankListValue();
        trepp().trendsPage().selectRankListCountSwitch("Top 25");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyselectedRankListCountSwitch("Top 25");
        trepp().trendsPage().verifyRankListValue();
        trepp().trendsPage().selectRankListCountSwitch("Top 50");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyselectedRankListCountSwitch("Top 50");
        trepp().trendsPage().verifyRankListValue();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"sanity", "regression"})
    public void verify6RankBy() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().selectRankByDropdown("Delinquency");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyselectedRankby("Delinquency");
        trepp().trendsPage().selectRankByDropdown("Watchlist");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyselectedRankby("Watchlist");
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"sanity", "regression"})
    public void verify4DifferrentPropertyType() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().selectRankListCountSwitch("Top 25");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyselectedRankListCountSwitch("Top 25");
        trepp().trendsPage().selectPropertyTypeDropdown("Garden / Low-Rise");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyRankListValue();
        trepp().trendsPage().selectPropertyTypeDropdown("Urban Office");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyRankListValue();
        trepp().trendsPage().selectPropertyTypeDropdown("Regional Mall");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyRankListValue();
        trepp().crehomepage().waitForLoading();
        trepp().softAssert.assertAll();
    }

    /*@Test(dataProvider = "source-data-provider", dataProviderClass = StaticDataProvider.class, groups = {"sanity", "regression"})
    public void verify5DifferrentSource(String source) throws Exception {
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().selectRankListCountSwitch("Top 25");
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyselectedRankListCountSwitch("Top 25");

        if (!source.equalsIgnoreCase("FHA")) {
            trepp().trendsPage().selectRankByDropdown("Delinquency");
            trepp().crehomepage().waitForLoading();
        } else {
            trepp().trendsPage().selectRankByDropdown("Last Quarter Origination");
            trepp().crehomepage().waitForLoading();
        }
        trepp().trendsPage().selectSourceDropdown(source);
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyRankListValue();
        trepp().softAssert.assertAll();
    }*/

    @Test(groups = {"sanity", "regression"})
    public void verify7marketDrilldownPage() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        String marketName = trepp().trendsPage().goToMarketDrillDown(0);
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyDrillDownPage(marketName);
    }

    @Test(groups = {"sanity", "regression"}, enabled = false)
    public void verify8Download() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyDownload("Trends and Rankings.xlsx");
        String marketName = trepp().trendsPage().goToMarketDrillDown(0);
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyDownload("Trends and Rankings " + marketName + ".xlsx");
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().companyExposure().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();

    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().runAssessibilityfunction();
    }
    
    @Test(groups = {"regression"})
    public void verifyTrendsRankingsCharts() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().selectFirstMSAFromMSARankingList();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().verifyTrendsAndRankingsCharts();
        trepp().softAssert.assertAll();

    }
    
    
    @Test(groups = {"regression"})
    public void verifyTrendsAndRankingsClickThroughs() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().trendsPage().selectFirstMSAFromMSARankingList();
        trepp().crehomepage().waitForLoading();
        //Loan Performance
        trepp().trendsPage().clickLoanPerformanceProperties();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().switchToNewWindow();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData(); 
        trepp().newListingPage().switchToOldWindow();
        //Underwriting Trends
        trepp().trendsPage().clickUnderwritingTrendsProperties();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().switchToNewWindow();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData(); 
        trepp().newListingPage().switchToOldWindow();
        //Income And Expense Benchmark
        trepp().trendsPage().clickIncomeAndExpenseBenchmarkProperties();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().switchToNewWindow();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData(); 
        trepp().newListingPage().switchToOldWindow();
        trepp().softAssert.assertAll();

    }
}
