package com.newdemo.framework.pages;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class StratsPage {

    private WebDriver driver = BaseSetupClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    /**
     * portfolio summary table
     **/
    @FindBy(css = "table.summary-table")
    public WebElement portfolioSummary;

    @FindBy(css = "table.summary-table th")
    public List<WebElement> portfolioSummaryLabels;

    @FindBy(css = "table.summary-table td")
    public List<WebElement> portfolioSummaryValues;

    /**
     * Header button
     **/
    @FindBy(xpath = ".//a[./span[text()='Strat Ranges']]")
    public WebElement stratRangesButton;

    @FindBy(xpath = ".//a[./span[text()='Download']]")
    public WebElement stratDownloadButton;

    @FindBy(xpath = ".//a[./span[text()='Print']]")
    public WebElement stratPrintButton;


    @FindBy(css = "span.page-title.dropdown-toggle")
    public WebElement stratsTypeDropdown;

    /**
     * Left Side Panel
     **/
    @FindBy(css = "div#left-column")
    public WebElement sidePanel;

    @FindBy(css = "select.plaintablesmbold")
    public WebElement sidePanelStratSelect;

    @FindBy(css = "a#_go_button_")
    public WebElement sidePanelDisplayButton;

    /**
     * tabel and chart
     **/
    @FindBy(css = ".sortable-table")
    public WebElement stratsTable;

    @FindBy(css = ".graph.highlightable")
    public WebElement stratsChart;

    /**
     * Strats Ranges Page
     **/
    @FindBy(xpath = ".//a[./span[text()='Read Notes']]")
    public WebElement stratsRangesReadNotesButton;

    @FindBy(xpath = ".//a[./span[text()='Save']]")
    public WebElement stratsRangesSaveButton;

    @FindBy(xpath = ".//a[./span[text()='Reset All']]")
    public WebElement stratsRangesResetAllButton;

    @FindBy(xpath = ".//a[./span[text()='Cancel']]")
    public WebElement stratsRangesCancelButton;

    @FindBy(css = "h6.header")
    public List<WebElement> stratsRangesList;

    @FindBy(css = "#hints-dialog")
    public WebElement stratsRangesHintDialog;

    @FindBy(css = ".ui-dialog-titlebar-close")
    public WebElement stratsRangesHintDialogClose;

    /**
     * Dynamic Element
     **/
    public WebElement stratDropdownChoice(String stratsType) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h1[@class='header main-title']//li/a[text()[contains(.,'" + stratsType + "')]]")));
    }
}
