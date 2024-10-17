package com.newdemo.framework.controller;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.LoanByLoanPage;

public class LoanByLoanController extends ReuseableFunctions {
    LoanByLoanPage loanByLoanPage = null;
    public LoadData data = null;
    SoftAssert softAssert = null;

    public LoanByLoanController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        loanByLoanPage = PageFactory.initElements(driver, LoanByLoanPage.class);
    }

    /**
     * validation methods
     **/
    public void verifyPortfolioSelected(String portfolioName) throws Exception {
        waitForElement(loanByLoanPage.portfolioSelect);
        elementPresentorEnabled(loanByLoanPage.portfolioSelect, "Present", "portfolio dropdown");
        String pName = getText(loanByLoanPage.portfolioSelect.findElement(By.cssSelector(" option[selected]")),
                "selected portfolio", "innerText");
        softAssert.assertEquals(pName, portfolioName, "portfolio name should be: " + portfolioName);
    }

    public void verifyCalculatedTables() throws Exception {
        waitForElement(loanByLoanPage.individualLoanReportTable);
        for (WebElement ele : loanByLoanPage.overrideSummaryData) {
            softAssert.assertFalse(getText(ele, "", "innerText").isEmpty(), "should nt be empty");
        }

        softAssert.assertTrue(loanByLoanPage.individualLoanReportTable.isDisplayed(),
                "Individual loan table displayed");
    }

    /**
     * Action methods
     **/
    public void selectOverride(String overrideName) throws Exception {
        selectFromDropDownVisibleText(loanByLoanPage.loanOverrideSelect, "Override select", overrideName);
    }

    public void clickCalculateButton() throws Exception {
        clickObject(loanByLoanPage.calculateButton, "calculate button");
    }

    public void closeloanByLoanPage() {
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(newTab.get(0));
    }

}
