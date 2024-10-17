package com.newdemo.framework.pages.portfolio;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RankingPage {
    @FindBy(xpath = "//i[@class='fa fa-lg fa-fw fa-trophy']")
    public WebElement rankicon;

    @FindBy(xpath = "//a[contains(text(),'Summary Data')]")
    public WebElement rankingSummary;

    @FindBy(xpath = "//strong[contains(text(),'Loan Rankings Summary (% of current balance)')]")
    public WebElement loanrankingsummary;

    @FindBy(xpath = "//a[contains(text(),'Detailed Data')]")
    public WebElement detaileddata;

    @FindBy(xpath = "//input[@value='msa']")
    public WebElement msaradiobuttion;

    @FindBy(xpath = "//input[@value='state']")
    public WebElement stateradiobuttion;

    @FindBy(xpath = "//input[@value='region']")
    public WebElement regionradiobuttion;

    @FindBy(xpath = "//span[contains(text(),'Display')]")
    public WebElement displaybutton;

    @FindBy(xpath = "//span[contains(text(),'Loan Rankings versus MSA')]")
    public WebElement msadetaildata;

    @FindBy(xpath = "//span[contains(text(),'Loan Rankings versus State')]")
    public WebElement statedetaildata;

    @FindBy(xpath = "//span[contains(text(),'Loan Rankings versus Region')]")
    public WebElement regiondetaildata;

    @FindBy(css = "a.download-button")
    public WebElement downloadButton;

    @FindBy(css = "a.print-button")
    public WebElement printButton;

    // pdf viewer
    @FindBy(css = "embed[type='application/pdf']")
    public WebElement pdfViewerRoot;

}
