package com.newdemo.framework.controller.loan;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.loan.PropertyDetailPageNew;

public class PropertyDetailPageNewController extends ReuseableFunctions {
    public PropertyDetailPageNew propertyDetailPage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public PropertyDetailPageNewController(WebDriver driver) throws Exception {
        super(driver);

        propertyDetailPage = PageFactory.initElements(driver, PropertyDetailPageNew.class);
    }

    public void verifyPropertyName(String propertyName) throws Exception {
        // waitForElement(propertyDetailPage.NewPageTitle);
        // softAssert.assertEquals(getText(propertyDetailPage.NewPageTitle, "page sub
        // title", "innerText"), propertyName, "Page sub title should be: " +
        // propertyName);
        printNGPageLoadTime();
    }

    /**
     * this method will verify table on loan property detail page having the title
     *
     * @param String name/title of the table
     */
    public void verifyTable(String tableTitle) throws Exception {
        softAssert.assertTrue(propertyDetailPage.getTableByTitle(tableTitle).isDisplayed(),
                tableTitle + " is not displayed on the page");
        List<String> data = propertyDetailPage.getTableValuesByTitle(tableTitle).stream()
                .map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("Header Data:" + " " + data);
        softAssert.assertTrue(data.isEmpty(), tableTitle + ": values are not populated");

    }

    public void verifyTableNew(String tableTitle) throws Exception {
        softAssert.assertTrue(propertyDetailPage.getTableByTitleNew(tableTitle).isDisplayed(),
                tableTitle + " is not displayed on the page");
        List<String> data = propertyDetailPage.getTableValuesByTitleNew(tableTitle).stream()
                .map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("Header Data:" + " " + data);
        softAssert.assertTrue(data.isEmpty(), tableTitle + ": values are not populated");

    }

    public void verifyTableProFormaHeaderAndData(String tableTitle, int i) throws Exception {
        softAssert.assertTrue(propertyDetailPage.getTableHeaderByTitle(tableTitle).isDisplayed(),
                tableTitle + " is not displayed on the page");
        System.out.println("Table Header:" + " " + tableTitle);
        List<String> data = driver.findElements(By.xpath("(//div[@ref='gridPanel'])[" + i + "]//div[@role='gridcell']"))
                .stream().map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("Table Data:" + " " + data);
        softAssert.assertTrue(data.isEmpty(), tableTitle + ": values are not populated");
    }

    public void verifyExcelDownload() throws Exception {
        clickObjectJavascript(propertyDetailPage.downloadButton, "download button");
        waitUntilDonwloadCompleted();
    }

    public void verifyPDFDownload() throws Exception {
        clickObjectJavascript(propertyDetailPage.printButton, "Print button");
        Thread.sleep(2000);
        waitUntilDonwloadCompleted();
    }

    public boolean switchToDetailv2() {
        try {
            Thread.sleep(5000);
            if (propertyDetailPage.detailv2.isDisplayed()) {
                clickObjectJavascript(propertyDetailPage.detailv2, "detail v2");
                Thread.sleep(5000);
                return true;
            }

        } catch (Exception e) {
        }
        return false;
    }

    public void clickonmycomment() throws Exception {
        clickObjectJavascript(propertyDetailPage.mycommenttab, "My Comment Tab");

    }

    public void verifyComment(String comment) {
        String text = propertyDetailPage.comments.getText();
        softAssert.assertTrue(text.equalsIgnoreCase(comment));

    }

    public void clickOnTab(String tabName) throws Exception {
        Thread.sleep(3000);
        WebElement tabelement = driver.findElement(By.xpath("//label[contains(text(),'" + tabName + "')]"));
        Boolean status = clickObjectJavascript(tabelement, tabName);
        assertTrue(status);
        Thread.sleep(3000);
    }

    public void clickOnfinancial() throws Exception {
        WebElement tabelement = driver
                .findElement(By.cssSelector(" div.trepp__tabGroup__navbar > div:nth-child(3) > a"));
        Boolean status = clickObjectJavascript(tabelement, "financial");
    }

    public void verifyMyCommentHeading() throws Exception {
        waitForElement(propertyDetailPage.myCommentHeading);
        Boolean status = propertyDetailPage.myCommentHeading.isDisplayed();
        assertTrue(status);
    }

    public void verifyCapRateChangesEstimatedMarketValue() throws Exception {
        if (propertyDetailPage.estimatedMarketValue.getText().isEmpty()
                || propertyDetailPage.estimatedMarketValue.getText().equals("")) {
            System.out.println("Estimated Market Value is not pupulated");
            org.testng.Assert.assertTrue(false);
        } else {
            String eMarketVal = propertyDetailPage.estimatedMarketValue.getText();
            System.out.println("Estimated Market Value:" + eMarketVal + " is populated");
        }

    }

