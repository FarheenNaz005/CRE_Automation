package com.newdemo.framework.controller.portfolio;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.controller.CREListingPageController;
import com.newdemo.framework.pages.CommentsPage;
import com.newdemo.framework.pages.StratsPage;
import com.newdemo.framework.pages.portfolio.PortfolioPage;

public class PortfolioPageController extends CREListingPageController {
    PortfolioPage portfolioPage = null;
    CommentsPage commentTab = null;
    StratsPage stratsPage = null;

    public PortfolioPageController(WebDriver driver) throws Exception {
        super(driver);
        portfolioPage = PageFactory.initElements(driver, PortfolioPage.class);
        commentTab = PageFactory.initElements(driver, CommentsPage.class);
        stratsPage = PageFactory.initElements(driver, StratsPage.class);
    }

    /**
     * validation methods
     **/
    public void verifyCommentsLink() throws Exception {
        waitForElement(portfolioPage.navCommentLink);
        elementPresentorEnabled(portfolioPage.navCommentLink, "Present", "Portfolio page | Comments link");
    }

    public void verifyCalculatorLink() throws Exception {
        waitForElement(portfolioPage.navCalculatorsLink);
        elementPresentorEnabled(portfolioPage.navCalculatorsLink, "Present", "Portfolio page | Calculator link");
    }

    public void verifyCalcLoanOverridesLink() throws Exception {
        waitForElement(portfolioPage.calcLoanOverrideLink);
        elementPresentorEnabled(portfolioPage.calcLoanOverrideLink, "Present",
                "Portfolio page | Calc | Loan Overrides link");
    }

    public void verifyCalcLoanByLoanLink() throws Exception {
        waitForElement(portfolioPage.calcLoanByLoanLink);
        elementPresentorEnabled(portfolioPage.calcLoanByLoanLink, "Present",
                "Portfolio page | Calc | Loan by Loan link");
    }

    /**
     * Action methods
     **/
    public void clickCommentLink() throws Exception {
        clickObject(portfolioPage.navCommentLink, "comment tab");
        /*
         * Robot robot = new Robot(); // press key Ctrl+Shift+r
         * robot.keyPress(KeyEvent.VK_CONTROL); robot.delay(100);
         * robot.keyPress(KeyEvent.VK_SHIFT); robot.delay(100);
         * robot.keyPress(KeyEvent.VK_R); // relase key Ctrl+Shift+r robot.delay(100);
         * robot.keyRelease(KeyEvent.VK_R); robot.delay(100);
         * robot.keyRelease(KeyEvent.VK_SHIFT); robot.delay(100);
         * robot.keyRelease(KeyEvent.VK_CONTROL);
         */
    }

    public void clickCalculatorLink() throws Exception {
        clickObject(portfolioPage.navCalculatorsLink, "calculator tab");
    }

    public void clickCalcLoanOverridesLink() throws Exception {
        clickObject(portfolioPage.calcLoanOverrideLink, "Calc | Loan Overrides tab");
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
    }

    public void clickCalcLoanByLoanLink() throws Exception {
        clickObject(portfolioPage.calcLoanByLoanLink, "Calc | Loan by Loan tab");
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
    }

    public void deleteRecordfromPortfolio() throws Exception {
        Thread.sleep(5000);
        try {
            if (portfolioPage.checkallsize.size() == 1) {
                clickObject(portfolioPage.checkall, "checkall record in Portfolio button");
                clickObject(portfolioPage.deleterecords, "Delete record in Portfolio button");
                clickObject(portfolioPage.confirmDeleterecords, "Confirm Delete record in Portfolio button");
                waitForElement(portfolioPage.empyprotfoliMessage);
            }

        } catch (Exception objException) {

        }
    }

    public void verifyandaddTTpercentage(String ttpercentage) throws Exception {
        Thread.sleep(3000);
        clickObject(portfolioPage.ttpercentage, "ttpercentage clicked");
        clearNTypeValue(portfolioPage.ttpercentage, ttpercentage, ttpercentage);

    }

