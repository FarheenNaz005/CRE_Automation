package com.newdemo.framework.pages.stats;


//import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TenantReportPage {

    /**
     * header
     **/
    @FindBy(css = "span.navbar-brand")
    public WebElement navbarBrand;

    @FindBy(css = "button[ng-click= 'download()'].navbar-btn")
    public WebElement download;

    @FindBy(css = "a[ng-click *= 'gotoLoanListing']")
    public WebElement propertyListLink;

    @FindBy(css = "div.tenant-title-text")
    public WebElement tenantTitle;

    @FindBy(css = "tenant-map-section")
    public WebElement outstandingLoanMap;

    @FindBy(css = "div[tenant-summary]")
    public WebElement tenantDeliquencySummary;

    @FindBy(css = "div.recent-transactions")
    public WebElement recentTransactionTable;

    @FindBy(css = "div.origination-year")
    public WebElement outstandingDebtSummary;

    @FindBy(css = "div.deal-exposure")
    public WebElement cmbsdealexposure;


}
