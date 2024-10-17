package com.newdemo.framework.controller.portfolio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.portfolio.Snapshotpage;

public class SnapshotpageController extends ReuseableFunctions {
    public LoadData data = null;
    Snapshotpage snapshotpage = null;
    public SoftAssert softAssert = null;
    String propertyname;

    public SnapshotpageController(WebDriver objTempWebDriver) throws Exception {
        super(objTempWebDriver);
        snapshotpage = PageFactory.initElements(driver, Snapshotpage.class);
    }

    public void clickonsnapshot() throws Exception {
        waitForElement(snapshotpage.snapshoticon);
        clickObjectJavascript(snapshotpage.snapshoticon, "snapshot icon");

    }

    public void verifyTables() {
        softAssert.assertEquals(true, snapshotpage.table1.isDisplayed());

        softAssert.assertEquals(true, snapshotpage.table2.isDisplayed());

        softAssert.assertEquals(true, snapshotpage.table3.isDisplayed());

    }

    public void verifydownloads() throws Exception {
        clickObject(snapshotpage.downloadButton, "download button");
        waitUntilDonwloadCompleted();

    }

    public void verifyprint() throws Exception {

        clickObject(snapshotpage.printButton, "Print button");
        switchToNewWindow();
        softAssert.assertTrue(snapshotpage.pdfViewerRoot.isDisplayed(), "pdf viewer is not visible on page.");
        switchToOldWindow();
    }

}
