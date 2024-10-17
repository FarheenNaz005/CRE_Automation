package com.newdemo.framework.controller;

import java.io.File;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.pages.LoginPage;
import com.newdemo.framework.data.LoadData;

public class LoginController extends ReuseableFunctions {
    LoginPage login = null;
    LoadData data = null;
    String sheet1 = System.getProperty("user.dir") + "/Results/Test1.csv";

    String sheet2 = System.getProperty("user.dir") + "/Results/Test2.csv";

    File file2 = new File(sheet2);


    public LoginController(WebDriver driver) throws Exception {
        super(driver); //  driver instance of ReuseableFunctions class that all the page objects inherit from

        login = PageFactory.initElements(driver, LoginPage.class);

    }

    public void launchURL() throws Exception {
        launchURL(data.strDTURL);

        //runpython(file1);
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

    public void clicktestEnv(String envName) throws Exception {
        clickObject(login.testEnv(envName), "testEnv clicked");

    }

    public Set<Cookie> getSessionCookies() {
        return BaseSetupClass.getDriver().manage().getCookies();
    }

    public Cookie getNamedCookie(String cookieName) {
        return BaseSetupClass.getDriver().manage().getCookieNamed(cookieName);
    }
}
