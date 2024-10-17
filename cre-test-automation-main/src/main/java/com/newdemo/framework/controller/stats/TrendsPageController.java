package com.newdemo.framework.controller.stats;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.stats.TrendsPage;

public class TrendsPageController extends ReuseableFunctions {
    TrendsPage trendsPage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public TrendsPageController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        trendsPage = PageFactory.initElements(driver, TrendsPage.class);
    }

    /**
     * validation methods
     *
     * @throws Exception
     **/

    public void verifyTopButtons() throws Exception {
        waitForElement(trendsPage.downloadButton);
        elementPresentorEnabled(trendsPage.marketSwitchButton("State"), "Present", "state switch button");
        elementPresentorEnabled(trendsPage.marketSwitchButton("MSA"), "Present", "MSA switch button");
        elementPresentorEnabled(trendsPage.sourceDropdown, "Present", "source dropdown");
        elementPresentorEnabled(trendsPage.propertyTypeDropdown, "Present", "property type dropdown");
        elementPresentorEnabled(trendsPage.propertyRankByDropdown, "Present", "Rank by dropdown");
        elementPresentorEnabled(trendsPage.downloadButton, "Present", "download button");
        elementPresentorEnabled(trendsPage.marketSnapshotButton, "Present", "market snapshot button");
    }

    public void verifyMap() throws Exception {
        elementPresentorEnabled(trendsPage.trendsAndRankingMap, "Present", "map");
    }


    public void verifyRankListValue() throws Exception {
        elementPresentorEnabled(trendsPage.rankListTitle, "Present", "rank list title");
        int emptyValueCount = 0;
        for (WebElement ele : trendsPage.rankListItemsValue) {
            if (ele.getText().trim().equalsIgnoreCase("-"))
                emptyValueCount++;
        }
        softAssert.assertTrue(emptyValueCount != trendsPage.rankListItemsValue.size(), "not more than 50% of values should be empty:: " + emptyValueCount + "/" + trendsPage.rankListItemsValue.size());

    }

    /***
     * verify market
     * @param market String "State" | "MSA" | "County" | "Zipcode" | "Opp. Zone"
     * @param count int 10 | 25 | 50
     * @param rankBy WatchList
     * ***/
    public void verifyRankList(String market, int count, String rankBy) throws Exception {
        elementPresentorEnabled(trendsPage.rankListTitle, "Present", "rank list title");
        if (market.equalsIgnoreCase("MSA")) {
            softAssert.assertEquals(trendsPage.rankListTopCountSwitches.size(), 4);
        } else if (market.equalsIgnoreCase("State")) {
            softAssert.assertEquals(trendsPage.rankListTopCountSwitches.size(), 3);
        }
        softAssert.assertTrue(trendsPage.rankListTitle.getText().toLowerCase().contains(market.toLowerCase()), "market on top of list should be " + market);
        softAssert.assertTrue(trendsPage.rankListItems.size() == count, "number of item in list should be " + count);
    }

    public void verifyselectedRankListCountSwitch(String count) throws Exception {
        softAssert.assertEquals(getText(trendsPage.rankListTopCountSwitcheActive, "active switch", "innerText"), count);
        if (!count.equals("All")) {
            int cnt = Integer.parseInt(count.split(" ")[1]);
            softAssert.assertTrue(trendsPage.rankListItems.size() == cnt, "number of item in list should be " + cnt);
        }
    }

    public void verifyselectedRankby(String rankBy) throws Exception {
        softAssert.assertTrue(getText(trendsPage.rankListRankByTitle, "active switch", "innerText").contains(rankBy), " Rank by title should be " + rankBy);
    }

    public void verifyDrillDownPage(String marketName) throws Exception {
        softAssert.assertTrue(getText(trendsPage.drillDownPageHeader, "drill down page header", "innerText").contains(marketName), " Rank by title should be " + marketName);
        elementPresentorEnabled(trendsPage.marketSwitchButton("County"), "Present", "county switch button");
        elementPresentorEnabled(trendsPage.marketSwitchButton("Zipcode"), "Present", "Zipcode switch button");
        elementPresentorEnabled(trendsPage.marketSwitchButton("Opp. Zone"), "Present", "Opp. Zone switch button");
        elementPresentorEnabled(trendsPage.drillDownPagePagination, "Present", "pagination ");
        elementPresentorEnabled(trendsPage.loanPerformanceGraph, "Present", "Loan performace graph ");
        elementPresentorEnabled(trendsPage.underwritingTrend, "Present", "Underwriting Trends graph ");
        elementPresentorEnabled(trendsPage.incomeExpenseBenchmark, "Present", "Income Expense benchmark graph ");
    }

    public void verifyDownload(String fileName) throws Exception {
        elementPresentorEnabled(trendsPage.downloadButton, "present", "Download button");
        clickObject(trendsPage.downloadButton, "download all button");

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(trendsPage.downloadButton));

        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");
    }

    //actions

    /***
     * select market
     * @param marketName String "State" | "MSA" | "County" | "Zipcode" | "Opp. Zone"
     * ***/
    public void selectMarket(String marketName) throws Exception {
        WebElement marketSwitch = trendsPage.marketSwitchButton(marketName);
        elementPresentorEnabled(marketSwitch, "Present", "market switch button " + marketName);
        clickObject(marketSwitch, "market switch button " + marketName);
    }

    /***
     * select rank list top count
     * @param count String "Top 10" | "Top 25" | "Top 50" | "All"
     * ***/
    public void selectRankListCountSwitch(String count) throws Exception {
        WebElement countSwitch = trendsPage.rankListTopCountSwitches(count);
        elementPresentorEnabled(countSwitch, "Present", "market switch button " + count);
        clickObjectJavascript(countSwitch, "rank list count switch " + count);
    }

    /***
     * select source
     * @param Source dropdown
     * ***/
    public void selectSourceDropdown(String source) throws Exception {
        clickObject(trendsPage.sourceDropdown, "Property Type dropdown " + source);
        clickObject(trendsPage.sourceDropdownChoice(source), "source dropdown choice" + source);
    }

    /***
     * select rank by
     * @param RankBy dropdown
     * ***/
    public void selectPropertyTypeDropdown(String propertyType) throws Exception {
        clickObject(trendsPage.propertyTypeDropdown, "Property Type dropdown");
        clickObject(trendsPage.propertyTypeDropdownChoice(propertyType), "Property type dropdown choice" + propertyType);
    }

    /***
     * select property type
     * @param Propertytype String
     * ***/
    public void selectRankByDropdown(String rankBy) throws Exception {
        clickObject(trendsPage.propertyRankByDropdown, "Rank by dropdown");
        clickObject(trendsPage.propertyRankByDropdownChoice(rankBy), "Rank by dropdown choice" + rankBy);
    }

    public String goToMarketDrillDown(int index) throws Exception {
        String marketName = getText(trendsPage.rankListItems.get(index), "market to be selected", "innerText");
        clickObjectJavascript(trendsPage.rankListItems.get(0), "first market from rank list");
        return marketName;
    }
    
    public void selectFirstMSAFromMSARankingList() throws Exception {
        waitForElement(trendsPage.clicktMSA);
        clickObject(trendsPage.clicktMSA, "First MASA");
    }
    
    public void clickLoanPerformanceProperties() throws Exception {
        waitForElement(trendsPage.loanPerformanceProp);
        clickObjectJavascript(trendsPage.loanPerformanceProp, "Loan performance number of properties link");
    }
    
    public void clickUnderwritingTrendsProperties() throws Exception {
        waitForElement(trendsPage.underwritingTrendsProp);
        scrollIntoView(trendsPage.underwritingTrendsProp, "Underwriting Trends");
        clickObjectJavascript(trendsPage.underwritingTrendsProp, "Underwriting Trends number of properties link");
    }
    
    public void clickIncomeAndExpenseBenchmarkProperties() throws Exception {
        waitForElement(trendsPage.incomeAndExpenseBenchmarkProp);
        scrollIntoView(trendsPage.incomeAndExpenseBenchmarkProp, "Income And ExpenseBenchmark");
        clickObjectJavascript(trendsPage.incomeAndExpenseBenchmarkProp, "income And ExpenseBenchmark number of properties link");
    }
    
    public void verifyTrendsAndRankingsCharts() throws Exception { 
        waitForElement(trendsPage.loanPerformanceHeader);
        softAssert.assertTrue(trendsPage.loanPerformanceHeader.isDisplayed(), "loanPerformanceHeader not displayed.");
        softAssert.assertTrue(trendsPage.loanPerformanceGraph.isDisplayed(), "loanPerformanceGraph not displayed.");
        
        waitForElement(trendsPage.underwritingTrendsHeader);
        softAssert.assertTrue(trendsPage.underwritingTrendsHeader.isDisplayed(), "underwritingTrendsHeader not displayed.");
        softAssert.assertTrue(trendsPage.underwritingTrendsHeaderGraph.isDisplayed(), "underwritingTrendsHeaderGraph not displayed.");
        
        waitForElement(trendsPage.incomeExpenseBenchmarkHeader);
        softAssert.assertTrue(trendsPage.underwritingTrendsHeader.isDisplayed(), "incomeExpenseBenchmarkHeader not displayed.");
        softAssert.assertTrue(trendsPage.incomeExpenseBenchmarkGraph.isDisplayed(), "incomeExpenseBenchmarkGraph not displayed.");              
    }

}
