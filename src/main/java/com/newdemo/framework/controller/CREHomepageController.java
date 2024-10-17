package com.newdemo.framework.controller;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.LoanHomePage;
import com.newdemo.framework.pages.LoginPage;
import com.newdemo.framework.pages.loan.LoansearchPropertyPage;
import com.newdemo.framework.pages.stats.StatsPage;

public class CREHomepageController extends ReuseableFunctions {
    LoanHomePage loanHomePage = null;
    LoginPage login = null;
    LoadData data = null;
    LoansearchPropertyPage loanDetails = null;
    StatsPage statsPage = null;
    public SoftAssert softAssert = null;
    File file1 = new File(System.getProperty("user.dir") + "/Config/comparescript.py");
    File newfile = new File(System.getProperty("user.dir") + "/Downloads/difference.csv");
    File oldfile = new File(System.getProperty("user.dir") + "/Downloads/Test1.csv");

    public CREHomepageController(WebDriver driver) throws Exception {
        super(driver);
        loanHomePage = PageFactory.initElements(driver, LoanHomePage.class);
        login = PageFactory.initElements(driver, LoginPage.class);
        statsPage = PageFactory.initElements(driver, StatsPage.class);
    }

    public void waitForHomepageLoan() throws Exception {
        try {
            waitForElementWithRefresh(loanHomePage.advancedbutton);

        } catch (Exception objException) {
            waitForElement(login.loginButton);
            if (login.loginButton.isDisplayed()) {
                launchURL();
                clickLoginButton();
                waitForUserName();
                clickusername();
                typeEmail();
                clickpassword();
                typePassword();
                clickSignin();
                waitForLoan();
                clickLoan();
                waitForLoading();
                waitForHomepageLoan();

            } else {

                throw new NoSuchElementException(strErrorMsg);

            }

        }
    }

    public void launchURL() throws Exception {
        launchURL(data.strDTURL);

        // runpython(file1);
    }

    public void clickLoginButton() throws Exception {
        clickObject(login.loginButton, "Login Button");
    }

    public void typeEmail() throws Exception {
        typeValue(login.userName, "userName", data.strDTUserName);
    }

    public void typePassword() throws Exception {
        typeValue(login.password, "password", data.strDTPassword);
    }

    public void waitForUserName() throws Exception {
        waitForElement(login.userName);
    }

    public void clickSignin() throws Exception {
        clickObject(login.login, "SignIn");
    }

    public void clickusername() throws Exception {
        clickObject(login.userName, "username ");
    }

    public void clickpassword() throws Exception {
        clickObject(login.password, "password ");

    }

    public void waitForLoan() throws Exception {
        try {
            if (login.loan.isDisplayed()) {
                waitForElementWithRefresh(login.loan);
            }
        } catch (Exception e) {
        }

    }

    public void clickLoan() throws Exception {
        try {
            if (login.loan.isDisplayed()) {
                clickObject(login.loan, "Loan");
            }
        } catch (Exception e) {

        }

    }

    /**
     * Advance Search start
     **/
    public void clickAdvanced() throws Exception {
        waitForElement(loanHomePage.advancedbutton);
        clickObject(loanHomePage.advancedbutton, "Advanced search ");
        waitForElement(loanHomePage.modalDialog);
    }

    public void clickNewAdvanced() throws Exception {
        waitForElement(loanHomePage.advancedbutton);
        clickObject(loanHomePage.advancedbutton, "Advanced search ");
        waitForElement(loanHomePage.newAdvanceModalDialog);
    }

    public void selectState(String state) throws Exception {
        waitForElement(loanHomePage.openStatedropdown);
        Thread.sleep(3000);
        clickObject(loanHomePage.openStatedropdown, "Open State DropDown ");
        Thread.sleep(2000);
        WebElement stateCheckbox = driver.findElement(
                By.xpath(".//taui-multi-select[@id='state']//ul/li/a[text()[contains(.,'" + state + "')]]"));
        waitForElement(stateCheckbox);
        Thread.sleep(1000);
        clickObject(stateCheckbox, "Select " + state);
        clickObject(loanHomePage.openStatedropdown, "Close State DropDown ");
    }

    public void selectCounty(String county) throws Exception {
        waitForElement(loanHomePage.countyInput);
        clearNTypeValue(loanHomePage.countyInput, county, "County searchbox");
        WebElement countySuggestion = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//auto-complete[contains(@source,'countySearch')]//em[text()='" + county + "']")));

        clickObject(countySuggestion, "county suggestion");
    }