    public void clickGetValuation() throws Exception {
        elementPresentorEnabled(portfolioPage.getValuation, "Enabled", "GetValuation Enabled");
        clickObject(portfolioPage.getValuation, "GetValuation clicked");
        Thread.sleep(3000);
    }

    public void clickSaveEdits() throws Exception {
        elementPresentorEnabled(portfolioPage.saveEdits, "Enabled", "Save Edits Enabled");
        clickObject(portfolioPage.saveEdits, "Save Edits clicked");
        Thread.sleep(10000);

    }

    public void clickModel() throws Exception {
        elementPresentorEnabled(portfolioPage.model, "Enabled", "Model Enabled");
        clickObject(portfolioPage.model, "Model clicked");
        Thread.sleep(3000);

    }

    public void verifyDownloadValuations() throws Exception {
        elementPresentorEnabled(portfolioPage.downloadValuations, "", "Download Valuations Enabled");
    }

    public void verifySaveValuation() throws Exception {
        elementPresentorEnabled(portfolioPage.saveValuation, "", "Save Valuation Enabled");
        // clickObject(loanlistingPage.DownloadValuations, "Download Valuations
        // clicked");

    }

    public void verifySavedValuationList() throws Exception {
        waitForElement(portfolioPage.valuationDropDown);
        clickObject(portfolioPage.valuationDropDown, "valuation Drop Down clicked");
        for (int i = 1; i < portfolioPage.valuationList.size(); i++) {

            String values = portfolioPage.valuationList.get(i).getText();
            if (values.equalsIgnoreCase("testanish_2") || values.equalsIgnoreCase("testanish_5")) {

                System.out.println(values + " : Found in the saved Valuation list");
            }
        }
    }

    public void clicksavemodelValuation() throws Exception {
        clickObject(portfolioPage.saveValuation, "Save Valuation  clicked");
    }

    public void clicknVerifyDownloadValuation() throws Exception {
        waitForElement(portfolioPage.downloadValuations);
        clickObject(portfolioPage.downloadValuations, "Download Valuations Clicked");
        Thread.sleep(1000);
        waitUntilDonwloadCompleted();
    }

    public void addnSaveValuationname(String ttpercentage) throws Exception {
        waitForElement(portfolioPage.valuationName);
        clickObject(portfolioPage.valuationName, "valuation Name clicked");

        clearNTypeValue(portfolioPage.valuationName, "Testvaluation_" + ttpercentage, "Testvaluation_" + ttpercentage);
        driver.switchTo().activeElement();
        clickObject(portfolioPage.acceptAlert, "Save Valuation Alert clicked");
        clickObject(portfolioPage.acceptAlert, "view portfolio clicked");
        waitForElement(portfolioPage.propertyListingCheckbox);
    }

    public void clickapplyModel() throws Exception {
        waitForElement(portfolioPage.applyModel);
        clickObject(portfolioPage.applyModel, "apply Model clicked");
        Thread.sleep(3000);
    }

    public void verifyModelpopupvalues() throws Exception {

        System.out.println("model values ::" + portfolioPage.modelvalues.size());

        for (int i = 1; i < portfolioPage.modelvalues.size(); i++) {

            String values = portfolioPage.modelvalues.get(i).getText();

            if (!values.equals(null)) {
                System.out.println(values);
            } else {
                break;
            }
        }
    }

    /**
     * comment page
     **/
    public void verifyNewCommentInput() throws Exception {
        waitForElement(commentTab.newCommentInput);
        elementPresentorEnabled(commentTab.newCommentInput, "Present", "comment | new comment input");
    }

    public void verifysaveCommentButton() throws Exception {
        waitForElement(commentTab.saveCommentButton);
        elementPresentorEnabled(commentTab.saveCommentButton, "Present", "comment | save comment button");
    }

    public void addPortfolioComment(String comment) throws Exception {
        waitForElement(commentTab.newCommentInput);
        clearNTypeValue(commentTab.newCommentInput, comment, "New comment input");
        clickObject(commentTab.saveCommentButton, "save comment button");
    }

    public void verifyPortfolioComment(String comment) throws Exception {

        waitForElement(commentTab.commentByText(comment));
        elementPresentorEnabled(commentTab.commentByText(comment), "Present", "comment | comment text " + comment);

    }

