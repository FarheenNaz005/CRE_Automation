package com.newdemo.framework.controller.loan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.loan.ValuationManagerPage;

public class ValuationManagerController extends ReuseableFunctions {
    ValuationManagerPage valuationManager = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public ValuationManagerController(WebDriver driver) throws Exception {
        super(driver);
        valuationManager = PageFactory.initElements(driver, ValuationManagerPage.class);
    }

    //Action Methods
    public void goToValuation(String valuationName) throws Exception {
        clickObjectJavascript(valuationManager.valuationLink(valuationName), valuationName + "valuation link");
    }

    public void goToFirstProperty() throws Exception {
        clickObject(valuationManager.trusteeLoanIDList, "First Trustee loan id ");
    }

    //Validation Methods
    public void verifyValuationManagerPage() throws Exception {
        softAssert.assertTrue(getBrowserUrl().contains("/valuation/manager?valuationType=Property"), "url does not match");
        waitForElement(Waitcondition.toBeVisible, valuationManager.heading);
        elementPresentorEnabled(valuationManager.heading, "Present", "Heading");
        elementPresentorEnabled(valuationManager.valuationCount, "Present", "valuation count");

    }

    public void verifyValuationCount() throws Exception {
        int expectedCount = Integer.parseInt((getText(valuationManager.valuationCount, "valuation count", "innerText").split(" "))[0]);
        int actualCount = valuationManager.valuationList.size();
        softAssert.assertEquals(actualCount, expectedCount, "valuation count does not match with header count");
    }

    public void verifyValuation(String valuationName) {
        softAssert.assertTrue(valuationManager.valuationLink(valuationName).isDisplayed(), valuationName + " : is not visible on screen");
    }


}
