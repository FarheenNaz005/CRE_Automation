package com.newdemo.framework.pages.portfolio;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.newdemo.framework.pages.PropertyListingPage;

public class PortfolioPage extends PropertyListingPage {


    /**
     * nav link
     **/
    @FindBy(xpath = ".//span[text()='Snapshot']/..")
    public WebElement navSnapshotLink;

    @FindBy(xpath = ".//span[text()='Rankings']/..")
    public WebElement navRankingsLink;

    @FindBy(xpath = ".//span[text()='Calculators']/..")
    public WebElement navCalculatorsLink;

    //calculator sub menu
    @FindBy(css = "a[ng-href*='loan_overrides.cgi']")
    public WebElement calcLoanOverrideLink;

    @FindBy(css = "a[ng-href*='loan_by_loan.cgi']")
    public WebElement calcLoanByLoanLink;

    @FindBy(xpath = ".//span[text()='Comments']/..")
    public WebElement navCommentLink;


    //portfolio

    @FindBy(xpath = ".//span[contains(text(),'Delete')]")
    public WebElement deleterecords;

    @FindBy(xpath = ".//button[@class='btn btn-danger ng-binding']")
    public WebElement confirmDeleterecords;

    @FindBy(xpath = ".//h2[contains(text(),'This portfolio is empty.')]")
    public WebElement empyprotfoliMessage;


    @FindBy(xpath = ".//div[@class='col-md-6 ng-binding']")
    public WebElement statsMSA;
    @FindBy(xpath = ".//div[@class='col-md-5 list-data-value ng-binding']")
    public WebElement statsdelinquency;

    @FindBy(xpath = ".//input[@class='form-control ng-pristine ng-valid ng-valid-number']")
    public WebElement ttpercentage;

    @FindBy(xpath = ".//a[@class='btn btn-success navbar-btn']")
    public WebElement getValuation;

    @FindBy(xpath = ".//span[contains(text(),'Save Edits')]")
    public WebElement saveEdits;

    @FindBy(xpath = ".//strong[contains(text(),'Save Valuation')]")
    public WebElement saveValuation;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    public WebElement acceptAlert;


    @FindBy(xpath = ".//strong[contains(text(),'Model')]")
    public WebElement model;

    @FindBy(xpath = ".//i[@class='fa fa-download']")
    public WebElement downloadValuations;

    @FindBy(xpath = ".//input[@class='form-control ng-pristine ng-invalid ng-invalid-required']")
    public WebElement modelname;

    @FindBy(xpath = ".//input[@type='number']")
    public WebElement changeTTpercent;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    public WebElement applyModel;

    @FindBy(xpath = ".//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-with-height ag-cell-value']")
    public List<WebElement> modelvalues;

    @FindBy(xpath = ".//input[@class='ag-floating-filter-input']")
    public List<WebElement> modelcolumnames;

    @FindBy(xpath = ".//div[@class= 'ag-floating-bottom-container']//div//span")
    public List<WebElement> valuationpercentage;

    @FindBy(xpath = ".//workflow-menu[@class='bottom-menu ng-scope ng-isolate-scope']//li")
    public List<WebElement> valuationList;

    @FindBy(xpath = ".//i[@class='fa fa-lg fa-caret-down']")
    public WebElement valuationDropDown;

    @FindBy(xpath = ".//input[@id='valuationID']")
    public WebElement valuationName;


}
