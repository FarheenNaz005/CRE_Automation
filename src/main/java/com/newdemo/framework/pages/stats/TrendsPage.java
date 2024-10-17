package com.newdemo.framework.pages.stats;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class TrendsPage {

    private WebDriver driver = BaseSetupClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);


    //top buttons
    @FindBy(css = "button[ng-click='download()']")
    public WebElement downloadButton;

    @FindBy(css = "button[ng-click='selectionUpdater()']")
    public WebElement propertyTypeDropdown;

    @FindBy(css = "#sourceDropdown")
    public WebElement sourceDropdown;

    @FindBy(xpath = ".//label[text()='Rank By:']/following-sibling::div/button")
    public WebElement propertyRankByDropdown;

    @FindBy(css = "button[ng-click='marketSnapshotModal()']")
    public WebElement marketSnapshotButton;

    //maps
    @FindBy(css = "div#trends-and-rankings-map-id")
    public WebElement trendsAndRankingMap;

    //ranking list
    @FindBy(css = "span.listing-header-title")
    public WebElement rankListTitle;

    @FindBy(css = "li[ng-repeat='count in marketCounts'] a")
    public List<WebElement> rankListTopCountSwitches;

    @FindBy(css = "li[ng-repeat='count in marketCounts'].active a")
    public WebElement rankListTopCountSwitcheActive;

    @FindBy(css = "a[ui-sref*='selected.ranking.dir']")
    public WebElement rankListRankByTitle;

    @FindBy(css = ".list-data-row a[href*='trendsAndRankings?market=county'] .col-md-6")
    public List<WebElement> rankListItems;

    @FindBy(css = ".list-data-row a[href*='trendsAndRankings?market=county'] div.list-data-value")
    public List<WebElement> rankListItemsValue;


    /**
     * drill down page
     **/
    @FindBy(css = ".map-header div[ng-if*='selected.']")
    public WebElement drillDownPageHeader;

    @FindBy(css = "trends-and-rankings-pager ul")
    public WebElement drillDownPagePagination;

    @FindBy(css = "trends-and-rankings-loan-performance div.detail-section")
    public WebElement loanPerformanceGraph;

    @FindBy(css = "trends-and-rankings-underwriting div.detail-section")
    public WebElement underwritingTrend;

    @FindBy(css = "trends-and-rankings-financials div.detail-section")
    public WebElement incomeExpenseBenchmark;


    /**dynamic elements**/
    /**
     * MSA / state switch buttons
     **/
    public WebElement marketSwitchButton(String count) {
        return wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[text()='" + count + "']/..")));
    }

    /**
     * rank list top count switch buttons
     **/
    public WebElement rankListTopCountSwitches(String marketName) {
        return wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li/a[text()='" + marketName + "']")));
    }

    public WebElement propertyRankByDropdownChoice(String choice) {
        return wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li/a[text()[contains(.,'" + choice + "')]]")));
    }

    public WebElement propertyTypeDropdownChoice(String propertyType) {
        return wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li[contains(@ng-repeat, 'subtype')]/a[text()[contains(.,'" + propertyType + "')]]")));
    }

    public WebElement sourceDropdownChoice(String source) {
        return wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li[contains(@ng-repeat, 'source')]/a[text()[contains(.,'" + source + "')]]")));
    }
    
    // CRE4550
    @FindBy(xpath = "(//a//div[@class='col-md-6 ng-binding'])[1]")
    public WebElement clicktMSA;
    @FindBy(xpath = "//div[text()='Loan Performance']")
    public WebElement loanPerformanceHeader;
    @FindBy(xpath = "//div[@class='donut-chart']")
    public WebElement loanPerformanceGraph1;
    @FindBy(xpath = "//div[contains(text(), 'Underwriting Trends')]")
    public WebElement underwritingTrendsHeader;
    @FindBy(xpath = "//*[name()='g' and @class='nvd3 nv-wrap nv-linePlusBar']")
    public WebElement underwritingTrendsHeaderGraph;
    @FindBy(xpath = "//div[contains(text(), 'Income and Expense Benchmark')]")
    public WebElement incomeExpenseBenchmarkHeader;
    @FindBy(xpath = "//*[name()='g' and @class='nvd3 nv-wrap nv-multiBarWithLegend']")
    public WebElement incomeExpenseBenchmarkGraph;
    
    @FindBy(xpath = "//num-props-preconfigured-loan-listing-query[@category=\"'watchlist'\"]//a")
    public WebElement loanPerformanceProp;
    @FindBy(xpath = "//num-props-preconfigured-loan-listing-query[@category=\"'allProperties'\"]//a")
    public WebElement underwritingTrendsProp;
    @FindBy(xpath = "//num-props-loan-listing-link[@category=\"'Revenue'\"]//a")
    public WebElement incomeAndExpenseBenchmarkProp;



}
