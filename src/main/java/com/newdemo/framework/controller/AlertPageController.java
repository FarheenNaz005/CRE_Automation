package com.newdemo.framework.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.AlertPage;

public class AlertPageController extends ReuseableFunctions {
    AlertPage alertPage = null;
    LoadData data = null;
    SoftAssert softAssert = null;

    public AlertPageController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        // from
        alertPage = PageFactory.initElements(driver, AlertPage.class);

    }

    /**
     * nav links
     **/
    public void clickManagerNavLink() throws Exception {
        clickObject(alertPage.navManagerButton, "manager nav link");
    }

    public void clickViewAlertsNavLink() throws Exception {
        clickObject(alertPage.navViewAlertButton, "view Alert nav link");
    }

    /**
     * Verify loans alert is added
     *
     * @param List<String> properties
     */
    public void verifyCreatedAlerts(List<String> properties) throws Exception {
        waitForElement(alertPage.managerPagePropertyNameFilterInput);

        for (String propName : properties) {

            clearNTypeValue(alertPage.managerPagePropertyNameFilterInput, propName, "property name filter");
            WebElement prop = new WebDriverWait(driver, 20)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@col-id='alertName' and ./div/a[contains(text(),'" + propName + "')]]")));
            softAssert.assertTrue(prop.isDisplayed(), propName + " displayed");
        }
    }

    public String searchAlertByName(String propName) throws Exception {
        waitForElement(alertPage.managerPagePropertyNameFilterInput);
        if (propName == null || propName.isEmpty()) {
            propName = getText(alertPage.firstAlertItem, "First Alert", "");
        }
        clearNTypeValue(alertPage.managerPagePropertyNameFilterInput, propName, "property name filter");
        return propName;
    }

    public void clickEditButton() throws Exception {
        WebElement scrollableElement = driver.findElement(By.xpath("//div[@class='ag-body-horizontal-scroll-viewport']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", scrollableElement);
        //JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("window.scrollTo(document.body.scrollWidth, 0)");
        clickObject(alertPage.firstEditButton, "Edit Alert Button");
    }

    /**
     * verifyAlertNotification - verify alert notification on View Alert Page
     */
    public void verifyAlertNotification() {

    }

    public void waitForAlertList() throws Exception {
        waitForElement(Waitcondition.toBeVisible, alertPage.firstAlertItem);

    }

    public void turnOffAlerts() throws Exception {
        int numberOfAlerts = alertPage.editAlertModalSwitchOff.size();
        for (int i = 1; i < numberOfAlerts - 1; i++) {

            WebElement offButton = driver.findElement(By.xpath(
                    "(//label[contains(text(), 'Off')])[" + i + "]"));
            clickObject(offButton, "Turn Off Alert Button");
        }
        clickObject(alertPage.saveButton, "Save Alert Button");
    }

    public void verifyEditedAlerts(String propName) throws Exception {
        clearNTypeValue(alertPage.managerPagePropertyNameFilterInput, propName, "property name filter");
        clickEditButton();
        for (int i = 1; i < alertPage.editAlertModalSwitchOff.size() - 1; i++) {
            // Check if the button state is "off"
            boolean buttonState = alertPage.editAlertModalSwitchOff.get(i).isSelected();
            softAssert.assertFalse(buttonState, "Toggle button " + i + " should be off.");
        }
        clickObject(alertPage.cancelButton, "Cancel Button");

    }

    public void clickDeleteAlertButton() throws Exception {
        softAssert.assertTrue(alertPage.deleteAlertButton.isEnabled());
        clickObject(alertPage.deleteAlertButton, "Delete Alert Button");
        clickObject(alertPage.deleteAlertConfirmButton, "Delete Alert Confirm Button");
    }

    public void verifyDeleteAlert(Set<String> alertList, String propName) throws Exception {
        softAssert.assertFalse(alertList.contains(propName), "Alert for " + propName + " should not be present");
    }

    public Set<String> getListOfAlerts() throws Exception {
        waitForAlertList();
        // Create a Set to store unique texts
        Set<String> propertyNames = new LinkedHashSet<>();

        // Iterate through the WebElements
        for (WebElement element : alertPage.listOfAlerts) {
            // Get the text from the WebElement
            String text = element.getText().trim();

            // Add the text to the Set if it's not empty
            if (!text.isEmpty()) {
                propertyNames.add(text);
            }
        }
        return propertyNames;
    }
}
