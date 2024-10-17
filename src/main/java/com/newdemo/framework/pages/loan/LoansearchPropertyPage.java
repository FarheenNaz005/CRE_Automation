package com.newdemo.framework.pages.loan;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class LoansearchPropertyPage {

    private WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    @FindBy(xpath = ".//span[contains(text(),'Summary')]")
    public WebElement summary;
    @FindBy(xpath = ".//span[(text()='Details v1')]")
    public WebElement details;
    @FindBy(xpath = ".//span[contains(text(),'Comps')]")
    public WebElement comps;
    @FindBy(xpath = ".//span[contains(text(),'Balance')]")
    public WebElement balance;
    @FindBy(xpath = ".//span[contains(text(),'Valuation')]")
    public WebElement valuation;
    @FindBy(xpath = ".//span[contains(text(),'Market')]")
    public WebElement market;
    @FindBy(xpath = ".//span[contains(text(),'Benchmarks')]")
    public WebElement benchmarks;
    @FindBy(xpath = ".//a[contains(text(),'Overview')]")
    public WebElement overview;
    @FindBy(xpath = ".//a[contains(text(),'Commentary')]")
    public WebElement commentary;

    @FindBy(xpath = ".//table[@id='market-fin-table']//tr/td")
    public List<WebElement> bechmarkDatList;

    @FindBy(xpath = ".//div[@id='c_loanProp_0']//table[@class='column-table']//tbody//tr//td[@class='first-col']//div[@class='DataFmt']//table//tbody")
    public WebElement trdatatable;


    /*
     * @FindBy(xpath=".//li[@class='ng-scope active']//div[@class='primary-label']")
     * public WebElement firstsearchresult;
     */


    //comp page
    @FindBy(css = "a[ng-click='downloadAllLoans()']")
    public WebElement compDownloadButton;

    @FindBy(xpath = ".//download-loans-bulk-button[@download-comps-report]//button[@disabled]")
    public WebElement compDownloadButtonDisabled;


    //Valuation Page
    @FindBy(css = "[items='periods'] button.dropdown-toggle")
    public WebElement spvPeriodDropdown;

    @FindBy(css = "span[item-selected='valuationSelected'] button")
    public WebElement spvsavedValuationdropdown;

    @FindBy(css = "span[item-selected='valuationSelected'] ul li a")
    public List<WebElement> spvsavedValuationdropdownList;

    @FindBy(css = "button[ng-click*='openSaveValuationModal']")
    public WebElement saveValuationButton;

    @FindBy(css = "[items='periods'] ul li:nth-of-type(6)")
    public WebElement spvPeriodDropdownChoice;

    @FindBy(css = "button.btn-download")
    public WebElement spvDownloadBtn;

    @FindBy(xpath = ".//span[@role='columnheader' and text()[contains(.,'Year')]]")
    public List<WebElement> spvYearColumnHeader;

    @FindBy(xpath = ".//span[text()[contains(.,'Direct Capitalization Method')]]/..")
    public WebElement directCapitalizationMethod;

    @FindBy(xpath = ".//span[text()[contains(.,'Discounted Cashflow Method')]]/..")
    public WebElement discountedCashFlowMethod;

    @FindBy(css = "p.spvFinalValueNumber")
    public WebElement spvFinalValue;

    @FindBy(xpath = ".//span[text()='Other Expenses']")
    public WebElement otherExpanceSpan;

    @FindBy(css = "[row-id='0'] div[col-id='weight']")
    public WebElement dcmweight;

    @FindBy(css = ".calculator-row div[col-id='discountRate']")
    public WebElement discountRateDiscountCashflow;

    @FindBy(css = ".calculator-row div[col-id='discountRate'] input.benchmark-input")
    public WebElement discountRateDiscountCashflowInput;

    @FindBy(css = "div.terminal-cap-rate-right .step-input")
    public WebElement terminalCapRateRightStepInput;

    @FindBy(css = "div.terminal-cap-rate-right")
    public List<WebElement> terminalCapRateRightList;

    @FindBy(css = "div.discount-rate-right .step-input")
    public WebElement discountRateRightStepInput;

    @FindBy(css = "div.discount-rate-right")
    public List<WebElement> discountRateRightList;

    //modal  div.modal-dialog
    @FindBy(css = "div.modal-dialog")
    public WebElement saveValuationModal;

    @FindBy(css = "#spvName")
    public WebElement saveValuationNameInput;

    @FindBy(css = "button[ng-click*='saveSpv']")
    public WebElement saveValuationSubmit;

    @FindBy(css = "button[ng-click='$dismiss()']")
    public WebElement saveValuationModalClose;

    public List<WebElement> valuationGetCellByColId(String colId) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[col-id='" + colId + "'][role*='grid']")));
    }

    public WebElement savedValuationChoice(String valuationName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[text()[contains(.,'" + valuationName + "')]]")));

    }

}
