package com.newdemo.framework.controller.stats;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.stats.NewIssuancePage;

public class NewIssuanceController extends ReuseableFunctions {
    NewIssuancePage newIssuancePage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public NewIssuanceController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        newIssuancePage = PageFactory.initElements(driver, NewIssuancePage.class);
    }

    /**
     * validation methods
     **/
    public void verifyVolumeAmount() throws Exception {
        elementPresentorEnabled(newIssuancePage.volumeAmount, "Present", "volume amount");
        System.out.println(getText(newIssuancePage.volumeAmount, "volume amount", "innerText"));
    }

    public void verifyDealCount() throws Exception {
        elementPresentorEnabled(newIssuancePage.dealsCount, "Present", "deal count");
        System.out.println(getText(newIssuancePage.dealsCount, "deal count", "innerText"));
    }

    public void verifyDownloadButton() throws Exception {
        waitForElement(newIssuancePage.downloadButton);
        elementPresentorEnabled(newIssuancePage.downloadButton, "Present", "download button");
    }

    public void verifyTransanctionTab() throws Exception {

        softAssert.assertTrue(newIssuancePage.issueDealList.size() > 1);
        waitForElement(newIssuancePage.newIssueSearch);

        WebElement dealId = newIssuancePage.issueDealList.get(0);
        clearNTypeValue(newIssuancePage.newIssueSearch, dealId.getText(), "deal id");

        softAssert.assertEquals(newIssuancePage.issueDealList.size(), 1);
    }

    public void verifyNewIssueTrendsTab() throws Exception {

        // waitForElement(newIssuancePage.riskRetentionTypes);
        elementPresentorEnabled(newIssuancePage.yearlyVolumeComparison, "Present", "yearly Volume Comparison");
        elementPresentorEnabled(newIssuancePage.quaterlyNewIssueVolume, "Present", "quaterly New Issue Volume");
        elementPresentorEnabled(newIssuancePage.monthlyAverageLTV, "Present", "monthly Average LTV");
        elementPresentorEnabled(newIssuancePage.monthlyAverageDebtYield, "Present", "monthly Average Debt Yield");
        elementPresentorEnabled(newIssuancePage.monthlyAverageDSCR, "Present", "monthly Average DSCR");
        // elementPresentorEnabled(newIssuancePage.monthlyAverageIOComposition,
        // "Present", "monthly Average IO Composition");
    }

    public void verifySpreadsNRatesTab() throws Exception {

        waitForElement(newIssuancePage.newIssueSpreadTreppWire);
        elementPresentorEnabled(newIssuancePage.newIssueBondSpreadChart, "Present",
                "U.S. Bond Initial Pricing Spreads (2019)");
        elementPresentorEnabled(newIssuancePage.newIssueSpreadTreppWire, "Present", "TreppWir");
        elementPresentorEnabled(newIssuancePage.newIssueCMBSSpreadChart, "Present", "Secondary Market Conduit Spreads");
        elementPresentorEnabled(newIssuancePage.newIssueCMBXSpreadChart, "Present", "CMBX Spreads");
        elementPresentorEnabled(newIssuancePage.newIssueTreppiSpreadChart, "Present",
                "Balance Sheet Lending Spreads (Trepp-)");
        elementPresentorEnabled(newIssuancePage.newIssueTreasuryYieldChart, "Present", "Treasury Yields");
    }

    public void verifySpreadNRatesDisableWithAllPopulation() throws Exception {
        clickObject(newIssuancePage.populationDropdown, "population dropdown");
        waitForElement(newIssuancePage.populationDropdownChoice("All"));
        clickObject(newIssuancePage.populationDropdownChoice("All"), "All in population dropdown");

        softAssert.assertTrue(newIssuancePage.newIssueTrendsTabLink.getAttribute("href") == null);
        softAssert.assertTrue(newIssuancePage.spreadsNRatesTabLink.getAttribute("href") == null);
    }

    public void verifyDownloadReport(String fileName) throws Exception {
        elementPresentorEnabled(newIssuancePage.downloadButton, "present", "Download button");
        clickObject(newIssuancePage.downloadButton, "download button");
        Thread.sleep(1000);
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button i.fa-download")));

        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");
    }

    /**
     * action methods
     **/
    public void openTransanctionTab() throws Exception {
        elementPresentorEnabled(newIssuancePage.transactionTabLink, "Present", "transaction tab link");
        clickObject(newIssuancePage.transactionTabLink, "transaction tab");
        waitForElement(newIssuancePage.newIssueSearch);
    }

    public void openNewIssueTrendsTab() throws Exception {
        elementPresentorEnabled(newIssuancePage.newIssueTrendsTabLink, "Present", "new Issuance link");
        clickObject(newIssuancePage.newIssueTrendsTabLink, "New Issuance tab");
    }

    public void openSpreadsNRatesTab() throws Exception {
        elementPresentorEnabled(newIssuancePage.spreadsNRatesTabLink, "Present", "spread and rates tab ");
        clickObject(newIssuancePage.spreadsNRatesTabLink, "Spread and Rates tab");
        // waitForElement(newIssuancePage.newIssueSpreadTreppWire);
    }
    
    public void verifyCreditAndPricingView() throws Exception {
        waitForElement(newIssuancePage.wacCol);
        elementPresentorEnabled(newIssuancePage.wacCol, "Present", "WAC col");
        waitForElement(newIssuancePage.wacColFirstVal);
        elementPresentorEnabled(newIssuancePage.wacColFirstVal, "Present", "WAC col first value");
        
        clickObject(newIssuancePage.pricingViewBtn, "Pricing View toggle");
        
        waitForElement(newIssuancePage.pricingCol);
        elementPresentorEnabled(newIssuancePage.pricingCol, "Present", "Pricing col");
        waitForElement(newIssuancePage.pricingColFirstValue);
        elementPresentorEnabled(newIssuancePage.pricingColFirstValue, "Present", "Pricing col first value");
    }
    
    public void verifyPopulationDropdown() throws Exception {
        waitForElement(newIssuancePage.newIssuancePopulationDropdown);
        selectFromDropDownVisibleText(newIssuancePage.newIssuancePopulationDropdown, "Agency CMBS", "Agency CMBS");
    }
    
    public void verifyDealTypeDropdown() throws Exception {
        waitForElement(newIssuancePage.dealType);
        selectFromDropDownVisibleText(newIssuancePage.dealType, "Deal Type", "Ginnie Mae");
    }
    
    public void verifyVintageeDropdown() throws Exception {
        waitForElement(newIssuancePage.vintage);
        selectFromDropDownVisibleText(newIssuancePage.vintage, "vintage", "2021");
    }
    
    public void verifyDataLoads() throws Exception {
        waitForElement(newIssuancePage.dataRow);
        softAssert.assertTrue(newIssuancePage.dataRow.isDisplayed(), "Data not displayed after selecting Agency CMBS from population dropdown");
    }
}
