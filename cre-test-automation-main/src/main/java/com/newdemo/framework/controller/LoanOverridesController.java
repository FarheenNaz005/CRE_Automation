package com.newdemo.framework.controller;


import java.nio.file.Paths;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.LoanOverridePage;

public class LoanOverridesController extends ReuseableFunctions {
    LoanOverridePage loanOverridePage = null;
    public LoadData data = null;
    SoftAssert softAssert = null;

    public LoanOverridesController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        loanOverridePage = PageFactory.initElements(driver, LoanOverridePage.class);
    }

    /**
     * validation methods
     **/
    public void verifyPortfolioName(String portfolioName) throws Exception {
        elementPresentorEnabled(loanOverridePage.pageHeader, "Present", "Header");
        String header = getText(loanOverridePage.pageHeader, "page header", "innerText");
        softAssert.assertTrue(header.contains(portfolioName));
    }

    /**
     * Action methods
     **/
    public void uploadOverride(String overrideName, String uploadFileName) throws Exception {
        waitForElement(loanOverridePage.overrideNameInput);
        clearNTypeValue(loanOverridePage.overrideNameInput, overrideName, "override name input");
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("cloud")) {
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            loanOverridePage.overrideNameInput = ((RemoteWebDriver) driver)
                    .findElement(By.id("fileToUpload"));
        }
        loanOverridePage.fileUploadInput.sendKeys(Paths.get(System.getProperty("user.dir"), BaseSetupClass.inputFilepath, uploadFileName).toString());
        clickObject(loanOverridePage.saveButton, "override save button");
        // Explicit wait condition for alert
        WebDriverWait w = new WebDriverWait(driver, 5);
        //alertIsPresent() condition applied
//        if (w.until(ExpectedConditions.alertIsPresent()) == null)
//            System.out.println("Alert not exists");
//        else
//            driver.switchTo().alert().accept();

    }

    public void verifyOverride(String overrideName) throws Exception {
        boolean isAlertPresent = false;
        try {
            driver.switchTo().alert();
            isAlertPresent = true;

        } catch (NoAlertPresentException e) {
            // Do nothing
        } 
        if (isAlertPresent) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        waitForElement(loanOverridePage.overrideByName(overrideName));
        softAssert.assertTrue(loanOverridePage.overrideByName(overrideName).isDisplayed(), overrideName + " is displayed in override list");
    }

    public void deleteOverride(String overrideName) throws Exception {
        waitForElement(loanOverridePage.overrideByName(overrideName));
        clickObject(loanOverridePage.overrideDeleteByName(overrideName), "delete link for " + overrideName);
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(loanOverridePage.overrideDeleteByName(overrideName)));
    }

    public void closeLoanOverridePage() {
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(newTab.get(0));
    }

}
