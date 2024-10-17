package com.newdemo.framework.pages;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PropertyListingPage {

    //nav links
    @FindBy(xpath = ".//span[text()='Strats']/..")
    public WebElement navStratsLink;

    //common listing button
    @FindBy(xpath = ".//span[contains(text(),'Strats')]")
    public WebElement strats;

    @FindBy(xpath = ".//span[contains(text(),'Benchmarks')]")
    public WebElement benchmarks;

    @FindBy(xpath = ".//span[contains(text(),'Listing')]")
    public WebElement listing;

    @FindBy(xpath = ".//span[text()='List']/..")
    public WebElement listViewButton;

    @FindBy(xpath = ".//span[text()='Map']/..")
    public WebElement mapViewButton;

    //common button after property selected
    @FindBy(xpath = ".//div[@class='navbar-left']//span[contains(text(),'Add to Portfolio')]")
    public WebElement addtoportoflio;

    @FindBy(xpath = ".//span[text()='Deal List']/..")
    public WebElement dealListButton;

    @FindBy(css = "button[ng-click*='createAlert']")
    public WebElement createAlertButton;

    @FindBy(xpath = ".//span[text()='Alerts']/..")
    public WebElement alertButton;

    @FindBy(css = "a[ng-click='downloadAllLoans()']")
    public WebElement downloadAllButton;

    @FindBy(xpath = ".//button[./i[contains(@class,'fa-download')]]")
    public WebElement downloadButton;

    /* Map view */

    @FindBy(xpath = ".//div[@class='leaflet-google-layer leaflet-top leaflet-left']")
    public WebElement mapLoad;

    @FindBy(css = "div[loan-listing-map] div[loan-list-panel] .list-group li table tr")
    public WebElement mapViewData;

    /* List view */

    @FindBy(css = "div[custom-views-dropdown-button]")
    public WebElement customViewDropdownButton;

    @FindBy(xpath = "//span[contains(text(),'Views')]")
    public WebElement customViewDropdownButton1;

    @FindBy(css = "i[ng-click='resetGridFilters()']")
    public WebElement resetButton;

    @FindBy(xpath = ".//trepp-http-activity-widget")
    public WebElement loading;

    //Property list table
    @FindBy(xpath = ".//div[@col-id='loanCheckbox']//div[@class='ag-cell-label-container']//span[contains(@class,'ag-icon-checkbox')]")
    public WebElement selectAllPropertyCheckbox;

    @FindBy(xpath = ".//span[@class='ag-header-cell-text'][contains(text(),'Class')]")
    public List<WebElement> classcolumn;

    @FindBy(css = "div.ag-header-viewport div.ag-header-cell span.ag-header-cell-text")
    public List<WebElement> propertyListingUnpinnedColumn;

    @FindBy(xpath = ".//div[@class='ag-column-tool-panel-column ag-toolpanel-indent-0']//span[@class='ag-column-tool-panel-column-label']")
    public List<WebElement> columlist;

    @FindBy(xpath = ".//span[@class='ag-selection-checkbox']")
    public WebElement propertyListingCheckbox;

    @FindBy(xpath = ".//span[@class='ag-selection-checkbox']")
    public By propertyListingCheckboxs;

    //side panel
    @FindBy(css = "[single-loan-panel].pull-left")
    public WebElement propertySidePanel;


    //filter for column
    @FindBy(css = ".ag-header-viewport div[aria-rowindex='2'] .ag-floating-filter-button button")
    public WebElement firstColumnFilterbutton;

    @FindBy(xpath = ".//label[span//text()[contains(.,'(Select All)')]]")
    public WebElement choiceFilterSelectAll;

    @FindBy(xpath = ".//select[@class='ag-filter-select']")
    public WebElement dropdownFilterSelect;

    @FindBy(xpath = ".//div[@class='ag-filter-body']//input[@class='ag-filter-filter']")
    public WebElement dropdownFilterInput;
    @FindBy(xpath = ".//div[@class='ag-filter-apply-panel']/button[@ref='eApplyButton']")
    public WebElement applyFilterButton;

    //column side bar
    @FindBy(xpath = ".//span[contains(text(),'Columns')]")
    public WebElement columnssidebar;

    @FindBy(css = "div.ag-column-select-panel input.ag-primary-cols-filter")
    public WebElement columnSidebarSearch;

    @FindBy(xpath = ".//div[@class='ag-tool-panel-wrapper']//span[@class='ag-icon ag-icon-checkbox-checked']")
    public List<WebElement> columnSidebarCheckboxChecked;

    @FindBy(xpath = ".//span[@class='ag-icon ag-icon-checkbox-checked']")
    public WebElement columnssidebarcheckbox;

    @FindBy(xpath = ".//span[@class='ag-icon ag-icon-checkbox-indeterminate']")
    public WebElement columnssidebarunchecked;

    @FindBy(css = "div[ref='eSelect'] span.ag-icon-checkbox-checked")
    public WebElement columnSidebarSelectAllChecked;

    @FindBy(css = "div[ref='eSelect'] span.ag-icon-checkbox-indeterminate")
    public WebElement columnSidebarSelectAllIntermidiate;


    @FindBy(xpath = "//div[@row-index='0']//div[@col-id='propname']//a")
    public WebElement firstrowpropertname;

    @FindBy(xpath = "//div[@row-index='0']//div[@col-id='propName']//a")
    public WebElement portfoliofirstrowpropertname;

    @FindBy(xpath = ".//div[@class='ag-header-select-all ag-labeled ag-label-align-right ag-checkbox']")
    public List<WebElement> checkallsize;

    @FindBy(xpath = ".//div[@class='ag-header-select-all ag-labeled ag-label-align-right ag-checkbox']")
    public WebElement checkall;

    @FindBy(xpath = ".//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-with-height align-c ag-cell-value']")
    public List<WebElement> classlist;

    //modaldialog
    @FindBy(css = "div.modal-dialog")
    public WebElement modalDialog;

    @FindBy(css = "div.modal-dialog .alert-info")
    public WebElement modalDialogAlertInfo;

    @FindBy(css = "div.modal-dialog .alert-success")
    public WebElement modalDialogAlertSuccess;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    public WebElement modalAccept;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    public WebElement modalCancel;

    //Save search modal
    @FindBy(css = ".modal-dialog #name")
    public WebElement saveSearchNameInput;

    @FindBy(css = ".modal-dialog [type='submit']")
    public WebElement saveSearchSubmit;

    //Add portfolio Modal
    @FindBy(xpath = ".//div[@class='tab-content']//li[1]")
    public WebElement selectfirstportfolio;

    @FindBy(css = "div.portfolios-panel ul")
    public WebElement portfolioPanelList;

    @FindBy(css = "tab-content-choose-portfolio #search-input") //#tab-set-pane-choosePortfolio input
    public WebElement choosePortfolioSearchInput;

    @FindBy(css = ".modal-footer .btn-primary")
    public WebElement addbutton;

    @FindBy(xpath = ".//button[contains(text(),'View Portfolio')]")
    public WebElement viewPortfolio;

    @FindBy(xpath = ".//a[.//text()[contains(.,'New Portfolio')]]")
    public WebElement newPortfolioButton;

    @FindBy(css = "tab-content-new-portfolio #name-input") //#tab-set-pane-newPortfolio input
    public WebElement newPortfolioNameInput;

    @FindBy(xpath = ".//button[@class='btn btn-primary' and text()='Add']")
    public WebElement newPortfolioAddButton;

    //Deal Alert modal
    @FindBy(xpath = ".//ul[contains(@class,'deal-alert-triggers')]//label[text()='On']")
    public List<WebElement> dealAlertModalSwitchYes;


}
