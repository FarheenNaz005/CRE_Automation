package com.newdemo.framework.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.base.Utilites;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.PropertyListingPageV2;

public class CREnewlistingPageController extends ReuseableFunctions {
    public LoadData data = null;
    PropertyListingPageV2 loanlistingPage = null;
    public SoftAssert softAssert = null;
    String propertyname;

    public CREnewlistingPageController(WebDriver driver) throws Exception {
        super(driver);
        loanlistingPage = PageFactory.initElements(driver, PropertyListingPageV2.class);
    }

    public void verifyMapViewnData() throws Exception {
        waitForDataLoading();
        waitForElement(loanlistingPage.mapViewData);
        elementPresentorEnabled(loanlistingPage.mapViewData, "Present", "Map Data ");
        softAssert.assertTrue(loanlistingPage.mapViewData.isDisplayed(), "Property not displayed.");
        String propertyname = loanlistingPage.mapViewData.getText();
        System.out.println("property fetch: " + propertyname);
    }

    public void verifyMapPinsLoad() throws Exception {
        List<WebElement> mapPins = loanlistingPage.mapPropertyPins;
        softAssert.assertTrue(!mapPins.isEmpty(), "Map pins not present.");
        System.out.println("Number of Map Pins in the list: " + mapPins.size());
    }

    public void verifyMapviewListAgainstListView() throws Exception {
        clickObject(loanlistingPage.listbutton, "click on list page");
        Thread.sleep(2000);
        List<String> propertiesMapView = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@col-id='propname']//a")))
                .stream().map(ele -> ele.getText()).collect(Collectors.toList());

        clickList();
        waitForLoading();
        waitForElement(loanlistingPage.propertyListingCheckbox);
        elementPresentorEnabled(loanlistingPage.propertyListingCheckbox, "Present", "List of checkboxes");

