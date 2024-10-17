package com.newdemo.framework.controller;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.NewAdvanceSearchLoan;
import com.newdemo.framework.pages.NewAdvanceSearchProperty;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


public class NewAdvanceSearchController extends ReuseableFunctions {

    NewAdvanceSearchProperty advanceSearchProperty = null;
    NewAdvanceSearchLoan advanceSearchLoan = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public NewAdvanceSearchController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReusableFunctions class that all the pages objects inherit from
        advanceSearchProperty = PageFactory.initElements(driver, NewAdvanceSearchProperty.class);
        advanceSearchLoan = PageFactory.initElements(driver, NewAdvanceSearchLoan.class);
    }

    /*
    Action Methods
    */

    public void selectTab(NewAdvanceSearchProperty.TabName tab) throws Exception {
        clickObject(advanceSearchProperty.tab(tab), "Select " + tab);
    }

    public void toggleByText(String value) throws Exception {
        //WebElement element = driver.findElement(By.xpath("//label[contains(@class, 'toggle-option__label') and contains(text(), '" + value + "')]"));
        WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(@class, 'toggle-option__label') and contains(text(), '" + value + "')]")));
        clickObject(element, "Toggled to " + value);
    }

    public void selectPropertyType(String propertyType) throws Exception {
        waitForElement(advanceSearchProperty.openPropertyTypeDropdown);
        clickObject(advanceSearchProperty.openPropertyTypeDropdown, "Open PropertyType DropDown ");
        WebElement propertyTypeCheckbox = driver.findElement(
                By.xpath(".//label[contains(text(),'" + propertyType + "')]"));
        waitForElement(propertyTypeCheckbox);
        clickObject(propertyTypeCheckbox, "Select " + propertyType);
        clickObject(advanceSearchProperty.heading, "Close Dropdown ");
    }

    public void selectPropertyState(String state) throws Exception {
        waitForElement(advanceSearchProperty.openPropertyStateDropdown);
        clickObject(advanceSearchProperty.openPropertyStateDropdown, "Open PropertyState DropDown ");
        WebElement propertyStateCheckbox = driver.findElement(
                By.xpath(".//label[contains(text(),'" + state + "')]"));
        waitForElement(propertyStateCheckbox);
        clickObject(propertyStateCheckbox, "Select " + state);
        clickObject(advanceSearchProperty.heading, "Close Dropdown ");
    }

    public void selectPropertyCity(String city) throws Exception {
        waitForElement(advanceSearchProperty.propertyCity);
        clearNTypeValue(advanceSearchProperty.propertyCity, city, "PropertyCity searchBox");
        WebElement citySuggestion = new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='trepp__typeAhead__listItem']/span[contains(text(),'" + city + "')]")));
        clickObject(citySuggestion, "city suggestion");
    }

    public void selectDelinquencyStatus(String delinquencyStatus) throws Exception {
        waitForElement(advanceSearchLoan.delinquencyStatus);
        clickObject(advanceSearchLoan.delinquencyStatus, "Open DelinquencyStatus DropDown ");
        WebElement delinquencyStatusCheckbox = driver.findElement(
                By.xpath(".//label[contains(text(),'" + delinquencyStatus + "')]"));
        waitForElement(delinquencyStatusCheckbox);
        clickObject(delinquencyStatusCheckbox, "Select " + delinquencyStatus);
        clickObject(advanceSearchProperty.heading, "Close Dropdown ");
    }

    public void selectTransactionDate(String date) throws Exception {
        clearNTypeValue(advanceSearchProperty.salesDateMin, date, "date input");
    }

    public void selectRemTermMax(String remTerm) throws Exception {
        clearNTypeValue(advanceSearchLoan.remTermMax, remTerm, "Max rem term");
    }

    public void clickSearch() throws Exception {
        clickObject(advanceSearchProperty.searchbutton, "Search Button ");
    }
    
    public void selectPropertyByZipCode(String zipCode) throws Exception {
        waitForElement(advanceSearchProperty.zipCode);
        clearNTypeValue(advanceSearchProperty.zipCode, zipCode, "PropertyZip searchBox");
        WebElement zipSuggestion = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='trepp__typeAhead__listItem']/span[contains(text(),'" + zipCode + "')]")));
        clickObject(zipSuggestion, "zip suggestion");
    }



    /*
    Validation Methods
    */

    public void verifyNewListingPage() throws Exception {
        // Get the URL of the opened page
        String url = driver.getCurrentUrl();

        // Validate the URL
        softAssert.assertTrue(url.contains("loanListingv3"), "The landed page is not new loanListingv3");
    }

    public void verifySearchCount() throws Exception {
        waitForElement(advanceSearchProperty.searchCount);
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSearchProperty.loading));
        String text = getText(advanceSearchProperty.searchCount, "Search Result Count", "");
        String digits = StringUtils.getDigits(text);
        int count = Integer.parseInt(digits);
        softAssert.assertNotEquals(count, 0, "The search shows zero results");
    }
    
    public void selectDataLoanSources(String dataLoanSource) throws Exception {
        waitForElement(advanceSearchLoan.loanDataSource);
        clickObject(advanceSearchLoan.loanDataSource, "Loan Data dropdown DropDown ");
        WebElement dataLoanSourceCheckbox = driver.findElement(
                By.xpath(".//label[contains(text(),'" + dataLoanSource + "')]"));
        waitForElement(dataLoanSourceCheckbox);
        clickObject(dataLoanSourceCheckbox, "Select " + dataLoanSource);
        clickObject(advanceSearchProperty.heading, "Close Dropdown ");
    }
    
    public void selectMaturityYear(String maturityYearVal) throws Exception {
        waitForElement(advanceSearchLoan.maturityYear);
        clickObject(advanceSearchLoan.maturityYear, "Maturity year");
        typeValue(advanceSearchLoan.maturityYear, "Maturity year", maturityYearVal);             
    }
    
    public void selectOriginalBalance(String originalBalance) throws Exception {
        scrollIntoView(advanceSearchLoan.originalBalance, "Original Balance");
        waitForElement(advanceSearchLoan.originalBalance);
        clickObject(advanceSearchLoan.originalBalance, "Original Balance");
        typeValue(advanceSearchLoan.originalBalance, "Original Balance", originalBalance);             
    }
    
    public void selectYearBuilt(String yearBuilt) throws Exception {
        waitForElement(advanceSearchLoan.yearBuilt);
        advanceSearchLoan.yearBuilt.clear();
        clickObject(advanceSearchLoan.yearBuilt, "Year Built");
        typeValue(advanceSearchLoan.yearBuilt, "Year built", yearBuilt);             
    }
    
    public void selectPropertySubType(String propertySubtype) throws Exception {
        waitForElement(advanceSearchLoan.propertySubtype);
        clickObject(advanceSearchLoan.propertySubtype, "Open Property Subtype DropDown ");
        WebElement propertySubtypeVal = driver.findElement(
                By.xpath(".//label[contains(text(),'" + propertySubtype + "')]"));
        waitForElement(propertySubtypeVal);
        clickObject(propertySubtypeVal, "Select " + propertySubtype);
        clickObject(advanceSearchProperty.heading, "Close Dropdown ");
    }
    
    public void selectPropertySumOfUnits(String sum) throws Exception {
        waitForElement(advanceSearchProperty.sumOfUnits);
        advanceSearchProperty.sumOfUnits.clear();
        clickObjectJavascript(advanceSearchProperty.sumOfUnits, "Sum(# of Units)");
        typeValue(advanceSearchProperty.sumOfUnits, "Sum(# of Units)", sum);
    }

    public void selectOperatingStatementAvailable() throws Exception {
        waitForElement(advanceSearchProperty.operatingStatementAvailable);
        scrollIntoView(advanceSearchProperty.operatingStatementAvailable, "Operating Statement Available");
        clickObjectJavascript(advanceSearchProperty.operatingStatementAvailable, "Yes value");
    }

    public void selectMSA(String msa) throws Exception {
        waitForElement(advanceSearchProperty.msa);
        clearNTypeValue(advanceSearchProperty.msa, msa, "MSA searchBox");
        WebElement msaSuggestion = new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='trepp__typeAhead__listItem']/span[contains(text(),'" + msa + "')]")));
        clickObject(msaSuggestion, "city suggestion");
    }

    public void selectAppraisalValue(String appraisalValue) throws Exception {
        waitForElement(advanceSearchProperty.viewMore);
        scrollIntoView(advanceSearchProperty.viewMore, "View More");
        clickObjectJavascript(advanceSearchProperty.viewMore, "View More");
        advanceSearchProperty.appraisalValue.clear();
        clickObjectJavascript(advanceSearchProperty.appraisalValue, "Appraisal Value");
        typeValue(advanceSearchProperty.appraisalValue, "Appraisal Value", appraisalValue);
    }

    public void enterTransactionDate(String transactionDate) throws Exception {
        waitForElement(advanceSearchProperty.transactionDate);
        scrollIntoView(advanceSearchProperty.transactionDate, "Transaction Date");
        clickObject(advanceSearchProperty.transactionDate, "Transaction Date");
        typeValue(advanceSearchProperty.transactionDate, "Transaction Date", transactionDate);
    }

    public void clickUncheckAllLoanDataSource() throws Exception {
        waitForElement(advanceSearchLoan.loanDataSource);
        scrollIntoView(advanceSearchLoan.loanDataSource, "Loan Data Source");
        clickObject(advanceSearchLoan.loanDataSource, "Loan Data Source");
        waitForElement(advanceSearchProperty.uncheckAll);
        clickObject(advanceSearchProperty.uncheckAll, "Uncheck All");
    }

    public void selectAmortizationType(String amortizationType) throws Exception {
        waitForElement(advanceSearchLoan.amortizationType);
        scrollIntoView(advanceSearchLoan.amortizationType, "Amortization Type");
        clickObject(advanceSearchLoan.amortizationType, "Amortization Type DropDown ");
        WebElement amortizationTypeCheckbox = driver
                .findElement(By.xpath(".//label[contains(text(),'" + amortizationType + "')]"));
        waitForElement(amortizationTypeCheckbox);
        clickObject(amortizationTypeCheckbox, "Select " + amortizationType);
        clickObject(advanceSearchProperty.heading, "Close Dropdown ");
    }
    
    public void selectSpecialServiced() throws Exception {
        waitForElement(advanceSearchProperty.specialServiced);
        scrollIntoView(advanceSearchProperty.specialServiced, "Special Serviced");
        clickObjectJavascript(advanceSearchProperty.specialServiced, "Yes value");
    }
    
    public void selectLTV(String ltv) throws Exception {
        waitForElement(advanceSearchProperty.ltv);
        scrollIntoView(advanceSearchProperty.ltv, "LTV %");
        clickObjectJavascript(advanceSearchProperty.ltv, "LTV % input");
        advanceSearchProperty.ltv.clear();
        typeValue(advanceSearchProperty.ltv, "LTV Value", ltv);
    }


}
