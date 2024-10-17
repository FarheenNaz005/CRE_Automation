package com.newdemo.framework.pages;

import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PropertyListingPageV2 {

    // nav links
    @FindBy(xpath = ".//span[text()='Strats']/..")
    public WebElement navStratsLink;

    // common listing button
    @FindBy(xpath = ".//span[contains(text(),'Strats')]")
    public WebElement strats;

    @FindBy(xpath = ".//span[contains(text(),'Benchmarks')]")
    public WebElement benchmarks;

    @FindBy(xpath = ".//span[contains(text(),'Listing')]")
    public WebElement listing;

    @FindBy(xpath = ".//span[text()='List']/..")
    public WebElement listViewButton;

    @FindBy(xpath = "//*[contains(text(),'Listing v2')]")
    public WebElement listviewv2;

    @FindBy(xpath = ".//span[text()='Map']/..")
    public WebElement mapViewButton;

//    //common button after property selected
//    @FindBy(xpath = ".//div[@class='navbar-left']//span[contains(text(),'Add to Portfolio')]")
//    public WebElement addtoportoflio;

    // common button after property selected
    @FindBy(xpath = ".//span[@class='AddToPortfolioIcon']")
    public WebElement addtoportoflio;

    @FindBy(css = ".AddSelectedLoansToDealList button")
    public WebElement addTodealListButton;

    @FindBy(css = "button[ng-click*='createAlert']")
    public WebElement createAlertButton;

    @FindBy(css = ".AlertButton button")
    public WebElement alertButton;

    @FindBy(xpath = "//a[contains(text(),'All (Excel)')]")
    public WebElement downloadAllButton;

    @FindBy(xpath = ".//button[./i[contains(@class,'fa-download')]]")
    public WebElement downloadButton;

    /* Map view */

    @FindBy(xpath = ".//div[@class='leaflet-google-layer leaflet-top leaflet-left']")
    public WebElement mapLoad;

    @FindBy(xpath = "//div[@class='property-name bold']")
    public WebElement mapViewData;
    
    @FindBy(xpath = "//img[contains(@class, 'leaflet-interactive')]")
    public List<WebElement> mapPropertyPins;
    
    @FindBy(xpath = "(//img[contains(@class, 'leaflet-interactive')])[1]")
    public WebElement mapPropertyPins1;

    /* List view */

    @FindBy(xpath = "//span[contains(text(),'Views')]")
    public WebElement customViewDropdownButton;

    @FindBy(xpath = "(//span[contains(text(),'Listing')])[1]")
    public WebElement lstv1button;
    @FindBy(xpath = "(//span[@class='ag-icon ag-icon-filter'])[7]")
    public WebElement filterbuttom;
    @FindBy(xpath = "(//input[@class='ag-input-field-input ag-number-field-input'])[1]")
    public WebElement filtetext;
    @FindBy(xpath = "(//div[@col-id='expenses']//div[@class='ag-cell-wrapper']")
    public WebElement expencefilterdata;

    @FindBy(xpath = "//div[contains(text(),'My Views')]")
    public WebElement myview;

    @FindBy(css = "i[ng-click='resetGridFilters()']")
    public WebElement resetButton;

    @FindBy(xpath = ".//trepp-http-activity-widget")
    public WebElement loading;

    @FindBy(xpath = "//div[@class='ag-loading']")
    public WebElement mapViewDataLoading;

    @FindBy(xpath = "//div[@class='ag-header-viewport']//div[@role='columnheader']//span[@class='ag-icon ag-icon-menu']")
    public WebElement columnHeaderIcon;

    @FindBy(xpath = "//span[contains(text(),'Pin Column')]")
    public WebElement pinColumnButton;

    @FindBy(xpath = "//span[contains(text(),'Pin Left')]")
    public WebElement pinLeftButton;

    @FindBy(xpath = "//span[contains(text(),'Expenses')]")
    public WebElement columnExpenses;

    @FindBy(xpath = "//span[@class='ag-header-cell-text' and contains(text(),'County')]")
    public WebElement columnCounty;

    // Property list table
    @FindBy(xpath = "(.//span[@class='ag-icon ag-icon-checkbox-unchecked'])[1]")
    public WebElement selectAllPropertyCheckbox;
    @FindBy(xpath = "(.//span[@class='ag-icon ag-icon-checkbox-checked'])[1]")
    public WebElement selectAllPropertyCheckbox2;

    @FindBy(xpath = "(//span[@class='hidden-sm hidden-xs'])[2]")
    public WebElement listbutton;

    @FindBy(xpath = ".//span[@class='ag-header-cell-text'][contains(text(),'Class')]")
    public List<WebElement> classcolumn;

    @FindBy(css = "div.ag-header-viewport div.ag-header-cell span.ag-header-cell-text")
    public List<WebElement> propertyListingUnpinnedColumn;

    @FindBy(xpath = ".//div[@class='ag-column-tool-panel-column ag-toolpanel-indent-0']//span[@class='ag-column-tool-panel-column-label']")
    public List<WebElement> columlist;

    @FindBy(xpath = "//span[@class='ag-icon ag-icon-checkbox-unchecked']")
    public WebElement propertyListingCheckbox;

    @FindBy(xpath = ".//span[@class='ag-selection-checkbox']")
    public By propertyListingCheckboxs;

    // side panel
    @FindBy(css = "[single-loan-panel].pull-left")
    public WebElement propertySidePanel;

    // filter for column
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

    // column side bar
    @FindBy(xpath = ".//span[contains(text(),'Columns')]")
    public WebElement columnssidebar;
    @FindBy(xpath = "//input[@id='ag-54-input']")
    public WebElement unselectcheckbox;

    @FindBy(xpath = "//input[@placeholder='Search...']")
    public WebElement columnSidebarSearch;
    @FindBy(xpath = "//span[@class='ag-column-select-column-label' and contains(text(),'Expenses')]")
    public WebElement expencecheckbox;
    @FindBy(xpath = "//span[@class='ag-column-select-column-label' and contains(text(),'Owner 1 Mailing Address')]")
    public WebElement ownerAddresscheckbox;

    @FindBy(xpath = "(//input[@class='ag-input-field-input ag-number-field-input'])[1]")
    public WebElement firstfilter;

    @FindBy(xpath = ".//div[@class='ag-tool-panel-wrapper']//span[@class='ag-icon ag-icon-checkbox-checked']")
    public List<WebElement> columnSidebarCheckboxChecked;

    @FindBy(xpath = ".//span[@class='ag-icon ag-icon-checkbox-checked']")
    public WebElement columnssidebarcheckbox;

    @FindBy(xpath = ".//span[@class='ag-icon ag-icon-checkbox-indeterminate']")
    public WebElement columnssidebarunchecked;

    @FindBy(xpath = "(//div[@class='ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper ag-checked' and @ref='eWrapper'])[1]")
    public WebElement columnSidebarSelectAllChecked;

    @FindBy(xpath = "//div[@class='ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper ag-indeterminate' and @ref='eWrapper']")
    public WebElement columnSidebarSelectAllIntermidiate;

    @FindBy(css = "[row-index='0'] [col-id='propname'] a")
    public WebElement firstrowpropertname;

    @FindBy(css = "[row-index='0'] [col-id='propName'] a")
    public WebElement firstrowpropertnamePortfolio;

    @FindBy(xpath = ".//div[@class='ag-header-select-all ag-labeled ag-label-align-right ag-checkbox']")
    public List<WebElement> checkallsize;

    @FindBy(xpath = ".//div[@class='ag-header-select-all ag-labeled ag-label-align-right ag-checkbox']")
    public WebElement checkall;

    @FindBy(xpath = ".//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-with-height align-c ag-cell-value']")
    public List<WebElement> classlist;

    // modaldialog
    @FindBy(css = "div.modal-dialog")
    public WebElement modalDialog;

    @FindBy(css = "div.modal-dialog .alert-info")
    public WebElement modalDialogAlertInfo;

    @FindBy(css = "div.modal-dialog .alert-success")
    public WebElement modalDialogAlertSuccess;

    @FindBy(css = "div.DealListModal .modal-footer button.btn-primary")
    public WebElement modalAccept;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    public WebElement modalCancel;

    // Save search modal
    @FindBy(css = ".MarketAlertSettings .name-input-container input")
    public WebElement saveSearchNameInput;

    @FindBy(css = ".modal-dialog [type='submit']")
    public WebElement saveSearchSubmit;

    // Add portfolio Modal
    @FindBy(xpath = ".//div[@class='tab-content']//li[1]")
    public WebElement selectfirstportfolio;

    @FindBy(css = "div.portfolios-panel ul")
    public WebElement portfolioPanelList;

    @FindBy(xpath = ".//i[@class='fa fa-table']")
    public WebElement listview;

    @FindBy(css = "#tab-set-pane-choosePortfolio input") // #tab-set-pane-choosePortfolio input
    public WebElement choosePortfolioSearchInput;

    @FindBy(xpath = "//button[contains(text(),'Add')]")
    public WebElement addbutton;
    @FindBy(xpath = "//button[@class='trepp__button trepp__button--medium trepp__button--primary bs-modal-footer-btn']")
    public WebElement aleartsavebutton;

    @FindBy(xpath = ".//button[contains(text(),'View Portfolio')]")
    public WebElement viewPortfolio;
    @FindBy(xpath = "//button[contains(text(),'OK')]")
    public WebElement confirmSaveDeal;
    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    public WebElement deleteDeal;
    @FindBy(xpath = "//button[@class='btn btn-danger ng-binding' and contains(text(),'Delete')]")
    public WebElement confirmDelete;

    @FindBy(xpath = ".//a[.//text()[contains(.,'New Portfolio')]]")
    public WebElement newPortfolioButton;

    @FindBy(css = "#tab-set-pane-newPortfolio input")
    public WebElement newPortfolioNameInput;

    @FindBy(xpath = ".//button[@class='btn btn-primary' and text()='Add']")
    public WebElement newPortfolioAddButton;

    // Deal Alert modal
    @FindBy(css = ".DealAlertSettings input[type='checkbox']")
    public List<WebElement> dealAlertModalSwitchYes;

    @FindBy(css = "div.deal-lists-panel ul")
    public WebElement dealListPanelList;

    @FindBy(css = ".TabContentChooseDealList input")
    public WebElement chooseDealListSearchInput;

    @FindBy(css = ".deal-lists-panel li:nth-of-type(1)")
    public WebElement selectfirstdealList;

    @FindBy(xpath = ".//button[contains(text(),'View Deal List')]")
    public WebElement viewDealList;

    @FindBy(css = "[row-index='0'] [col-id='userloancomment_comment']")
    public WebElement usercomment;

    // Loan List Column
    @FindBy(xpath = "//div[@col-id='address' and @role='gridcell']//div[normalize-space(text()) != ' ']")
    public List<WebElement> addresCol;
    @FindBy(xpath = "//div[@col-id='loanname' and @role='gridcell']//div[normalize-space(text()) != ' ']")
    public List<WebElement> loanCol;
    @FindBy(xpath = "//div[@col-id='trepprealid' and @role='gridcell']//div[normalize-space(text()) != ' ']")
    public List<WebElement> treppRealIdCol;
    @FindBy(xpath = "//span[@class='ag-side-button-label' and text()='Views']")
    public WebElement sidebarViewButton;
    @FindBy(xpath = "//button[text()='Save As...']")
    public WebElement saveAsButton;
    @FindBy(xpath = "//input[@id='save-as-name']")
    public WebElement viewNameInputField;
    @FindBy(xpath = "//button[contains(@class, 'bs-modal-footer-btn') and text()='Save']")
    public WebElement saveViewButton;
    @FindBy(xpath = "//button[contains(@class, 'button--outline block') and text()='Save']")
    public WebElement viewSaveButton;
    @FindBy(xpath = "//button[text()='Delete View']")
    public WebElement deleteButton;
    @FindBy(xpath = "//div[@data-testid='confirmation-modal']")
    public WebElement deleteViewConfirmBlock;
    @FindBy(xpath = "//button[@data-testid='confirm' and text()='Delete']")
    public WebElement deleteConfirmButton;
    @FindBy(xpath = "//span[@class='ag-icon ag-icon-loading']")
    public WebElement dataSpinner;
    //CRE4585
    @FindBy(xpath = "//h4[contains(@class, 'property-name')]")
    public List<WebElement> propertyHeader;
    @FindBy(xpath = "//img[contains(@class, 'leaflet-clickable')]")
    public List<WebElement> mapPins;
    @FindBy(xpath = "(//img[contains(@class, 'leaflet-clickable')])[1]")
    public WebElement mapPins1;
    @FindBy(xpath = "//h4[@class='bold']/loan-prop-detail-link[@section='Property']")
    public WebElement propertyName;
    @FindBy(xpath = "//div[@loan='treppLoanDetailPopupLoan']")
    public WebElement mapPinPopUp;
    @FindBy(xpath = "//a[@ng-if=\"loanType === 'treppLoanLoan'\"]")
    public WebElement popupHeader;
    //CRE4586
    @FindBy(xpath = "//div[contains(text(), 'Trepp View')]")
    public WebElement treppViewButton;
    @FindBy(xpath = "//div[@ref='eLeftContainer']//span[@class='ag-selection-checkbox']")
    public List<WebElement> checkboxList;
    @FindBy(xpath = "//span[text()='Add to Portfolio']")
    public WebElement addToPortfolioButton;
    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchField;
    @FindBy(xpath = "//button[text()='Add']")
    public WebElement addButton;
    @FindBy(xpath = "//h4[text()='Confirmation']")
    public WebElement confirmationBox;
    @FindBy(xpath = "//button[@ng-if='loanListingToolbarConfig.delete']")
    public WebElement deleteIcon;
    @FindBy(xpath = "//h2[text()='This portfolio is empty.']")
    public WebElement emptyPortfolioContent;
    @FindBy(xpath = "//button[text()='Close']")
    public WebElement closeButton;
    @FindBy(xpath = "(//span[@class='ag-icon ag-icon-checkbox-unchecked'])[1]")
    public WebElement selectAllCheckbox;
    @FindBy(xpath = "//span[text()=' Add to Deal List']")
    public WebElement addToDealListBtn;
    @FindBy(xpath = "//input[@placeholder='Search Deal Lists']")
    public WebElement searchDealList;
    @FindBy(xpath = "//button[text()='View Deal List']")
    public WebElement viewDealListBtn;
    @FindBy(xpath = "//h4[contains(text(),'Delete Selected Loans?')]")
    public WebElement deleteConfirmationBox;
    @FindBy(xpath = "//div[@ng-if='loans.length === 0']")
    public WebElement noDealListPresent;
    @FindBy(xpath = "//div[@col-id='propName']/div/a[contains(text(), ' ')]")
    public List<WebElement> propertyNameList;
    @FindBy(xpath = "//h4[@class='property-name bold ng-binding']")
    public WebElement propertyHeader1;
    //CRE4587
    @FindBy(xpath = "(//div[@ref='cbSelectAll'])[1]")
    public WebElement selectAllDeal;
    @FindBy(xpath = "//span[text()='Alerts']")
    public WebElement alertsButton;
    @FindBy(xpath = "//h4[text()='Deal Alert ']")
    public WebElement dealAlertModalHeader;
    @FindBy(xpath = "//li[@class='list-group-item']")
    public List<WebElement> alertModalContent;
    @FindBy(xpath = "(//label[@btn-radio='1' and text()='On'])[1]")
    public WebElement ncfToggle;
    @FindBy(xpath = "(//label[@btn-radio='1' and text()='On'])[4]")
    public WebElement delinquentToggle;
    @FindBy(xpath = "//button[@ng-click='saveListAlertSettings(dealListAlert)']")
    public WebElement saveAlertButton;
    @FindBy(xpath = "//i[@class='fa fa-bell text-warning']")
    public List<WebElement> alertBellOn;
    @FindBy(xpath = "//span[text()='Delete Alert']")
    public WebElement deleteAlertButton;
    @FindBy(xpath = "//div[@class='modal-body ng-scope']")
    public WebElement deleteAlertModalBox;
    @FindBy(xpath = "//button[text()='Delete']")
    public WebElement deleteButtonOnAlertModalBox;
    @FindBy(xpath = "//span[contains(text(), 'Alerts')]")
    public WebElement myWorkAlertButton;
    @FindBy(xpath = "//small[text()='My Work']")
    public WebElement myWork;
    //CRE4555
    @FindBy(xpath = "//span[text()=' Market Snapshot']")
    public WebElement marketSnapshot;
    @FindBy(xpath = "(//num-props-preconfigured-loan-listing-aggregate-query/a[@ng-click='gotoLoanListing()'])[1]")
    public WebElement propertyName1;
    @FindBy(xpath = "(//loan-prop-detail-link[@loan='row']/span/a)[1]")
    public WebElement loanName1;
    @FindBy(xpath = "//li[@role='tab']")
    public List<WebElement> detailsTab;
    @FindBy(xpath = "//div[@class='property-name bold']")
    public List<WebElement> mapViewDataList;  
    
    //CRE4554
    @FindBy(xpath = "//div[@ng-repeat='meta in columnSpecs']")
    public List<WebElement> highlightSectionContent;
    @FindBy(xpath = "//span[text()='Recent Transactions']")
    public WebElement recentTransactions;
    @FindBy(xpath = "(//div[@class='col-xs-8']//table[contains(@class,'table table')]//tbody//tr)[1]")
    public List<WebElement> incomeAndExpenseBenchmarkData;
    @FindBy(xpath = "(//span[@ng-if='value !== null && value !== undefined && filter'])[1]")
    public WebElement highLightsData1;
    @FindBy(xpath = "(//button[@ng-click='selectionUpdater()'])[2]")
    public WebElement propertyTypeDropdown;
    @FindBy(xpath = "(//a[contains(text(), 'Retail')])[2]")
    public WebElement retailValue;
    @FindBy(xpath = "//i[@ng-if='loading']")
    public WebElement loader;
    
    //Trepp hot search homepage
    @FindBy(xpath = "//h2[text()='Trepp Hot Search']")
    public WebElement treppHotSearchHeader;
    @FindBy(xpath = "//button[@aria-label='Next slide']/i")
    public WebElement nextSlideArrow;
    @FindBy(xpath = "//a//h3[@class='heading']")
    public List<WebElement> hotSearchContentHeading;
    @FindBy(xpath = "(//a//h3[@class='heading'])[1]")
    public WebElement hotSearchContentHeading1;
    //4525
    @FindBy(xpath = "//span[text()='New Issuance']")
    public WebElement newIssuance;
    @FindBy(xpath = "//div[contains(@class, 'ag-cell-value')]/div")
    public List<WebElement> gridData;
    @FindBy(xpath = "//div[@class='trepp__progressSpinner']")
    public WebElement spreadesAndRatesSpinner;
    @FindBy(xpath = "//a[text()='U.S. Conduit CMBS Loan Spreads']")
    public WebElement cMBSLoanSpreads;
    @FindBy(xpath = "(//div[@class='recharts-wrapper'])[1]")
    public WebElement cMBSLoanSpreadsGraph;
    @FindBy(xpath = "//div[@class='panelHeaderTitle' and text()='U.S. Bond Initial Pricing Spreads (2023)']")
    public WebElement scrollToPricingSpreads;
    @FindBy(xpath = "//div[@class='panelHeaderTitle treppwire-logo']")
    public WebElement treppWireSpreadsHeading;
    @FindBy(xpath = "//div[@class='article-content']")
    public WebElement spreadsContent;
    @FindBy(xpath = "//a[@class='prop-name-label']")
    public WebElement pinPopupHeader;
    @FindBy(xpath = "//div[@class='data-value single-column']")
    public List<WebElement> pinPopupData;
    @FindBy(xpath = "//span[text()='Details']")
    public WebElement details;
    @FindBy(xpath = "//label[text()='Financial']")
    public WebElement financialTab;
    @FindBy(xpath = "//span[text()='Financials']")
    public WebElement financialsHeaders;
    @FindBy(xpath = "(//tr[@data-testid='table-headerGroups'])[1]/th[2]")
    public WebElement mostRecent;  
    @FindBy(xpath = "//span[text()='Listing']")
    public WebElement listingTab;
    @FindBy(xpath = "//span[text()='My Internal ID']")
    public WebElement mYInternalIdCol;
    @FindBy(xpath = "//button[@ng-click='editSearch(userSearch)']")
    public WebElement editButton;
    @FindBy(xpath = "//a[@ng-click='$close()']")
    public WebElement closeCrossButton;
    
}
