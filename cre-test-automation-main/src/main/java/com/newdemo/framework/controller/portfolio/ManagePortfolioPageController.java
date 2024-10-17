package com.newdemo.framework.controller.portfolio;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.LoanHomePage;
import com.newdemo.framework.pages.PropertyListingPageV2;
import com.newdemo.framework.pages.portfolio.ManagePorfolioPage;

public class ManagePortfolioPageController extends ReuseableFunctions {
    ManagePorfolioPage managePortfolioPage = null;
    LoanHomePage loanHomePage = null;
    public LoadData data = null;
    PropertyListingPageV2 loanlistingPage = null;
    public SoftAssert softAssert = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    Date date = new Date();
    String currentDate = dateFormat.format(date);

    public ManagePortfolioPageController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        // from

        managePortfolioPage = PageFactory.initElements(driver, ManagePorfolioPage.class);
        loanlistingPage = PageFactory.initElements(driver, PropertyListingPageV2.class);
        loanHomePage = PageFactory.initElements(driver, LoanHomePage.class);

    }


    /**
     * Verify portfolio is visible
     *
     * @param String portfolioName
     */
    public void verifyPortfolioVisible(String portfolioName) throws Exception {
        WebElement portfolio = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span//a[text()='" + portfolioName + "']")));
        softAssert.assertTrue(portfolio.isDisplayed(), portfolioName + " displayed");

    }
    
    public void waitForLoading() {
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(loanHomePage.loading));
        } catch (StaleElementReferenceException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Open portfolio
     *
     * @param String portfolioName
     */
    public void openPortfolio(String portfolioName) throws Exception {
        WebElement portfolio = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span//a[text()='" + portfolioName + "']")));
        clickObject(portfolio, portfolioName + "porfolio link");

    }

    /**
     * open valuations of portfolio
     *
     * @param String portfolioName
     */
    public void openValuation(String portfolioName) throws Exception {

        WebElement portfolioexpand = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + portfolioName + "')]]]//span[@class='ag-group-contracted']")));
        clickObject(portfolioexpand, "portfolio expand button");
    }

    public void verifyValuation(String valuationName) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + valuationName + "')]]]/..")));
    }

    /**
     * open valuations of portfolio
     *
     * @param String portfolioName
     */
    public void closeValuation(String portfolioName) throws Exception {

        WebElement portfolioclose = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + portfolioName + "')]]]//span[@class='ag-group-expanded']")));
        clickObject(portfolioclose, "portfolio expand button");
    }

    public void clickActionButton(WebElement rowElement) throws Exception {
        WebElement actionButton = rowElement.findElement(By.xpath(".//button[@id='portfolio-manager-ag-grid-action-button']"));
        clickObject(actionButton, "action button");
    }

    /**
     * add comment to valuation
     *
     * @param String valuationName
     */
    public void addvaluationComment(String valuationName) throws Exception {
        WebElement evaluationRow = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + valuationName + "')]]]/..")));
        //open action menu for evaluation
        clickActionButton(evaluationRow);
        WebElement comment = evaluationRow.findElement(By.cssSelector("a.comment"));
        clickObject(comment, "comment for " + valuationName);

        waitForElement(managePortfolioPage.modalDialog);

        clearNTypeValue(managePortfolioPage.commentTextArea, "test comment update", "comment input testare");
        clickObject(managePortfolioPage.addCommentButton, "add comment button");
        waitForElement(managePortfolioPage.addCommentButtonLoading);
