package com.newdemo.framework.controller.stats;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.stats.TreppWirePage;

public class TreppWirePageController extends ReuseableFunctions {
    TreppWirePage treppWirePage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public TreppWirePageController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        treppWirePage = PageFactory.initElements(driver, TreppWirePage.class);
    }

    /****************
     * Verify Commentary Tab
     *
     * @throws Exception
     ************/
    public void verifyCommentaryTab() throws Exception {

        waitForElement(treppWirePage.commentaryTab);
        waitForElement(treppWirePage.featuredCommentary);
        waitForElement(treppWirePage.recentCommentary);
        softAssert.assertTrue(treppWirePage.sendEmailButton.isDisplayed(), "Send button is not visible");
        softAssert.assertTrue(treppWirePage.archieveButton.isDisplayed(), "Archieve button is not visible");
        softAssert.assertTrue(treppWirePage.commentaryPrintBtn.isDisplayed(), "Print button is not visible");
        softAssert.assertTrue(treppWirePage.searchBox.isDisplayed(), "search box is not visible");
        softAssert.assertTrue(treppWirePage.recentCommentaryList.size() > 0, "search box is not visible");


    }

    /****** go To Spotlight Tab ****/
    public void openSpotlightTab() throws Exception {
        waitForElement(treppWirePage.spotlightTab);
        clickObject(treppWirePage.spotlightTab, "In the Spotlight");

    }

    /****** Select Topic and click Display And Verify List ****/
    public void selectTopicAndClickOnDisplay() throws Exception {
        waitForElement(treppWirePage.spotLightTopic);

        selectFromDropDownVisibleText(treppWirePage.spotLightTopic, "Washington Prime CMBS Exposure",
                "Washington Prime CMBS Exposure");
        clickObject(treppWirePage.displayBtn, "Display button");
        softAssert.assertTrue(treppWirePage.spotlightList.size() > 0, "List is Not visible In the SpotLight tab");
    }

    /****** Open Statistics ****/
    public void openStatisticsTab() throws Exception {

        waitForElement(treppWirePage.statisticTab);
        clickObject(treppWirePage.statisticTab, "Statistics Tab");

    }

    /****** Verify Statistics Tab ******************/
    public void verifyStatisticsTables() throws Exception {

        waitForElement(treppWirePage.propertyTypebyCountry);
        waitForElement(treppWirePage.originations);
        waitForElement(treppWirePage.nearTermMaturities);
        waitForElement(treppWirePage.delinquencyStatus);
        waitForElement(treppWirePage.largestKnownDelinguentLoans);

        clickObject(treppWirePage.usStatistics, "US Statistics");

        waitForElement(treppWirePage.delinquencyStatus);
        waitForElement(treppWirePage.hightestDelinquency);
        waitForElement(treppWirePage.msashighestdelinguent);
        waitForElement(treppWirePage.dealVintage);
        waitForElement(treppWirePage.largestDelinquent);
        waitForElement(treppWirePage.usSpreadprices);


    }

    /******** Verify and click Download ********/
    public void verifyDownload(String fileName) throws Exception {

        waitForElement(Waitcondition.toBeVisible, treppWirePage.downloadBtn);
        //waitForElement(treppWirePage.downloadBtn);
        clickObject(treppWirePage.downloadBtn, "Download");
        waitUntilDonwloadCompleted();

        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");
    }

    /******* click print and Verify *************/
    public void verifyPrint() throws Exception {

        clickObject(treppWirePage.printBtn, "Print Button");
        ArrayList<String> windowTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowTab.get(1));
        softAssert.assertTrue(treppWirePage.applicationPDF.isDisplayed(), "pdf viewer is visible on page.");

    }

    /*******VERIFY SEND EMAIL*******/
    public void verifySendEmail() throws Exception {

        clickObject(treppWirePage.sendEmailButton, "click || Send Email Button");
        softAssert.assertTrue(treppWirePage.sendMailDialog.isDisplayed(), "Send Mail Box is Not Visible");
        softAssert.assertTrue(treppWirePage.verifyMailBoxHeadr.isDisplayed(), "Mail box Headr Text Is Not Visible");
        softAssert.assertEquals(treppWirePage.verifyRecipientName.getText(), "Recipient's Name:",
                "Recipient Name is not Visible");
        softAssert.assertEquals(treppWirePage.verifyRecipientEmail.getText(), "Recipient's Email:",
                "Recipient Email is not Visible");
        typeValue(treppWirePage.recipientName, "Enter || Recipient Name", "Test");
        typeValue(treppWirePage.recipientEmail, "Enter || Recipient Email", "intTest@gmail.com");
        clickObject(treppWirePage.dialogSendButton, "Click || Dialog Send Button");

        /*
         * new WebDriverWait(driver, 10)
         * .until(ExpectedConditions.visibilityOfElementLocated(By.
         * cssSelector("#send_mail_dialog .success.info")));
         */
        waitForElement(treppWirePage.successInfo);
        //waitForElement(waitCondition.toBeVisible, treppWirePage.successInfo);
        softAssert.assertTrue(treppWirePage.successInfo.getText().contains("Your Message was sent."));
        //clickObject(treppWirePage.closeMailBox," click || close Mail Box");
        //waitForElement(waitCondition.toBeInvisible, treppWirePage.sendMailDialog);

    }

    /*******VERIFY ARCHIEVE ********/
    public void verifyArchieve() throws Exception {
        Thread.sleep(2000);
        waitForElement(treppWirePage.archieveButton);
        //waitForElement(waitCondition.toBeClickable, treppWirePage.archieveButton);
        clickObject(treppWirePage.archieveButton, "click || Archieve Button");
        new WebDriverWait(driver, 120)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#archive_listings p label")));
        waitForElement(Waitcondition.toBeVisible, treppWirePage.archiveListing);
        softAssert.assertTrue(treppWirePage.archiveCommentaryList.size() > 0, "List is Not Visible in Archieve");


    }
}
