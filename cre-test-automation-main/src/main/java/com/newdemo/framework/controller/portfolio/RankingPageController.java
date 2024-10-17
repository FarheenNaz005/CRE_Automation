package com.newdemo.framework.controller.portfolio;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.portfolio.RankingPage;;

public class RankingPageController extends ReuseableFunctions {
    public LoadData data = null;
    RankingPage rankingPage = null;
    public SoftAssert softAssert = null;
    String propertyname;

    public RankingPageController(WebDriver objTempWebDriver) throws Exception {
        super(driver);
        rankingPage = PageFactory.initElements(driver, RankingPage.class);
    }

    public void clickonranking() throws Exception {
        waitForElement(rankingPage.rankicon);
        clickObjectJavascript(rankingPage.rankicon, "rank icon");

    }

    public void verifyRankingsSummary() throws Exception {
        waitForElement(rankingPage.rankingSummary);
        clickObjectJavascript(rankingPage.rankingSummary, "Ranking Summary");

        waitForElement(rankingPage.loanrankingsummary);
        elementPresentorEnabled(rankingPage.loanrankingsummary, "Present", "summary Data present");
        String data = getText(rankingPage.loanrankingsummary, " summary data ", "");
        if (!data.isEmpty()) {
            System.out.println("No data found");
            softAssert.assertTrue(StringUtils.isNotBlank(data));
        }

    }

    public void clickondetaileddata() throws Exception {
        waitForElement(rankingPage.detaileddata);
        clickObjectJavascript(rankingPage.detaileddata, "Detailed data");
    }

    public void verifydetaileddata() throws Exception {
        /////////////////////// for msa //////////////////////
        waitForElement(rankingPage.msaradiobuttion);
        clickObjectJavascript(rankingPage.msaradiobuttion, "MSA radio button");
        Thread.sleep(1000);
        clickObjectJavascript(rankingPage.displaybutton, "display button");
        WebDriverWait wait2 = new WebDriverWait(driver, 60);
        wait2.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        waitForElement(rankingPage.msadetaildata);
        elementPresentorEnabled(rankingPage.msadetaildata, "Present", "summary Data present");
        String data = getText(rankingPage.msadetaildata, " msa summary data ", "");
        if (!data.isEmpty()) {
            System.out.println("No data found");
            softAssert.assertTrue(StringUtils.isNotBlank(data));

        }
        ///////////////// for state///////////////////////////
        waitForElement(rankingPage.stateradiobuttion);
        clickObjectJavascript(rankingPage.stateradiobuttion, "State radio button");
        Thread.sleep(1000);
        clickObjectJavascript(rankingPage.displaybutton, "display button");
        wait2.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        waitForElement(rankingPage.statedetaildata);
        elementPresentorEnabled(rankingPage.statedetaildata, "Present", "summary Data present");
        String data1 = getText(rankingPage.statedetaildata, " state summary data ", "");
        if (!data1.isEmpty()) {
            System.out.println("No data found");
            softAssert.assertTrue(StringUtils.isNotBlank(data));

        }
        ///////////////// for region//////////////
        waitForElement(rankingPage.regionradiobuttion);
        clickObjectJavascript(rankingPage.regionradiobuttion, "Region radio button");
        Thread.sleep(1000);
        clickObjectJavascript(rankingPage.displaybutton, "display button");
        wait2.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        waitForElement(rankingPage.regiondetaildata);
        elementPresentorEnabled(rankingPage.regiondetaildata, "Present", "summary Data present");
        String data2 = getText(rankingPage.regiondetaildata, " Region summary data ", "");
        if (!data2.isEmpty()) {
            System.out.println("No data found");
            softAssert.assertTrue(StringUtils.isNotBlank(data));

        }

    }

    public void verifyExcelDownload() throws Exception {
        clickObject(rankingPage.downloadButton, "download button");
        waitUntilDonwloadCompleted();
    }

    public void verifyPDFDownload() throws Exception {
        clickObject(rankingPage.printButton, "Print button");
        switchToNewWindow();
        softAssert.assertTrue(rankingPage.pdfViewerRoot.isDisplayed(), "pdf viewer is visible on page.");
        switchToOldWindow();
    }

}