//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-dialog .btn-primary i.fa-spinner")));
        //to do varify comment is added in modal after the bug is fixed

    }

    /**
     * delete valuation
     *
     * @param String valuationName
     */
    public void deleteValuation(String portfolioName, String valuationName) throws Exception {
        WebElement evaluationRow = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + valuationName + "')]]]/..")));
        //open action menu for evaluation
        clickActionButton(evaluationRow);

        WebElement delete = evaluationRow.findElement(By.cssSelector("a.delete-valuation"));
        clickObject(delete, "delete button for " + valuationName);
        waitForElement(managePortfolioPage.modalDialog);
        clickObject(managePortfolioPage.modalDialogDelete, "modal delete button");
        openValuation(portfolioName);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + valuationName + "')]]]/..")));
    }

    /**
     * navigate to history of portfolio
     *
     * @param String portfolioName
     */
    public void navigateToHistoryPage(String portfolioName) throws Exception {
        Thread.sleep(1000);
        WebElement portfolioRow = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + portfolioName + "')]]]/..")));
        //open action menu for evaluation
        clickActionButton(portfolioRow);
        Thread.sleep(2000);
        WebElement history = portfolioRow.findElement(By.cssSelector("a.history"));
        clickObject(history, "history for " + portfolioName);
    }

    /**
     * verify history page loaded
     */
    public void verifyHistoryPage() throws Exception {
        WebElement backBtn = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[./span[contains(text(),'Back to My Portfolio Manager')]]")));
        softAssert.assertEquals(driver.getTitle(), "Portfolio History - TreppLoan", "Title of page should be Portfolio History - TreppLoan");
        clickObject(backBtn, "back to portfolio manager");
    }

    /**
     * add comment to portfolio
     *
     * @param String portfolioName
     */
    public void addPortfolioComment(String portfolioName) throws Exception {
        WebElement portfolioRow = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + portfolioName + "')]]]/..")));
        //open action menu for evaluation
        clickActionButton(portfolioRow);
        WebElement comment = portfolioRow.findElement(By.cssSelector("a.comment"));
        clickObject(comment, "comment for " + portfolioName);

        waitForElement(managePortfolioPage.modalDialog);

        clearNTypeValue(managePortfolioPage.commentTextArea, "test comment update", "comment input testare");
        clickObject(managePortfolioPage.addCommentButton, "add comment button");
        waitForElement(managePortfolioPage.addedComments);
