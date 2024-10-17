package com.newdemo.framework.controller.stats;


import java.util.List;
import java.util.stream.Collectors;

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
import com.newdemo.framework.pages.stats.CompanyExposurePage;
import com.newdemo.framework.pages.stats.HotelSnapshotPage;
import com.newdemo.framework.pages.stats.TenantReportPage;

public class CompanyExposurePageController extends ReuseableFunctions {
    CompanyExposurePage companyExposurePage = null;
    TenantReportPage tenantReportPage = null;
    HotelSnapshotPage hotelSnapshotPage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public CompanyExposurePageController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        companyExposurePage = PageFactory.initElements(driver, CompanyExposurePage.class);
        tenantReportPage = PageFactory.initElements(driver, TenantReportPage.class);
        hotelSnapshotPage = PageFactory.initElements(driver, HotelSnapshotPage.class);
    }

    /**
     * validation methods
     **/
    //CompanyExposure Page
    public void verifyDownloadButton() throws Exception {
        elementPresentorEnabled(companyExposurePage.downloadButton, "Present", "download button");
    }

    public void verifyExposureTable() throws Exception {
        waitForElement(companyExposurePage.exposureTable);
        softAssert.assertTrue((companyExposurePage.allRowsOfTable.size() > 0), "rows number of exposure table should be atleast 1");
    }

    public void verifyHotelSearch(String name) throws Exception {
        waitForElement(companyExposurePage.hotelSearchbar);
        clearNTypeValue(companyExposurePage.hotelSearchbar, name, "hotel search bar");
        List<String> hotels = companyExposurePage.allHotelSnapshotLink.stream().map(ele -> ele.getAttribute("innerText")).collect(Collectors.toList());

        for (String hotel : hotels) {
            softAssert.assertTrue(hotel.contains(name));
        }
    }

    public void verifyPropertyListLink() throws Exception {
        waitForElement(companyExposurePage.propertyListLink);
        elementPresentorEnabled(companyExposurePage.propertyListLink, "Present", "Property List Link");
    }

    public void verifyTenantSearch(String name) throws Exception {
        waitForElement(companyExposurePage.tanentSearchbar);
        clearNTypeValue(companyExposurePage.tanentSearchbar, name, "tenant search bar");
        List<String> tenants = companyExposurePage.allTenantReportLink.stream().map(ele -> ele.getAttribute("innerText")).collect(Collectors.toList());

        for (String tenant : tenants) {
            softAssert.assertTrue(tenant.contains(name));
        }
    }

    //TenantReportPage
    public void verifyTenantReportLoad() throws Exception {
        waitForElement(tenantReportPage.tenantTitle);
        elementPresentorEnabled(tenantReportPage.tenantTitle, "Present", "tenantTitle");
    }

    public void verifyTenantOutstandingLoanMap() throws Exception {
        elementPresentorEnabled(tenantReportPage.outstandingLoanMap, "Present", "teants outstanding LoanMap");
    }

    public void verifyTenantDeliquencySummary() throws Exception {
        elementPresentorEnabled(tenantReportPage.tenantDeliquencySummary, "Present", "teants DeliquencySummary");
    }

    public void verifyTenantRecentTransactionTable() throws Exception {
        elementPresentorEnabled(tenantReportPage.recentTransactionTable, "Present", "teants recent Transaction Table");
    }

    public void verifyTenantOutstandingDebtSummary() throws Exception {
        elementPresentorEnabled(tenantReportPage.outstandingDebtSummary, "Present", "teants outstanding Debt Summary");
    }

    public void verifyTenantCMBSDealExposure() throws Exception {
        elementPresentorEnabled(tenantReportPage.cmbsdealexposure, "Present", "teants CMBS Deal Exposure");
    }

    public void verifyPropertyListLinkTenantReport() throws Exception {
        elementPresentorEnabled(tenantReportPage.propertyListLink, "Present", "teants Property List Link");
    }

    //hotel snapshot page
    public void verifyHotelSnapshotLoad() throws Exception {
        waitForElement(hotelSnapshotPage.navbarBrand);
        elementPresentorEnabled(hotelSnapshotPage.navbarBrand, "Present", "hotel snapshot title");
    }

    public void verifyHotelOverviewPanel() throws Exception {
        waitForElement(hotelSnapshotPage.hotelOverview);
        elementPresentorEnabled(hotelSnapshotPage.hotelOverview, "Present", "Franchise Overview panel");
    }

    public void verifyHotelPerformanceSummaryPanel() throws Exception {
        elementPresentorEnabled(hotelSnapshotPage.hotelPerformanceSummary, "Present", "hotel Performance Summaryw panel");
    }

    public void verifyHotelRecentTransactionTable() throws Exception {
        elementPresentorEnabled(hotelSnapshotPage.recentTransactionTable, "Present", "recent Transaction Table panel");
    }

    public void verifyHotelUnderWritingPanel() throws Exception {
        elementPresentorEnabled(hotelSnapshotPage.hotelUnderWriting, "Present", "hotel UnderWriting panel");
    }

    public void verifyHotelCMBSDealExposurePanel() throws Exception {
        elementPresentorEnabled(hotelSnapshotPage.cmbsdealexposure, "Present", "CMBS Deal Exposure panel");
    }

    public void verifyHotelOperatingPerformancePanel() throws Exception {
        elementPresentorEnabled(hotelSnapshotPage.hotelOperatingPerformance, "Present", "hotel Operating Performance panel");
    }

    public void verifyBrandDropdownHotelSnapshot() throws Exception {
        elementPresentorEnabled(hotelSnapshotPage.brandSelect, "Present", "brand dropdown");
    }

    public void verifyMarketDropdownHotelSnapshot() throws Exception {
        elementPresentorEnabled(hotelSnapshotPage.marketSelect, "Present", "market dropdown");
    }

    /**
     * action methods
     **/
    public void clickPropertyList() throws Exception {
        clickObject(companyExposurePage.propertyListLink, "Property List Link");
    }

    //Hotel Snapshot Page
    public void clickSwitchHotelButton() throws Exception {
        elementPresentorEnabled(companyExposurePage.switchHotelButton, "Present", "hotel button");
        clickObject(companyExposurePage.switchHotelButton, "switch hotel button");
    }

    public void selectViewByHotel(String choice) throws Exception {
        clickObject(companyExposurePage.hotelViewByDropdown, "hotel select view by dropdown");
        clickObject(companyExposurePage.getHotelViewChoice(choice), choice + " choice for hotel table view");
    }

    public void clickFirstHotelLink() throws Exception {
        WebElement link = companyExposurePage.hotelSnapshotLink("");
        clickObject(link, "link for hotel snapshot of first row");
    }

    public void selectBrandHotelSnapshot(String name) throws Exception {
        clickObject(hotelSnapshotPage.brandSelect, "brand dropdown clicked");
        clickObjectJavascript(hotelSnapshotPage.brandMarketChoice(name), name);
    }

    public void selectMarketHotelSnapshot(String name) throws Exception {
        waitForElement(Waitcondition.toBeClickable, hotelSnapshotPage.marketSelect);
        clickObject(hotelSnapshotPage.marketSelect, "Market dropdown clicked");
        clickObject(hotelSnapshotPage.brandMarketChoice(name), name);
    }

    public void verifyDownloadSnapshot(String fileName) throws Exception {
        elementPresentorEnabled(hotelSnapshotPage.download, "present", "Download button");
        clickObject(hotelSnapshotPage.download, "download all button");

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(hotelSnapshotPage.download));

        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");
    }

    //Tenant Report Page
    public void clickSwitchTenantsButton() throws Exception {
        waitForElement(companyExposurePage.switchTenantButton);
        elementPresentorEnabled(companyExposurePage.switchTenantButton, "Present", "tenants button");
        clickObject(companyExposurePage.switchTenantButton, "switch tenants button");
    }

    public void clickFirstTenantReport() throws Exception {
        WebElement link = companyExposurePage.tenantReportLink("");
        clickObject(link, "link for tenant report of first row");
    }

    public void verifyDownloadTenantReport(String fileName) throws Exception {
        elementPresentorEnabled(tenantReportPage.download, "present", "Download button");
        clickObjectJavascript(tenantReportPage.download, "download all button");

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(tenantReportPage.download));

        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");
    }

    public void clickPropertyListTenantReport() throws Exception {
        clickObject(tenantReportPage.propertyListLink, "Property List Link");
    }
    
    public void clickPropertyList1() throws Exception {
        waitForElement(companyExposurePage.propertyListBtn);
        clickObject(companyExposurePage.propertyListBtn, "Property List");
    }
    
    public void clickFirstPropertyNameOnHotelReport() throws Exception {
        waitForElement(companyExposurePage.selectFirstPropertyName);
        scrollIntoView(companyExposurePage.selectFirstPropertyName, "First property");
        clickObjectJavascript(companyExposurePage.selectFirstPropertyName, "First property");
    }
    
    public void clickDelinquencySummaryGraph() throws Exception {
        waitForElement(companyExposurePage.chart);
        moveToElement(companyExposurePage.chart, "Delinquency Summary chart");
        clickObject(companyExposurePage.chart, "Delinquency Summary chart");
    }
    
    public void selectOperatingPerformanceYear() throws Exception {
        waitForElement(companyExposurePage.operatingPerformanceDropdown);
        scrollIntoView(companyExposurePage.operatingPerformanceDropdown, "Operating Performance Dropdown");
        clickObjectJavascript(companyExposurePage.operatingPerformanceDropdown, "Operating performance");
        waitForElement(companyExposurePage.dropdownValue);
        clickObjectJavascript(companyExposurePage.dropdownValue, "Dropdown Value");
    }
    
    public void verifyselectOperatingPerformanceTable(String graphHeader) throws Exception {
        waitForElement(companyExposurePage.revenuesHeader);
        softAssert.assertTrue(companyExposurePage.revenuesHeader.isDisplayed(), "Revenue col not present.");
        List<WebElement> data = companyExposurePage.operatingPerformancedata;
        wait.until(ExpectedConditions.visibilityOfAllElements(data));
        softAssert.assertTrue(!data.isEmpty(), "present.");
        System.out.println("Number of data: " + data.size());
        waitForElement(companyExposurePage.operatingPerformanceGraph);
        softAssert.assertTrue(companyExposurePage.operatingPerformanceGraph.isDisplayed(),
                "Operating Performance Graph");
        WebElement gHeader = driver.findElement(By.xpath("//h3[contains(text(), '" + graphHeader + "')]"));
        softAssert.assertTrue(gHeader.isDisplayed(), "Graph header not present.");
    }

    public void verifyTogglePresent() throws InterruptedException {
        elementPresentorEnabled(companyExposurePage.roomToggle, "Present", "roomToggle");
        elementPresentorEnabled(companyExposurePage.revToggle, "Present", "revToggle");
        //elementPresentorEnabled(companyExposurePage.opExToggle, "Present", "opExToggle");
    }

    public void verifyRoomTogglePopulateData() throws Exception {
        clickObjectJavascript(companyExposurePage.revToggle, "revToggle");
        verifyselectOperatingPerformanceTable("Room Revenues % of Revenue");
    }

    public void verifyRevTogglePopulateData(String graphHeader) throws Exception {
        clickObjectJavascript(companyExposurePage.opExToggle, "opExToggle");
        waitForElement(companyExposurePage.opExHeader);
        softAssert.assertTrue(companyExposurePage.opExHeader.isDisplayed(), "opExHeader col header not present.");
        List<WebElement> data = companyExposurePage.operatingPerformancedata;
        softAssert.assertTrue(!data.isEmpty(), "present.");
        System.out.println("Number of data: " + data.size());
        waitForElement(companyExposurePage.operatingPerformanceGraph);
        softAssert.assertTrue(companyExposurePage.operatingPerformanceGraph.isDisplayed(),
                "Operating Performance Graph");
        WebElement gHeader = driver.findElement(By.xpath("//strong[contains(text(), '" + graphHeader + "')]"));
        softAssert.assertTrue(gHeader.isDisplayed(), "Graph header not present.");
    }

    public void verifyCompareToFeature(String searchData) throws Exception {
        clearNTypeValue(companyExposurePage.hotelSearch, searchData, searchData);
        WebElement searchResult = driver.findElement(
                By.xpath("//li[@data-testid='typeahead-listbox-item']/div/span[text()='" + searchData + "']"));
        waitForElement(searchResult);
        clickObjectJavascript(searchResult, searchData);
        wait.until(ExpectedConditions.invisibilityOf(companyExposurePage.isLoadingPage));
        clickObjectJavascript(companyExposurePage.roomToggle, "roomToggle");
        Thread.sleep(5000);

        // Hotel names and properties column(s) load
        int colCount = companyExposurePage.hotelAndPropertyCol.size();
        softAssert.assertEquals(colCount, 5, "Column count does not match after comparing two hotels.");
        System.out.println("Total column present: " + colCount);

        // Compare 2 hotel graph bar present
        List<WebElement> bar1Hotel = companyExposurePage.bar1;
        int barCount = bar1Hotel.size();
        softAssert.assertEquals(barCount, 6, "Hotels bar(s) not present.");
    }
    
}
