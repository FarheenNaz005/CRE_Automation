package com.newdemo.framework.pages.stats;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class TreppWirePage {

    private WebDriver driver = BaseSetupClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    //Tabs
    @FindBy(css = "")
    public WebElement volumeAmount;

    @FindBy(css = "#t_treppWire_0")
    public WebElement commentaryTab;

    @FindBy(css = "#t_treppWire_1")
    public WebElement spotlightTab;

    @FindBy(css = "#t_treppWire_2")
    public WebElement statisticTab;

    //top buttons
    @FindBy(css = "a.trepp-btn.archive-button")
    public WebElement archieveButton;

    @FindBy(xpath = "//span[@class='button-container']/a/span[text()='Download']")
    public WebElement downloadBtn;


    @FindBy(css = "#search_form #q")
    public WebElement searchBox;

    @FindBy(css = ".trepp-btn.search-button[type='submit']")
    public WebElement searchBtn;

    @FindBy(css = "div.button-container a.trepp-btn.send-button")
    public WebElement sendEmailButton;

    @FindBy(css = "#printHref")
    public WebElement commentaryPrintBtn;

    /*commnetary tab*/
    @FindBy(css = "#recent-commentary #featured-commentary")
    public WebElement featuredCommentary;

    @FindBy(css = "#boxes")
    public WebElement recentCommentary;

    @FindBy(css = "#boxes p a")
    public List<WebElement> recentCommentaryList;


    //send mail dialog
    @FindBy(css = "#send_mail_dialog form[name='send_email']")
    public WebElement sendMailDialog;

    @FindBy(css = "#ui-dialog-title-send_mail_dialog")
    public WebElement verifyMailBoxHeadr;


    @FindBy(css = "#recipientName")
    public WebElement recipientName;

    @FindBy(css = "#recipientEmail")
    public WebElement recipientEmail;

    @FindBy(xpath = "//td[@class='RowHead']/label[@for='recipientName']")
    public WebElement verifyRecipientName;

    @FindBy(xpath = "//input[@id='recipientEmail']/../../td/label")
    public WebElement verifyRecipientEmail;


    @FindBy(css = "#send_mail_dialog .send-button")
    public WebElement dialogSendButton;

    @FindBy(css = "#send_mail_dialog  .success.info")
    public WebElement successInfo;

    @FindBy(css = "#span.ui-icon.ui-icon-closethick")
    public WebElement closeMailBox;


    /*archieve listing page*/
    @FindBy(css = "a[href*='#Commentary']")
    public List<WebElement> archiveCommentaryList;

    @FindBy(css = "#archive_listings p label")
    public WebElement archiveListing;

    /* spotlight tab*/

    @FindBy(xpath = "//span[@class='button-container']/a/span[text()='Print']")
    public WebElement printBtn;

    @FindBy(css = "select#customLoanListID")
    public WebElement spotLightTopic;

    @FindBy(css = ".go-button")
    public WebElement displayBtn;

    @FindBy(css = "span a[href*='dealsumm.cgi']")
    public List<WebElement> spotlightList;

    @FindBy(css = "#daysToCheck")
    public WebElement filterTopicByTImeframe;

    @FindBy(xpath = "//label[@class='multiselect']/select/following-sibling::button")
    public WebElement filterTopicByCategory;


    /*******statistics Tab****/

    @FindBy(xpath = "//a/span[text()='US Statistics']")
    public WebElement uSStatistics;

    @FindBy(xpath = "//div/h1[text()='Property Type by Country']/../..")
    public WebElement propertyTypebyCountry;

    @FindBy(xpath = "//div/h1[text()='Originations']/../..")
    public WebElement originations;

    @FindBy(xpath = "//div/h1[text()='Near Term Maturities']/../..")
    public WebElement nearTermMaturities;

    @FindBy(xpath = "//div/h1[text()='Delinquency Status']/../..")
    public WebElement delinquencyStatus;

    @FindBy(xpath = "//div/h1[text()='Largest Known Delinquent Loans (90+ Days)']/../..")
    public WebElement largestKnownDelinguentLoans;

    @FindBy(xpath = "//div/h1[text()='Highest Delinquency % (30+ Days)']/../..")
    public WebElement hightestDelinquency;

    @FindBy(xpath = "//a/span[text()='European Statistics']")
    public WebElement europeanStatistics;

    @FindBy(css = "[onclick*='TreppWire.statistics']")
    public WebElement usStatistics;

    @FindBy(xpath = "//div/h1[text()='MSAs with Highest Delinquent Balance']/../..")
    public WebElement msashighestdelinguent;

    @FindBy(xpath = "//div/h1[text()='Deal Vintage']/../..")
    public WebElement dealVintage;

    @FindBy(xpath = "//div/h1[text()='Largest Delinquent Loans (60+ Days)']/../..")
    public WebElement largestDelinquent;

    @FindBy(xpath = "//div/h1[text()='US Spreads & Prices']/../..")
    public WebElement usSpreadprices;

    @FindBy(css = "embed[type='application/x-google-chrome-pdf']")
    public WebElement applicationPDF;


}
