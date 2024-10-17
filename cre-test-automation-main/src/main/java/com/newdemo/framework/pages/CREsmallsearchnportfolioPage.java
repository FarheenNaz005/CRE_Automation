package com.newdemo.framework.pages;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CREsmallsearchnportfolioPage {

    @FindBy(xpath = ".//div[contains(@class,'btn-group ng-scope open')]//ul[contains(@class,'with-checkboxes')]//li[29]//a[1]//i[1]")
    public WebElement montanacheckbox;
    @FindBy(xpath = ".//button[contains(@class,'btn btn-primary ng-binding')]//i[contains(@class,'fa fa-search')]")
    public WebElement searchbuttonModal;
    @FindBy(xpath = ".//i[@class='fa fa-table']")
    public WebElement listproperties;
    @FindBy(xpath = ".//span[contains(text(),'Strats')]")
    public WebElement strats;
    @FindBy(xpath = ".//span[contains(text(),'Benchmarks')]")
    public WebElement benchmarks;
    @FindBy(xpath = ".//span[contains(text(),'Listing')]")
    public WebElement listing;
    @FindBy(xpath = ".//strong[contains(text(),'ADVANCED')]")
    public WebElement advancedbutton;

    @FindBy(xpath = ".//div[@class='leaflet-google-layer leaflet-top leaflet-left']")
    public WebElement mapLoad;

    @FindBy(xpath = ".//market-loan-listing-screen[@class='ng-scope ng-isolate-scope']//li[1]//table[1]//tbody[1]//tr[1]//td[2]")
    public WebElement mapViewData;

    @FindBy(xpath = ".//span[contains(text(),'Add to Portfolio')]")
    public WebElement addtoportoflio;

    @FindBy(xpath = ".//div[@class='tab-content']//li[1]")
    public WebElement selectfirstportfolio;

    @FindBy(xpath = ".//button[text()='Add']")
    public WebElement addbutton;

    @FindBy(xpath = ".//button[contains(text(),'View Portfolio')]")
    public WebElement viewPortfolio;

    @FindBy(xpath = ".//div[@class='ag-pinned-left-cols-container']//div[1]//div[3]")
    public WebElement firstrowpropertname;


    @FindBy(xpath = ".//div[@class='ag-header-select-all ag-labeled ag-label-align-right ag-checkbox']")
    public List<WebElement> checkallsize;

    @FindBy(xpath = ".//div[@class='ag-header-select-all ag-labeled ag-label-align-right ag-checkbox']")
    public WebElement checkall;

    @FindBy(xpath = ".//span[contains(text(),'Delete')]")
    public WebElement deleterecords;

    @FindBy(xpath = ".//button[@class='btn btn-danger ng-binding']")
    public WebElement confirmDeleterecords;

    @FindBy(xpath = ".//h2[contains(text(),'This portfolio is empty.')]")
    public WebElement empyprotfoliMessage;

    @FindBy(xpath = ".//span[@class='col col-xs-12 loan-portfolio-name ng-binding'][contains(text(),'testanish')]")
    public WebElement testanish;


    @FindBy(xpath = ".//div[@class='btn-group ng-scope']/button[1]")
    public WebElement openStatedropdown;


    @FindBy(xpath = ".//tags-input[@id='countyState']//input[@placeholder='ex. New York, NY']")
    public WebElement county;

    @FindBy(xpath = ".//li[@class='suggestion-item ng-binding ng-scope']")
    public List<WebElement> nassauCounty;


    @FindBy(xpath = ".//div[@class='col-md-6 ng-binding']")
    public WebElement statsMSA;
    @FindBy(xpath = ".//div[@class='col-md-5 list-data-value ng-binding']")
    public WebElement statsdelinquency;

    @FindBy(xpath = "//input[@class='dateInput__picker']")
    public WebElement valuationdate;
    
    @FindBy(xpath = "//span[contains(text(),'App Val/ SqFt Or Unit')]")
    public WebElement gridtoscrollforbclass;
  
    @FindBy(xpath = "//div[@class= 'ag-cell ag-cell-not-inline-editing ag-cell-with-height align-r ag-cell-value ag-cell-focus ag-cell-range-selected ag-cell-range-selected-1 ag-cell-range-single-cell']")
    
    
    public WebElement gridtoscrollforbclass2;
   
    
    @FindBy(xpath = "//span[@class='closeModal']")
    public WebElement valuationmodal;
    
    @FindBy(xpath = ".//button[@class='ValuationModal__button']")
    public WebElement getValuationmoalbutton;
    
  
  
    
    @FindBy(xpath = "//input[@class='ttInput__pct']")
    public WebElement ttpercentage;

    @FindBy(xpath = ".//button[@class='ValuationButton__btn ']")
    public WebElement getValuation;

    @FindBy(xpath = ".//span[contains(text(),'Save')]")
    public WebElement saveEdits;

    @FindBy(xpath = ".//strong[contains(text(),'Save Valuation')]")
    public WebElement saveValuation;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    public WebElement acceptAlert;


    @FindBy(xpath = ".//strong[contains(text(),'Model')]")
    public WebElement model;

    @FindBy(xpath = ".//i[@class='fa fa-download']")
    public WebElement downloadValuations;

    @FindBy(xpath = ".//input[@class='form-control ng-pristine ng-invalid ng-invalid-required']")
    public WebElement modelname;

    @FindBy(xpath = ".//input[@type='number']")
    public WebElement changeTTpercent;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    public WebElement applyModel;

    @FindBy(xpath = ".//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-with-height ag-cell-value']")
    public List<WebElement> modelvalues;

    @FindBy(xpath = ".//span[@class='ag-header-cell-text']")
    public List<WebElement> modelcolumnames;

    @FindBy(xpath = ".//div[@class= 'ag-floating-bottom-container']//div//span")
    public List<WebElement> valuationpercentage;

    @FindBy(xpath = ".//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-with-height align-c ag-cell-value']")
    public List<WebElement> classlist;

    @FindBy(xpath = ".//span[@class='ag-header-cell-text'][contains(text(),'Class')]")
    public List<WebElement> classcolumn;

    @FindBy(xpath = ".//span[contains(text(),'Columns')]")
    public WebElement columnssidebar;

    @FindBy(xpath = ".//span[@class='ag-icon ag-icon-checkbox-checked']")
    public WebElement columnssidebarcheckbox;

    @FindBy(xpath = ".//span[@class='ag-icon ag-icon-checkbox-indeterminate']")
    public WebElement columnssidebarunchecked;

    @FindBy(xpath = ".//workflow-menu[@class='bottom-menu ng-scope ng-isolate-scope']//li")
    public List<WebElement> valuationList;

    @FindBy(xpath = ".//i[@class='fa fa-lg fa-caret-down']")
    public WebElement valuationDropDown;

    @FindBy(xpath = ".//input[@id='valuationID']")
    public WebElement valuationName;


    @FindBy(xpath = ".//span[@class='ag-column-tool-panel-column-label']")
    public List<WebElement> columlist;


    @FindBy(xpath = ".//select[@class='classSelectorDropdown']")
    public WebElement classSelectorDropdown;


    @FindBy(xpath = ".//span[contains(text(),'Company Exposure')]")
    public WebElement companyExposure;


    @FindBy(xpath = ".//span[contains(text(),'Tenants')]")
    public WebElement tenants;
    
    @FindBy(xpath = "//button[@type='button']")
    public WebElement weightedmean;

    
    @FindBy(xpath = ".//span[contains(text(),'Hotels')]")
    public WebElement hotels;


    @FindBy(xpath = ".//a[contains(text(),'Hilton Worldwide')]")
    public WebElement hiltonWorldwide;

    @FindBy(xpath = ".//div[@class='hotel-stats-row animated bounceInUp']")
    public WebElement hiltonStats;

    @FindBy(xpath = ".//div[@class='statistic-tile-header']//span[@class='ng-binding ng-scope']")
    public WebElement hotelPerfomance;

    @FindBy(xpath = ".//a[contains(text(),'Macys')]")
    public WebElement tenantsMacys;

    @FindBy(xpath = ".//[@class='nvd3-svg']")
    public WebElement macysDelinquencySummary;

    @FindBy(xpath = ".//span[@class='ag-selection-checkbox']")
    public WebElement listingCheckbox;

    @FindBy(xpath = ".//span[@class='ag-selection-checkbox']")
    public By listingCheckboxs;


}