    public void enterCapRateVal(String inputNo) throws Exception {
        scrollIntoView(propertyDetailPage.capRate, "scroll into view");
        Actions a = new Actions(driver);
        a.click(propertyDetailPage.capRate).sendKeys(inputNo).sendKeys(Keys.ENTER).build().perform();
    }

    public boolean verifyPropertyPageWithTaxAndSaleTabVisibility(String nonSecuritizedProperty, boolean isTaxAndSaleTab) throws Exception { //added  new method
        wait.until(ExpectedConditions.visibilityOf(propertyDetailPage.nonSecuritizedPageTitle));
        assertEquals(nonSecuritizedProperty, propertyDetailPage.nonSecuritizedPageTitle.getText(), "The Property Title doesn't match");
        //assertThat(propertyDetailPage.nonSecuritizedPageSmallTitle.getText(),containsStringIgnoringCase(nonSecuritizedProperty));
        try {
            propertyDetailPage.taxAndSaleDataTab.isDisplayed();
        } catch (NoSuchElementException e) {
            assertFalse(isTaxAndSaleTab, "--> Tax & Sale Data Tab should not be available for " + nonSecuritizedProperty);
            System.out.println("--> Tax & Sale Data Tab is not available for " + nonSecuritizedProperty);
            return false;
        }
        assertTrue(isTaxAndSaleTab, "--> Tax & Sale Data Tab should not be available for " + nonSecuritizedProperty);
        clickObject(propertyDetailPage.taxAndSaleDataTab, "Tax & Sale Data ");
        return true;
    }

    public void verifyTransferCharacteristicsTable(String nonSecuritizedProperty) {
        List<WebElement> tables = driver.findElements(By.xpath("//span[text()='Transaction Details']"));
        if (tables == null)
            fail("--> no data found for property " + nonSecuritizedProperty);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[text()='Sale Price']"))));
        List<WebElement> salePrice = driver.findElements(By.xpath("//td[text()='Sale Price']"));
        List<WebElement> saleDate = driver.findElements(By.xpath("//td[text()='Sale Date']"));
        //List<WebElement> buyerName = driver.findElements(By.xpath("//td[text()='Buyer Name']"));
        List<WebElement> sellerName = driver.findElements(By.xpath("//td[text()='Seller Name']"));
        softAssert.assertEquals(tables.size(), salePrice.size(), "--> Sale price data is not available for property " + nonSecuritizedProperty);
        assertEquals(tables.size(), saleDate.size(), "--> Sale date data is not available for property " + nonSecuritizedProperty);
        //assertEquals(tables.size(),buyerName.size(),"--> buyer name data is not available for property " + nonSecuritizedProperty);
        assertEquals(tables.size(), sellerName.size(), "--> Seller name data is not available for property " + nonSecuritizedProperty);
    }
    
    public void clickDetailsTab() throws Exception {
        waitForElement(propertyDetailPage.deatailsTab);
        clickObject(propertyDetailPage.deatailsTab, "Details Tab");
    }
    
    public void clickFirstTrusteeLoanId() throws Exception {
        waitForElement(propertyDetailPage.fistTrusteeLoanId);
        clickObject(propertyDetailPage.fistTrusteeLoanId, "First Trustee LoanID");
        Thread.sleep(5000);
        
    }

    public void verfiyLoanInfoTableAndUniqueRow(String tableInnerName, String tableName , String... fields) throws Exception {
        WebElement table =  driver.findElement(By.xpath("//span[normalize-space()='" + tableName + "']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return arguments[0].scrollIntoView({inline:'center'});", table);
        Thread.sleep(2000);
        boolean isTableVisible = waitForElement(table);
        if (isTableVisible)
            System.out.println("--> " + tableName + " table is visible");
        softAssert.assertTrue(isTableVisible, "The table " + tableName + " is not visible");
        for (String field : fields) {
            verifyTableRow(tableInnerName, field);
        }
    }
    private void verifyTableRow(String tableInnerName , String rowField) throws Exception {
        WebElement element = driver.findElement(By.xpath("//div[@class='" + tableInnerName + "']//td[normalize-space()='" + rowField + "']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return arguments[0].scrollIntoView({inline:'center'});", element);
        if (element.isDisplayed())
            System.out.println(rowField + " Field is visible");
        softAssert.assertTrue(element.isDisplayed(), rowField + " is not visible on page.");
    }
}
