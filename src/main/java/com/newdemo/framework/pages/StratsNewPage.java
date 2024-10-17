package com.newdemo.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class StratsNewPage {
    private WebDriver driver = BaseSetupClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    @FindBy(xpath = ".//span[contains(text(),'Strats v2')]")
    public WebElement stratslink;
    @FindBy(xpath = ".//span[text()='Strats']")
    public WebElement oldstratslink;

    @FindBy(xpath = "//h1[contains(text(),'Market Strats Main Collections')]")
    public WebElement stratsheader;

    @FindBy(xpath = "//span[contains(text(),'Basic Strats')]")
    public WebElement oldstratsheader;
    // 4526
    @FindBy(xpath = "//span[text()='TreppWire']")
    public WebElement treppWireTab;
    @FindBy(xpath = "//span[text()='Company Exposure']")
    public WebElement companyExposureTab;
    @FindBy(xpath = "//span[text()='In the Spotlight']")
    public WebElement inTheSpotLight;
    @FindBy(xpath = "(//tr[@class='Odd mh-1 mh tbody']//td[@class=' mw mw-140 ']//span//a)[1]")
    public WebElement firstDealName;
    @FindBy(xpath = "(//td[@class=' mw mw-200 ']//span//span/a)[1]")
    public WebElement firstPropertyName;
    @FindBy(xpath = "(//li[contains(@class, 'trepp__tabs__item--primary')])[1]")
    public WebElement loanTab;
    @FindBy(xpath = "//a[contains(@class, 'prop-name-label')]")
    public WebElement pinPopHeader;
    @FindBy(xpath = "//span[text()='Tenants']")
    public WebElement tenants;
    @FindBy(xpath = "(//i[contains(@class, 'fa fa-map-marker')])[1]")
    public WebElement pins;
    @FindBy(xpath = "(//div[@class='ag-react-container'])[1]//a")
    public WebElement selectDeal;

}
