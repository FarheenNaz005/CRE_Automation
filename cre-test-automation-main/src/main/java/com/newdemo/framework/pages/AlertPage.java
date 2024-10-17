package com.newdemo.framework.pages;


//import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class AlertPage {

    /**
     * nav link buttons
     **/
    @FindBy(xpath = ".//span[text()='Manager']/..")
    public WebElement navManagerButton;

    @FindBy(xpath = ".//span[contains(.,'View Alerts')]/.")
    public WebElement navViewAlertButton;


    /**
     * Manager Page
     **/
    @FindBy(xpath = "//div[@role='presentation']/input[@class='ag-floating-filter-input']")
    public WebElement managerPagePropertyNameFilterInput;

    @FindBy(xpath = ".//div[@col-id='alertName' and ./div/a[contains(text(),'')]]")
    public WebElement firstAlertItem;

    @FindBy(xpath = ".//div[@col-id='alertName' and ./div/a[contains(text(),'')]]")
    public List<WebElement> listOfAlerts;

    @FindBy(xpath = "//button[@class='btn btn-link absolute-center']")
    public WebElement firstEditButton;

    @FindBy(xpath = "//label[contains(text(), 'Off')]")
    public List<WebElement> editAlertModalSwitchOff;

    @FindBy(xpath = "//button[@ng-click='saveAlertSettings(dealAlert)']")
    public WebElement saveButton;

    @FindBy(xpath = "//button[@class='btn btn-link']")
    public WebElement cancelButton;

    @FindBy(xpath = "//span[contains(text(), 'Delete Alert')]")
    public WebElement deleteAlertButton;

    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
    public WebElement deleteAlertConfirmButton;

}
