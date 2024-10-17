package com.newdemo.framework.controller.portfolio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.stats.StatsPage;

public class StatsPageController extends ReuseableFunctions {
    StatsPage statsPage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public StatsPageController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        statsPage = PageFactory.initElements(driver, StatsPage.class);
    }

    /**
     * validation methods
     **/
    public void verifyTrendsLink() throws Exception {
        elementPresentorEnabled(statsPage.navTrendsLink, "Present", "Stats | Trends link");
    }

    public void verifyTreppWireLink() throws Exception {
        elementPresentorEnabled(statsPage.navTreppWireLink, "Present", "Stats | TreppWire link");
    }

    public void verifyPerformanceLink() throws Exception {
        elementPresentorEnabled(statsPage.navPerformanceLink, "Present", "Stats | Performance link");
    }

    public void verifyNewsLink() throws Exception {
        elementPresentorEnabled(statsPage.navNewsLink, "Present", "Stats | News link");
    }

    public void verifyNewIssuanceLink() throws Exception {
        elementPresentorEnabled(statsPage.navNewIssuanceLink, "Present", "Stats | New Issuance link");
    }

    public void verifyCompanyExposureLink() throws Exception {
        waitForElement(statsPage.navCompanyExposureLink);
        elementPresentorEnabled(statsPage.navCompanyExposureLink, "Present", "Stats | Company Exposure link");
    }

    /**
     * Action methods
     **/
    public void clickTrendsLink() throws Exception {
        clickObject(statsPage.navTrendsLink, "Stats | Trends link");
    }

    public void clickTreppWireLink() throws Exception {
        clickObject(statsPage.navTreppWireLink, "Stats | TreppWire link");
    }

    public void clickPerformanceLink() throws Exception {
        clickObject(statsPage.navPerformanceLink, "Stats | Performance link");
    }

    public void clickNewsLink() throws Exception {
        clickObject(statsPage.navNewsLink, "Stats | News link");
    }

    public void clickNewIssuanceLink() throws Exception {
        clickObject(statsPage.navNewIssuanceLink, "Stats | New Issuance link");
    }

    public void clickCompanyExposureLink() throws Exception {
        clickObject(statsPage.navCompanyExposureLink, "Stats | Company Exposure link");
    }

    public void clickMarketSnapshotPrintableButton() throws Exception {
        clickObjectJavascript(statsPage.marketSnapshotButton, "Stats | Market Snapshot Button");
    }

    public void clickMarketSnapshotExportButton(String fileName) throws Exception {
        waitForElement(Waitcondition.tobePresent, statsPage.exportButton);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(statsPage.exportButton));
        clickObjectJavascript(statsPage.exportButton, "Stats | Market Snapshot export Button");

        Thread.sleep(5000);
        new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(statsPage.exportButton));
        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");
    }

    public void verifyPerformanceTable() throws Exception {

        softAssert.assertTrue(statsPage.performanceReportTable.isDisplayed(),
                "Performance report table is visible on page.");
        // elementPresentorEnabled(statsPage.performanceReportTable, "Present",
        // "Performance Report Table");
    }

    public void selectCollateralUniverse(String value) throws Exception {
        waitForElement(Waitcondition.tobePresent, statsPage.collateralDropdown);
        selectFromDropDownVisibleText(statsPage.collateralDropdown, "Collateral Dropdown", value);

    }

    public void selectReportType(String value) throws Exception {
        waitForElement(Waitcondition.tobePresent, statsPage.reportTypeDropdown);
        selectFromDropDownVisibleText(statsPage.reportTypeDropdown, "Report Type Dropdown", value);

    }

    public void selectPeriod(String value) throws Exception {
        waitForElement(Waitcondition.tobePresent, statsPage.periodDropdown);
        selectFromDropDownVisibleText(statsPage.periodDropdown, "Period Displayed Dropdown", value);
    }

    public void clickDisplayButton() throws Exception {
        waitForElement(Waitcondition.toBeClickable, statsPage.displayButton);
        clickObjectJavascript(statsPage.displayButton, "Display Button");
    }

    public void verifyExcelDownload() throws Exception {
        clickObjectJavascript(statsPage.performanceDownloadButton, "Download button");
        waitUntilDonwloadCompleted();
    }

    public void verifyPDFDownload() throws Exception {
        clickObjectJavascript(statsPage.performancePrintButton, "Print button");
        Thread.sleep(2000);
        waitUntilDonwloadCompleted();
        switchToNewWindow();
        switchToOldWindow();
    }

}
