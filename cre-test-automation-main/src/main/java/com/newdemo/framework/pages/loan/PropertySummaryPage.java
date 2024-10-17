package com.newdemo.framework.pages.loan;


import java.util.List;

//import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PropertySummaryPage {

    @FindBy(xpath = ".//trepp-http-activity-widget")
    public WebElement loading;


    @FindBy(css = "li[ng-class*='Overview'] a")
    public WebElement overviewTab;

    @FindBy(css = "li[ng-class*='Commentary'] a")
    public WebElement commentaryTab;

    @FindBy(xpath = ".//a[.//strong[text()[contains(.,'Back to Listing')]]]")
    public WebElement backToListingButton;

    //Overview tab
    @FindBy(css = "h4 loan-prop-detail-link a")
    public WebElement propertyTitleLink;

    @FindBy(css = "button[ng-click='editUserComment(loan)']")
    public WebElement addCommentButton;

    @FindBy(css = "div[ng-if='loan.userLoanComment'] p:nth-of-type(2)")
    public WebElement userLoanComment;

    @FindBy(css = "div[ng-if='loan.userLoanComment']")
    public List<WebElement> userLoanComments;

    @FindBy(css = "button[ng-click='editUserComment(loan)']")
    public WebElement userCommentEditButton;

    @FindBy(css = ".modal-dialog")
    public WebElement dialogModal;

    @FindBy(css = ".modal-dialog [name='userCommentForm']")
    public WebElement userCommentModal;

    @FindBy(css = "textarea[ng-model='userLoanComment.comment']")
    public WebElement userCommentInput;

    @FindBy(css = "button[ng-click='saveComment()']")
    public WebElement userCommentSaveButton;

    @FindBy(css = "button[ng-click='deleteComment()']")
    public WebElement userCommentDeleteButton;


    @FindBy(xpath = "//span[contains(text(),'Summary')]")
    public WebElement summaryPagelink;
    
    @FindBy(xpath = "//a[contains(text(), 'Related Records')]")
    public WebElement relatedRecordTab;
    @FindBy(xpath = "(//td[@ng-repeat='meta in columnMeta'])[1]")
    public WebElement selectFirstProperty;
    @FindBy(xpath = "//h4[@class='bold']//loan-prop-detail-link//span//a")
    public WebElement propertyNameHeader;
    @FindBy(xpath = "//a[@title='Occupancy']")
    public WebElement occupancyHeader;
    @FindBy(xpath = "//div[@class='graph-div']")
    public WebElement occupancyGraph;
    @FindBy(xpath = "//tr[@ng-repeat='tenant in tenants']")
    public List<WebElement> top5Tenants;
    @FindBy(xpath = "//button[contains(text(),'View Historical Comments')]")
    public WebElement viewHistoricalComments;
    @FindBy(xpath = "//div[@class='modal-content']")
    public WebElement modalOpens;
    @FindBy(xpath = "(//p[contains(@ng-bind, 'loanCommentary')])[1]")
    public WebElement comment;
    @FindBy(xpath = "(//p[contains(@ng-bind, 'loanCommentary')])[2]")
    public WebElement modalContent;
    @FindBy(xpath = "//button[contains(text(), 'Close')]")
    public WebElement closeModel;
 // 4661
    @FindBy(xpath = "//button[@ng-click='addSelectedToPortfolio()']")
    public WebElement addToPortfolioIcon;
    @FindBy(xpath = "//a[@title='Deal List']")
    public WebElement dealListIcon;
    @FindBy(xpath = "//h4[text()='Select Deal List']")
    public WebElement addDealListModal;
    @FindBy(xpath = "//a[@class='ExcelTool__iconButton']/i")
    public WebElement downloadIcon;
    @FindBy(xpath = "//button[i[normalize-space(@class)='fa fa-lg fa-bell-o fa-2x']]")
    public WebElement bellOffIcon;
    @FindBy(xpath = "//button[i[normalize-space(@class)='fa fa-lg fa-bell has-alert fa-2x']]")
    public WebElement bellOnIcon;
    @FindBy(xpath = "//h4[text()='Deal Alert']")
    public WebElement alertModalBox;
    @FindBy(xpath = "//p[text()='FHA Loan Status']")
    public WebElement clickCheckboxFHALoanStatus;
    @FindBy(xpath = "//button[text()='Save Search/Alert']")
    public WebElement saveAlertBtn;
    @FindBy(xpath = "//span[@ng-if='loan.myMarkets.length > 1']")
    public WebElement addedDeaListIcon;
    @FindBy(xpath = "//button[@class='AlertConfirmationModal__btn']")
    public WebElement saveAlertConfirmationModalBtn;
    @FindBy(xpath = "(//span[@class='ag-cell-wrapper ag-row-group ag-row-group-indent-0'])[1]")
    public WebElement firstAlert;

}