//new WebDriverWait(driver, 10)
//.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-dialog .btn-primary i.fa-spinner")));
        String commentText = getText(managePortfolioPage.addedComments, "added comment ", "innerText");
        softAssert.assertEquals(commentText, "test comment update", "added comment should match");
        //to do varify comment is added in modal after the bug is fixed

    }

    /**
     * edit comment to portfolio
     *
     * @param String portfolioName
     */
    public void editPortfolioComment(String portfolioName) throws Exception {
        elementPresentorEnabled(managePortfolioPage.editCommentButton, "Present", "edit button for comment");
        clickObject(managePortfolioPage.editCommentButton, "comment edit button");

        waitForElement(managePortfolioPage.commentEditTextArea);
        clearNTypeValue(managePortfolioPage.commentEditTextArea, "test comment edited", "edit comment text are");
        clickObject(managePortfolioPage.editCommentSaveButton, "save button for edit comment");

        waitForElement(managePortfolioPage.addedComments);
        String commentText = getText(managePortfolioPage.addedComments, "added comment ", "innerText");
        softAssert.assertEquals(commentText, "test comment edited", "edited comment should match");
    }

    /**
     * delete comment to portfolio
     *
     * @param String portfolioName
     */
    public void deletePortfolioComment(String portfolioName) throws Exception {

        elementPresentorEnabled(managePortfolioPage.deleteCommentButton, "Present", "delete button for comment");
        clickObject(managePortfolioPage.deleteCommentButton, "delete button ");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.comment-text")));
    }

    /**
     * download portfolio
     *
     * @param String portfolioName
     */
    public void downloadPortfolio(String portfolioName) throws Exception {

        WebElement portfolioRow = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + portfolioName + "')]]]/..")));
        clickActionButton(portfolioRow);
        WebElement download = portfolioRow.findElement(By.cssSelector("a.download"));
        clickObject(download, "delete button for " + portfolioName);
        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath, portfolioName + ".xlsx");
        softAssert.assertTrue(fileExist, "file " + portfolioName + ".xlsx" + " exist in download folder");

    }

    /**
     * share portfolio
     *
     * @param String portfolioName
     */
    public void sharePortfolio(String portfolioName) throws Exception {
        WebElement portfolioRow = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + portfolioName + "')]]]/..")));
        //open action menu for evaluation
        clickActionButton(portfolioRow);
        WebElement share = portfolioRow.findElement(By.cssSelector("a.share"));
        clickObject(share, "share for " + portfolioName);

        waitForElement(managePortfolioPage.modalDialog);
        clearNTypeValue(managePortfolioPage.shareEmailInput, "yogendra@binmile.com", "share input email");
        clickObject(managePortfolioPage.shareAddPersonButton, "share portfolio modal add person button");
        waitForElement(managePortfolioPage.shareModalEmailListFirst);

        softAssert.assertEquals(managePortfolioPage.shareModalEmailListFirst.getText(), "yogendra@binmile.com", "email shoud match");
        clickObject(managePortfolioPage.shareModalDoneButton, "share portfolio modal done button");
    }

    /**
     * delete portfolio
     *
     * @param String portfolioName
     */
    public void deletePortfolio(String portfolioName) throws Exception {

        WebElement portfolioRow = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + portfolioName + "')]]]/..")));
        clickActionButton(portfolioRow);
        Thread.sleep(2000);
        WebElement delete = portfolioRow.findElement(By.cssSelector("a.delete"));
        clickObject(delete, "delete button for " + portfolioName);
        waitForElement(managePortfolioPage.modalDialog);
        clickObject(managePortfolioPage.modalDialogDelete, "modal delete button");
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@col-id='ag-Grid-AutoColumn' and .//a[text()[contains(.,'" + portfolioName + "')]]]/..")));
    }


    public void clickOnAddPortfoliobutton() throws Exception {
        waitForElement(Waitcondition.toBeClickable, managePortfolioPage.addportfoliobutton);
        clickObjectJavascript(managePortfolioPage.addportfoliobutton, "Add protfolio button");

    }
    
    public void verifyPortfolioInformationPage() throws Exception {
        waitForElement(managePortfolioPage.loanInformation);
        softAssert.assertTrue(managePortfolioPage.loanInformation.isDisplayed(), "loanInformation tab not displayed");
        waitForElement(managePortfolioPage.propertyFinancials);
        softAssert.assertTrue(managePortfolioPage.propertyFinancials.isDisplayed(), "propertyFinancials tab not displayed");       
    }
    
    public void clickSnapshot() throws Exception {
        waitForElement(managePortfolioPage.snapShotTab);
        clickObject(managePortfolioPage.snapShotTab, "Snapshot");
    }
    
    public void clickFirstMSA() throws Exception {
        waitForElement(managePortfolioPage.clickMSALink);
        clickObject(managePortfolioPage.clickMSALink, "First MSA");
    }
    
    public void clickFirstPropLink() throws Exception {
        waitForElement(managePortfolioPage.propName);
        clickObject(managePortfolioPage.propName, "First Property link");
    }

    public void clickEditLoan() throws Exception {
        waitForElement(managePortfolioPage.editLoan);
        clickObject(managePortfolioPage.editLoan, "Edit Loan");
    }

    public void clickpropFinancials() throws Exception {
        waitForElement(managePortfolioPage.propFinancials);
        clickObject(managePortfolioPage.propFinancials, "prop Financials");
    }

    public void clickOnFoodBev(String fAndB, String fAndB1) throws Exception {
        // First time updating food and beverages
        waitForElement(managePortfolioPage.foodBevBefore);
        clickObjectJavascript(managePortfolioPage.foodBevBefore, "Food Beverages");
        clearNTypeValue(managePortfolioPage.foodBevBefore, fAndB, "Food Beverages");
        Thread.sleep(3000);
        clickonUpdateLoan();
        waitForLoading();
        //wait.until(ExpectedConditions.invisibilityOf(managePortfolioPage.foodBevBefore));
        String fAndBValue = managePortfolioPage.foodBevAfter.getText();
        System.out.println("First updated value: " + fAndBValue);

        // Second time updating food and beverages
        clickEditLoan();
        waitForElement(managePortfolioPage.foodBevBefore);
        clickObjectJavascript(managePortfolioPage.foodBevBefore, "Food Beverages");
        clearNTypeValue(managePortfolioPage.foodBevBefore, fAndB1, "Food Beverages");
        Thread.sleep(3000);
        clickonUpdateLoan();
        waitForLoading();
        //wait.until(ExpectedConditions.invisibilityOf(managePortfolioPage.foodBevBefore));
        String fAndBValue1 = managePortfolioPage.foodBevAfter.getText();
        System.out.println("Second updated value: " + fAndBValue1);

        // comparing first and second
        softAssert.assertNotEquals(fAndBValue, fAndBValue1, "Food Beverages value is not updated.");
    }

    public void clickonRealEstateTx(String realEstateTx1, String realEstateTx2) throws Exception {
        // First time updating food and beverages
        clickEditLoan();
        scrollIntoView(managePortfolioPage.realEstateTx, "Real Estate Tx");
        waitForElement(managePortfolioPage.realEstateTx);
        clickObjectJavascript(managePortfolioPage.realEstateTx, "Real Estate Tx");
        clearNTypeValue(managePortfolioPage.realEstateTx, realEstateTx1, "Real Estate Tx");
        Thread.sleep(3000);
        clickonUpdateLoan();
        waitForLoading();
        //wait.until(ExpectedConditions.invisibilityOf(managePortfolioPage.realEstateTx));
        String realEstateTxVal = managePortfolioPage.realEstateTxAfter.getText();
        System.out.println("First updated value: " + realEstateTxVal);

        // Second time updating food and beverages
        clickEditLoan();
        waitForElement(managePortfolioPage.realEstateTx);
        clickObjectJavascript(managePortfolioPage.realEstateTx, "Real Estate Tx");
        clearNTypeValue(managePortfolioPage.realEstateTx, realEstateTx2, "Real Estate Tx");
        Thread.sleep(3000);
        clickonUpdateLoan();
        waitForLoading();
        //wait.until(ExpectedConditions.invisibilityOf(managePortfolioPage.realEstateTx));
        String realEstateTxVal1 = managePortfolioPage.realEstateTxAfter.getText();
        System.out.println("Second updated value: " + realEstateTxVal1);

        // comparing first and second
        softAssert.assertNotEquals(realEstateTxVal, realEstateTxVal1, "Real Estate Tx value is not updated.");
    }

    public void clickonUpdateLoan() throws Exception {
        waitForElement(managePortfolioPage.updateLoan);
        clickObject(managePortfolioPage.updateLoan, "Update Loan");
    }

    public void verifyFilterAndAddColumnOnPortfolio(String columnName) throws Exception {
        waitForElement(loanlistingPage.columnssidebar);
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar opened");

        Thread.sleep(2000);
        clickObject(loanlistingPage.columnSidebarSearch, "Searchbar clicked");

        clearNTypeValue(loanlistingPage.columnSidebarSearch, columnName, "Column sidebar search");
        Thread.sleep(2000);
        WebElement element1 = driver.findElement(By.xpath(
                "//span[@class='ag-column-tool-panel-column-label' and contains(text(), '" + columnName + "')]"));
        waitForElement(element1);
        clickObject(element1, "Checkbox");
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar opened");
    }

    public void removeAllCheckedColumnPortfolio() throws Exception {
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar");
        Thread.sleep(2000);
        if (managePortfolioPage.selectAllCheckbox.isDisplayed())
            clickObject(managePortfolioPage.selectAllCheckbox, "All column select checked");
        
        //clickObject(managePortfolioPage.selectAllCheckbox, "All column select checked");
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar");

    }

    public void addClassToProperty(String firstClassVal, String secondClassVal) throws Exception {
        List<WebElement> classVal = managePortfolioPage.classCol;
        if (classVal.size() > 0) {
            clickObject(classVal.get(1), "First Input");
            classVal.get(1).sendKeys(firstClassVal);
            classVal.get(1).sendKeys(Keys.ENTER);

            clickObject(classVal.get(2), "Second Input");
            classVal.get(2).sendKeys(secondClassVal);
            classVal.get(2).sendKeys(Keys.ENTER);

        }
    }
    
    public void clickGetValutionButtonAndAddDateAndPercentage(String per) throws Exception {
        waitForElement(managePortfolioPage.getValuationBtn);
        clickObject(managePortfolioPage.getValuationBtn, "Get Valuation button");
        waitForElement(managePortfolioPage.getValuationModalBox);

        clearNTypeValue(managePortfolioPage.valuationdate, currentDate, "valuationdate date");
        clearNTypeValue(managePortfolioPage.valuationTT, per, "TT");
        wait.until(ExpectedConditions.elementToBeClickable(managePortfolioPage.getValuationButton));
        clickObject(managePortfolioPage.getValuationButton, "Get Valuation Modal button");
    }

    public void clickMoadalButton(String a1Value, String c1Value) throws Exception {
        // Get current value of two class
        waitForElement(managePortfolioPage.modalButton);
        clickObject(managePortfolioPage.modalButton, "Modal Button");
        waitForElement(managePortfolioPage.valuationModal);
        String aClassValueBefore = getText(managePortfolioPage.classA1, "class A before update", "value");
        String bClassValueBefore = getText(managePortfolioPage.classC1, "class C before update", "value");

        // Update the value of two class
        managePortfolioPage.classA1.sendKeys(a1Value);
        managePortfolioPage.classA1.sendKeys(Keys.ENTER);
        String aClassValueAfter = getText(managePortfolioPage.classA1, "class A after update", "value");
        managePortfolioPage.classC1.sendKeys(c1Value);
        managePortfolioPage.classC1.sendKeys(Keys.ENTER);
        String bClassValueAfter = getText(managePortfolioPage.classC1, "class C after update", "value");

        // Compare the value of two class
        clickObject(managePortfolioPage.revertToPresets, "reverttoPresets");
        softAssert.assertEquals(!aClassValueAfter.equals(aClassValueBefore), true);
        softAssert.assertEquals(!bClassValueAfter.equals(bClassValueBefore), true);
    }
    
}
