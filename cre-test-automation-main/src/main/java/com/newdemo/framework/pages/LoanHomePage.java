package com.newdemo.framework.pages;

import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class LoanHomePage {

    private WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    @FindBy(xpath = ".//strong[contains(text(),'Atlanta')]")
    public WebElement homepageLoan;

    @FindBy(xpath = ".//h2[contains(text(),'Balance Sheet Lending Spreads (Trepp-i')]")
    public WebElement balanceSheetLendingSpreads;
    @FindBy(xpath = ".//a[@class='navbar-brand']//img")
    public WebElement homePageTreppLogo;

    /******* search module ********/
    @FindBy(xpath = ".//input[@placeholder='Enter a Property Name, Address, ID or Location']")
    public WebElement searchbox;
    @FindBy(xpath = ".//i[@class='fa fa-fw fa-lg fa-search']")
    public WebElement searchMaginifingglass;

    @FindBy(xpath = ".//button[contains(text(),'Advanced')]")
    public WebElement advancedbutton;

    @FindBy(css = "taui-multi-select#state button[ng-click='toggleSelect()']")
    public WebElement openStatedropdown;
    @FindBy(xpath = ".//tags-input[@id='countyState']//input")
    public WebElement countyInput;
    @FindBy(xpath = ".//tags-input[@id='zip']//input")
    public WebElement zipInput;

    @FindBy(css = "#dataSource button.btn-dropdown")
    public WebElement dataSourcedropdownButton;

    @FindBy(css = "#dataSource ul li button[ng-click='checkAll()']")
    public WebElement dataSourcedropdownCheckALLButton;

    @FindBy(css = "#dataSource ul li button[ng-click='uncheckAll()']")
    public WebElement dataSourcedropdownUncheckALLButton;

    @FindBy(xpath = ".//tab-heading[contains(text(),'Property')]")
    public WebElement property;

    @FindBy(xpath = ".//div[@class='row']//div[1]//div[4]//div[1]//div[2]//button[1]")
    public WebElement yesOperatingStatment;

    @FindBy(xpath = ".//button[contains(@class,'btn btn-primary ng-binding')]//i[contains(@class,'fa fa-search')]")
    public WebElement searchbuttonModal;

    @FindBy(xpath = "//button[@data-testid='advanced-search-button']")
    public WebElement searchModalButton;

    // dynamic variable
    public WebElement sourceCheckByName(String source) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//taui-multi-select[@id='dataSource']//ul/li/a[text()[contains(.,'" + source + "')]]")));
    }

    /**
     * Search module end
     **/

    @FindBy(xpath = ".//button[contains(text(),'Saved Searches')]")
    public WebElement savedsearches;

    /**
     * Loading element
     **/
    @FindBy(xpath = ".//trepp-http-activity-widget")
    public WebElement loading;

    /**
     * home page elements
     **/
    // Trends and Ranking
    @FindBy(xpath = ".//h2[contains(text(),'Trends & Rankings')]")
    public WebElement trendsNRankings;
    @FindBy(xpath = ".//dashboard-trends-and-rankings-tile//div[contains(@class,'market-name')]")
    public List<WebElement> trendsNRankingsMarketNames;

    // news module
    @FindBy(xpath = ".//label[contains(text(),'CRE News')]")
    public WebElement news;
    @FindBy(xpath = ".//label[contains(text(),'TreppTalk')]")
    public WebElement trepptalk;
    @FindBy(xpath = ".//label[contains(text(),'TreppWire')]")
    public WebElement newsTreppWire;
    @FindBy(xpath = ".//dashboard-news//a[contains(@href,'www.twitter.com')]")
    public List<WebElement> newsTwitterList;

    @FindBy(xpath = "//span[text()='Trepp Learning']")
    public WebElement trepplearning;
    @FindBy(xpath = ".//h2[contains(text(),'TreppWire Podcast')]")
    public WebElement treppwirepodcast;
    @FindBy(css = "tutorials-panel table")
    public WebElement treppTutorialsListTable;
    @FindBy(css = "tutorials-panel table td a")
    public List<WebElement> webElementList;
    @FindBy(xpath = ".//span[contains(text(),'multifamily')]")
    public WebElement multifamily;
    @FindBy(xpath = ".//span[contains(text(),'retail')]")
    public WebElement retail;
    @FindBy(xpath = ".//span[contains(text(),'office')]")
    public WebElement office;
    @FindBy(xpath = ".//span[contains(text(),'industrial')]")
    public WebElement industrial;

    @FindBy(xpath = ".//i[@title='Weekly Change']")
    public WebElement weeklychange; // List check 4 indexes
    @FindBy(xpath = ".//a[@title='As of 10/17/2019']")
    public WebElement weeklychangenumber;

    @FindBy(xpath = ".//p[@class='text-muted footnote']")
    public WebElement footnote; // Get text

    /**
     * My Work Panel
     **/
    @FindBy(xpath = ".//small[contains(text(),'My Work')]/..")
    public WebElement myWorkButton;

    /*
     * @FindBy(xpath = ".//span[text()[contains(.,'ALERTS')]]") public WebElement
     * alerts;
     */
    @FindBy(xpath = "//span[text()='Alerts']") 
    public WebElement alerts;

    @FindBy(xpath = ".//span[text()[contains(.,'PORTFOLIOS')]]")
    public WebElement portfolios;

    @FindBy(xpath = "//add-to-portfolio-icon//span[@ng-if='displayShortcut']")
    public WebElement portfolioshorcutbutton;

    @FindBy(xpath = "//tab-heading//i[@class='fa fa-plus']")
    public WebElement newportfoliotab;

    @FindBy(xpath = "//button[text()='Add']")
    public WebElement addportfoliobutton;

    @FindBy(css = "img[alt='Create Portfolio']")
    public WebElement addNewPortfolioLink;

    @FindBy(xpath = ".//span[contains(text(),'Deal Lists')]")
    public WebElement dealList;

    @FindBy(xpath = ".//span[text()[contains(.,'Valuations')]]")
    public WebElement valuations;

    /**
     * My Portfolio panel
     **/

    @FindBy(css = "span a.view-all-link")
    public WebElement viewAllportfolioLink;

    @FindBy(xpath = ".//small[contains(text(),'Stats')]/..")
    public WebElement stats;

    /**
     * more menu panel
     **/
    @FindBy(xpath = ".//small[contains(text(),'More')]/..")
    public WebElement more;
    @FindBy(xpath = ".//a[text()[contains(.,'Profile')]]")
    public WebElement profile;
    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement lastName;
    @FindBy(xpath = "//span[normalize-space()='Save']")
    public WebElement saveButton;
    @FindBy(xpath = "//p[@class='info']")
    public WebElement updateMessage;
    @FindBy(xpath = "//button[normalize-space()='Change Your Password']")
    public WebElement changePasswordButton;
    @FindBy(xpath = "//form[@name='changePasswordForm']")
    public WebElement changePasswordModel;
    @FindBy(xpath = "//button[@class='btn btn-default ng-scope']")
    public WebElement passwordModelCancelButton;
    @FindBy(xpath = ".//a[text()[contains(.,'Settings')]]")
    public WebElement settings;
    @FindBy(xpath = ".//a[text()[contains(.,'Knowledge Base')]]")
    public WebElement knowledgeBase;
    @FindBy(xpath = "..//a[text()[contains(.,'Contact Us')]]")
    public WebElement contactus;
    @FindBy(xpath = ".//a[text()[contains(.,'Apps')]]")
    public WebElement apps;
    @FindBy(xpath = ".//a[text()[contains(.,'Logout')]]")
    public WebElement logout;

    @FindBy(xpath = ".//li[@class='ng-scope active']")
    public WebElement firstsearchresult;

    @FindBy(xpath = ".//a[contains(text(),'Commentary')]")
    public WebElement commentary;

    @FindBy(xpath = ".//div[@class='col-md-6 ng-binding']")
    public WebElement statsmsa;
    @FindBy(xpath = ".//div[@class='col-md-5 list-data-value ng-binding']")
    public WebElement statsdelinquency;

    @FindBy(xpath = ".//span[contains(text(),'Company Exposure')]/..")
    public WebElement companyExposure;

    @FindBy(xpath = ".//span[contains(text(),'Tenants')]")
    public WebElement tenants;

    @FindBy(xpath = ".//span[contains(text(),'Hotels')]")
    public WebElement hotels;

    @FindBy(xpath = ".//a[contains(text(),'Hilton Worldwide')]")
    public WebElement hiltonWorldwide;

    @FindBy(xpath = ".//div[@class='hotel-stats-row animated bounceInUp']")
    public WebElement hiltonStats;

    @FindBy(xpath = ".//div[@class='statistic-tile-header']//span[@class='ng-binding ng-scope']")
    public WebElement hotelPerfomance;

    @FindBy(xpath = ".//a[contains(text(),'Macys')]")
    public WebElement tenantsMacys;

    @FindBy(xpath = ".//*[@class='nvd3-svg']")
    public WebElement macysDelinquencySummary;

    @FindBy(xpath = "//span[text()='Insights']")
    public WebElement insights;

    @FindBy(xpath = ".//*[@title='Sales Transaction Analysis'] ")
    public WebElement insightsframeloaded;

    @FindBy(xpath = ".//*[@class='text-box-visual']/p ")
    public WebElement insightsHighlights;

    @FindBy(xpath = ".//*[@class='ytp-title-text']//a")
    public WebElement trepplearningvideo;

    @FindBy(xpath = ".//a[@class='navbar-brand']//img")
    public WebElement treppLogo;

    @FindBy(xpath = ".//input[@placeholder='Enter a Property Name, Address, ID or Location']")
    public WebElement searchbar;

    // modal common
    @FindBy(css = "div.modal-dialog")
    public List<WebElement> openModalDialogs;

    @FindBy(css = "div.modal-dialog")
    public WebElement modalDialog;

    @FindBy(css = "div.trepp__modal__body")
    public WebElement newAdvanceModalDialog;

    @FindBy(css = "div.modal-dialog .close")
    public WebElement modalDialogClose;

    // Add new portfolio modal
    @FindBy(css = "div.modal-dialog input[name='displayPortfolioName']")
    public WebElement addNewPortfolioNameInput;
    @FindBy(css = "div.modal-dialog input[placeholder='Name Portfolio']")
    public WebElement addNewPortfolioNameInput1;
    @FindBy(css = "div.modal-dialog input[type='file']")
    public WebElement addNewPortfolioFileInput;

    @FindBy(css = "div.modal-dialog [ng-click*='uploadLoanPortfolioLoans']")
    public WebElement addNewPortfolioNextButton;

    @FindBy(css = "div.modal-dialog button[ng-click='finish()']")
    public WebElement addNewPortfolioFinishButton;

    @FindBy(css = "li[title=\"'Property'\"] li[ng-repeat='searchResult in searchResults']:nth-of-type(1) a add-to-portfolio-icon")
    public WebElement addToPortfolioIcon;

    // Saved Search modal
    public WebElement getSavedSearchLink(String savedSearchName) {
        return new WebDriverWait(BaseSetupClass.getDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//a[text()[contains(.,'" + savedSearchName + "')]]")));
    }

    public WebElement getSavedSearchDelete(String savedSearchName) {
        return new WebDriverWait(BaseSetupClass.getDriver(), 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[text()[contains(.,'"
                        + savedSearchName + "')]]/../../../..//button[text()[contains(.,'Delete')]]")));
    }

    @FindBy(css = "button[ng-click='$close(true)']")
    public WebElement saveSearchDeleteConfirm;
    
    @FindBy(xpath = "//h2[text()='TreppWire Podcast']")
    public WebElement treppWirePodcastHeader;
    @FindBy(xpath = "//button[@title='Play']")
    public WebElement videoPlayButton;
    @FindBy(xpath = "//button[@title='Pause']")
    public WebElement videoPauseButton;
    @FindBy(xpath = "//div[contains(@class,'sc-font-light')]")
    public WebElement prodCastList;
    @FindBy(xpath = "(//div[contains(@class,'sc-font-light')])[10]")
    public WebElement prodCastListFirst;
    @FindBy(xpath = "//iframe[@width='497px']")
    public WebElement prodCastListIframe;
    
    @FindBy(xpath = "//button[text()='Tenants']")
    public WebElement tenantsToggle;
    @FindBy(xpath = "(//div[@class='CompanyExposureList__record'])[1]")
    public WebElement firstHotel;
    @FindBy(xpath = "//a[text()='Hotel']")
    public WebElement viewAllHotelExposure;
    @FindBy(xpath = "//a[text()='Tenant']")
    public WebElement viewAllHotelTenant;
    @FindBy(xpath = "//span[text()='Hotel Snapshot']")
    public WebElement hotelReportsHeader;
    @FindBy(xpath = "//span[contains(text(), 'Tenant Report')]")
    public WebElement tenantReportHeader;
    @FindBy(xpath = "//span[contains(text(), 'Exposure Hotels')]")
    public WebElement viewAllHotelExposureHeader;
    @FindBy(xpath = "//span[contains(text(), 'Exposure Top 200 Tenants')]")
    public WebElement viewAllTenantExposureHeader;
    @FindBy(xpath = "//h4[text()='Government']")
    public WebElement goverment;
    
    @FindBy(xpath = "//input[@id='tab-TreppWire']")
    public WebElement treppWire;
    @FindBy(xpath = "//input[@id='tab-TreppTalk']")
    public WebElement treppTalk;
    @FindBy(xpath = "//input[@id='tab-CREDirect']")
    public WebElement creNews;
    @FindBy(xpath = "//a[@target='_blank']//em[contains(text(),'The Real Deal')]")
    public WebElement treppWireInArticleClickThru;
    @FindBy(xpath = "(//div[@class='TreppTalkCard'])[1]")
    public WebElement treppTalkContent;
    @FindBy(xpath = "//a[@href='https://crenews.com/']/img")
    public WebElement creNewsLogoImg;
    @FindBy(xpath = "(//div[@class='article-content']/div/a)[1]")
    public WebElement creNewsInArticleClickThru;
    @FindBy(xpath = "//img[@alt='The Real Deal Logo']")
    public WebElement treppWireClickThruNewTabPageLogo;
    @FindBy(xpath = "(//img[@alt='Commercial Real Estate Direct'])[1]")
    public WebElement creNewsClickThruNewTabPageLogo;
    @FindBy(xpath = "//div[@class='ot-sdk-container']")
    public WebElement creNewsInLineClickThruNewTabContent;
    
    @FindBy(xpath = "//button[text()='MSA']")
    public WebElement msaToggle;
    @FindBy(xpath = "//select[@id='rankBy']")
    public WebElement rankByDropdown;
    @FindBy(xpath = "//button[text()='List']")
    public WebElement listToggle;
    @FindBy(xpath = "(//div[@col-id='market']/div[@class='ag-react-container']/a)[1]")
    public WebElement listToggleState;

}