        // comparing list of properties from mapview against list of properties in
        // listview
//for (int i = 0; i < propertiesMapView.size(); i++) {
//String propName = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
//By.xpath(".//div[@class='ag-pinned-left-cols-container']/div[@row-index='" + i
//+ "']//div[@col-id='propName']")))
//.getText();
//System.out.println("expected: " + propertiesMapView.get(i) + ", actual: " + propName);
//softAssert.assertEquals(propName, propertiesMapView.get(i));
//}

    }

    public void verifyDownloanAllButton() throws Exception {
        waitForElement(loanlistingPage.downloadButton);
        elementPresentorEnabled(loanlistingPage.downloadButton, "present", "Download All button");
        softAssert.assertTrue(loanlistingPage.downloadButton.isDisplayed(), "download all button visible");

    }

    public void clickSelectAllPropertyCheckbox() throws Exception {
        clickObject(loanlistingPage.selectAllPropertyCheckbox, "select all property checkbox");

    }

    public void clickSelectAllPropertyCheckbox2() throws Exception {
        clickObject(loanlistingPage.selectAllPropertyCheckbox2, "select all property checkbox");

    }

    public void verifyDealListButton() throws InterruptedException {
//ElementPresentorEnabled(loanlistingPage.dealListButton, "present", "Deal List button");
//softAssert.assertTrue(loanlistingPage.dealListButton.isDisplayed(), "deal list button visible");

    }

    public void verifyAddPortfolioButton() throws InterruptedException {
        elementPresentorEnabled(loanlistingPage.addtoportoflio, "present", "Add to Portfolio button");
        softAssert.assertTrue(loanlistingPage.addtoportoflio.isDisplayed(), "Add to Portfolio button visible");

    }

    public void waitForLoading() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(loanlistingPage.loading));
    }

    public void waitForDataLoading() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(loanlistingPage.mapViewDataLoading));
    }

    public void clickList() throws Exception {
        clickObject(loanlistingPage.listViewButton, "List view button");
        Thread.sleep(5000);
    }

    public void clickonlistingpagev2() throws Exception {
        clickObject(loanlistingPage.listing, "listviewv2");

    }

    public void removeAllCheckedColumn() throws Exception {
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar");
        Thread.sleep(2000);
        if (loanlistingPage.columnSidebarSelectAllIntermidiate.isDisplayed())
            clickObject(loanlistingPage.columnSidebarSelectAllIntermidiate, "All column select checked");

        clickObject(loanlistingPage.columnSidebarSelectAllChecked, "All column select checked");
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar");

    }

    public void verifyCountOfUnpinnedColumn(int count) {
        softAssert.assertEquals(loanlistingPage.propertyListingUnpinnedColumn.size(), count);

    }

    public void verifyFilterAndAddColumn(String columnName) throws Exception {
        Thread.sleep(2000);
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar opened");
        // clickObject(loanlistingPage.columnssidebar, "columnssidebar opened");
        Thread.sleep(2000);

        clickObject(loanlistingPage.columnSidebarSearch, "Searchbar clicked");

        clearNTypeValue(loanlistingPage.columnSidebarSearch, columnName, "Column sidebar search");
        Thread.sleep(2000);
        WebElement element1 = driver.findElement(
                By.xpath("//span[@class='ag-column-select-column-label' and contains(text(),'" + columnName + "')]"));
        clickObject(element1, "Checkbox clicked");

        Thread.sleep(2000);

        for (int i = 0; i < loanlistingPage.columlist.size(); i++) {
            String expectedColumnName = loanlistingPage.columlist.get(i).getText().toLowerCase();
            System.out.println(expectedColumnName);
            softAssert.assertTrue(expectedColumnName.contains(columnName.toLowerCase()),
                    "Verifying filtered column name " + expectedColumnName);

            if (expectedColumnName.equals(columnName.toLowerCase())) {
                loanlistingPage.columlist.get(i).click();
                break;
            }

        }
        loanlistingPage.columnSidebarSearch.clear();
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar closed");

        waitForElement(driver.findElement(
                By.xpath("//span[contains(text(),'" + columnName + "') and @class='ag-header-cell-text']")));

    }

    public void verifyFilterAndAddColumn2(String columnName) throws Exception {
        Thread.sleep(2000);

        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar opened");
        Thread.sleep(2000);

        clickObject(loanlistingPage.columnSidebarSearch, "Searchbar clicked");

        clearNTypeValue(loanlistingPage.columnSidebarSearch, columnName, "Column sidebar search");
        Thread.sleep(2000);
        clickObject(loanlistingPage.ownerAddresscheckbox, "Checkbox clicked");

        Thread.sleep(2000);

        for (int i = 0; i < loanlistingPage.columlist.size(); i++) {
            String expectedColumnName = loanlistingPage.columlist.get(i).getText().toLowerCase();
            System.out.println(expectedColumnName);
            softAssert.assertTrue(expectedColumnName.contains(columnName.toLowerCase()),
                    "Verifying filtered column name " + expectedColumnName);

            if (expectedColumnName.equals(columnName.toLowerCase())) {
                loanlistingPage.columlist.get(i).click();
                break;
            }

        }
        loanlistingPage.columnSidebarSearch.clear();
        clickObject(loanlistingPage.columnssidebar, "ColumnSidebar closed");

        waitForElement(driver.findElement(
                By.xpath("//span[contains(text(),'" + columnName + "') and @class='ag-header-cell-text']")));

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
            String cellValue = getText(columnCell, "Column cell", "innerText");
            softAssert.assertTrue(!cellValue.isEmpty(), "Expected column to not have null values");
        }

    }

    public void selectCustomeView(String viewName) throws Exception {
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown open");
        clickObject(loanlistingPage.myview, "my view");
        WebElement viewLink = new WebDriverWait(driver, 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + viewName + "')]")));

        clickObject(viewLink, viewName);
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown close");

    }

    public void verifyColumnFilterCheckbox(String columnID) throws Exception {
        clickObject(loanlistingPage.firstColumnFilterbutton, "filter button of first column");
        clearNTypeValue(loanlistingPage.firstfilter, columnID, "filter text box");

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
            String cellValue5 = getText(columnCell, "Column cell", "innerText").trim();
            switch (selectChoice) {
                case "Less than":
                    int cellValue = Integer.parseInt(cellValue5.replace(",", "").trim());
                    softAssert.assertTrue((cellValue < Integer.parseInt(query)),
                            "Expected value to be filtered and be" + selectChoice + " " + query);
                    break;
                case "Contains":
                    softAssert.assertTrue(cellValue5.toLowerCase().contains(query.toLowerCase()),
                            "Expected value to be filtered and be" + selectChoice + "Expected value to be filtered and be"
                                    + selectChoice);
                    break;
                default:
                    break;
            }
        }

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
            String cellValue1 = getText(columnCellList.get(i - 1), "Column cell", "innerText").trim();
            String cellValue2 = getText(columnCellList.get(i), "Column cell", "innerText").trim();
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

    public void verifyLoanListingDownloadAll(String fileName) throws Exception {
        // ElementPresentorEnabled(loanlistingPage.downloadAllButton, "present",
        // "Download All button");
        waitForElement(Waitcondition.tobePresent, loanlistingPage.downloadAllButton);
        clickObjectJavascript(loanlistingPage.downloadAllButton, "download all button");
        String downloadFilepath = Paths.get(System.getProperty("user.dir"), "Downloads").toString();

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(loanlistingPage.downloadButton));

        boolean fileExist = isFileExist(downloadFilepath, fileName);
        softAssert.assertTrue(fileExist, "file " + fileName + " exist in download folder");

    }

    public void clickMapview() throws Exception {
        waitForElement(loanlistingPage.mapViewButton);
        clickObject(loanlistingPage.mapViewButton, "Map view button");

    }

    public void verifyfilterdata(String value) {

        List<WebElement> columnCellList = getColumnCellValuesWebelement("perpastmaturity");
        for (int i = 0; i <= columnCellList.size(); i++) {
            String cellValue7 = columnCellList.get(1).getText();
            // System.out.println(value);
            int cellValue = Integer.parseInt(cellValue7.replace(",", "").trim());
            softAssert.assertTrue((cellValue < Integer.parseInt(value)),
                    "Expected value to be filtered and be less than" + value);
        }

    }

    public void selectview(String viewType, String viewName) throws Exception {
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view option open");
        WebElement viewtype = new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + viewType + "')]")));
        clickObjectJavascript(viewtype, "view type= " + viewType);
        Thread.sleep(2000);
        WebElement viewname = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + viewName + "')]")));
        clickObjectJavascript(viewname, "View: " + viewName);
        Thread.sleep(2000);
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view option close");
    }

    public static int countItems(Map<Integer, List<String>> data) {
        int counter = 0;
        for (List<String> list : data.values()) {
            counter += list.size();
        }
        return counter;
    }

    @SuppressWarnings("checkstyle:ArrayTypeStyle")
    public void convertCSVtoExcel(String csvFileName, String excelFileName) {
        try {
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("sheet1");
            String currentLine = null;
            int rowNum = 0;
            BufferedReader br = new BufferedReader(
                    new FileReader(System.getProperty("user.dir") + "/Downloads/" + csvFileName));
            while ((currentLine = br.readLine()) != null) {
                String[] str;
                str = currentLine.split(",");
                rowNum++;
                XSSFRow currentRow = sheet.createRow(rowNum);
                for (int i = 0; i < str.length; i++) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(
                    System.getProperty("user.dir") + "/Downloads/" + excelFileName);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Done");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Exception in try");
        }
    }

    public void compareFiles(String oldList, String newList, String diff)
            throws IOException, InterruptedException, NumberFormatException {

        String folderpath = System.getProperty("user.dir") + "/Downloads/";

        LinkedHashMap<Object, LinkedHashMap<Object, Object>> map1 = new LinkedHashMap<Object, LinkedHashMap<Object, Object>>();
        LinkedHashMap<Object, Object> map2 = new LinkedHashMap<Object, Object>();

        Thread.sleep(2000);
//deleteRows(filePath2,1,10);
        int totalRow = Utilites.getRowCount(folderpath + oldList, "Sheet");
        int lastRowCountOld = totalRow - 15;
        int lastRowCountNew = Utilites.getRowCount(folderpath + newList, "Market Loan Listing");

        LinkedHashMap<Object, LinkedHashMap<Object, Object>> valueExcel1 = storeDataInLinkedHashMap(
                folderpath + oldList, map1, map2, 7, lastRowCountOld);
        LinkedHashMap<Object, LinkedHashMap<Object, Object>> valueExcel2 = storeDataInLinkedHashMap(
                folderpath + newList, map1, map2, 5, lastRowCountNew);

        System.out.println(valueExcel1.size() + "-" + valueExcel2.size());
        compareLinkedHashMapData(valueExcel1, valueExcel2, System.getProperty("user.dir") + "/difference/" + diff);

        System.out.println("DONE");
    }

    public static LinkedHashMap<Object, LinkedHashMap<Object, Object>> storeDataInLinkedHashMap(String filePath,
                                                                                                LinkedHashMap<Object, LinkedHashMap<Object, Object>> sheet1Map, LinkedHashMap<Object, Object> child1Map,
                                                                                                int firstRowNumber, int lastRowNumber) throws IOException, NumberFormatException {
        File file = new File(filePath);
        FileInputStream fos = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fos);
        XSSFSheet sheet1 = wb.getSheetAt(0);

        sheet1Map = new LinkedHashMap<Object, LinkedHashMap<Object, Object>>();

        if (firstRowNumber == lastRowNumber) {
            firstRowNumber = 0;
            lastRowNumber = sheet1.getLastRowNum();
        }
        int k = 0;
        for (int i = firstRowNumber; i <= lastRowNumber; i++) {
            Row row = sheet1.getRow(i);
            Row keyRow = sheet1.getRow(0);

            if (row != null) {
                Object value = null;
                child1Map = new LinkedHashMap<Object, Object>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
//System.out.println(i + "::" + j );
                    CellType cellType = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType();

                    switch (cellType) {
                        case STRING:
                            value = row.getCell(j).getStringCellValue();
                            break;
                        case NUMERIC:
                            value = row.getCell(j).getNumericCellValue();
                            break;
                        case BLANK:
                            value = "BLANK";
                        default:

                            break;
                    }

                    child1Map.put(j, value);

                }
                sheet1Map.put(k, child1Map);
                k++;
            }

        }
        return sheet1Map;
    }

    public static void compareLinkedHashMapData(LinkedHashMap<Object, LinkedHashMap<Object, Object>> linkedHashMapA,
                                                LinkedHashMap<Object, LinkedHashMap<Object, Object>> linkedHashMapB, String filePath)
            throws IOException, NumberFormatException {

        // -------------------------------
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();

        // ---------------------------------
        if (linkedHashMapA.size() == linkedHashMapB.size()) {
            for (int i = 1; i <= linkedHashMapA.size(); i++) {
                Row row = sheet.createRow(i);
                int j = 0;
                try {
                    for (Object key : linkedHashMapA.get(i).keySet()) {
                        // System.out.println(i+":::"+j);
                        j = j + 1;
                        Cell cell = row.createCell(j - 1);
                        if (compareValue(linkedHashMapA.get(i).get(key), linkedHashMapB.get(i).get(key))) {
//System.out.println(i + "= The first map Key : " + key.toString() + "-> data: "
//+ LinkedHashMapA.get(i).get(key) + " Matched with second map: "
//+ LinkedHashMapB.get(i).get(key));
                            cell.setAsActiveCell();

                            cell.setCellValue("True");

                        } else {
                            System.err.println(i + "= The first map Key : " + key.toString() + "-> data: "
                                    + linkedHashMapA.get(i).get(key) + " Does not matched with second map  "
                                    + linkedHashMapB.get(i).get(key));
                            cell.setAsActiveCell();

                            cell.setCellValue("False");

                        }
                    }
                } catch (Exception NullPointerException) {

                }
            }
            FileOutputStream fout = new FileOutputStream(filePath);
            wb.write(fout);
            fout.close();

        } else {
            System.err.println("Both the Excel file are not same in size");

        }
    }

    public static boolean compareValue(Object actualVal, Object expVal) {
        boolean result = false;
        // String s="-";
        if (actualVal != null && expVal != null) {
            if (actualVal instanceof Number) {
                if ((actualVal) == (expVal)) {
                    result = true;

                }

            } else if (actualVal instanceof String) {
                if (actualVal.equals(expVal)) {
                    result = true;
                }

            } else if (actualVal instanceof Boolean) {

                if (actualVal == expVal) {
                    result = true;
                }

            }

        }
        return result;
    }

    public void clickv1List() throws Exception {

        clickObject(loanlistingPage.lstv1button, "v1 List Button");
    }

    public void applyfilter(String string) throws Exception {

        clickObject(loanlistingPage.filterbuttom, "filter Button");
        clearNTypeValue(loanlistingPage.filtetext, string, "filter text box");
        Thread.sleep(5000);
    }

    public void verifydata(String value) {
        List<WebElement> columnCellList = getColumnCellValuesWebelement("expenses");
        for (int i = 0; i <= columnCellList.size(); i++) {
            String cellValue9 = columnCellList.get(1).getText();
            // System.out.println(value);
            int cellValue = Integer.parseInt(cellValue9.replace(",", "").trim());
            softAssert.assertTrue((cellValue < Integer.parseInt(value)),
                    "Expected value to be filtered and be less than" + value);
        }

    }

    public List<String> selectProperties(int numberOfProperies) throws Exception {
        List<String> properties = new ArrayList();
        waitForElement(loanlistingPage.firstrowpropertname);
        waitForElement(loanlistingPage.propertyListingCheckbox);
        elementPresentorEnabled(loanlistingPage.propertyListingCheckbox, "Present", "List of checkboxes");

        propertyname = getText(loanlistingPage.firstrowpropertname, "", "");
        for (int i = 0; i < numberOfProperies; i++) {

            WebElement checkbox = driver.findElement(By.cssSelector(
                    "[row-index='" + i + "'] [col-id='loancheckbox'] span[class*='ag-icon-checkbox-unchecked']"));
            checkbox.click();
            properties.add(getText(driver.findElement(By.cssSelector("[row-index='" + i + "'] [col-id='propname'] a")),
                    "property name", "innerText"));
        }
        return properties;
    }

    public List<String> selectPropertiesPortfolio(int numberOfProperies) throws Exception {
        List<String> properties = new ArrayList();
        clickObject(loanlistingPage.listview, "click on list view");
        waitForElement(loanlistingPage.firstrowpropertnamePortfolio);
        waitForElement(loanlistingPage.propertyListingCheckbox);
        elementPresentorEnabled(loanlistingPage.propertyListingCheckbox, "Present", "List of checkboxes");
        int checkboxcount = driver
                .findElements(By.cssSelector("[col-id='loanCheckbox'] span[class*='ag-icon-checkbox-unchecked']"))
                .size();
        propertyname = getText(loanlistingPage.firstrowpropertnamePortfolio, "", "");
        Thread.sleep(3000);
        for (int i = 0; i < checkboxcount - 1; i++) {
            WebElement checkbox = driver.findElement(By.cssSelector(
                    "[row-index='" + i + "'] [col-id='loanCheckbox'] span[class*='ag-icon-checkbox-unchecked']"));
            checkbox.click();
            properties.add(getText(driver.findElement(By.cssSelector("[row-index='" + i + "'] [col-id='propName'] a")),
                    "property name", "innerText"));
        }
        return properties;
    }

    public void addToDealList(String dealListName) throws Exception {

        clickObject(loanlistingPage.addTodealListButton, "add To Deal List button");
        waitForElement(loanlistingPage.dealListPanelList);
        clearNTypeValue(loanlistingPage.chooseDealListSearchInput, dealListName, "deal list search search input");
        clickObject(loanlistingPage.selectfirstdealList, "first deal List from the List");
        waitForElement(loanlistingPage.addbutton);
        clickObjectJavascript(loanlistingPage.addbutton, "Add button on the modal");
        waitForElement(loanlistingPage.viewDealList);
        clickObjectJavascript(loanlistingPage.viewDealList, "view deal list button");

    }

    public String clickFirstProperty() throws Exception {
        String propName = getText(loanlistingPage.firstrowpropertname, "First property link", "innerText");
        clickObject(loanlistingPage.firstrowpropertname, "First property link");
        return propName;
    }

    public void createPortfolio(String portfolioName) throws Exception {
        clickObject(loanlistingPage.addtoportoflio, "addToPortfolio button");
        waitForElement(loanlistingPage.modalDialog);
        clickObject(loanlistingPage.newPortfolioButton, "new portfolio button");
        waitForElement(loanlistingPage.newPortfolioNameInput);
        clearNTypeValue(loanlistingPage.newPortfolioNameInput, "regressionPortfolio", "new portfolio name");
        elementPresentorEnabled(loanlistingPage.addbutton, "Enabled", "Add button");
        Thread.sleep(1000);
        clickObject(loanlistingPage.addbutton, "Add button");
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

    public void clickaddPortfolio(String portfolio) throws Exception {
        clickObject(loanlistingPage.addtoportoflio, "addToPortfolio button");
        waitForElement(loanlistingPage.portfolioPanelList);
        clearNTypeValue(loanlistingPage.choosePortfolioSearchInput, portfolio, "portfolio search input");
        clickObject(loanlistingPage.selectfirstportfolio, "first portfolio from the List");
        clickObject(loanlistingPage.addbutton, "Add button on the modal");

    }

    public void addclass() throws Exception {
        clickObject(loanlistingPage.columnssidebar, "columnssidebar clicked");
        boolean checkboxxhecked = loanlistingPage.columnssidebarcheckbox.isSelected();
        if (!checkboxxhecked) {

            loanlistingPage.columnssidebarcheckbox.click();
            clickObject(loanlistingPage.columnssidebarcheckbox, "All checkbox checked");
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
                System.out.println("Value Entered A: " + loanlistingPage.classlist.get(i));

            }

        }

        for (int i = 0; i < loanlistingPage.classlist.size(); i++) {
            System.out.println(loanlistingPage.classlist.get(i).getText());

            if (loanlistingPage.classlist.get(i).getText().equals("-")) {
                // loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);
                loanlistingPage.classlist.get(i).sendKeys("b");
                System.out.println("Value Entered B: " + loanlistingPage.classlist.get(i));
                // loanlistingPage.classlist.get(i).sendKeys(Keys.ENTER);

            }

        }

        for (int i = 0; i < loanlistingPage.classlist.size(); i++) {
            System.out.println(loanlistingPage.classlist.get(i).getText());

            if (loanlistingPage.classlist.get(i).getText().equals("-")) {
                loanlistingPage.classlist.get(i).sendKeys("c");
                System.out.println("Value Entered C: " + loanlistingPage.classlist.get(i));
                Thread.sleep(3000);
            }

        }

        for (int i = 0; i < loanlistingPage.classlist.size(); i++) {
            System.out.println(loanlistingPage.classlist.get(i).getText());

            if (loanlistingPage.classlist.get(i).getText().equals("-")) {
                loanlistingPage.classlist.get(i).sendKeys("a");
                System.out.println("Value Entered A: " + loanlistingPage.classlist.get(i));
                Thread.sleep(3000);
            }
        }

        // loanlistingPage.classlist.get(1).click();
        clickObject(loanlistingPage.columnssidebar, "columnssidebar clicked");
        clickObjectJavascript(loanlistingPage.columnssidebarunchecked, "columnssidebar clicked");
        clickObject(loanlistingPage.columnssidebar, "columnssidebar clicked");
        selectPropertiesPortfolio(5);

    }

    public void verifyAlertButton() throws Exception {
        waitForElement(loanlistingPage.alertButton);
        softAssert.assertTrue(loanlistingPage.alertButton.isDisplayed(), "Alert button visible");
    }

    public void createDealAlert() throws Exception {
        clickObject(loanlistingPage.alertButton, "alert button");
        waitForElement(loanlistingPage.modalDialog);

        for (WebElement switchYes : loanlistingPage.dealAlertModalSwitchYes) {
            clickObject(switchYes, "swicth label for alert");
        }
        Thread.sleep(5000);

        clickObjectJavascript(loanlistingPage.aleartsavebutton, "Save button");
        Thread.sleep(5000);

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-dialog")));

        waitForElement(loanlistingPage.alertButton);
    }

    public void saveSearchCriteria(String searchName) throws Exception {
        clickObject(loanlistingPage.alertButton, "alert button");
        waitForElement(loanlistingPage.modalDialog);
        clearNTypeValue(loanlistingPage.saveSearchNameInput, searchName, "saved search input");
        clickObjectJavascript(loanlistingPage.aleartsavebutton, "save search submit button");
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-dialog")));
    }

    public void verifyUserComment(String comments) {
        String text = loanlistingPage.usercomment.getText();
        softAssert.assertTrue(text.equalsIgnoreCase(comments));

    }

    public void confirmSaveDeal() throws Exception {
        waitForElement(loanlistingPage.confirmSaveDeal);
        clickObject(loanlistingPage.confirmSaveDeal, "OK Button");

    }

    public void clickOnDeleteButton() throws Exception {
        waitForElement(loanlistingPage.deleteDeal);
        clickObject(loanlistingPage.deleteDeal, "Delete deal");
        clickObject(loanlistingPage.confirmDelete, "confirm delete");

    }

    public void pinColumnToLeft() throws Exception {
        int count = loanlistingPage.propertyListingUnpinnedColumn.size();
        for (int i = 1; i <= count; i++) {
            clickObjectJavascript(loanlistingPage.columnHeaderIcon, "Column Header Icon");
            clickObjectJavascript(loanlistingPage.pinColumnButton, "Pin Column Button");
            clickObjectJavascript(loanlistingPage.pinLeftButton, "Pin Left Button");
            Thread.sleep(2000);
        }
    }

    public void verifyPinColumnToLeft() throws Exception {
        int count = loanlistingPage.propertyListingUnpinnedColumn.size();
        softAssert.assertEquals(count, 0, "Pin column to left failed");
    }

    public void changeColumnOrder() {

        Actions actions = new Actions(driver);
        // Perform the horizontal drag action
        actions.dragAndDrop(loanlistingPage.columnExpenses, loanlistingPage.columnCounty).build().perform();

    }

    // CRE:4499
    public void verifyColDataIsNotBlankOnLoanListingPage(String colName, String msg) throws Exception {
        Thread.sleep(1000);
        List<WebElement> colname = driver.findElements(By.xpath("//div[@col-id='" + colName + "' and @role='gridcell']//div[normalize-space(text()) != ' ']"));
        verifyColDataIsNotEmpty(colname, msg);
    }

    public void verifyColDataIsNotEmpty(List<WebElement> objListWebElement, String objListWebElementName)
            throws Exception {

        Thread.sleep(1000);
        int recordCount = 0;

        if (!objListWebElement.isEmpty() & objListWebElement.size() != 0)

            for (WebElement colData : objListWebElement) {
                String data = colData.getText();
                // System.out.println(objListWebElementName + " " + data);
                if (data.equals("-") || data.isEmpty() || data.contentEquals(" ")) {
                    recordCount++;
                }
            }

        if (recordCount == objListWebElement.size()) {
            List<String> data1 = objListWebElement.stream().map(element -> element.getText())
                    .collect(Collectors.toList());
            System.out.println(objListWebElementName + data1);
            throw new AssertionError("All list data is '-'. Test case failed.");
        } else {

            List<String> data1 = objListWebElement.stream().map(element -> element.getText())
                    .collect(Collectors.toList());
            System.out.println(objListWebElementName + data1);
        }

    }

    public void createNewViewOnLoanListingPage(String viewName) throws Exception {
        wait.until(ExpectedConditions.invisibilityOf(loanlistingPage.dataSpinner));
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view option");
        waitForElement(loanlistingPage.saveAsButton);
        clickObject(loanlistingPage.saveAsButton, "Save As...");
        waitForElement(loanlistingPage.viewNameInputField);
        clearNTypeValue(loanlistingPage.viewNameInputField, viewName, "View Name");
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(loanlistingPage.saveViewButton));
        clickObject(loanlistingPage.saveViewButton, "Save View");
        wait.until(ExpectedConditions.invisibilityOf(loanlistingPage.saveViewButton));
        driver.navigate().refresh();
        waitForLoading();
    }

    public void verifyOnlySelectedColInViewDisplayed(String colName) throws Exception {
        wait.until(ExpectedConditions.invisibilityOf(loanlistingPage.dataSpinner));
        WebElement viewtype = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='" + colName + "' and @class='ag-header-cell-text']")));
        System.out.println(colName + " Column added successfully.");
    }

    public void clickSaveViewButton() throws Exception {
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view option open");
        waitForElement(loanlistingPage.viewSaveButton);
        clickObjectJavascript(loanlistingPage.viewSaveButton, "Save");
        Thread.sleep(3000);
        driver.navigate().refresh();
    }

    public void deleteViewOnLoanListingPage() throws Exception {
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view option open");
        waitForElement(loanlistingPage.deleteButton);
        clickObject(loanlistingPage.deleteButton, "View Delete");
        elementPresentorEnabled(loanlistingPage.deleteViewConfirmBlock, "Present", "Delete confirmation block");
        clickObject(loanlistingPage.deleteConfirmButton, "Confirm view delete");
        wait.until(ExpectedConditions.invisibilityOf(loanlistingPage.deleteViewConfirmBlock));
        Thread.sleep(2000);
        driver.navigate().refresh();
    }

    public void verifyViewDeletedSuccessfullly(String viewType, String viewName) throws Exception {
        wait.until(ExpectedConditions.invisibilityOf(loanlistingPage.dataSpinner));
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view option");
        WebElement viewtype = new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + viewType + "')]")));
        clickObject(viewtype, "view type= " + viewType);

        try {
            WebElement viewname = driver.findElement(By.xpath("//label[contains(text(),'" + viewName + "')]"));

            if (viewname.isDisplayed()) {
                System.out.println("Unable to delete the view: " + viewName);
                org.testng.Assert.assertTrue(false);
            }
        } catch (NoSuchElementException e) {
            System.out.println(viewName + " View Deleted successfully.");
        }
    }

    public void moveToDifferentColumn(String colName) throws Exception {
        WebElement col = driver.findElement(
                By.xpath("//div[@col-id='" + colName + "' and @role='gridcell']//div[normalize-space(text()) != ' ']"));
        col.click();
        Actions action = new Actions(driver);

        Actions moveToElement = action.moveToElement(col);
        for (int i = 0; i < 3; i++) {
            moveToElement.sendKeys(Keys.RIGHT).build().perform();
        }

        Thread.sleep(2000);
    }

    //CRE4585
    public void openSavedDealList(String dealName) throws Exception {
        WebElement dealmodel = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + dealName + "')]")));
        clickObjectJavascript(dealmodel, dealName);
    }

    public void verifymapPinsCountEqualToPropertyShowsOnDealListMapViewPage() throws Exception {
        waitForElement(loanlistingPage.propertyHeader1);
        List<WebElement> propertyCount = loanlistingPage.propertyHeader;
        int propertyHeader = propertyCount.size();
        System.out.println("Property header present: " + propertyHeader);
        List<WebElement> mapPinsCount = loanlistingPage.mapPins;
        int mapPins = mapPinsCount.size();
        System.out.println("Pins on the map present: " + mapPins);
        softAssert.assertEquals(propertyHeader, mapPins, "Property header does not match with map pins.");
    }

    public void verifyPropertyOpensAfterClicking() throws Exception {
        waitForElement(loanlistingPage.mapPins1);
        clickObjectJavascript(loanlistingPage.mapPins1, "1st map pin");
        waitForLoading();
        waitForElement(loanlistingPage.propertyName);
        softAssert.assertTrue(loanlistingPage.propertyName.isDisplayed(), "property Name not present");
        String propertyName = loanlistingPage.propertyName.getText();
        System.out.println(propertyName + " open after clicking map pin.");

        Actions a = new Actions(driver);
        a.moveToElement(loanlistingPage.mapPins1).click(loanlistingPage.mapPins1).build().perform();
        waitForElement(loanlistingPage.mapPinPopUp);
        softAssert.assertTrue(loanlistingPage.mapPinPopUp.isDisplayed(), "Map pin pop up not open.");
        System.out.println("Map pin pop up open.");
        waitForElement(loanlistingPage.popupHeader);
        softAssert.assertTrue(loanlistingPage.popupHeader.isDisplayed(), "PopupHeader not present");
        clickObjectJavascript(loanlistingPage.popupHeader, "Popup header property name");
        waitForLoading();

    }

    public void verifySummaryPageOpens(String menuTitle, String tab) throws Exception {
        WebElement menutitle = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + menuTitle + "']")));
        WebElement menuTab = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + tab + "')]")));
    }

    // CRE4587
    public void createAlertOnDeaLListPage() throws Exception {
        selectDealListCheckBox(2);
        clickObject(loanlistingPage.alertsButton, "Alerts button on deal list");
        waitForElement(loanlistingPage.dealAlertModalHeader);
        softAssert.assertTrue(loanlistingPage.dealAlertModalHeader.isDisplayed(),
                "dealAlertModalHeader is not present");
        System.out.println("Alert modal header present");
        clickObject(loanlistingPage.ncfToggle, "NCF % Change toggle On");
        clickObject(loanlistingPage.delinquentToggle, "Delinquent Status toggle On");
        clickObject(loanlistingPage.saveAlertButton, "Save button");
        waitForElement(loanlistingPage.modalDialogAlertSuccess);
        driver.navigate().refresh();
        waitForLoading();
    }

    public void selectDealListCheckBox(int indexToStop) throws Exception {
        List<WebElement> checkBox = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElements(loanlistingPage.checkboxList));
        if (!checkBox.isEmpty()) {
            int checkboxesToClick = indexToStop;
            for (int i = 0; i < checkboxesToClick && i < checkBox.size(); i++) {
                waitForElement(checkBox.get(i));
                clickObject(checkBox.get(i), i + " ☑ index checkbox");
            }
        }
    }

    public void deleteALertIfAlreadyPresentForPropertyDeal() throws Exception {
        List<WebElement> alertBellOn = loanlistingPage.alertBellOn;
        if (!alertBellOn.isEmpty()) {
            System.out.println("Alerts already present, so going to delete existing alerts.");
            for (WebElement element : alertBellOn) {
                clickObject(element, "🔔 Alert bell On");
                waitForElement(loanlistingPage.deleteAlertButton);
                clickObject(loanlistingPage.deleteAlertButton, "Delete alert button");
                softAssert.assertTrue(loanlistingPage.deleteAlertModalBox.isDisplayed(),
                        "deleteAlertModalBox is not present");
                clickObject(loanlistingPage.deleteButtonOnAlertModalBox, "Delete Button on Modal Box");
                waitForLoading();
            }
            driver.navigate().refresh();
            waitForLoading();
            softAssert.assertEquals(alertBellOn.size(), 0, "Alerts... not deleted.");
        }
    }

    public void verifyAlertSavedSuccessfully() throws Exception {
        List<WebElement> alertBellOn = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfAllElements(loanlistingPage.alertBellOn));
        if (!alertBellOn.isEmpty()) {
            System.out.println("Alert bell(s)turns to yellow");
            clickObject(loanlistingPage.myWork, "My Work");
            clickObject(loanlistingPage.myWorkAlertButton, "Alerts");
            waitForLoading();
        }
    }

    public void verifyAlertPresentOnAlertManagerScreen(String alertName) {
        WebElement viewLink = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + alertName + "']")));
        System.out.println("Alert present for property " + alertName);
    }

    // 4586
    public void verifyWorkoutOppurtunitiesView(String viewName, String colName) throws Exception {
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown");
        clickObject(loanlistingPage.treppViewButton, "Trepp View");
        WebElement viewLink = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + viewName + "')]")));
        clickObject(viewLink, viewName);
        WebElement colname = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[@role='columnheader' and text()='" + colName + "']")));
        softAssert.assertTrue(colname.isDisplayed(), "Column is not present");
    }

    public void verifyDealsCanBeAddedToPortfolio(String portfolioName, String portfolioToSelect) throws Exception {
        selectDealListCheckBox(2);
        clickObject(loanlistingPage.addToPortfolioButton, "Add to Portfolio button");
        clearNTypeValue(loanlistingPage.searchField, portfolioName, portfolioName);
        WebElement portfolioToSearch = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[contains(. , '" + portfolioName + "')]/i")));
        clickObject(portfolioToSearch, "searched portfolio");
        clickObject(loanlistingPage.addButton, "Add button");
        WebElement confirmationBox = new WebDriverWait(driver, 40)
                .until(ExpectedConditions.visibilityOf(loanlistingPage.confirmationBox));
        clickObjectJavascript(loanlistingPage.closeButton, "Close button");
        clickObjectJavascript(loanlistingPage.myWork, "My Work");
        WebElement portfoliotoSelect = new WebDriverWait(driver, 20).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + portfolioToSelect + "']")));
        clickObjectJavascript(portfoliotoSelect, portfolioToSelect);
        waitForElement(loanlistingPage.listview);
        clickObjectJavascript(loanlistingPage.listview, "List view");
    }

    public void verifyPropertyAddedToPortfolioPage(String propertyName) {
        WebElement portfoliotoSelect = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + propertyName + "']")));
    }

    public void verifyPortfolioDeleted() throws Exception {
        //selectDealListCheckBox(2);
        waitForElement(loanlistingPage.selectAllCheckbox);
        clickObject(loanlistingPage.selectAllCheckbox, "Select all checkbox");
        clickObject(loanlistingPage.deleteIcon, "Delete Icon");
        clickObject(loanlistingPage.deleteButtonOnAlertModalBox, "Confirm Delete button");
        waitForLoading();
        waitForElement(loanlistingPage.emptyPortfolioContent);
        softAssert.assertTrue(loanlistingPage.emptyPortfolioContent.isDisplayed(), "No portfolio content header is present");
    }

    public void verifyAddandDeletePropertiesFromDealList(String dealListName) throws Exception {
        //select all properties to add in deal list
        waitForElement(loanlistingPage.selectAllCheckbox);
        clickObject(loanlistingPage.selectAllCheckbox, "All checkbox selector");
        waitForElement(loanlistingPage.addToDealListBtn);
        clickObject(loanlistingPage.addToDealListBtn, "Add to deal list button");

        //search for deal list in which you want to add properties
        clearNTypeValue(loanlistingPage.searchDealList, dealListName, dealListName);
        WebElement searchedDeal = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(. , '" + dealListName + "')]/i")));
        clickObject(searchedDeal, dealListName);
        clickObject(loanlistingPage.addButton, "Add button");
        waitForElement(loanlistingPage.confirmationBox);
        clickObject(loanlistingPage.viewDealListBtn, "View deal list button");
        waitForLoading();

        //verify properties added to deal list
        List<WebElement> propertiesName = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfAllElements(loanlistingPage.propertyNameList));
        List<String> propertiesNames = propertiesName.stream().map(element -> element.getText())
                .collect(Collectors.toList());
        System.out.println("Properties added to deal list: " + propertiesNames);
        softAssert.assertTrue(!propertiesNames.isEmpty(), "Properties are not added to deal list.");
        //softAssert.assertEquals(propertiesNames.size(), 10, "List size is not equal to 10.");

        //Delete all properties from deal list
        waitForElement(loanlistingPage.selectAllCheckbox);
        clickObject(loanlistingPage.selectAllCheckbox, "All checkbox selector");
        clickObject(loanlistingPage.deleteDeal, "Delete button");
        softAssert.assertTrue(loanlistingPage.deleteConfirmationBox.isDisplayed(), "Delete Confirmation model box not displayed.");
        clickObject(loanlistingPage.deleteButtonOnAlertModalBox, "Delete confirmation button");
        waitForLoading();
        waitForElement(loanlistingPage.noDealListPresent);
        softAssert.assertTrue(loanlistingPage.noDealListPresent.isDisplayed(), "Properties not deleted from deal list.");

    }

    // CRE4555
    public void verifyMarketSnapshotClickThroughToLoanDeatailsPage() throws Exception {
        clickObject(loanlistingPage.marketSnapshot, "Market snapshot button");
        waitForElement(loanlistingPage.propertyName1);
        clickObject(loanlistingPage.propertyName1, "1st #properties link");
        waitForLoading();
        switchToNewWindow();
        verifyMapViewnData();
        verifyDownloanAllButton();
        switchToOldWindow();
    }
    
    public void closeMarketSnapshotWindow() throws Exception {
        waitForElement(loanlistingPage.closeCrossButton);
        clickObjectJavascript(loanlistingPage.closeCrossButton, "Close cross icon");
    }

    public void verifyMarketSnapshotClickThroughToLoanListingPage(String tab) throws Exception {
        clickObject(loanlistingPage.marketSnapshot, "Market snapshot button");
        waitForElement(loanlistingPage.loanName1);
        scrollIntoView(loanlistingPage.loanName1, "scroll to 1st property nam");
        clickObjectJavascript(loanlistingPage.loanName1, "1st property name");
        waitForLoading();
        WebElement menutitle = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='hidden-md hidden-sm hidden-xs' and text()='" + tab + "']")));
        List<WebElement> detailsPageTab = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfAllElements(loanlistingPage.detailsTab));
        List<String> detailsTab = detailsPageTab.stream().map(element -> element.getText())
                .collect(Collectors.toList());
        System.out.println("Loan details page tab: " + detailsTab);
        softAssert.assertTrue(!detailsTab.isEmpty(), " Unable to find Loan Details page tab(s) " + detailsTab);
    }

    //4554
    public void clickMArketSnapshotButton() throws Exception {
        clickObject(loanlistingPage.marketSnapshot, "Market snapshot button");
    }

    public void verifyMarketSnapshotModalHeader(String headerName) {
        WebElement menutitle = new WebDriverWait(driver, 30).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='" + headerName + "'])[1]")));
        softAssert.assertTrue(menutitle.isDisplayed(), "Section name on market snapshot is not displayed.");
        System.out.println("Section Name ================= " + menutitle.getText());
    }

    public void verifyMarketSnapshotHighlightsSectionLoads() throws Exception {
        List<WebElement> higlightsSection = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElements(loanlistingPage.highlightSectionContent));
        List<String> highlights = higlightsSection.stream().map(element -> element.getText())
                .collect(Collectors.toList());
        softAssert.assertTrue(!highlights.isEmpty(), "Highlights section does not  load ");
        System.out.println(highlights);
    }

    public void verifyMarketSnapshotSectionsLoad(String tableIndex) throws Exception {
        List<WebElement> sectionData = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[@class='market-snapshot-data-table'])[" + tableIndex + "]//table//thead//tr//th")));
        List<String> data = sectionData.stream().map(element -> element.getText())
                .collect(Collectors.toList());
        softAssert.assertTrue(!data.isEmpty(), "Section data does not load.");
        System.out.println(data);
    }

    public void verifyMarketIncomeAndExpenseBenchmarkSectionLoads() throws Exception {
        List<WebElement> incomeAndExpenseBenchmarkData = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfAllElements(loanlistingPage.incomeAndExpenseBenchmarkData));
        List<String> incomeAndExpenseBenchmark = incomeAndExpenseBenchmarkData.stream().map(element -> element.getText())
                .collect(Collectors.toList());
        softAssert.assertTrue(!incomeAndExpenseBenchmark.isEmpty(), "income And Expense Benchmark section does not load. ");
        System.out.println(incomeAndExpenseBenchmark);
    }

    public void verifyDropdownCheck() throws Exception {
        scrollIntoView(loanlistingPage.highLightsData1, "Highlight section");
        String data1 = loanlistingPage.highLightsData1.getText();
        System.out.println("Value before: " + data1);
        clickObject(loanlistingPage.propertyTypeDropdown, "Property type dropdown");
        waitForElement(loanlistingPage.retailValue);

        clickObject(loanlistingPage.retailValue, "Retail");
        wait.until(ExpectedConditions.invisibilityOf(loanlistingPage.loader));
        String data2 = loanlistingPage.highLightsData1.getText();
        System.out.println("Value after: " + data2);

        softAssert.assertNotEquals(data1, data2, "Data is equal.");

    }

    public void verifyTreppHotSearchOnHomepageClickThruToNewLoanListingPage() throws Exception {
        scrollIntoView(loanlistingPage.treppHotSearchHeader, "Trepp hot search");
        elementPresentorEnabled(loanlistingPage.treppHotSearchHeader, "Present", "Trepp hot search header");
        softAssert.assertTrue(!loanlistingPage.hotSearchContentHeading.isEmpty(),
                "Hot search content header is not present.");
        int size = loanlistingPage.hotSearchContentHeading.size();
        System.out.println("Total number of trepp hot search present: " + size);
        softAssert.assertEquals(size, 4, "Size not matched.");

        // click on one of the trepp hot search
        clickObject(loanlistingPage.hotSearchContentHeading1, "Trepp 1st hot search");
        waitForLoading();
        verifyMapViewnData();
        verifyDownloanAllButton();
    }

    // 4525
    public void clickNewIssuance() throws Exception {
        waitForElement(loanlistingPage.newIssuance);
        clickObject(loanlistingPage.newIssuance, "New Issuance");
    }

    public void clickNewIssuanceTabs(String tabName) throws Exception {
        WebElement menutitle = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + tabName + "']")));
        String tabname = menutitle.getText();
        System.out.println(tabname + " Tab is present");
        clickObject(menutitle, tabName);
    }

    public void verifyTransactionTabDataLoad() {
        wait.until(ExpectedConditions.visibilityOfAllElements(loanlistingPage.gridData));
        List<String> data = loanlistingPage.gridData.stream().map(element -> element.getText())
                .collect(Collectors.toList());
        softAssert.assertTrue(!loanlistingPage.gridData.isEmpty(), "Tranaction tab data is not displayed");
        System.out.println("Tranaction tab data: " + data);
    }

    public void verifyNewIssuenceTabsDataLoad(String headerText, int headerGraph) {
        WebElement headerName = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='panelHeaderTitle' and text()='" + headerText + "']")));
        System.out.println(headerText + " header is present");

        WebElement graph = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[contains(@class, 'recharts-wrapper')])[" + headerGraph + "]")));
        System.out.println(headerText + " graph is present");
    }

    public void verifyNewSpreadsAndRatesTabCMBSSpreadLoads() throws Exception {
        waitForElement(loanlistingPage.cMBSLoanSpreads);
        softAssert.assertTrue(loanlistingPage.cMBSLoanSpreads.isDisplayed(), "Header not displayed");
        String heading = loanlistingPage.cMBSLoanSpreads.getText();
        System.out.println(heading + " is present");
        wait.until(ExpectedConditions.invisibilityOf(loanlistingPage.spreadesAndRatesSpinner));
        waitForElement(loanlistingPage.cMBSLoanSpreadsGraph);
        softAssert.assertTrue(loanlistingPage.cMBSLoanSpreadsGraph.isDisplayed(), "graph not displayed");
        scrollIntoView(loanlistingPage.scrollToPricingSpreads, "Pricing spreads");
    }

    public void verifyTreppWireSpreadsLoads() {
        softAssert.assertTrue(loanlistingPage.treppWireSpreadsHeading.isDisplayed(), "graph not displayed");
        String spreadHeader = loanlistingPage.treppWireSpreadsHeading.getText();
        System.out.println(spreadHeader + " is present");
        softAssert.assertTrue(loanlistingPage.spreadsContent.isDisplayed(), "graph not displayed");
    }

    public boolean delteOldViewIfExist(String viewName) throws Exception {
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown open");
        clickObject(loanlistingPage.myview, "my view");
        try {
            WebElement viewLink = new WebDriverWait(driver, 5).until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + viewName + "')]")));
            clickObject(viewLink, viewName);
            clickObject(loanlistingPage.deleteButton, "View Delete");
            elementPresentorEnabled(loanlistingPage.deleteViewConfirmBlock, "Present", "Delete confirmation block");
            clickObject(loanlistingPage.deleteConfirmButton, "Confirm view delete");
            System.out.println("--> deleted old view");
            clickObject(loanlistingPage.myview, "my view");
            clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown close");

        } catch (Exception e) {
            System.err.println("Old View does not exist: " + viewName);
            return false;
        }
        return true;
    }

    public void verifyMapPinPopup() throws Exception {
        waitForElement(loanlistingPage.mapPropertyPins1);
        moveToElement(loanlistingPage.mapPropertyPins1, "map pin");
        clickObject(loanlistingPage.mapPropertyPins1, "Map pin");
        waitForElement(loanlistingPage.pinPopupHeader);
        softAssert.assertTrue(loanlistingPage.pinPopupHeader.isDisplayed(), "property name is not present");
        String propertyName = loanlistingPage.pinPopupHeader.getText();
        System.out.println("Map pin pop-up property name: " + propertyName);
        //Validate map pin pop-up data
        List<String> data = loanlistingPage.pinPopupData.stream().map(element -> element.getText()).collect(Collectors.toList());
        softAssert.assertTrue(!data.isEmpty(), data + ": values are not populated in map pin pop-up.");
        System.out.println("Map pin pop-up content: " + data);
        //click property name on popup
        clickObject(loanlistingPage.pinPopupHeader, "Property name on map pin pop-up");
    }

    public void createDefaultView(String viewName) throws Exception { //added new from here
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown open");
        //clickObject(loanlistingPage.myview, "my view");
        Thread.sleep(2000);
        WebElement viewLink = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + viewName + "')]")));

        clickObject(viewLink, viewName);
        WebElement makeDefaultButton = driver.findElement(By.xpath("//button[normalize-space()='Make Default']"));
        clickObject(makeDefaultButton, "Make Default Button");
        Thread.sleep(2000);
    }

    public void goToFinancialTabOnNewDtailsPage() throws Exception {
        waitForElement(loanlistingPage.details);
        clickObject(loanlistingPage.details, "Details");
        waitForLoading();
        waitForElement(loanlistingPage.financialTab);
        clickObject(loanlistingPage.financialTab, "Financial");
    }

    public void verifyDefaultView(String viewName) throws Exception {
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown open");
        clickObject(loanlistingPage.myview, "my view");
        Thread.sleep(2000);

        WebElement currentDefaultViewName = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//label[@class='default']")));

        WebElement currentDefaultRadioSelected = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//input[@value='" + viewName + "']")));

        System.out.println("--> current default view name: " + currentDefaultViewName.getText());
        if (currentDefaultRadioSelected.isSelected()) {
            System.out.println("--> " + currentDefaultViewName.getText() + " :Default view is selected ");
            softAssert.assertEquals(viewName, currentDefaultViewName.getText(), "--> view name and default view name not matched");
        } else {
            System.out.println("--> Default view is not selected by default");
            softAssert.assertTrue(false, "--> Assertion failed: Default view is not selected by default");
            currentDefaultViewName.click();
        }
        clickObject(loanlistingPage.customViewDropdownButton, "Custom view dropdown open");
    }

    public void verifyMostRecentTab(String colName) throws Exception {
        waitForElement(loanlistingPage.financialsHeaders);
        waitForElement(loanlistingPage.mostRecent);
        String mRT = loanlistingPage.mostRecent.getText();
        softAssert.assertEquals(colName, mRT, "Most recent col not found!");
        System.out.println("Expected and acutal results matched");
    }
    
    public void verifyLoanListingListView() throws Exception {
        waitForElement(loanlistingPage.listingTab);
        softAssert.assertTrue(loanlistingPage.listingTab.isDisplayed(), "Listing tab not present.");
        waitForElement(loanlistingPage.mYInternalIdCol);
        softAssert.assertTrue(loanlistingPage.mYInternalIdCol.isDisplayed(), "My Internal ID column not present.");
    }
    
    public void clickEditSearchBtn() throws Exception {
        waitForElement(loanlistingPage.editButton);
        clickObject(loanlistingPage.editButton, "Edit button");
    }


}
