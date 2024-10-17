package com.newdemo.framework.pages.loan;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class SinglePropertyValuationPage {

    private WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    //Valuation Page
    @FindBy(css = "[items='periods'] button.dropdown-toggle")
    public WebElement spvPeriodDropdown;

    @FindBy(css = "button.valuations-dropdown")
    public WebElement spvsavedValuationdropdown;

    @FindBy(css = "button.valuations-dropdown span[ng-if='itemSelected']")
    public WebElement selectedValuation;

    @FindBy(css = "span[item-selected='valuationSelected'] ul li a[ng-click='onClickFunc(i)']")
    public List<WebElement> spvsavedValuationdropdownList;

    @FindBy(xpath = ".//a[@ng-click='pinnedItem.onClickFunc()' and text()[contains(.,'NEW')]]")
    public WebElement newValuationChoice;

    @FindBy(css = "span[item-selected='valuationSelected'] ul li button[ng-click='footerItem.onClickFunc()']")
    public WebElement spvValuationManagerButton;

    @FindBy(css = "button[ng-click*='openSaveValuationModal']")
    public WebElement saveAsValuationButton;

    @FindBy(css = "button.btn-download")
    public WebElement spvDownloadBtn;

    //Financial accordian
    @FindBy(css = "h4[ng-class*='accordions.financials']")
    public WebElement financialAccordianHeading;

    @FindBy(css = "p[ng-show*='accordions.financials']")
    public WebElement financialAccordianContent;

    @FindBy(xpath = ".//span[@role='columnheader' and text()[contains(.,'Year')]]")
    public List<WebElement> spvYearColumnHeader;

    @FindBy(xpath = ".//span[text()='Other Expenses']")
    public WebElement otherExpanceSpan;

    //Direct capitalization accordian
    @FindBy(css = "h4[ng-class*='accordions.directCapMethod']")
    public WebElement directCapMethodAccordianHeading;

    @FindBy(css = "p[ng-show*='accordions.directCapMethod']")
    public WebElement directCapMethodAccordianContent;

    @FindBy(css = "a.source-btn")
    public WebElement addSourceButton;

    @FindBy(css = ".ag-row-last div[col-id='impliedCapRateSecNOI']")
    public WebElement capRate;

    @FindBy(css = "[row-index='0'] div[col-id='impliedCapRateSecNOI']")
    public WebElement medianCapRate;

    @FindBy(css = "[ref='eBottom'] div[col-id='impliedCapRateSecNOI']")
    public WebElement averageCapRate;

    @FindBy(css = ".ag-center-cols-container [col-id='actual']")
    public List<WebElement> mostRecentActualNOI;

    @FindBy(css = "[row-id='0'] div[col-id='weight']")
    public WebElement dcmweight;

    //discounted cashflow accordian
    @FindBy(css = "h4[ng-class*='accordions.discountedCashflowMethod']")
    public WebElement discountedCashflowMethodAccordianHeading;

    @FindBy(css = "p[ng-show*='accordions.discountedCashflowMethod']")
    public WebElement discountedCashflowMethodAccordianContent;

    @FindBy(css = "p.spvFinalValueNumber")
    public WebElement spvFinalValue;

    @FindBy(css = ".calculator-row div[col-id='terminalCapRate'] .terminal-cap-rate-right")
    public WebElement terminalCapRateWrap;

    @FindBy(css = ".calculator-row div[col-id='terminalCapRate'] .terminal-cap-rate-right input.benchmark-input")
    public WebElement terminalCapRateWrapInput;

    @FindBy(css = "div.terminal-cap-rate-right .step-input")
    public WebElement terminalCapRateRightStepInput;

    @FindBy(css = "div.terminal-cap-rate-right")
    public List<WebElement> terminalCapRateRightList;

    @FindBy(css = "[row-id*='benchmark'] [col-id='terminalValue'")
    public List<WebElement> terminalCapRateValueList;

    @FindBy(css = "[row-id*='benchmark'] [col-id='unleveredIrr']")
    public List<WebElement> unleveredIRRInputList;

    @FindBy(css = ".calculator-row [col-id='unleveredIrr'][col-id='unleveredIrr']")
    public WebElement unleveredIRRInputWrap;

    @FindBy(css = ".calculator-row [col-id='unleveredIrr'] .discount-rate-right input")
    public WebElement unleveredIRRInput;

    @FindBy(css = "div.discount-rate-right .step-input")
    public WebElement unleveredIRRStepInput;

    @FindBy(css = "[row-id*='benchmark'] [col-id='unleveredValue']")
    public List<WebElement> unleveredIRRValueList;

    //modal  div.modal-dialog
    @FindBy(css = "div.modal-dialog")
    public WebElement saveValuationModal;

    @FindBy(css = "#spvName")
    public WebElement saveValuationNameInput;

    @FindBy(css = "#set-default-yes")
    public WebElement setDefaultYes;

    @FindBy(css = "#set-default-no")
    public WebElement setDefaultNo;

    @FindBy(css = "button[ng-click*='saveSpv']")
    public WebElement saveValuationSubmit;

    @FindBy(css = "button[ng-click='$dismiss()']")
    public WebElement saveValuationModalClose;

    //delete valuation modal
    @FindBy(css = "[ng-click*='respondAndClose(true)']")
    public WebElement deleteValuationButton;

    public List<WebElement> valuationGetCellByColId(String colId) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ag-row-level-1 [col-id='" + colId + "']")));
    }

    public WebElement savedValuationChoice(String valuationName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@ng-click='onClickFunc(i)' and text()[contains(.,'" + valuationName + "')]]")));
    }

    public WebElement savedValuationChoiceDelete(String valuationName) {
        return wait.until(ExpectedConditions.visibilityOf(savedValuationChoice(valuationName).findElement(By.xpath("./../span/a[@class='valuation-item-delete ng-scope']"))));
    }

    public WebElement periodDropdownChoice(int period) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[items='periods'] ul li:nth-of-type(" + Integer.toString(period - 4) + ")")));
    }
    
    @FindBy(xpath = "(//a[@class='source-btn float-right'])[1]")
    public WebElement addSource;
    @FindBy(xpath = "(//div[@col-id='ag-Grid-AutoColumn'])[2]")
    public WebElement revinewLastCol;
    @FindBy(xpath = "(//div[@col-id='baseValue'])[2]")
    public WebElement baseRateCol;
}
