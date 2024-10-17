package com.newdemo.framework.controller.loan;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.loan.SinglePropertyValuationPage;

public class SinglePropertyValuationController extends ReuseableFunctions {
    SinglePropertyValuationPage spv = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public SinglePropertyValuationController(WebDriver driver) throws Exception {
        super(driver);
        spv = PageFactory.initElements(driver, SinglePropertyValuationPage.class);
    }

    // Action Methods
    public void selectValuation(String valuationName) throws Exception {
        clickObject(spv.spvsavedValuationdropdown, "valuation choice dropdown");
        if (valuationName.equalsIgnoreCase("NEW")) {
            clickObject(spv.newValuationChoice, "new valuation choice");
        } else {
            clickObject(spv.savedValuationChoice(valuationName), "saved valuation " + valuationName);
        }

    }

    public void deleteValuation(String valuationName) throws Exception {
        clickObject(spv.spvsavedValuationdropdown, "valuation choice dropdown");
        clickObject(spv.savedValuationChoiceDelete(valuationName), "delete for valuation " + valuationName);

        waitForElement(Waitcondition.toBeVisible, spv.saveValuationModal);
        Thread.sleep(500);
        clickObject(spv.deleteValuationButton, "delete valuation button");
        Thread.sleep(500);
        waitForElement(Waitcondition.toBeInvisible, spv.saveValuationModal);
    }

    public void selectPeriod(int years) throws Exception {
        clickObject(spv.spvPeriodDropdown, " period dropdown");
        clickObject(spv.periodDropdownChoice(years), " period dropdown choice " + years);
        Thread.sleep(5000);
        softAssert.assertTrue(spv.spvYearColumnHeader.size() > years, "Year column numbers should be " + years);
    }

    public void fillBasePercentage() throws Exception {
        List<WebElement> basecols = spv.valuationGetCellByColId("basePct");
        System.out.println(basecols.size());
        int count = 1;
        for (WebElement col : basecols) {
            col.sendKeys("5");
            if (count > 25) {
                break;
            }
        }
        clickObject(spv.financialAccordianContent, "clicked outside to trigger final value population");
        Thread.sleep(1000);
    }

    public void fillDirectCapMethod() throws Exception {
        scrollIntoView(spv.addSourceButton, "Add source button");
        clickObjectJavascript(spv.addSourceButton, "add source button");
        waitForElement(Waitcondition.toBeVisible, spv.capRate);
        spv.capRate.sendKeys("5");
        spv.capRate.sendKeys(Keys.ENTER);
    }

    public void fillTerminalCapRate(String terminalCapRate, String step) throws Exception {
        clickObjectJavascript(spv.terminalCapRateWrap, "Terminal cap rate cell");
        waitForElement(Waitcondition.toBeVisible, spv.terminalCapRateWrapInput);
        clearNTypeValue(spv.terminalCapRateWrapInput, terminalCapRate, "terminal cap rate");
        clearNTypeValue(spv.terminalCapRateRightStepInput, step, "terminal cap rate step");
        spv.terminalCapRateRightStepInput.sendKeys(Keys.ENTER);
    }

    public void fillUnleveredIRR(String unleveredIRR, String step) throws Exception {
        waitForElement(Waitcondition.toBeClickable, spv.unleveredIRRInputWrap);
        clickObjectJavascript(spv.unleveredIRRInputWrap, "UnleveredIRR cell");
        waitForElement(Waitcondition.toBeVisible, spv.unleveredIRRInput);
        clearNTypeValue(spv.unleveredIRRInput, unleveredIRR, "UnleveredIRR input");
        clearNTypeValue(spv.unleveredIRRStepInput, step, "UnleveredIRR step");
        spv.unleveredIRRStepInput.sendKeys(Keys.ENTER);
    }

    // Validation Methods
    public void verifyValuationTab() throws Exception {
        elementPresentorEnabled(spv.saveAsValuationButton, "Present", "save Valuation Button");
        elementPresentorEnabled(spv.spvPeriodDropdown, "Present", "spv Period Dropdown");
        elementPresentorEnabled(spv.spvsavedValuationdropdown, "Present", "spv saved valuation Dropdown");
        elementPresentorEnabled(spv.directCapMethodAccordianHeading, "Present", "Direct Capitalization Method Heading");
        elementPresentorEnabled(spv.directCapMethodAccordianContent, "Present", "Direct Capitalization Method Content");
        elementPresentorEnabled(spv.discountedCashflowMethodAccordianHeading, "Present",
                "Discounted CashFlow Method Heading");
        elementPresentorEnabled(spv.discountedCashflowMethodAccordianContent, "Present",
                "Discounted CashFlow Method Content");
    }