    public void selectSource(String source) throws Exception {
        waitForElement(loanHomePage.dataSourcedropdownButton);

        clickObject(loanHomePage.dataSourcedropdownButton, "data source dropdown");
        clickObject(loanHomePage.dataSourcedropdownUncheckALLButton, "data source dropdown | uncheck all button");
        clickObject(loanHomePage.sourceCheckByName(source), "data source check box for: " + source);
    }

    public void selectyesoperatingstatment() throws Exception {
        clickObject(loanHomePage.property, "propert selected from model");
        waitForElement(loanHomePage.yesOperatingStatment);
        clickObject(loanHomePage.yesOperatingStatment, "yes selected from operating ststment");

    }

    public void clickModalSearch() throws Exception {
        clickObject(loanHomePage.searchbuttonModal, "Search button");

    }

    public void clickModalSearchButton() throws Exception {
        clickObject(loanHomePage.searchModalButton, "Search button");

    }

    /**
     * Advance Search End
     **/

    // Loading wait
    public void waitForLoading() {
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(loanHomePage.loading));
        } catch (StaleElementReferenceException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * modal common
     *
     * @throws Exception
     **/
    public void closeAnyOpenModal() throws Exception {
        if (loanHomePage.openModalDialogs.size() > 0) {
            try {
                clickObject(loanHomePage.modalDialogClose, "modal dialog close button");
            } catch (StaleElementReferenceException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    /**
     * HOME Page functionality
     **/
    public void verifyTrendsNRankings() throws Exception {
        elementPresentorEnabled(loanHomePage.trendsNRankings, "Present", "TrendsNRankings");
    }

    public void verifyTrendsNRankingsMarket() throws Exception {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//dashboard-trends-and-rankings-tile//div[contains(@class,'market-name')]")));
        Assert.assertTrue(loanHomePage.trendsNRankingsMarketNames.size() > 0);

        for (WebElement market : loanHomePage.trendsNRankingsMarketNames) {
            System.out.println(getText(market, "Trends and Ranking market names", "innerText"));
        }
    }

    public void verifyBalanceSheetLendingSpreads() throws Exception {
        elementPresentorEnabled(loanHomePage.balanceSheetLendingSpreads, "Present", "BalanceSheetLendingSpreads");
    }

    public void verifyHomePageTreppLogo() throws Exception {
        elementPresentorEnabled(loanHomePage.homePageTreppLogo, "Present", "HomePageTreppLogo");
    }

    public void verifysearchbox() throws Exception {
        elementPresentorEnabled(loanHomePage.searchbox, "Present", "searchbox");
    }

    public void verifysearchMaginifingglass() throws Exception {
        elementPresentorEnabled(loanHomePage.searchMaginifingglass, "Present", "searchMaginifingglass");
    }

    public void verifyADVANCEDButton() throws Exception {
        elementPresentorEnabled(loanHomePage.advancedbutton, "Present", "ADVANCEDButton");
    }

    /**
     * Saved search modal Actions
     **/
    public void verifySAVEDSEARCHES() throws Exception {
        elementPresentorEnabled(loanHomePage.savedsearches, "Present", "SAVEDSEARCHES");
    }

    public void clickSavedSearchButton() throws Exception {
        clickObject(loanHomePage.savedsearches, "saved search button");
    }

    public void verifyOpenSavedSearch(String savedSearchName) throws Exception {
        waitForElement(loanHomePage.modalDialog);
        clickObject(loanHomePage.getSavedSearchLink(savedSearchName), "saved search link for " + savedSearchName);

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-dialog")));

        // driver.findElement(By.cssSelector("button[ng-click='$dismiss()']")).click();

    }

    public void verifyDeleteSavedSearch(String savedSearchName) throws Exception {
        waitForElement(loanHomePage.modalDialog);
        clickObject(loanHomePage.getSavedSearchDelete(savedSearchName),
                "saved search delete button for " + savedSearchName);
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//a[text()[contains(.,'"
                        + savedSearchName + "')]]/../../../..//button[text()[contains(.,'Delete')]]")));
    }

    /**
     * Saved search modal Actions end
     **/

    public void clickMyWork() throws Exception {
        clickObjectJavascript(loanHomePage.myWorkButton, "My Work button");
        Thread.sleep(2000);
    }

    public void clickValuations() throws Exception {
        clickObject(loanHomePage.valuations, "Valuations button");
    }

    public void verifyMyWork() throws Exception {
        elementPresentorEnabled(loanHomePage.myWorkButton, "Present", "My Work button");
    }

    public void verifyPortfolios() throws Exception {
        elementPresentorEnabled(loanHomePage.portfolios, "Present", "Portfolios");
    }

    /**
     * portfolio button
     **/
    public void clickPortfolioButton() throws Exception {
        Thread.sleep(5000);
        waitForElement(Waitcondition.toBeClickable, loanHomePage.portfolios);
        clickObjectJavascript(loanHomePage.portfolios, "Portfolios");
    }

    public void clickViewAllPortfolio() throws Exception {
        clickObject(loanHomePage.viewAllportfolioLink, "portfolio view All link");
    }

    public void clickAddNewPortfolio() throws Exception {
        waitForElementWithRefresh(loanHomePage.addNewPortfolioLink);
        Thread.sleep(5000);
        clickObject(loanHomePage.addNewPortfolioLink, "Add New Portfolio link");
    }

    public long addNewPortfolio(String portfolioName, String uploadFileName) throws Exception {
        long timeTaken = 0;
        Date startTime;
        Date endTime;
        waitForElement(loanHomePage.modalDialog);
        waitForElement(loanHomePage.addNewPortfolioNameInput);
        clearNTypeValue(loanHomePage.addNewPortfolioNameInput, portfolioName, "portfolio upload input");

        if (BaseSetupClass.executionEnv.equalsIgnoreCase("cloud")) {
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            loanHomePage.addNewPortfolioFileInput = ((RemoteWebDriver) driver)
                    .findElement(By.cssSelector("div.modal-dialog input[type='file']"));
        }

        jsexecutor.executeScript("arguments[0].style.visibility='visible';", loanHomePage.addNewPortfolioFileInput);
        waitForElement(loanHomePage.addNewPortfolioFileInput);

        loanHomePage.addNewPortfolioFileInput.sendKeys(
                Paths.get(System.getProperty("user.dir"), BaseSetupClass.inputFilepath, uploadFileName).toString());

        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(loanHomePage.addNewPortfolioNextButton));
        startTime = new Date();
        clickObject(loanHomePage.addNewPortfolioNextButton, "add new portfolio next button");
        Thread.sleep(1000);
        new WebDriverWait(driver, 60).until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div.modal-dialog button[ng-click='finish()']")));
        endTime = new Date();
        clickObject(loanHomePage.addNewPortfolioFinishButton, "add new portfolio finish button");
        timeTaken = endTime.getTime() - startTime.getTime();
        System.out.println(timeTaken + "ms taken in portfolio upload of 50 records");
        return timeTaken;
    }

    public void selectPortfolio(String portfolioName) throws Exception {
        Thread.sleep(3000);
        WebElement portfolioLink = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//div[@class='loan-portfolio-tile ng-scope']//span[contains(text(),'" + portfolioName
                        + "')]")));

        clickObject(portfolioLink, "portfolio " + portfolioName);

    }

    public void verifyDealLists() throws Exception {
        elementPresentorEnabled(loanHomePage.dealList, "Present", "DealLists");
    }

    public void clickDealListButton() throws Exception {
        clickObject(loanHomePage.dealList, "DealLists");
    }

    public void selectDealList(String name) throws Exception {
        WebElement dealTile = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//div[contains(@class,'item-title') and text()[contains(.,'" + name + "')]]")));
        clickObject(dealTile, "deal list with name " + name);
    }

    public void verifyStats() throws Exception {
        elementPresentorEnabled(loanHomePage.stats, "Present", "Stats");
    }

    public void clickStats() throws Exception {
        clickObject(loanHomePage.stats, "Stats link");
    }

    public void verifyMore() throws Exception {
        elementPresentorEnabled(loanHomePage.more, "Present", "More");
    }

    public void verifymultifamily() throws Exception {
        elementPresentorEnabled(loanHomePage.multifamily, "Present", "multifamily");
    }

    public void verifyRETAIL() throws Exception {
        elementPresentorEnabled(loanHomePage.retail, "Present", "RETAIL");
    }

    public void verifyOFFICE() throws Exception {
        elementPresentorEnabled(loanHomePage.office, "Present", "OFFICE");
    }

    public void verifyINDUSTRIAL() throws Exception {
        elementPresentorEnabled(loanHomePage.industrial, "Present", "INDUSTRIAL");
    }

    public void verifyNews() throws Exception {
        elementPresentorEnabled(loanHomePage.news, "Present", "News");
    }

    public void verifyNewsCREDirect() throws Exception {
        elementPresentorEnabled(loanHomePage.trepptalk, "Present", "Trepp Talk");
    }

    public void verifyNewsTreppWire() throws Exception {
        elementPresentorEnabled(loanHomePage.newsTreppWire, "Present", "Trepp Wire");
    }

    public void verifyNewsTwitter() throws Exception {
        Assert.assertTrue(loanHomePage.newsTwitterList.size() > 0);

        for (WebElement tweet : loanHomePage.newsTwitterList) {
            System.out.println(getText(tweet, "Trepp  new Tweets", "innerText"));
        }
    }

    public void verifyTreppLearning() throws Exception {
        elementPresentorEnabled(loanHomePage.trepplearning, "Present", "Trepp Learning");
    }

    public void verifyTreppWirePodcast() throws Exception {
        elementPresentorEnabled(loanHomePage.treppwirepodcast, "Present", "TreppWire Podcast");
    }

    // h2[contains(text(),'TreppWire Podcast')]

    public void switchtotrepplearningiframe() throws Exception {
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        int numberOfTags = elements.size();
        System.out.println("No. of Iframes on this Web Page are: " + numberOfTags);
        driver.switchTo().frame(0);
        System.out.println("Frame switched");
        waitForElement(loanHomePage.trepplearningvideo);
        Thread.sleep(5000);
        String learningvideo = loanHomePage.trepplearningvideo.getAttribute("href");
        if (learningvideo.isEmpty()) {
            System.err.println("insights Dashboard  not loaded");
            assertNotNull(learningvideo);
        }
        System.out.println(learningvideo);
        driver.switchTo().defaultContent();
        switchToMainFrame();
    }

    public void clicktrepplearning() throws Exception {
        waitForElement(loanHomePage.trepplearning);
        clickObject(loanHomePage.trepplearning, "trepp learning");
        Thread.sleep(5000);

    }

    public void verifyTreppTutorialList() throws Exception {
        elementPresentorEnabled(loanHomePage.treppTutorialsListTable, "Present", "Trepp Tutorial Video Table");

        Assert.assertTrue(loanHomePage.webElementList.size() > 0);

        for (WebElement tutorial : loanHomePage.webElementList) {
            System.out.println(getText(tutorial, "tutorial", "innerText"));
        }
    }

    public void verifyWeeklyChange() throws Exception {
        elementPresentorEnabled(loanHomePage.weeklychange, "Present", "Weekly Change");
        List<WebElement> list = driver.findElements(By.xpath("//i[@title='Weekly Change']"));
        int count = list.size();
        // System.out.println(count);
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(count <= 4);
        sa.assertAll();
//waitForElement(LoanHomePage.WeeklyChangenumber);
//String weeklychange =getText(LoanHomePage.WeeklyChangenumber,"weekly change number", "");
//System.out.println(weeklychange);
//if (weeklychange.isEmpty()) {
//System.out.println("Data on the details page not loaded");
//assertTrue(StringUtils.isNotBlank(weeklychange));
//}
    }

    /************** more menu **************/
    public void clickMore() throws Exception {
        clickObject(loanHomePage.more, "More ");

    }

    public void verifySettings() throws Exception {
        elementPresentorEnabled(loanHomePage.settings, "Present", "Settings");
    }

    public void verifyAlerts() throws Exception {
        elementPresentorEnabled(loanHomePage.alerts, "Present", "Alerts");
    }

    public void clickAlert() throws Exception {
        waitForElement(loanHomePage.alerts);
        clickObject(loanHomePage.alerts, "Alert");

    }

    public void verifyKnowledgeBase() throws Exception {
        elementPresentorEnabled(loanHomePage.knowledgeBase, "Present", "KnowledgeBase");
    }

    public void verifyAPPS() throws Exception {
        elementPresentorEnabled(loanHomePage.apps, "Present", "APPS");
    }

    public void verifyLogout() throws Exception {
        elementPresentorEnabled(loanHomePage.logout, "Present", "Logout");
    }

    public void clickLogOut() throws Exception {
        waitForElement(loanHomePage.logout);
        clickObject(loanHomePage.logout, "LogOut");

    }

    public void verifyContactUs() throws Exception {
        elementPresentorEnabled(loanHomePage.contactus, "Present", "ContactUs");
    }

    public void textfromfootnote() throws Exception {
        waitForElement(loanHomePage.footnote);
        String data = getText(loanHomePage.footnote, "footnote Text", "");
        assertTrue(StringUtils.isNotBlank(data));
    }

    public void clicksearchbox() throws Exception {
        // jsexecutor.executeScript("window.scrollTo(0, 0)");
        clickObject(loanHomePage.searchbox, "search box ");

    }

    public void verifySectionsInQuickSearch(String sectionName) throws Exception { //adding new method 24 aug 2023
        WebElement section = driver.findElement(By.cssSelector("li[title=\"'" + sectionName + "'\"]"));
        //waitForElement(section);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);
        softAssert.assertTrue(section.isDisplayed(), "The " + sectionName + " is not Present");
        if (section.isDisplayed()) {
            System.out.println(sectionName + " section is present");
        }
        if (sectionName.equals("Market Search")) {
            WebElement city = driver.findElement(By.xpath("//span[normalize-space()='City']"));
            softAssert.assertTrue(city.isDisplayed(), "City is not Present");
            WebElement sponsor = driver.findElement(By.xpath("//span[normalize-space()='Sponsor']"));
            softAssert.assertTrue(sponsor.isDisplayed(), "Sponsor is not Present");
        }
    }

    public void verifyModal() throws Exception {
        waitForElement(loanHomePage.addToPortfolioIcon);
        clickObjectJavascript(loanHomePage.addToPortfolioIcon, "addToPortfolioIcon");
        elementPresentorEnabled(driver.findElement(By.xpath("//div[@class='modal-body']")), "Present", "Add to Portfolio modal");
    }

    public void searchaddress(String address) throws Exception {
        WebElement searchResult;
        for (int i = 0; i < 3; i++) {
            try {
                clearNTypeValue(loanHomePage.searchbox, address, "search box");
                // clicksearchbox();
                // LoanHomePage.searchbox.sendKeys(Keys.RETURN);
                searchResult = new WebDriverWait(driver, 20).until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//span[@class='ng-binding']/strong[1]")));
                clickObject(searchResult, "Search clicked ");
                break;

            } catch (Exception e) {
                if (i == 2)
                    throw e;
                System.out.println("suggestion not displayed in 10 secs refreshing page::" + (i + 1));
                goToHome();
                waitForElement(Waitcondition.toBeVisible, loanHomePage.searchbox);
                continue;
            }
        }
        // waitForElement(loanHomePage.commentary);
    }

    public void verifyMSARanking() throws Exception {
        waitForElement(loanHomePage.statsmsa);
        elementPresentorEnabled(loanHomePage.statsmsa, "Present", "MSA Ranking");
        String data = getText(loanHomePage.statsmsa, "MSA Ranking", "");
        assertTrue(StringUtils.isNotBlank(data));
    }

    public void verifystatsdelinquency() throws Exception {
        waitForElement(loanHomePage.statsdelinquency);
        elementPresentorEnabled(loanHomePage.statsdelinquency, "Present", "Stats delinquency");
        String data = getText(loanHomePage.statsdelinquency, "Stats delinquency", "");
        assertTrue(StringUtils.isNotBlank(data));
    }

    public void clickCompanyExposure() throws Exception {
        waitForElement(loanHomePage.companyExposure);
        clickObject(loanHomePage.companyExposure, "Company Exposure ");

    }

    public void clickHiltonHotel() throws Exception {
        waitForElement(loanHomePage.tenants);
        clickObject(loanHomePage.hiltonWorldwide, "Hilton Worldwide");

    }

    public void verifyHoteldata() throws Exception {
        waitForElement(loanHomePage.hiltonStats);
        elementPresentorEnabled(loanHomePage.hiltonStats, "Present", "Hilton Stats");
        elementPresentorEnabled(loanHomePage.hotelPerfomance, "Present", "Hilton Hotel Perfomance");
        getText(loanHomePage.hiltonStats, "Hotel Data", "");
    }

    public void clickTenants() throws Exception {
        waitForElement(loanHomePage.tenants);
        clickObject(loanHomePage.tenants, "Tenants");

    }

    public void clickTenantsMacys() throws Exception {
        waitForElement(loanHomePage.tenantsMacys);
        clickObject(loanHomePage.tenantsMacys, "Tenant Macy's");

    }

    public void verifymacysdata() throws Exception {
        waitForElement(loanHomePage.macysDelinquencySummary);
        elementPresentorEnabled(loanHomePage.macysDelinquencySummary, "Present", "Macys Delinquency Summary");

    }

    public void clickTreppLogo() throws Exception {
        closeAnyOpenModal();
        clickObject(loanHomePage.treppLogo, "Trepp logo");

    }

    public void goToHome() throws Exception {
        launchURL(data.strDTURL + "/loan/app/dashboard");
        waitForHomepageLoan();
    }

    public void searchdata(String data) throws Exception {
        clearNTypeValue(loanHomePage.searchbox, data, "search box");
        clicksearchbox();
    }

    public void searchdata2(String data) throws Exception {
        clearNTypeValue(loanHomePage.searchbox, data, "search box");
        Thread.sleep(2000);
        waitForElement(loanHomePage.searchMaginifingglass);
        //clicksearchbox();
    }

    public void verifySearchdata(String data) {
        WebElement searchResult = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[@class='ng-binding']//strong[contains(text(),'" + data + "')]"))));
        Assert.assertEquals(true, searchResult.isDisplayed());
    }

    public void clickOnProftfolioshortcut() throws Exception {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(loanHomePage.portfolioshorcutbutton));
        clickObject(loanHomePage.portfolioshorcutbutton, "portfolio short cut button");

    }

    public void clickOnNewPortfolioAdd(String portfolioName) throws Exception {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(loanHomePage.newportfoliotab));
        clickObject(loanHomePage.newportfoliotab, "New portfolio tab");
        clearNTypeValue(loanHomePage.addNewPortfolioNameInput1, portfolioName, "portfolio name input");
        clickObject(loanHomePage.addportfoliobutton, "add portfolio button");
        WebElement confirmmodel = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h4[@class='modal-title manage-portfolios-title']")));
        Assert.assertEquals(true, confirmmodel.isDisplayed());

    }

    public void switchtoinsightiframe() throws Exception {
        Thread.sleep(10000);
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));

        int numberOfTags = elements.size();
        System.out.println("No. of Iframes on this Web Page are: " + numberOfTags);
        driver.switchTo().frame(0);
        System.out.println("Frame switched");
        Thread.sleep(5000);
        String highlightsmonth = loanHomePage.insightsHighlights.getText();
        if (highlightsmonth.isEmpty()) {
            System.err.println("insights Dashboard  not loaded");
            assertNotNull(highlightsmonth);
        }

        System.out.println(highlightsmonth);
        driver.switchTo().defaultContent();
        switchToMainFrame();

    }

    public void clickinsights() throws Exception {
        waitForElement(loanHomePage.insights);
        clickObject(loanHomePage.insights, "Insights");
        Thread.sleep(5000);

    }

    public void selectZipCode(String zipCode) throws Exception {
        waitForElement(loanHomePage.zipInput);
        clearNTypeValue(loanHomePage.zipInput, zipCode, "zipCode searchbox");
        WebElement zipCodeSuggestion = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        ".//auto-complete[contains(@source,'zipSearch($query)')]//em[text()='" + zipCode + "']")));

        clickObject(zipCodeSuggestion, "Zip code suggestion");
    }

    public void selectPortfolioFromList(String portfolioName) throws Exception {
        WebElement portfolio = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + portfolioName + "']")));
        clickObject(portfolio, portfolioName);
    }

    public void clickOfficeTabAndVerifyCharts() throws Exception {
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        System.out.println("No. of Iframes on this Web Page are: " + elements.size());
        Thread.sleep(5000);
        driver.switchTo().frame(0);
        System.out.println("Frame switched");
        WebElement officeTab = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Office']")));
        clickObject(officeTab, "OfficeTab");
        WebElement governmentText = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Government']")));
        clickObject(governmentText, "governmentText");
        WebElement yearlySalesPriceVolumeChart = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='block-7087168c-df06-4eaf-b067-d7745319e882_5eec1c04-2308-41d7-9ab5-83ce4a80687a']")));
        verifyCharts(yearlySalesPriceVolumeChart, "yearlySalesPriceVolumeChart");
        WebElement annualAverageTransactionPricePerSqftChart = driver.findElement(By.xpath("//div[@id='block-7087168c-df06-4eaf-b067-d7745319e882_a7159f43-dc8a-45e6-918a-059ab296a919']"));
        verifyCharts(annualAverageTransactionPricePerSqftChart, "annualAverageTransactionPricePerSqftChart");
        WebElement quarterlySalesPriceVolumeAndPricePerSqftChart = driver.findElement(By.xpath("//div[@id='block-7087168c-df06-4eaf-b067-d7745319e882_0079cf70-e34b-449b-ad0f-045116ec58ac']"));
        verifyCharts(quarterlySalesPriceVolumeAndPricePerSqftChart, "quarterlySalesPriceVolumeAndPricePerSqftChart");
    }

    public void verifyCharts(WebElement element, String charName) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        softAssert.assertTrue(element.isDisplayed(), charName + " chart is not present on page");
        if (element.isDisplayed()) {
            System.out.println(charName + " chart is present on page");
        }
    }

    public void verifyEditFunctionalityInSavedSearch() throws Exception {
        waitForElement(loanHomePage.modalDialog);
        clickEditButton();
        waitForElement(loanHomePage.openStatedropdown);
        verifySelectedState("Montana");
        //selectState("Alaska");
        clickObject(loanHomePage.openStatedropdown, "Open State DropDown ");
        WebElement stateCheckbox = driver.findElement(
                By.xpath(".//taui-multi-select[@id='state']//ul/li/a[text()[contains(.,'Alaska')]]"));
        clickObject(stateCheckbox, "Selected Alaska");
        clickObject(loanHomePage.openStatedropdown, "Close State DropDown ");
        clickSaveButton();
        clickEditButton();
        verifySelectedState("Montana");
        verifySelectedState("Alaska");
        clickSaveButton();
    }

    public void verifySelectedState(String state) throws Exception {
        clickObject(loanHomePage.openStatedropdown, "Open State DropDown ");
        Thread.sleep(4000);
        WebElement stateCheckbox = driver.findElement(
                By.xpath(".//taui-multi-select[@id='state']//ul/li/a[text()[contains(.,'" + state + "')]]/i"));
        //WebElement stateCheckbox = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//taui-multi-select[@id='state']//ul/li/a[text()[contains(.,'" + state + "')]]/i")));
        softAssert.assertTrue(stateCheckbox.getAttribute("class").equals("fa fa-fw fa-check-square-o"), "Previously selected state " + state + " is not selected");
        if (stateCheckbox.getAttribute("class").equals("fa fa-fw fa-check-square-o")) {
            System.out.println("Previously selected state " + state + " is selected");
        }
        clickObject(loanHomePage.openStatedropdown, "Close State DropDown ");
        Thread.sleep(2000);
    }

    private void clickSaveButton() {
        WebElement saveButton = driver.findElement(By.xpath("//button[@class='btn btn-primary ng-binding']"));
        saveButton.click();
    }

    private void clickEditButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement editButton = driver.findElement(By.xpath(".//a[text()[contains(.,'testSavedSearch')]]/../../../..//button[text()[contains(.,'Edit')]]"));
        editButton.click();
    }
    
    public void searchaddressWithFollowedByName(String address, String followedByName) throws Exception {
        WebElement searchResult;
        for (int i = 0; i < 3; i++) {
            try {
                clearNTypeValue(loanHomePage.searchbox, address, "search box");
                // clicksearchbox();
                // LoanHomePage.searchbox.sendKeys(Keys.RETURN);
                searchResult = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//span[@class='ng-binding' and  text()='" + followedByName + "']/strong)[1]")));
                clickObject(searchResult, "Search clicked ");
                break;

            } catch (Exception e) {
                if (i == 2)
                    throw e;
                System.out.println("suggestion not displayed in 10 secs refreshing page::" + (i + 1));
                goToHome();
                waitForElement(Waitcondition.toBeVisible, loanHomePage.searchbox);
                continue;
            }
        }
    }
    
    public void verifyTreppWireProdcast() throws Exception {
        waitForElement(loanHomePage.treppWirePodcastHeader);
        scrollIntoView(loanHomePage.treppWirePodcastHeader, "Trepp wire prodcast");

        switchToFrame(loanHomePage.prodCastListIframe);
        waitForElement(loanHomePage.prodCastList);
        moveToElement(loanHomePage.prodCastList, "Prodcast list");

        scrollIntoView(loanHomePage.prodCastListFirst, "Trepp wire prodcast list");
        switchToFrame(loanHomePage.prodCastListIframe);
        Actions a = new Actions(driver);
        a.click(loanHomePage.prodCastListFirst).build().perform();
        waitForElement(loanHomePage.videoPauseButton);
        Thread.sleep(3000);

        softAssert.assertTrue(loanHomePage.videoPauseButton.isDisplayed(), "pause button not displayed.");
    }
    
    public void verifyExposureSectionOnHomePageHotelReport() throws Exception {
        // Scroll into exposure section
        waitForElement(loanHomePage.tenantsToggle);
        scrollIntoView(loanHomePage.tenantsToggle, "Tenants Toggle");

        // Verify Hotels click-thru
        waitForElement(loanHomePage.firstHotel);
        clickObject(loanHomePage.firstHotel, "First hotel");
        switchToNewWindow();
        waitForLoading();
        waitForElement(loanHomePage.hotelReportsHeader);
        softAssert.assertTrue(loanHomePage.hotelReportsHeader.isDisplayed(), "Hotel Reports Header is not present");
        switchToOldWindow();
        waitForElement(loanHomePage.viewAllHotelExposure);
        clickObject(loanHomePage.viewAllHotelExposure, "View All Hotel Exposure link");
        switchToNewWindow();
        waitForLoading();
        
        waitForElement(loanHomePage.viewAllHotelExposureHeader);
        softAssert.assertTrue(loanHomePage.viewAllHotelExposureHeader.isDisplayed(),
                "View all Hotel Reports Header is not present");
        switchToOldWindow();
    }
    
    public void verifyExposureSectionOnHomePageTenantsReport() throws Exception {
        //Verify Tenants click-thru
        Thread.sleep(3000);
        waitForElement(loanHomePage.tenantsToggle);
        clickObjectJavascript(loanHomePage.tenantsToggle, "Tenants Toggle");
        waitForElement(loanHomePage.goverment);
        clickObjectJavascript(loanHomePage.goverment, "Goverment");
        switchToNewWindow();
        waitForLoading();
        waitForElement(loanHomePage.tenantReportHeader);
        softAssert.assertTrue(loanHomePage.tenantReportHeader.isDisplayed(), "Tenant Reports Header is not present");
        switchToOldWindow();
        waitForElement(loanHomePage.viewAllHotelTenant);
        clickObject(loanHomePage.viewAllHotelTenant, "View All Tenant Exposure link");
        switchToNewWindow();
        waitForLoading();
        
        waitForElement(loanHomePage.viewAllTenantExposureHeader);
        softAssert.assertTrue(loanHomePage.viewAllTenantExposureHeader.isDisplayed(), "View all Tenant exposure Header is not present");
        switchToOldWindow();        
    }

    public void goToProfileSectionOfUser() throws Exception {
        clickObject(loanHomePage.more, "more options");
        clickObject(loanHomePage.profile, "Profile option");
        switchToNewWindow();
    }

    public void editAndVerifyLastNameField(String lastName) throws Exception {
        String lastNameFieldOriginalText = loanHomePage.lastName.getAttribute("value").toString();
        clearNTypeValue(loanHomePage.lastName, lastName, "Last Name Updated Input");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loanHomePage.saveButton);
        clickObject(loanHomePage.saveButton, "saveButton");
        waitForElement(loanHomePage.updateMessage);
        switchToOldWindow();
        goToProfileSectionOfUser();
        loanHomePage.lastName = driver.findElement((By.xpath("//input[@id='lastName']")));
        System.out.println("--> Last name updated value is " + loanHomePage.lastName.getAttribute("value").toString());
        softAssert.assertTrue(loanHomePage.lastName.getAttribute("value").toString().equals(lastNameFieldOriginalText), "Last name is not updated");

    }

    public void verifyChangePasswordModel() throws Exception {
        clickObject(loanHomePage.changePasswordButton, "Change Password Button");
        Thread.sleep(2000);
        driver.switchTo().frame(0);
        softAssert.assertTrue(loanHomePage.changePasswordModel.isDisplayed(), "Change Password model not available");
        clickObject(loanHomePage.passwordModelCancelButton, "Cancel Button");
        driver.switchTo().defaultContent();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loanHomePage.saveButton);
        clickObject(loanHomePage.saveButton, "saveButton");
        switchToOldWindow();
    }
    
    public void verifyTreppWireSection() throws Exception {
        waitForElement(loanHomePage.treppWire);
        scrollIntoView(loanHomePage.treppWire, "TreppWire");
        clickObjectJavascript(loanHomePage.treppWireInArticleClickThru, "TreppWire inside link");
        switchToNewWindow();
        wait.until(ExpectedConditions.visibilityOf(loanHomePage.treppWireClickThruNewTabPageLogo));
        switchToOldWindow();
    }
    
    public void verifyTreppTalkSection() throws Exception {
        waitForElement(loanHomePage.treppTalk);
        clickObjectJavascript(loanHomePage.treppTalk, "TreppTalk toggle");
        waitForElement(loanHomePage.treppTalkContent);
        wait.until(ExpectedConditions.visibilityOf(loanHomePage.treppTalkContent));
    }
    
    public void verifyCRENewsSection() throws Exception {
        waitForElement(loanHomePage.creNews);
        clickObjectJavascript(loanHomePage.creNews, "creNews toggle");
        waitForElement(loanHomePage.creNewsInArticleClickThru);
        clickObjectJavascript(loanHomePage.creNewsInArticleClickThru, "CRE News In Article link");
        switchToNewWindow();
        //wait.until(ExpectedConditions.visibilityOf(loanHomePage.creNewsInLineClickThruNewTabContent));
        switchToOldWindow();
        waitForElement(loanHomePage.creNewsLogoImg);
        clickObjectJavascript(loanHomePage.creNewsLogoImg, "CRE News Logo Image");
        wait.until(ExpectedConditions.visibilityOf(loanHomePage.creNewsClickThruNewTabPageLogo));
    }
    
    public void verifyTrendsAndRanking() throws Exception {
        waitForElement(loanHomePage.msaToggle);
        scrollIntoView(loanHomePage.msaToggle, "Trend And Ranking");
        clickObjectJavascript(loanHomePage.msaToggle, "MSA toggle");
        selectFromDropDownVisibleText(loanHomePage.rankByDropdown, "", "DSCR <1");
        clickObjectJavascript(loanHomePage.listToggle, "List toggle");
        waitForElement(loanHomePage.listToggleState);
        clickObjectJavascript(loanHomePage.listToggleState, "First state");
        waitForLoading();
        waitForElement(statsPage.navTrendsLink);
        softAssert.assertTrue(statsPage.navTrendsLink.isDisplayed(), "Trends navigation link not displayed.");
    }

}
