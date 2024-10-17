package com.newdemo.framework.pages.stats;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class NewIssuancePage {

    private WebDriver driver = BaseSetupClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    @FindBy(xpath = ".//span[text()[contains(.,'Volume')]]")
    public WebElement volumeAmount;

    @FindBy(xpath = ".//span[text()[contains(.,'Deals')]]")
    public WebElement dealsCount;

    @FindBy(css = "button.population-dropdown-button")
    public WebElement populationDropdown;

    public WebElement populationDropdownChoice(String choiceName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li[@ng-repeat='p in dealPopulations']/a[contains(text(),'" + choiceName + "')]")));
    }

    @FindBy(css = "button.broad-deal-type-dropdown-button")
    public WebElement dealTypeDropdown;

    @FindBy(css = "button.vintage-dropdown-button")
    public WebElement vintageDropdown;

    @FindBy(css = "button i.fa-download")
    public WebElement downloadButton;

    @FindBy(css = "button i.fa-spinner")
    public WebElement downloadSpinner;

    /**
     * Transaction tab
     **/
    @FindBy(xpath = ".//a[text()='Transactions']")
    public WebElement transactionTabLink;

    @FindBy(css = "input[placeholder='Search New Issues']")
    public WebElement newIssueSearch;

    @FindBy(css = "#compareSelectedSwitch")
    public WebElement compareSelectedSwitch;

    @FindBy(xpath = ".//button[text()='Credit View']")
    public WebElement creditViewButton;

    @FindBy(xpath = ".//button[text()='Pricing View']")
    public WebElement pricingViewButton;

    @FindBy(css = "div[col-id='bloombergName'] a")
    public List<WebElement> issueDealList;


    /**
     * New Issuance tab
     **/
    @FindBy(xpath = ".//a[text()='New Issue Trends']")
    public WebElement newIssueTrendsTabLink;

    @FindBy(css = "//select[@name='risk-retention-type']")
    public WebElement riskRetentionTypes;

    public WebElement riskRetentionTypesChoice(String choiceName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li/a[contains(text(),'" + choiceName + "')]")));
    }

    //charts
    @FindBy(xpath = "//div[@class='YearVolumeChart card']")
    public WebElement yearlyVolumeComparison;

    @FindBy(xpath = "//div[@class='QtrVolumeChart card']")
    public WebElement quaterlyNewIssueVolume;

    @FindBy(xpath = "(//div[@class='CreditTimeSeries card'])[1]")
    public WebElement monthlyAverageLTV;

    @FindBy(xpath = "(//div[@class='CreditTimeSeries card'])[2]")
    public WebElement monthlyAverageDebtYield;

    @FindBy(xpath = "(//div[@class='CreditTimeSeries card'])[3]")
    public WebElement monthlyAverageDSCR;

    @FindBy(xpath = "(//div[@class='CreditTimeSeries card'])[4]")
    public WebElement monthlyAverageIOComposition;

    /**
     * Spread and Rates tab
     **/
    @FindBy(xpath = ".//a[text()='Spreads & Rates']")
    public WebElement spreadsNRatesTabLink;

    //chart
    @FindBy(css = "div[new-issue-bond-spread-chart]")
    public WebElement newIssueBondSpreadChart;

    @FindBy(css = "div[new-issuance-spreads-treppwire]")
    public WebElement newIssueSpreadTreppWire;

    @FindBy(css = "new-issuance-cmbs-spread-chart")
    public WebElement newIssueCMBSSpreadChart;

    @FindBy(css = "div[new-issue-cmbx-spread-chart]")
    public WebElement newIssueCMBXSpreadChart;

    @FindBy(css = "div[new-issue-treppi-spread-chart]")
    public WebElement newIssueTreppiSpreadChart;

    @FindBy(css = "div[new-issue-treasury-yields-chart]")
    public WebElement newIssueTreasuryYieldChart;
    //4570
    @FindBy(xpath = "//span[text()='WAC']")
    public WebElement wacCol;
    @FindBy(xpath = "(//div[@col-id='secWAC'])[2]")
    public WebElement wacColFirstVal;
    @FindBy(xpath = "//button[contains(text(), 'Pricing View')]")
    public WebElement pricingViewBtn;
    @FindBy(xpath = "//span[text()='Pricing']")
    public WebElement pricingCol;
    @FindBy(xpath = "(//div[@col-id='initialPricingDate'])[2]")
    public WebElement pricingColFirstValue;
    @FindBy(xpath = "//select[@aria-label='newIssuancePopulation']")
    public WebElement newIssuancePopulationDropdown;
    @FindBy(xpath = "((//div[contains(@class,'ag-body-viewport ag-layout-normal')]//div[@row-index='0'])[2])[1]")
    public WebElement dataRow;
    @FindBy(xpath = "//select[@name='dealType']")
    public WebElement dealType;
    @FindBy(xpath = "//select[@name='vintage']")
    public WebElement vintage;

}

