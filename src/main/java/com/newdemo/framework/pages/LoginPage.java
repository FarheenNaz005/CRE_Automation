package com.newdemo.framework.pages;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class LoginPage {

    WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);
    @FindBy(css = "button[ng-click='login()']")
    public WebElement loginButton;
    @FindBy(css = "input[name='email']")
    public WebElement userName;
    @FindBy(css = "input[name='password']")
    public WebElement password;
    @FindBy(css = "button.auth0-lock-submit")
    public WebElement login;
    @FindBy(xpath = ".//a[contains(text(),'Analytics')]")
    public WebElement analytics;
    @FindBy(xpath = ".//a[contains(text(),'Derivative')]")
    public WebElement derivative;
    @FindBy(xpath = ".//a[contains(text(),'Loan')]")
    public WebElement loan;

    // dynamic element
    public WebElement testEnv(String envName) {
        return wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@title='" + envName + "']")));
    }
}
