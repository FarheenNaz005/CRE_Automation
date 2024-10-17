package com.newdemo.framework.pages.stats;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class CompanyExposurePage {

    private WebDriver driver = BaseSetupClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    /**
     * tenants/hotel switch buttons
     **/
    @FindBy(xpath = ".//span[text()='Hotels']/..")
    public WebElement switchHotelButton;

    @FindBy(xpath = ".//span[text()='Tenants']/..")
    public WebElement switchTenantButton;

    @FindBy(css = "button[ng-click='download()']")
    public WebElement downloadButton;

    @FindBy(css = "div[company-exposure-table] table tbody")
    public WebElement exposureTable;

    @FindBy(css = "div[company-exposure-table] table tbody tr")
    public List<WebElement> allRowsOfTable;

    @FindBy(css = "a[ng-click *= 'gotoLoanListing']")
    public WebElement propertyListLink;

    /**
     * hotel
     **/

    @FindBy(xpath = ".//span[text()='View by:']/following-sibling::div/button")
    public WebElement hotelViewByDropdown;

    @FindBy(css = "input[placeholder='Search hotel name...']")
    public WebElement hotelSearchbar;

    @FindBy(xpath = ".//a[contains(@ui-sref,'hotelReport')]")
    public List<WebElement> allHotelSnapshotLink;

    /**
     * tenants
     **/
    @FindBy(css = "input[placeholder='Search tenant name...']")
    public WebElement tanentSearchbar;

    @FindBy(xpath = ".//a[contains(@ui-sref,'tenantReport')]")
    public List<WebElement> allTenantReportLink;


    /**
     * dynamic elements
     **/
    public WebElement hotelSnapshotLink(String hotelName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(@ui-sref,'hotelReport') and contains(text(),'" + hotelName + "')]")));
    }

    public WebElement tenantReportLink(String tenantName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(@ui-sref,'tenantReport') and contains(text(),'" + tenantName + "')]")));
    }


    public WebElement getHotelViewChoice(String name) {
        return wait.until(ExpectedConditions.visibilityOf(hotelViewByDropdown.findElement(By.xpath("./following-sibling::ul//a[text()[contains(.,'" + name + "')]]"))));
    }
    /*
     * public List<String> columnValuesByName(String hotelTanentName) { return
     * hotelTanentSnapshotLink(hotelTanentName).findElements(By.xpath(
     * "./../../../../td")) .stream() .map(ele ->
     * ele.getAttribute("innerText")).collect(Collectors.toList()); }
     */
    
    @FindBy(xpath = "//a[@ng-if='user.can.loan_research']")
    public WebElement propertyListBtn;
    
    @FindBy(xpath = "(//tr[@ng-repeat='row in recentTransactions']//td/a)[1]")
    public WebElement selectFirstPropertyName;
    
    @FindBy(xpath = "//*[name()='g' and @class='nv-pie']")
    public WebElement chart;
    
    @FindBy(xpath = "//button[@id='floating-ui-5']")
    public WebElement operatingPerformanceDropdown;
    @FindBy(xpath = "//li[text()='2020']")
    public WebElement dropdownValue;
    @FindBy(xpath = "//th[@class='trepp__tableSubHeader' and text()='Revenues'] ")
    public WebElement revenuesHeader;
    @FindBy(xpath = "//tr[@class='trepp__tableRow rowData clickable-row']")
    public List<WebElement> operatingPerformancedata;
    @FindBy(xpath = "//div[@class='recharts-wrapper']")
    public WebElement operatingPerformanceGraph;
    @FindBy(xpath = "//button[contains(text(), '$/Room')]")
    public WebElement roomToggle;
    @FindBy(xpath = "//button[contains(text(), '% Rev')]")
    public WebElement revToggle;
    @FindBy(xpath = "//button[contains(text(), '% Op Ex')]")
    public WebElement opExToggle;
    @FindBy(xpath = "//div[contains(text(), 'OPERATING EXPENSES')]")
    public WebElement opExHeader;
    @FindBy(xpath = "//input[@placeholder='Enter Hotel Franchise or Brand...']")
    public WebElement hotelSearch;
    @FindBy(xpath = "//div[@data-testid='progress-bar-logo']")
    public WebElement isLoadingPage;
    @FindBy(xpath = "//button[contains(text(), 'Go')]")
    public WebElement goBtn;
    @FindBy(xpath = "//*[name()='g' and @class='recharts-layer recharts-bar-rectangle']//*")
    public List<WebElement> bar1;
    @FindBy(xpath = "//*[name()='g' and @class='nv-group nv-series-1']//*[name()='rect' and @class='nv-bar positive']")
    public List<WebElement> bar2;
    @FindBy(xpath = "(//tr[@data-testid='table-headerGroups'])[1]//th")
    public List<WebElement> hotelAndPropertyCol;
    


}
