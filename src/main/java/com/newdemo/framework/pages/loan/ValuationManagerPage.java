package com.newdemo.framework.pages.loan;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class ValuationManagerPage {

    private WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    //Valuation Page
    @FindBy(css = ".ValuationManagerToolbar .navbar-left")
    public WebElement heading;

    @FindBy(css = ".ValuationManagerToolbar .navbar-right .navbar-text")
    public WebElement valuationCount;

    @FindBy(css = ".ag-center-cols-clipper div[role='row'][aria-expanded] a[href*='singlePropertyValuation']")
    public List<WebElement> propertyList;

    @FindBy(css = "a.ag-valuation-name[href*='valuationId=']")
    public List<WebElement> valuationList;

    @FindBy(css = "div[col-id='poolnum'] a")
    public WebElement trusteeLoanIDList;

    public WebElement propertyLink(String propertyName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@role='row' and @aria-expanded]//a[@href[contains(.,'singlePropertyValuation')] and text()[contains(.,'" + propertyName + "')]]")));
    }

    public WebElement valuationLink(String valuationName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(@class,'ag-valuation-name') and contains(@href,'valuationId=') and text()[contains(.,'" + valuationName + "')]]")));
    }


}
