package com.newdemo.framework.controller.portfolio;


import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.portfolio.PortfolioLoanDetailPage;

public class PortfolioLoanDetailPageController extends ReuseableFunctions {
    PortfolioLoanDetailPage portfolioLoanDetailPage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public PortfolioLoanDetailPageController(WebDriver driver) throws Exception {
        super(driver);
        portfolioLoanDetailPage = PageFactory.initElements(driver, PortfolioLoanDetailPage.class);
    }


    /**
     * validation methods
     **/
    public void verifyportfolioName(String portfolioName) throws Exception {
        waitForElement(portfolioLoanDetailPage.loanPortfolioName);
        softAssert.assertEquals(getText(portfolioLoanDetailPage.loanPortfolioName, "portfolio name", "innerText"), portfolioName, "portfolio name doesn't match");
    }

    public void verifypropertyName(String propertyName) throws Exception {
        waitForElement(portfolioLoanDetailPage.propertyName);
        elementPresentorEnabled(portfolioLoanDetailPage.propertyName, "Present", "Property name");
        softAssert.assertEquals(getText(portfolioLoanDetailPage.propertyName, "property name", "innerText"), propertyName, "property name doesn't match");
    }

    public void verifyTopButton() throws Exception {
        softAssert.assertTrue(portfolioLoanDetailPage.relatedRecordButton.isDisplayed(), "Related record button is displayed.");
        softAssert.assertTrue(portfolioLoanDetailPage.editButton.isDisplayed(), "Edit button is displayed.");
        softAssert.assertTrue(portfolioLoanDetailPage.historyButton.isDisplayed(), "History button is displayed.");
        softAssert.assertTrue(portfolioLoanDetailPage.downloadButton.isDisplayed(), "Download button is displayed.");
    }


    /**
     * Action methods
     **/
    public void clickEditLoanButton() throws Exception {
        clickObject(portfolioLoanDetailPage.editButton, "edit loan button");
    }

    public void updateLoanDetail(HashMap<String, String> loanDetail) throws Exception {
        clickEditLoanButton();
        waitForElement(portfolioLoanDetailPage.updateButton);
        clearNTypeValue(portfolioLoanDetailPage.inputOriginalBal, loanDetail.get("originalBal"), "original balance");
        clearNTypeValue(portfolioLoanDetailPage.inputYearBuilt, loanDetail.get("yearBuilt"), "year built");
        clickUpdateLoanButton();

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("button.update-button")));
        Thread.sleep(2000);
        softAssert.assertEquals(getText(portfolioLoanDetailPage.formOriginalBal, "original Balance", "innerText").replace(",", ""), loanDetail.get("originalBal"), "Original Balance doesn't match");
        softAssert.assertEquals(getText(portfolioLoanDetailPage.formYearBuilt, "Year Built", "innerText"), loanDetail.get("yearBuilt"), "Year Built doesn't match");
    }

    public void clickUpdateLoanButton() throws Exception {
        clickObject(portfolioLoanDetailPage.updateButton, "update loan button");
    }


}
