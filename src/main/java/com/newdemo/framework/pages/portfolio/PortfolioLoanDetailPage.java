package com.newdemo.framework.pages.portfolio;


//import org.openqa.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortfolioLoanDetailPage {


    /**
     * Left Side Panel
     **/
    @FindBy(css = ".loan-portfolio-name strong")
    public WebElement loanPortfolioName;

    @FindBy(css = "p.property-name")
    public WebElement propertyName;


    /**
     * Top Button
     **/
    @FindBy(css = "a.related-records")
    public WebElement relatedRecordButton;

    @FindBy(css = "a.edit-button[href*='display=edit']")
    public WebElement editButton;

    @FindBy(css = "a[ui-sref*='loanPortfolioLoanHistory']")
    public WebElement historyButton;

    @FindBy(css = "button[ng-click*='downloadLoanPortfolioLoan']")
    public WebElement downloadButton;

    @FindBy(css = "a[ng-class*='Information']")
    public WebElement loanInformationLink;

    @FindBy(css = "a[ng-class*='Financials']")
    public WebElement loanFinancialsLink;

    //Loan Info
    @FindBy(css = "form[name='yearBuiltForm'] h2")
    public WebElement formYearBuilt;

    @FindBy(css = "form[name='secBalForm'] h2")
    public WebElement formOriginalBal;

    //Editing mode
    @FindBy(css = "button.update-button")
    public WebElement updateButton;

    @FindBy(css = ".text-info h4 strong")
    public WebElement editModeTextInfo;

    @FindBy(id = "yearBuilt")
    public WebElement inputYearBuilt;

    @FindBy(id = "secBal")
    public WebElement inputOriginalBal;

}
