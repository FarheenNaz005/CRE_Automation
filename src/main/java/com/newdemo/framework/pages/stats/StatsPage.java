package com.newdemo.framework.pages.stats;


//import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatsPage {

    /**
     * nav link buttons
     **/
    @FindBy(xpath = ".//span[text()='Trends']/..")
    public WebElement navTrendsLink;

    @FindBy(xpath = ".//span[text()='TreppWire']/..")
    public WebElement navTreppWireLink;

    @FindBy(xpath = ".//span[text()='Performance']/..")
    public WebElement navPerformanceLink;

    @FindBy(xpath = "//td[@class='col filter-left-col']")
    public WebElement performanceReportTable;

    @FindBy(xpath = "//select[@id='collateral_universe']")
    public WebElement collateralDropdown;

    @FindBy(xpath = "//select[@id='rpt_name']")
    public WebElement reportTypeDropdown;

    @FindBy(xpath = "//select[@id='rpt_month']")
    public WebElement periodDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Display')]")
    public WebElement displayButton;

    @FindBy(xpath = "//span[contains(text(), 'Download')]")
    public WebElement performanceDownloadButton;

    @FindBy(xpath = "//span[contains(text(), 'Print')]")
    public WebElement performancePrintButton;

    @FindBy(xpath = ".//span[text()='News']/..")
    public WebElement navNewsLink;

    @FindBy(xpath = ".//span[text()='New Issuance']/..")
    public WebElement navNewIssuanceLink;

    @FindBy(xpath = ".//span[text()='Company Exposure']/..")
    public WebElement navCompanyExposureLink;

    @FindBy(xpath = "//span[contains(text(),' Market Snapshot')]")
    public WebElement marketSnapshotButton;

    @FindBy(css = "button[ng-click='export()']")
    public WebElement exportButton;

}
