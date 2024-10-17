package com.newdemo.framework.pages;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class LoanByLoanPage {

    WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    /**
     * Elements
     **/
    @FindBy(css = "select[name='loanPortfolioID']")
    public WebElement portfolioSelect;

    @FindBy(css = "select[name='loanOverrideOption_0']")
    public WebElement loanOverrideSelect;

    @FindBy(id = "button_calculate")
    public WebElement calculateButton;

    @FindBy(xpath = ".//b[text()[contains(.,'OVERRIDE SUMMARY')]]/following-sibling::div/table/tbody/tr[@class='Odd tbody']/td")
    public List<WebElement> overrideSummaryData;

    @FindBy(id = "the_table")
    public WebElement individualLoanReportTable;

}
