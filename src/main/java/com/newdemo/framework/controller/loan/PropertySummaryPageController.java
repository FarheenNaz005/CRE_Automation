package com.newdemo.framework.controller.loan;


import static org.testng.Assert.assertTrue;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.controller.CREHomepageController;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.PropertyListingPageV2;
import com.newdemo.framework.pages.loan.PropertySummaryPage;

public class PropertySummaryPageController extends ReuseableFunctions {
    PropertySummaryPage summaryPage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;
    PropertyListingPageV2 loanlistingPage = null;

    public PropertySummaryPageController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        summaryPage = PageFactory.initElements(driver, PropertySummaryPage.class);
        loanlistingPage = PageFactory.initElements(driver, PropertyListingPageV2.class);
    }

    /**
     * validation methods
     **/
    public void verifyOverviewTab() throws Exception {
        elementPresentorEnabled(summaryPage.overviewTab, "Present", "Summary | Commentary Tab");
    }

    public void verifyCommentaryTab() throws Exception {
        elementPresentorEnabled(summaryPage.commentaryTab, "Present", "Summary | Commentary Tab");
    }

    public void verifyComment(String comment) throws Exception {
        assertTrue(summaryPage.userLoanComments.size() > 0, "comment should be present.");

        softAssert.assertEquals(getText(summaryPage.userLoanComment, "user comment", "innerText"), comment, "user comment");
    }

    public void verifyPropertyNameLink() throws Exception {
        elementPresentorEnabled(summaryPage.propertyTitleLink, "Present", "Summary | overview Tab | Propertyy name link");
    }

    /**
     * action methods
     **/

    public void clickBackToListing() throws Exception {
        clickObject(summaryPage.backToListingButton, "Back to Listing button");
    }

    public void clickCommentaryTab() throws Exception {
        clickObject(summaryPage.commentaryTab, "Summary | Commentary Tab");
    }

    public void clickOverviewTab() throws Exception {
        clickObject(summaryPage.overviewTab, "Summary | Overview Tab");
    }

    public void clickPropertyNameLink() throws Exception {
        clickObject(summaryPage.propertyTitleLink, "Summary | Overview Tab | Propertyy name link");
    }

    public void addComment(String comment) throws Exception {
        if (summaryPage.userLoanComments.size() > 0) {
            deleteComment();
        }
        WebElement ele = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[text()[contains(.,'Add Comments')]]")));
        clickObject(ele, "summary page | Add Comment button");
        waitForElement(summaryPage.dialogModal);

        waitForElement(summaryPage.userCommentModal);
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(summaryPage.loading));
        Thread.sleep(2000);
        clickObject(summaryPage.userCommentInput, "comment input");
        clearNTypeValue(summaryPage.userCommentInput, comment, "user comment modal input");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(summaryPage.userCommentSaveButton));
        clickObject(summaryPage.userCommentSaveButton, "comment modal save button");
        waitForElement(summaryPage.userCommentEditButton);

    }

    public void deleteComment() throws Exception {
        clickObject(summaryPage.userCommentEditButton, "comment edit button");
        waitForElement(summaryPage.userCommentModal);
        clickObject(summaryPage.userCommentDeleteButton, "user comment modal | delete button");
        new CREHomepageController(driver).waitForLoading();
    }

    public void clickOnSummryPage() throws Exception {
        clickObject(summaryPage.summaryPagelink, "comment edit button");

    }
    
    public void clickRelatedRecordTab() throws Exception {
        waitForElement(summaryPage.relatedRecordTab);
        clickObject(summaryPage.relatedRecordTab, "Related Records Tab");
    }
    
    public void verifyPropertyOpens() throws Exception {
        //Get first property`and click
        waitForElement(summaryPage.selectFirstProperty);
        moveToElement(summaryPage.selectFirstProperty, "first property");
        String propertyClicked = summaryPage.selectFirstProperty.getText();
        System.out.println("Property displayed in grid: " + propertyClicked);
        clickObject(summaryPage.selectFirstProperty, "Select First Property");
        
        waitForLoading();
        
        //Get property header after click
        String propertHeader = summaryPage.propertyNameHeader.getText();
        System.out.println("Property open after click: " + propertHeader);
        
        softAssert.assertEquals(propertyClicked, propertHeader, "Property name not matched.");
    }
    
    public void waitForLoading() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(loanlistingPage.loading));
    }
    
    public void verifyOccupancySectionOnSummaryPage() throws Exception {
        // Occupancy graph
        waitForElement(summaryPage.occupancyHeader);
        scrollIntoView(summaryPage.occupancyHeader, "Occupancy section");
        softAssert.assertTrue(summaryPage.occupancyGraph.isDisplayed(), "Occupancy graph not displayed.");
        // Top 5 tenants
        List<WebElement> data = summaryPage.top5Tenants;
        List<String> tenants = data.stream().map(element -> element.getText()).collect(Collectors.toList());
        softAssert.assertTrue(!tenants.isEmpty(), "tenants are not populated");
        System.out.println("Top 5 tenants" + tenants);

    }
    
    public void verifyHistoricalCommentsSection() throws Exception {
        waitForElement(summaryPage.viewHistoricalComments);
        scrollIntoView(summaryPage.viewHistoricalComments, "Comment section");
        elementPresentorEnabled(summaryPage.viewHistoricalComments, "Present", "Summary | View Historical Comments Link");
        elementPresentorEnabled(summaryPage.comment, "Present", "Summary | Latest Comments");
    }
    
    public void verifyModelOpen() throws Exception {
        clickObject(summaryPage.viewHistoricalComments, "View Historical Comments link");
        waitForElement(summaryPage.modalOpens);
        softAssert.assertTrue(summaryPage.modalOpens.isDisplayed(), "Model not displayed");
        waitForElement(summaryPage.modalContent);
        elementPresentorEnabled(summaryPage.modalContent, "Present", "Summary | Modal Content");
        clickObject(summaryPage.closeModel, "Model close button");       
    }
    
    public void addAndDeletePropertyToPortfolioOnSummaryPage(String portfolioName, String propertyName)
            throws Exception {
        // Select portfolio to add property
        waitForElement(summaryPage.addToPortfolioIcon);
        clickObject(summaryPage.addToPortfolioIcon, "Add to portfolio icon");
        waitForElement(loanlistingPage.searchField);
        clearNTypeValue(loanlistingPage.searchField, portfolioName, "search field");
        WebElement portfolioToSearch = new WebDriverWait(driver, 20).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[contains(. , '" + portfolioName + "')]/i")));
        clickObject(portfolioToSearch, "searched portfolio");
        clickObject(loanlistingPage.addButton, "Add button");
        WebElement confirmationBox = new WebDriverWait(driver, 40)
                .until(ExpectedConditions.visibilityOf(loanlistingPage.confirmationBox));
        clickObject(loanlistingPage.viewPortfolio, "View Portfolio button");
        waitForLoading();

        // Verify property added to portfolio
        WebElement property = new WebDriverWait(driver, 20).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h4[contains(text(),'" + propertyName + "')]")));
        softAssert.assertTrue(property.isDisplayed(), "Property not added to portfolio");
        getText(property, "the property list", propertyName);

        // Verify property deleted from portfolio
        WebElement propertyToDelete = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h4[contains(text(),'" + propertyName + "')]/ancestor::td/preceding-sibling::td/a/i")));
        clickObject(propertyToDelete, propertyName + " checkbox");
        clickObject(loanlistingPage.deleteIcon, "Delete icon");
        waitForElement(loanlistingPage.deleteAlertModalBox);
        clickObject(loanlistingPage.deleteButtonOnAlertModalBox, "Delete Button on alert box");
        waitForLoading();
        wait.until(ExpectedConditions.invisibilityOf(property));
    }

    public void addAndDeletePropertyToDealListOnSummaryPage(String dealList) throws Exception {
        // Add property to deal list
        waitForElement(summaryPage.dealListIcon);
        clickObject(summaryPage.dealListIcon, "Add to deal List icon");

        WebElement dealListName1 = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h4[@class='dl-name ng-binding' and contains(text(), '" + dealList + "')]")));
        clickObject(dealListName1, dealList);
        waitForLoading();
        //wait.until(ExpectedConditions.invisibilityOf(summaryPage.addDealListModal));
        waitForElement(summaryPage.addedDeaListIcon);
        softAssert.assertTrue(summaryPage.addedDeaListIcon.isDisplayed(), "Property not added to deal list");
        Thread.sleep(3000);

        // Remove property from deal list
        clickObject(summaryPage.dealListIcon, "Add to deal List icon");
        WebElement dealListName2 = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h4[@class='dl-name ng-binding' and contains(text(), '" + dealList + "')]")));
        clickObject(dealListName2, dealList);
        waitForLoading();
        //wait.until(ExpectedConditions.invisibilityOf(summaryPage.addDealListModal));
        wait.until(ExpectedConditions.invisibilityOf(summaryPage.addedDeaListIcon));
    }

    public void verifyDownloadIconOnLoanDetailPage(String fileName) throws Exception {
        waitForElement(Waitcondition.tobePresent, summaryPage.downloadIcon);
        clickObjectJavascript(summaryPage.downloadIcon, "Download icon button");
        String downloadFilepath = Paths.get(System.getProperty("user.dir"), "Downloads").toString();
        wait.until(ExpectedConditions.elementToBeClickable(summaryPage.downloadIcon));
        boolean fileExist = isFileExist(downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");
    }

    public void verifyCreateAlertOnSummaryPage() throws Exception {
        // Creating Alert
        clickObject(summaryPage.bellOffIcon, "Bell button");
        waitForElement(summaryPage.alertModalBox);
        clickObjectJavascript(summaryPage.clickCheckboxFHALoanStatus, "FHA Loan status checkbox");
        clickObject(summaryPage.saveAlertBtn, "Save alert button");
        clickObject(summaryPage.saveAlertConfirmationModalBtn, "Save alert confirmation modal button OK");
        waitForElement(summaryPage.bellOnIcon);
        softAssert.assertTrue(summaryPage.bellOnIcon.isDisplayed(), "Alert not created.");
    }
    


}
