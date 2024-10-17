package com.newdemo.framework.pages;


//import org.openqa.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class AdvanceSearchModal {
    private WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    /*enum for filed with suggestion */
    public enum TabName {
        general,
        property,
        loan
    }

    /*enum for filed with suggestion */
    public enum FieldWithSuggestion {
        cityState,
        countyState,
        zip,
        msa,
        tenant,
        loanSpecialServicer,
        submarket,
        originator,
        bloombergName,
        poolnum,
        fhaNumber,
        loanUniversePropID
    }

    /*simple search start*/
    @FindBy(xpath = ".//input[@placeholder='Enter a Property Name, Address, ID or Location']")
    public WebElement searchbox;

    @FindBy(xpath = ".//i[@class='fa fa-fw fa-lg fa-search']")
    public WebElement searchMaginifingglass;
    /*simple search end*/

    @FindBy(xpath = ".//div[@class='btn-group ng-scope']/button[1]")
    public WebElement openStatedropdown;

    @FindBy(css = "#dataSource button.btn-dropdown")
    public WebElement dataSourcedropdownButton;

    @FindBy(css = "#dataSource ul li button[ng-click='checkAll()']")
    public WebElement dataSourcedropdownCheckALLButton;

    @FindBy(css = "#dataSource ul li button[ng-click='uncheckAll()']")
    public WebElement dataSourcedropdownUncheckALLButton;

    @FindBy(xpath = ".//div[@class='row']//div[1]//div[4]//div[1]//div[2]//button[1]")
    public WebElement yesOperatingStatment;


    @FindBy(xpath = ".//button[contains(@class,'btn btn-primary ng-binding')]//i[contains(@class,'fa fa-search')]")
    public WebElement searchbuttonModal;

    @FindBy(css = "button[ng-click='showMoreLess()']")
    public WebElement moreLessButton;

    //dynamic variable
    public WebElement tab(TabName tab) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[ng-click^=select][ng-click*='" + tab + "']")));
    }

    public WebElement sourceCheckByName(String source) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//taui-multi-select[@id='dataSource']//ul/li/a[text()[contains(.,'" + source + "')]]")));
    }

    public WebElement inputForfiledWithSuggestion(FieldWithSuggestion field) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#" + field + " input")));
    }

    public WebElement suggestionItemByName(FieldWithSuggestion field, String query) {
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#" + field + " auto-complete ul")))
                .findElement(By.xpath(".//li/em[text()[contains(.,'" + query + "')]]"));
        return wait.until(ExpectedConditions.visibilityOf(item));
    }

    
    
    
    
    
    
    
    
    
    

}
