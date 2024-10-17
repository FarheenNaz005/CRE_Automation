package com.newdemo.framework.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.PropertyListingPage;

public class CREListingPageController extends ReuseableFunctions {
    public LoadData data = null;
    PropertyListingPage loanlistingPage = null;
    public SoftAssert softAssert = null;
    String propertyname;

    public CREListingPageController(WebDriver driver) throws Exception {
        super(driver);
        loanlistingPage = PageFactory.initElements(driver, PropertyListingPage.class);
    }

    /**
     * navigation
     **/
    public void clickStratsLink() throws Exception {
        clickObject(loanlistingPage.navStratsLink, "strats tab");
    }

    /*** Map View ***/
    public void clickMapview() throws Exception {
        clickObject(loanlistingPage.mapViewButton, "Map view button");
    }

    public void verifyMapViewnData() throws Exception {
        waitForElement(loanlistingPage.mapViewData);
        elementPresentorEnabled(loanlistingPage.mapViewData, "Present", "Map Data present");
        String data = getText(loanlistingPage.mapViewData, " Property Data in Maps ", "");
        if (!data.isEmpty()) {
            System.out.println("No data found");
            //softAssert.assertTrue(StringUtils.isNotBlank(Data));
        }
    }

    public void verifyMapviewListAgainstListView() throws Exception {
        Thread.sleep(4000);
        // getting property name from mapview
        List<String> propertiesMapView = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(".//ul[@class='list-group']/li//h4[contains(@class,'property-name')]")))
                .stream().map(ele -> ele.getText()).collect(Collectors.toList());

        clickList();
        waitForLoading();
        waitForElement(loanlistingPage.propertyListingCheckbox);
        elementPresentorEnabled(loanlistingPage.propertyListingCheckbox, "Present", "List of checkboxes");

        // comparing list of properties from mapview against list of properties in
        // listview
        for (int i = 0; i < propertiesMapView.size(); i++) {
            String propName = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(".//div[@class='ag-pinned-left-cols-container']/div[@row-index='" + i
                                    + "']//div[@col-id='propName']")))
                    .getText();
            System.out.println("expected: " + propertiesMapView.get(i) + ", actual: " + propName);
            softAssert.assertEquals(propName, propertiesMapView.get(i));
        }

    }

    public void clickSelectAllPropertyCheckbox() throws Exception {
        clickObject(loanlistingPage.selectAllPropertyCheckbox, "select all property checkbox");
    }

    public String clickFirstProperty() throws Exception {
        String propName = getText(loanlistingPage.firstrowpropertname, "First property link", "innerText");
        clickObject(loanlistingPage.firstrowpropertname, "First property link");
        return propName;
    }

    public String clickFirstPropertypotfoliolistingpage() throws Exception {
        String propName = getText(loanlistingPage.portfoliofirstrowpropertname, "First property link", "innerText");
        clickObject(loanlistingPage.portfoliofirstrowpropertname, "First property link");
        return propName;
    }

    public List<String> selectProperties(int numberOfProperies) throws Exception {
        List<String> properties = new ArrayList();
        waitForElement(loanlistingPage.firstrowpropertname);
        waitForElement(loanlistingPage.propertyListingCheckbox);
        elementPresentorEnabled(loanlistingPage.propertyListingCheckbox, "Present", "List of checkboxes");

        propertyname = getText(loanlistingPage.firstrowpropertname, "", "");
        for (int i = 0; i < numberOfProperies; i++) {

            WebElement checkbox = driver.findElement(By.cssSelector(
                    "[row-index='" + i + "'] [col-id='loanCheckbox'] span[class='ag-selection-checkbox']"));
            checkbox.click();
            properties.add(getText(driver.findElement(By.cssSelector("[row-index='" + i + "'] [col-id='propName'] a")), "property name", "innerText"));
        }
        return properties;
    }

    public void verifyCreateAlertButton() throws Exception {
        elementPresentorEnabled(loanlistingPage.createAlertButton, "present", "create alert button");
    }

    public void clickCreateAlertButton() throws Exception {
        clickObject(loanlistingPage.createAlertButton, "create alert button");
    }

    public void saveSearchCriteria(String searchName) throws Exception {
        waitForElement(loanlistingPage.modalDialog);
        clearNTypeValue(loanlistingPage.saveSearchNameInput, searchName, "saved search input");
        clickObject(loanlistingPage.saveSearchSubmit, "save search submit button");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-dialog")));
    }

    public void verifyCustomViewButton() throws Exception {
        elementPresentorEnabled(loanlistingPage.customViewDropdownButton, "present", "custom view dropdown button");
    }

    public void verifyDownloanAllButton() throws Exception {
        waitForElement(loanlistingPage.downloadButton);
        elementPresentorEnabled(loanlistingPage.downloadButton, "present", "Download All button");
        softAssert.assertTrue(loanlistingPage.downloadButton.isDisplayed(), "download all button visible");
    }

    public void verifyDealListButton() throws Exception {
        elementPresentorEnabled(loanlistingPage.dealListButton, "present", "Deal List button");
        softAssert.assertTrue(loanlistingPage.dealListButton.isDisplayed(), "deal list button visible");
    }

    public void verifyAddPortfolioButton() throws Exception {
        elementPresentorEnabled(loanlistingPage.addtoportoflio, "present", "Add to Portfolio button");
        softAssert.assertTrue(loanlistingPage.addtoportoflio.isDisplayed(), "Add to Portfolio button visible");
    }

    public void verifyAlertButton() throws Exception {
        waitForElement(loanlistingPage.alertButton);
        softAssert.assertTrue(loanlistingPage.alertButton.isDisplayed(), "Alert button visible");
    }


    /*** List View ***/
    public void waitForLoading() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(loanlistingPage.loading));
    }

    public void removeAllCheckedColumn() throws Exception {
        clickObject(loanlistingPage.columnssidebar, "columnssidebar");
//for (WebElement checkbox : loanlistingPage.columnSidebarCheckboxChecked) {
//clickObject(checkbox, "column sidebar checkbox");
//}
        if (loanlistingPage.columnSidebarSelectAllIntermidiate.isDisplayed())
            clickObject(loanlistingPage.columnSidebarSelectAllIntermidiate, "All coumn select checked");

        clickObject(loanlistingPage.columnSidebarSelectAllChecked, "All coumn select checked");
        clickObject(loanlistingPage.columnssidebar, "columnssidebar");
    }

    public void verifyCountOfUnpinnedColumn(int count) {
        softAssert.assertEquals(loanlistingPage.propertyListingUnpinnedColumn.size(), count);
    }

    public void verifyFilterAndAddColumn(String columnName) throws Exception {
        clickObject(loanlistingPage.columnssidebar, "columnssidebar opened");

        clickObject(loanlistingPage.columnSidebarSearch, "Searchbar clicked");

        clearNTypeValue(loanlistingPage.columnSidebarSearch, columnName, "Column sidebar search");

        Thread.sleep(2000);

        for (int i = 0; i < loanlistingPage.columlist.size(); i++) {
            String expectedColumnName = loanlistingPage.columlist.get(i).getText().toLowerCase();
            System.out.println(expectedColumnName);
            softAssert.assertTrue(expectedColumnName.contains(columnName.toLowerCase()),
                    "veifying filtered column name " + expectedColumnName);

            if (expectedColumnName.equals(columnName.toLowerCase())) {
                loanlistingPage.columlist.get(i).click();
                break;
            }

        }
        loanlistingPage.columnSidebarSearch.clear();
        clickObject(loanlistingPage.columnssidebar, "columnssidebar closed");

        waitForElement(driver
                .findElement(By.xpath(".//span[@class='ag-header-cell-text'][contains(text(),'" + columnName + "')]")));
    }

    public WebElement getColumnHeaderWebelement(String columnID) {
        return new WebDriverWait(driver, 20).until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("[col-id='" + columnID + "'].ag-header-cell")));

    }

    public List<WebElement> getColumnCellValuesWebelement(String columnID) {
        return new WebDriverWait(driver, 5).until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector("[col-id='" + columnID + "'].ag-cell")));
    }

    public void verifyColumnForNullValue(String columnID) throws Exception {
        getColumnHeaderWebelement(columnID);

        List<WebElement> columnCellList = getColumnCellValuesWebelement(columnID);

        for (WebElement columnCell : columnCellList) {
            String cellValue = getText(columnCell, "colulmn cell", "innerText");
            softAssert.assertTrue(!cellValue.isEmpty(), "Expected column to not have null values");
        }
    }

    public void selectCustomeView(String viewName) throws Exception {
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown");
        WebElement viewLink = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//div[@class='dropdown-menu']/a[span/text()[contains(.,'" + viewName + "')]]")));

        clickObject(viewLink, "view link");
    }

    public void resetViewAndFilter() throws Exception {
        clickObject(loanlistingPage.resetButton, "reset button");
    }

    public void verifyColumnSort(String columnID, String columnType) throws Exception {
        WebElement columnHeader = getColumnHeaderWebelement(columnID);

        clickObject(columnHeader, "column header " + columnID);
        waitForLoading();
        WebElement ascIcon = columnHeader.findElement(By.cssSelector("span.ag-sort-ascending-icon"));
        waitForElement(ascIcon);
        List<WebElement> columnCellList = getColumnCellValuesWebelement(columnID);

        for (int i = 1; i < 3; i++) {
            String cellValue1 = getText(columnCellList.get(i - 1), "colulmn cell", "innerText").trim();
            String cellValue2 = getText(columnCellList.get(i), "colulmn cell", "innerText").trim();
            switch (columnType) {
                case "number":
                    softAssert.assertTrue(
                            (Integer.parseInt(cellValue1.replace(",", "").trim()) <= Integer
                                    .parseInt(cellValue2.replace(",", "").trim())),
                            "Expected column to be in ascending order");
                    break;

                case "string":
                    softAssert.assertTrue(cellValue1.compareToIgnoreCase(cellValue2) >= 0,
                            "Expected column to be in ascending order");
                    break;
                default:
                    break;
            }
        }
    }

    public void verifyColumnFilterCheckbox(String choice, String columnID) throws Exception {
        clickObject(loanlistingPage.firstColumnFilterbutton, "filter button of first column");

        clickObject(loanlistingPage.choiceFilterSelectAll, "choiceFileterSelectAll");

        WebElement choiceToSelect = new WebDriverWait(driver, 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//label[span//text()[contains(.,'" + choice + "')]]")));

        clickObject(choiceToSelect, "choice for " + choice);
        clickObject(loanlistingPage.applyFilterButton, "Apply filter button");

        waitForLoading();
        Thread.sleep(2000);
        WebElement closeFilterButton = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.ag-tab.ag-tab-selected")));
        clickObject(closeFilterButton, "filter button of first column");

        getColumnHeaderWebelement(columnID);

        List<WebElement> columnCellList = getColumnCellValuesWebelement(columnID);

        for (WebElement columnCell : columnCellList) {
            String cellValue = getText(columnCell, "colulmn cell", "innerText");
            softAssert.assertEquals(cellValue, choice, "Expected " + choice + " in cell for " + columnID);
        }
    }

    public void addColumnFilterDropdown(String selectChoice, String query, String columnID) throws Exception {
        WebElement columnHeader = getColumnHeaderWebelement(columnID);

        String cssLeftValue = columnHeader.getCssValue("left");

        WebElement filterbutton = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".ag-header-cell[style*='left: " + cssLeftValue + "'] button")));

        clickObject(filterbutton, "filter button column");

        waitForElement(loanlistingPage.dropdownFilterSelect);
        selectFromDropDownVisibleText(loanlistingPage.dropdownFilterSelect, "filter select", selectChoice);

        clearNTypeValue(loanlistingPage.dropdownFilterInput, query, "drop down filter input");

        clickObject(loanlistingPage.applyFilterButton, "Apply filter button");
        waitForLoading();
        Thread.sleep(1000);
        WebElement closeFilterButton = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.ag-tab.ag-tab-selected")));
        clickObject(closeFilterButton, "filter button of first column");
    }

    public void verifyDropdownFilteredColumn(String selectChoice, String query, String columnID) throws Exception {
        getColumnHeaderWebelement(columnID);

        List<WebElement> columnCellList = getColumnCellValuesWebelement(columnID);

        for (WebElement columnCell : columnCellList) {
            String cellValue4 = getText(columnCell, "colulmn cell", "innerText").trim();
            switch (selectChoice) {
                case "Less than":
                    int cellValue = Integer.parseInt(cellValue4.replace(",", "").trim());
                    softAssert.assertTrue((cellValue < Integer.parseInt(query)),
                            "Expected value to be filtered and be" + selectChoice + " " + query);
                    break;
                case "Contains":
                    softAssert.assertTrue(cellValue4.toLowerCase().contains(query.toLowerCase()),
                            "Expected value to be filtered and be" + selectChoice + "Expected value to be filtered and be"
                                    + selectChoice);
                    break;
                default:
                    break;
            }
        }
    }

    public void clickList() throws Exception {
        clickObject(loanlistingPage.listViewButton, "List view button");
    }

    public void verifyLoanListingDownloadAll(String fileName) throws Exception {
        //ElementPresentorEnabled(loanlistingPage.downloadAllButton, "present", "Download All button");
        waitForElement(Waitcondition.tobePresent, loanlistingPage.downloadAllButton);
        clickObjectJavascript(loanlistingPage.downloadAllButton, "download all button");

        new WebDriverWait(driver, 40)
                .until(ExpectedConditions.elementToBeClickable(loanlistingPage.downloadButton));

        boolean fileExist = isFileExist(BaseSetupClass.downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");
    }

    public void clickaddPortfolio(String portfolio) throws Exception {
        clickObject(loanlistingPage.addtoportoflio, "addToPortfolio button");
        waitForElement(loanlistingPage.portfolioPanelList);
        clearNTypeValue(loanlistingPage.choosePortfolioSearchInput, portfolio, "portfolio search input");
        clickObject(loanlistingPage.selectfirstportfolio, "first portfolio from the List");
        clickObject(loanlistingPage.addbutton, "Add button on the modal");

    }

    public void createPortfolio(String portfolioName) throws Exception {
        clickObject(loanlistingPage.addtoportoflio, "addToPortfolio button");
        waitForElement(loanlistingPage.modalDialog);
        clickObject(loanlistingPage.newPortfolioButton, "new portfolio button");
        waitForElement(loanlistingPage.newPortfolioNameInput);
        clearNTypeValue(loanlistingPage.newPortfolioNameInput, "regressionPortfolio", "new portfolio name");
        elementPresentorEnabled(loanlistingPage.modalAccept, "Enabled", "Add button");
        Thread.sleep(1000);
        clickObject(loanlistingPage.newPortfolioAddButton, "Add button");
    }

    public void clickviewPortfolio() throws Exception {
        waitForElement(loanlistingPage.viewPortfolio);
        clickObject(loanlistingPage.viewPortfolio, "view Portfolio button");

    }

    public void verifysamerecords() throws Exception {

        waitForElement(loanlistingPage.checkall);
        WebElement propertrecord = driver.findElement(By.xpath(".//a[contains(text(),'" + propertyname + "')]"));

        elementPresentorEnabled(propertrecord, "Present", "Same Data present");

    }

    public void addToDealList(String dealListName) throws Exception {
        clickObject(loanlistingPage.dealListButton, "deal list button");
        WebElement dealListTile = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//deal-list-tile[.//h4[text()[contains(.,'" + dealListName + "')]]]")));

        clickObject(dealListTile, "deal list with name " + dealListName);

        waitForElement(loanlistingPage.modalDialogAlertSuccess);

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-dialog")));

    }

    public void createDealAlert() throws Exception {
        clickObject(loanlistingPage.alertButton, "alert button");
        waitForElement(loanlistingPage.modalDialog);

        for (WebElement switchYes : loanlistingPage.dealAlertModalSwitchYes) {
            clickObject(switchYes, "swicth label for alert");
        }

        clickObject(loanlistingPage.addbutton, "Save button");

        waitForElement(loanlistingPage.modalDialogAlertSuccess);

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-dialog")));
    }

    public void addclass() throws Exception {
        clickObject(loanlistingPage.columnssidebar, "columnssidebar clicked");
        boolean checkboxxhecked = loanlistingPage.columnssidebarcheckbox.isSelected();
        if (!checkboxxhecked) {

            loanlistingPage.columnssidebarcheckbox.click();
            clickObject(loanlistingPage.columnssidebarcheckbox, "extraodinary fee Box checked");
        }

        System.out.println("columlist List::" + loanlistingPage.columlist.size());

        for (int i = 0; i < loanlistingPage.columlist.size(); i++) {
            System.out.println(loanlistingPage.columlist.get(i).getText());

            if (loanlistingPage.columlist.get(i).getText().equals("Class")) {
                loanlistingPage.columlist.get(i).click();
                break;
            }

        }
        clickObject(loanlistingPage.columnssidebar, "columnssidebar clicked");
        System.out.println("Class List::" + loanlistingPage.classlist.size());

        for (int i = 0; i < loanlistingPage.classlist.size(); i++) {
            System.out.println(loanlistingPage.classlist.get(i).getText());

            if (loanlistingPage.classlist.get(i).getText().equals("-")) {
                // loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);
                loanlistingPage.classlist.get(i).sendKeys("a");
                // loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);
//loanlistingPage.classlist.get(i).sendKeys("b");
//loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);
//loanlistingPage.classlist.get(i).sendKeys("c");
//loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);
//loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);
//loanlistingPage.classlist.get(i).sendKeys("a");
//loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);
//loanlistingPage.classlist.get(i).sendKeys("b");
            }

        }

        for (int i = 0; i < loanlistingPage.classlist.size(); i++) {
            System.out.println(loanlistingPage.classlist.get(i).getText());

            if (loanlistingPage.classlist.get(i).getText().equals("-")) {
                // loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);
                loanlistingPage.classlist.get(i).sendKeys("b");
                // loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);

            }

        }

        for (int i = 0; i < loanlistingPage.classlist.size(); i++) {
            System.out.println(loanlistingPage.classlist.get(i).getText());

            if (loanlistingPage.classlist.get(i).getText().equals("-")) {
                loanlistingPage.classlist.get(i).sendKeys("c");
            }

        }
        // loanlistingPage.classlist.get(1).click();
        clickObject(loanlistingPage.columnssidebar, "columnssidebar clicked");
        clickObject(loanlistingPage.columnssidebarunchecked, "columnssidebar clicked");
        clickObject(loanlistingPage.columnssidebar, "columnssidebar clicked");
        selectProperties(5);

    }

    /**
     * side panel
     **/

    public boolean verifySidePanel() throws Exception {
        try {
            if (loanlistingPage.propertySidePanel.isDisplayed()) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public void selectview(String viewType, String viewName) throws Exception {

        clickObject(loanlistingPage.customViewDropdownButton1, "Custom view option");
        WebElement viewtype = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'" + viewType + "')]")));
        clickObjectJavascript(viewtype, "view type= " + viewType);
        WebElement viewname = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(text(),'" + viewName + "')]")));
        waitForElement(viewname);
        clickObjectJavascript(viewtype, "view name= " + viewName);
        Thread.sleep(10000);

    }

}
