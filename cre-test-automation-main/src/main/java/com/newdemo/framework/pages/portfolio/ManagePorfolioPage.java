package com.newdemo.framework.pages.portfolio;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagePorfolioPage {

    /**
     * top buttons
     **/
    @FindBy(css = "p.manage-portfolios-title")
    public WebElement pageTitle;

    @FindBy(css = "button.manage-portfolios-delete")
    public WebElement deleteButton;


    /**
     * portfolio table
     **/
    @FindBy(css = "div[aria-rowIndex='2'] .ag-header-cell[style*='left: 0px'] input")
    public WebElement nameFilterInput;


    //modaldialog
    @FindBy(css = "div.modal-dialog")
    public WebElement modalDialog;

    @FindBy(xpath = "//button[@class='btn btn-primary']//i[@class='fa fa-plus']")
    public WebElement addportfoliobutton;

    @FindBy(css = "div.modal-dialog .alert-info")
    public WebElement modalDialogAlertInfo;

    @FindBy(css = "div.modal-dialog .alert-success")
    public WebElement modalDialogAlertSuccess;

    @FindBy(css = "div.modal-dialog .btn-danger")
    public WebElement modalDialogDelete;


    //comment modal
    @FindBy(css = "div.modal-dialog textarea")
    public WebElement commentTextArea;

    @FindBy(css = "div.modal-dialog .btn-primary")
    public WebElement addCommentButton;

    @FindBy(css = "div.modal-dialog .btn-primary i.fa-spinner")
    public WebElement addCommentButtonLoading;

    @FindBy(css = "span.comment-text")
    public WebElement addedComments;

    @FindBy(css = "a[ng-click*='edit(comment)']")
    public WebElement editCommentButton;

    @FindBy(css = "[id*='edit-textarea']")
    public WebElement commentEditTextArea;

    @FindBy(css = "div.modal-dialog .btn-primary[ng-click*='updateComment']")
    public WebElement editCommentSaveButton;

    @FindBy(css = "a[ng-click*='delete(comment)']")
    public WebElement deleteCommentButton;

    //share modal
    @FindBy(css = "div.modal-dialog input")
    public WebElement shareEmailInput;

    @FindBy(css = "div.modal-dialog [ng-click*='addLoanPortfolioAccess']")
    public WebElement shareAddPersonButton;


    @FindBy(css = "div.modal-dialog td.email")
    public WebElement shareModalEmailListFirst;

    @FindBy(css = "div.modal-dialog [ng-click*='closeSharingModal']")
    public WebElement shareModalDoneButton;


    /**
     * History Page
     ***/
    @FindBy(xpath = ".//a[./span[contains(text(),'Back to My Portfolio Manager')]]")
    public WebElement historyPageBackButton;
    
    @FindBy(xpath = "//a[text()='Loan Information']")
    public WebElement loanInformation;
    
    @FindBy(xpath = "//a[text()='Property Financials']")
    public WebElement propertyFinancials;
    
    @FindBy(xpath = "//span[contains(text(), 'Snapshot')]")
    public WebElement snapShotTab;
    
    @FindBy(xpath = "(//tr[@class='Odd tbody']//td[@align='left']//a)[3]")
    public WebElement clickMSALink;
    
    @FindBy(xpath = "(//a[@class='ag-property-name'])[1]")
    public WebElement propName;
    
    @FindBy(xpath = "//a[text()='Property Financials']")
    public WebElement propFinancials;
    
    @FindBy(xpath = "(//a[contains(@class,'edit-button')])[2]")
    public WebElement editLoan;
    
    @FindBy(xpath = "//span//input[@label='Food & Beverage Revenues']")
    public WebElement foodBevBefore;
    
    @FindBy(xpath = "(//tr[@ng-form='FoodBeverageRevenuesForm']//td)[2]")
    public WebElement foodBevAfter;
    
    @FindBy(xpath = "//input[@label='Real Estate Taxes']")
    public WebElement realEstateTx;
    
    @FindBy(xpath = "(//tr[@ng-form='RealEstateTaxesForm']//td)[2]")
    public WebElement realEstateTxAfter;
     
    @FindBy(xpath = "//button[@ng-click='updateLoan()']")
    public WebElement updateLoan;

    @FindBy(xpath = "//div[@class='ag-primary-cols-header-panel']//div[2]//span[1]")
    public WebElement selectAllCheckbox;
    
    @FindBy(xpath = "//div[@ref='eSelect']//span[@class='ag-icon ag-icon-checkbox-indeterminate']")
    public WebElement selectAllCheckbox1;
    
    @FindBy(xpath = "//div[@col-id='propertyClass']")
    public List<WebElement> classCol;
    
    @FindBy(xpath = "//button[text()='Get Valuation']")
    public WebElement getValuationBtn;
    
    @FindBy(xpath = "//div[@class='ValuationModal__body']")
    public WebElement getValuationModalBox;
    
    @FindBy(xpath = "//input[@class='dateInput__picker']")
    public WebElement valuationdate;
        
    @FindBy(xpath = "//input[@class='ttInput__pct']")
    public WebElement valuationTT;
    
    @FindBy(xpath = "//button[@class='ValuationModal__button']")
    public WebElement getValuationButton;
    
    @FindBy(xpath = "//button[@class='btn btn-secondary btn-open-model']")
    public WebElement modalButton;
    
    @FindBy(xpath = "//div[@class='trepp-loan-modal valuation-model-modal ng-scope']//h4")
    public WebElement valuationModal;
    
    @FindBy(xpath = "(//div[@col-id='valuationModelingClass'])[2]")
    public WebElement classA1;
    
    @FindBy(xpath = "(//div[@col-id='valuationModelingClass_1'])[2]")
    public WebElement classC1;
    
    @FindBy(xpath = "//a[@class='revert-btn fa fa-undo']")
    public WebElement revertToPresets;
}