    public void editPortfolioComment(String oldComment, String newComment) throws Exception {
        clickObject(commentTab.commentEditButton(oldComment), "edit comment button");
        waitForElement(commentTab.editCommentInput);
        clearNTypeValue(commentTab.editCommentInput, newComment, "Edit comment input");
        clickObject(commentTab.editCommentSave, "save button for Edit comment");
    }

    public void deletePortfolioComment(String comment) throws Exception {
        WebElement com = commentTab.commentByText(comment);
        clickObject(commentTab.commentDeleteButton(comment), "delete comment button");
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(com));
    }

    /**
     * Strats Verification
     **/
    public void verifyStratsPortfolioSummary() throws Exception {
        elementPresentorEnabled(stratsPage.portfolioSummary, "Present", "Strats | Portfolio Summary Section");
        for (int i = 0; i < stratsPage.portfolioSummaryLabels.size(); i++) {
            String label = getText(stratsPage.portfolioSummaryLabels.get(i), i + " label of stats", "innerText");
            String value = getText(stratsPage.portfolioSummaryValues.get(i), " value of stats: " + label, "innerText");
            softAssert.assertFalse(label.isEmpty() && value.isEmpty(),
                    "value and label for stat: " + i + "should not be empty");
        }
    }

    public void verifyStratSelection() throws Exception {
        elementPresentorEnabled(stratsPage.stratsTypeDropdown, "Present", "Strats type dropdown");
    }

    public void selectStratsDropdownType(String stratsType) throws Exception {
        clickObject(stratsPage.stratsTypeDropdown, "strats type dropdown");
        clickObject(stratsPage.stratDropdownChoice(stratsType), "Strats type:" + stratsType);
    }

    public void verifyStratsHeaderButton() throws Exception {
        elementPresentorEnabled(stratsPage.stratDownloadButton, "Present", "Download button");
        elementPresentorEnabled(stratsPage.stratPrintButton, "Present", "Print button");
        elementPresentorEnabled(stratsPage.stratRangesButton, "Present", "Ranges button");
    }

    public void verifyStratsSidePanel() throws Exception {
        elementPresentorEnabled(stratsPage.sidePanel, "Present", "Strats | Side Panel");
        elementPresentorEnabled(stratsPage.sidePanelStratSelect, "Present", "Strats | Side Panel strat selection");
        elementPresentorEnabled(stratsPage.sidePanelDisplayButton, "Present", "Strats | Side Panel display button");
    }

    public void verifyStratsTableAndChart() throws Exception {
        elementPresentorEnabled(stratsPage.stratsTable, "Present", "Strats | table");
        elementPresentorEnabled(stratsPage.stratsChart, "Present", "Strats | charts");
    }

    public void verifySelectStrats(String stratsName) throws Exception {
        selectFromDropDownVisibleText(stratsPage.sidePanelStratSelect, "Strats side panel select", stratsName);
    }

    public void clickStratsRanges() throws Exception {
        clickObject(stratsPage.stratRangesButton, "Strat ranges button");
    }

    public void verifyStratsRangesList() throws Exception {
        for (WebElement ele : stratsPage.stratsRangesList) {
            System.out.println(getText(ele, "strats", "innerText"));
        }
    }

    public void verifyStratsRangesButtons() throws Exception {
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        elementPresentorEnabled(stratsPage.stratsRangesReadNotesButton, "Present", "Strats ranges | read notes");
        elementPresentorEnabled(stratsPage.stratsRangesResetAllButton, "Present", "Strats ranges | reset all button");
        elementPresentorEnabled(stratsPage.stratsRangesSaveButton, "Present", "Strats ranges | save button");
        elementPresentorEnabled(stratsPage.stratsRangesCancelButton, "Present", "Strats ranges | cancel button");
    }

    public void verifyStratsRangesReadNotes() throws Exception {
        clickObject(stratsPage.stratsRangesReadNotesButton, "strats ranges | read notes button");
        waitForElement(stratsPage.stratsRangesHintDialog);
        clickObject(stratsPage.stratsRangesHintDialogClose, "strats ranges | hint dialog close");
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(newTab.get(0));
    }
}
