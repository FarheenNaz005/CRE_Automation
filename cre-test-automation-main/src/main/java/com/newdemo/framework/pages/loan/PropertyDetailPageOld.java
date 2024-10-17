package com.newdemo.framework.pages.loan;

import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class PropertyDetailPageOld {

    private WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    /*Tabs*/
    @FindBy(css = "li[data-hash='Loan']")
    public WebElement loanTab;

    @FindBy(css = "li[data-hash='Pieces']")
    public WebElement loanPiecesTab;

    @FindBy(css = "li[data-hash='Property']")
    public WebElement propertyTab;

    @FindBy(css = "li[data-hash='Financials']")
    public WebElement financialsTab;

    @FindBy(css = "li[data-hash='Research']")
    public WebElement researchTab;

    @FindBy(css = "li[data-hash='Documents']")
    public WebElement dealDocumentsTab;

    @FindBy(css = "li[data-hash='Title']")
    public WebElement titleDataTab;


    /*Loan tab*/
    // title
    @FindBy(css = "span.page-title[title='Loan Detail']")
    public WebElement oldPageTitle;

    @FindBy(css = "strong.sub-title")
    public WebElement subTitle;

    // top button
    @FindBy(css = "a.deal-list-btn")
    public WebElement dealListButton;

    @FindBy(css = "a.download-button")
    public WebElement downloadButton;

    @FindBy(css = "a.print-button")
    public WebElement printButton;

    //pdf viewer
    @FindBy(css = "embed[type='application/pdf']")
    public WebElement pdfViewerRoot;

    // tables
    @FindBy(css = ".summary-table.loan-header")
    public WebElement dealSummaryTable;

    @FindBy(css = ".summary-table.loan-header td")
    public List<WebElement> dealSummaryTableValues;


    /**
     * dynamic element
     **/
    public WebElement getTableByTitle(String title) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//table[./caption//strong[text()[contains(.,'" + title + "')]]]")));
    }

    public List<WebElement> getTableValuesByTitle(String title) {
        return getTableByTitle(title).findElements(By.xpath("/tbody/tr/td[@align='right']"));
    }

}
