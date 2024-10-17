package com.newdemo.framework.listner;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.newdemo.framework.base.BaseSetupClass;

public class TestResultListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        ((JavascriptExecutor) BaseSetupClass.getDriver()).executeScript("lambda-status=failed");
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            String s = "yogendrabinmile:yBEQc6HUFnciunDo8LoeHMKUDi5Gq7TczhjFsNAIZYxqh2sihq";
            m.update(s.getBytes(), 0, s.length());
            System.out.println("https://automation.lambdatest.com/public/video?sessionID="
                    + ((RemoteWebDriver) BaseSetupClass.getDriver()).getSessionId() + "&auth="
                    + new BigInteger(1, m.digest()).toString(16));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ((JavascriptExecutor) BaseSetupClass.getDriver()).executeScript("lambda-status=passed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ((JavascriptExecutor) BaseSetupClass.getDriver()).executeScript("lambda-status=skipped");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }
}
