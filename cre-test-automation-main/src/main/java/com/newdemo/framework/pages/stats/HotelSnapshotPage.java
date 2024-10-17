package com.newdemo.framework.pages.stats;


//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class HotelSnapshotPage {

    private WebDriver driver = BaseSetupClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    /**
     * header
     **/
    @FindBy(css = "span.navbar-brand")
    public WebElement navbarBrand;

    @FindBy(xpath = ".//button[contains(@id,'object-list-dropdown') and ./text()[contains(.,'Brand')]]")
    public WebElement brandSelect;

    @FindBy(xpath = ".//button[contains(@id,'object-list-dropdown') and ./text()[contains(.,'Market')]]")
    public WebElement marketSelect;

    @FindBy(css = "button[ button-label= 'Download']")
    public WebElement download;

    @FindBy(css = "div.hotel-overview")
    public WebElement hotelOverview;

    @FindBy(css = "div.hotel-perf-summary")
    public WebElement hotelPerformanceSummary;

    @FindBy(css = "div.hotel-transactions")
    public WebElement recentTransactionTable;

    @FindBy(css = "div.hotel-underwriting")
    public WebElement hotelUnderWriting;

    @FindBy(css = "div.hotel-exposure")
    public WebElement cmbsdealexposure;

    @FindBy(css = "div.hotel-operating-perf")
    public WebElement hotelOperatingPerformance;


    /**
     * Dynamic Element
     **/
    public WebElement brandMarketChoice(String name) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li/a[text()[contains(.,'" + name + "')]]")));
    }

}
