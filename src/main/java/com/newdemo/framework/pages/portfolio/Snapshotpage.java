package com.newdemo.framework.pages.portfolio;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Snapshotpage {

    @FindBy(xpath = "//i[@class='fa fa-lg fa-fw fa-bar-chart-o']")
    public WebElement snapshoticon;
    @FindBy(xpath = "//span[contains(text(),'Download')]")
    public WebElement downloadButton;
    @FindBy(xpath = "//span[contains(text(),'Print')]")
    public WebElement printButton;
    @FindBy(xpath = "//strong[contains(text(),'Portfolio Stratifications (% of Current Balance)')]")
    public WebElement table1;
    @FindBy(xpath = "//strong[contains(text(),'TOP 15 ')]")
    public WebElement table2;
    @FindBy(xpath = "//strong[contains(text(),'MSA')]")
    public WebElement table3;
    @FindBy(css = "embed[type='application/pdf']")
    public WebElement pdfViewerRoot;

}
