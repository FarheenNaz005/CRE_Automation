package com.newdemo.framework.controller.loan;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.loan.PropertyDetailPageOld;

public class PropertyDetailPageOldController extends ReuseableFunctions {
    public PropertyDetailPageOld propertyDetailPage = null;
    LoadData data = null;
    SoftAssert softAssert = null;

    public PropertyDetailPageOldController(WebDriver driver) throws Exception {
        super(driver);


        propertyDetailPage = PageFactory.initElements(driver, PropertyDetailPageOld.class);
    }

    public void verifyPropertyName(String propertyName) throws Exception {
        //waitForElement(propertyDetailPage.OldPageTitle);
        //softAssert.assertEquals(getText(propertyDetailPage.OldPageTitle, "page title", "innerText"), "Loan Detail", "Page title should be Loan Detail");
        //softAssert.assertEquals(getText(propertyDetailPage.subTitle, "page sub title", "innerText"), propertyName, "Page sub title should be: " + propertyName);
        printPageLoadTime();
    }

    public void verifyloanSummary() throws Exception {
        elementPresentorEnabled(propertyDetailPage.dealSummaryTable, "Present", "tables on LoanDetails");
        List<String> data = propertyDetailPage.dealSummaryTableValues
                .stream().map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("Loan Data:" + " " + data);
        softAssert.assertTrue(!data.isEmpty(), "Data on the details page not loaded");
    }

    public void verifyTable(String tableTitle) throws Exception {
        softAssert.assertTrue(propertyDetailPage.getTableByTitle(tableTitle).isDisplayed(), tableTitle + " is not displayed on the page");
        List<String> data = propertyDetailPage.getTableValuesByTitle(tableTitle)
                .stream().map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("Header Data:" + " " + data);
        softAssert.assertTrue(data.isEmpty(), tableTitle + ": values are not populated");

    }

    public void verifyExcelDownload() throws Exception {
        clickObject(propertyDetailPage.downloadButton, "download button");
        waitUntilDonwloadCompleted();
    }

    public void verifyPDFDownload() throws Exception {
        waitForElement(propertyDetailPage.printButton);
        clickObject(propertyDetailPage.printButton, "Print button");
        switchToNewWindow();
        softAssert.assertTrue(propertyDetailPage.pdfViewerRoot.isDisplayed(), "pdf viewer is not visible on page.");
        switchToOldWindow();
    }

    public void verifydealTable(String string) throws InterruptedException {
        Thread.sleep(10000);
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + string + "')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return arguments[0].scrollIntoView({inline:'center'});", element);
        softAssert.assertTrue(element.isDisplayed(), "table is not visible on page.");

    }

    public void verifyTableNotPresent(String string) throws InterruptedException {
        Thread.sleep(10000);
        boolean flag = true;
        try {
            WebElement element = driver.findElement(By.xpath("//strong[contains(text(),'" + string + "')]"));
            if (!element.isDisplayed()) {
                flag = false;
            }

        } catch (Exception e) {
            flag = false;
        }
        softAssert.assertFalse(flag, " table are showing" + string);

    }
}
