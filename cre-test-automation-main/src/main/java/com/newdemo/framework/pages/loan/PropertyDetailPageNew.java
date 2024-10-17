package com.newdemo.framework.pages.loan;

import java.util.List;
//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.newdemo.framework.base.BaseSetupClass;

public class PropertyDetailPageNew {
    private WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);
    // title
    @FindBy(css = ".navbar .pull-left h3")
    public WebElement newpagetitle;
    // top button
    @FindBy(css = "a[title='Deal List']")
    public WebElement dealListButton;
    @FindBy(css = "a[title='Download']")
    public WebElement downloadButton;
    @FindBy(css = "a[title='Print']")
    public WebElement printButton;
    /* Tabs */
    @FindBy(css = "a[href*='dealDetailSection=Loan']")
    public WebElement loanTab;
    @FindBy(css = "a[href*='dealDetailSection=Commentary']")
    public WebElement myCommentsTab;
    @FindBy(css = "a[href*='dealDetailSection=Property']")
    public WebElement propertyTab;
    @FindBy(css = "li[data-hash='Financials']")
    public WebElement financialsTab;
    @FindBy(css = "li[data-hash='Research']")
    public WebElement researchTab;
    @FindBy(css = "li[data-hash='Documents']")
    public WebElement dealDocumentsTab;
    @FindBy(css = "a[href*='dealDetailSection=Title']")
    public WebElement titleDataTab;
    @FindBy(xpath = "//span[contains(text(),'Details')]")
    public WebElement detailv2;
    @FindBy(xpath = "//a[contains(text(),'My Comments')]")
    public WebElement mycommenttab;
    @FindBy(xpath = "(//div[@row-id='Cap Rate'])[2]")
    public WebElement capRate;
    @FindBy(xpath = "(//div[@row-id='Estimated Market Value'])[2]")
    public WebElement estimatedMarketValue;
    @FindBy(xpath = "//div[@class='comment']//div[@class='comment__text']")
    public WebElement comments;
    @FindBy(xpath = "//a[contains(text(),'Deal Documents')]")
    public WebElement dealDocumentTab;
    @FindBy(xpath = "//h2[contains(text(),'My Comments')]")
    public WebElement myCommentHeading;
    @FindBy(xpath = "//td[contains(text(),'Property Name')]/following-sibling::td")
    public WebElement propertyName;

    @FindBy(xpath = "//p[@class='title'] ")
    public WebElement nonSecuritizedPageTitle;

    @FindBy(xpath = "//p[@class='title-small']")
    public WebElement nonSecuritizedPageSmallTitle;

    @FindBy(xpath = "//label[@for='tab-taxAndSale']")
    public WebElement taxAndSaleDataTab;
    @FindBy(xpath = "(//span[contains(text(),'Details')])[1]")
    public WebElement deatailsTab;
    @FindBy(xpath = "((//tr[@class='trepp__tableRow rowData '])[2]//td[@class='trepp__tableData'])[8]/a")
    public WebElement fistTrusteeLoanId;
    /* Loan tab */
    // tables

    /**
     * dynamic element
     **/
    public WebElement getTableByTitle(String title) {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("deal-detail-table[heading='" + title + "']")));
    }

    public WebElement getTableByTitleNew(String title) {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='trepp__tableContainer']//span[text()='" + title
                        + "']/../following-sibling::table[@class='trepp__table']")));
    }

    public WebElement getTableHeaderByTitle(String title) {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='table__header']//h2[text()='" + title + "']")));
    }

    public List<WebElement> getTableValuesByTitle(String title) {
        return getTableByTitle(title).findElements(By.cssSelector(" table td.align-r"));
    }

    public List<WebElement> getTableValuesByTitleNew(String title) {
        return getTableByTitleNew(title).findElements(By.xpath("//div[@class='trepp__tableContainer']//span[text()='"
                + title + "']/../following-sibling::table[@class='trepp__table']//td[@class='trepp__tableData'][1]"));
    }
}

