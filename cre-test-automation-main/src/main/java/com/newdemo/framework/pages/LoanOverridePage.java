package com.newdemo.framework.pages;


//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class LoanOverridePage {

    WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    /**
     * Elements
     **/
    @FindBy(css = "div.specialheader")
    public WebElement pageHeader;

    @FindBy(css = "input[name='overrideId']")
    public WebElement overrideNameInput;

    @FindBy(css = "input[name='overrideDescriptionToSave']")
    public WebElement overrideCommentInput;

    @FindBy(css = "a[href*='saveUpload']")
    public WebElement saveButton;

    @FindBy(css = "#fileToUpload")
    public WebElement fileUploadInput;


    /**
     * Dynamic Element
     **/

    public WebElement overrideByName(String overrideName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//td[text()='" + overrideName + "']")));
    }

    public WebElement overrideDeleteByName(String overrideName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='DeleteLoanOverrideLP(\\'" + overrideName + "\\')']")));
    }


}