    public void validateBaseValue() throws Exception {
        List<WebElement> spvLatestFYList = spv.valuationGetCellByColId("latestFiscalYear");
        List<WebElement> spvBaseValueList = spv.valuationGetCellByColId("baseValue");
        for (int i = 0; i < spvLatestFYList.size(); i++) {
            String currFYCellValue = getText(spvLatestFYList.get(i), "Latest fiscal year cell " + i, "innerText");
            String currBaseValue = getText(spvBaseValueList.get(i), "Latest Base value cell " + i, "innerText");
            if (!currFYCellValue.equalsIgnoreCase("-")) {
                softAssert.assertTrue(!currBaseValue.isEmpty(), "base value should not be empty");
            } else {
                softAssert.assertTrue(currBaseValue.isEmpty(),
                        "base value should be empty when latest Fiscal year value is '-'");
            }
        }
    }

    public void validateDirectCapMethod() throws Exception {
        for (WebElement actualNOI : spv.mostRecentActualNOI) {
            String currCellValue = getText(actualNOI, "actual noi", "innerText");
            softAssert.assertTrue(!currCellValue.isEmpty(), "actual noi should not be empty");
        }
    }

    public void validateTerminalCapRate(String terminalCapRate, String step) throws Exception {
        for (WebElement terminalValue : spv.terminalCapRateValueList) {
            String currCellValue = getText(terminalValue, "terminal value", "innerText");
            softAssert.assertTrue(!currCellValue.isEmpty(), "Terminal value should not be empty");
        }
    }

    public void validateUnleveredIRR(String unLeveredIRR, String step) throws Exception {
        for (WebElement unLeveredValue : spv.unleveredIRRValueList) {
            String currCellValue = getText(unLeveredValue, "unLevered value", "innerText");
            softAssert.assertTrue(!currCellValue.isEmpty(), "unLevered value should not be empty");
        }
    }

    public void saveAsValuation(String valuationName, boolean makeDefault) throws Exception {
        jsexecutor.executeScript("return scroll(0,-1000)");

        waitForElement(spv.saveAsValuationButton);
        clickObject(spv.saveAsValuationButton, "save as valuation button");
        Thread.sleep(5000);
        waitForElement(spv.saveValuationModal);

        clearNTypeValue(spv.saveValuationNameInput, valuationName, "save valuation input");

        if (makeDefault) {
            clickObject(spv.setDefaultYes, "set default yes");
        } else {
            clickObject(spv.setDefaultNo, "set default no");
        }

        clickObject(spv.saveValuationSubmit, "save valuation submit");
        waitForElement(spv.saveValuationModalClose);
        clickObject(spv.saveValuationModalClose, "save valuation modal close button");
        waitForElement(Waitcondition.toBeInvisible, spv.saveValuationModal);
        Thread.sleep(1000);
    }

    public void verifySavedPropertyValuation(String valuationName) throws Exception {
        waitForElement(spv.spvsavedValuationdropdown);
        clickObject(spv.spvsavedValuationdropdown, "saved valuation dropdown");
        waitForElement(spv.savedValuationChoice(valuationName));
        clickObject(spv.savedValuationChoice(valuationName), "saved valuation choice " + valuationName);
    }

    public void verifySelectedValuation(String valuationName) throws Exception {
        waitForElement(Waitcondition.toBeVisible, spv.selectedValuation);
        softAssert.assertEquals(getText(spv.selectedValuation, "selected valuation", "innerText"), valuationName);
    }

    public void verifyPropertyValuationDownload(String valuationName) throws Exception {
        waitForElement(spv.spvDownloadBtn);
        clickObject(spv.spvDownloadBtn, "property valuation download");
        waitForElement(Waitcondition.toBeClickable, spv.spvDownloadBtn);

        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath,
                "Single Property Valuation - " + valuationName + ".xlsx");
        softAssert.assertTrue(fileExist,
                "file " + "Single Property Valuation - " + valuationName + ".xlsx" + " exist in download folder");
    }
    
    public void clickAddSource() throws Exception {
        waitForElement(spv.addSource);
        clickObjectJavascript(spv.addSource, "Add source");
    }

    public void verifyRevinewColValueUpdated(String revenues) throws Exception {
        // Get revenue value before
        waitForElement(spv.revinewLastCol);
        Actions a = new Actions(driver);
        a.doubleClick(spv.revinewLastCol).build().perform();
        String bData = spv.revinewLastCol.getText();
        System.out.println("Before value: " + bData);
        // Get revenue value after
        Thread.sleep(3000);
        a.sendKeys(revenues).sendKeys(Keys.RETURN.ENTER).perform();
        String aData = spv.revinewLastCol.getText();
        System.out.println("After value: " + aData);

        softAssert.assertNotEquals(bData, aData);
    }

    public void verifyBaseRateColValueUpdated(String baseRate) throws Exception {
        // Get base rate value before
        waitForElement(spv.baseRateCol);
        Actions a = new Actions(driver);
        a.doubleClick(spv.baseRateCol).build().perform();
        String bData = spv.baseRateCol.getText();
        System.out.println("Before value: " + bData);
        // Get base rate value after
        Thread.sleep(3000);
        a.sendKeys(baseRate).sendKeys(Keys.RETURN.ENTER).perform();
        String aData = spv.baseRateCol.getText();
        System.out.println("After value: " + aData);

        softAssert.assertNotEquals(bData, aData);
    }

}
