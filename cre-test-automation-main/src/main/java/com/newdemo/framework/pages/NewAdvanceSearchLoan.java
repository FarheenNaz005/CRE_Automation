package com.newdemo.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class NewAdvanceSearchLoan {
    private WebDriverWait wait = new WebDriverWait(BaseSetupClass.getDriver(), 20);

    /*enum for filed with suggestion */
    public enum TabName {
        Property,
        Loan
    }

    /*enum for filed with suggestion */
    public enum FieldWithSuggestion {
        stateregion,
        city, 
        county,
        zippostalcode,
        propertytype,
        msaname
    }

    @FindBy(xpath = ".//button[contains(text(),'Cancel')]//i")
    public WebElement cancelbutton;

    @FindBy(xpath = ".//button[contains(text(),'Search')]//i")
    public WebElement searchbutton;

    @FindBy(xpath = "//legend[text()='Delinquency Status']/following-sibling::div/button[@class='select-multiple__button']")
    public WebElement delinquencyStatus;

    @FindBy(xpath = "//input[@class='range-input__input input__max  undefined' and @name='remterm']")
    public WebElement remTermMax;

    @FindBy(xpath = "//input[@class='range-input__input input__min  undefined' and @name='remterm']")
    public WebElement remTermMin;
    
    
  //input[@name='loanname'] loanname
  
    
  //input[@id='originator'] originator
    
  //input[@id='bloombergname'] bloombergname  
    
    
  //input[@id='fhanumber']  fhanumber
    
    
    
  //input[@name='View More'] ViewMore 
    
   
  //input[@name='ratetype'] ratetype checkbox
    
  //input[@name='newlydq'] newlydq checkbox
    
    
  //input[@name='newlyspecserv'] newlyspecserv checkbox
    
  //input[@name='newlywatchlist'] newlywatchlist checkbox
    
    
  //input[@name='watchliststatus'] watchliststatus checkbox
    
    
  //input[@name='loanmodifiedflag'] loanmodifiedflag checkbox
    
    
    
   
     
    
 
     //input[@class='range-input__input input__min ' and @name='curloanbal'] currentbalance   
    
    //input[@class='range-input__input input__max  undefined' and @name='curloanbal'] currentbalance   
    
    
     //input[@class='range-input__input input__min ' and @name='specservstatus'] specservstatusmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='specservstatus'] specservstatusmax 
   
    
     //input[@class='range-input__input input__min ' and @name='mrdscrncf'] mrdscrncfmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='mrdscrncf'] mrdscrncfmax 
    
    
     //input[@class='range-input__input input__min ' and @name='mrdscrnoi'] mrdscrnoimin   
    
    //input[@class='range-input__input input__max  undefined' and @name='mrdscrnoi'] mrdscrnoimax 
    
    
    //input[@class='range-input__input input__min ' and @name='secdscrncf'] secdscrncfmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='secdscrncf'] secdscrncfmax 
    
    
    //input[@class='range-input__input input__min ' and @name='secdscrnoi'] secdscrnoimin   
    
    //input[@class='range-input__input input__max  undefined' and @name='secdscrnoi'] secdscrnoimax 
    
    
     //input[@class='range-input__input input__min ' and @name='curltv'] curltvmin
    
    //input[@class='range-input__input input__max  undefined' and @name='curltv'] curltvmax
    
   
    

 //input[@class='range-input__input input__min ' and @name='loanmodifiedexecutiondt'] loanmodifiedexecutiondtmin
    
    //input[@class='range-input__input input__max  undefined' and @name='loanmodifiedexecutiondt'] loanmodifiedexecutiondtmax
    
    
    
    
    
 //input[@class='range-input__input input__min ' and @name='securitizationyear'] securitizationyearmin
    
    //input[@class='range-input__input input__max  undefined' and @name='securitizationyear'] securitizationyearmax
    
    
 
    
    
     //input[@class='range-input__input input__min ' and @name='dispdt'] dispdtmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='dispdt'] dispdtmax 
    
    //input[@class='range-input__input input__min ' and @name='amortterm'] amorttermmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='amortterm'] amorttermmax 
    
    
     //input[@class='range-input__input input__min ' and @name='seasoning'] seasoningmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='seasoning'] seasoningmax 
    
    
     //input[@class='range-input__input input__min ' and @name='loanrate'] loanratemin   
    
    //input[@class='range-input__input input__max  undefined' and @name='loanrate'] loanratemax 
    
    

    
    
    //input[@class='range-input__input input__min ' and @name='origterm'] origtermmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='origterm'] origtermmax 
    
    
//input[@class='range-input__input input__min ' and @name='origloanbal'] origloanbalmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='origloanbal'] origloanbalmax 
    
    
    
    
//input[@class='range-input__input input__min ' and @name='maturitydt'] maturitydtmin  
    
    //input[@class='range-input__input input__max  undefined' and @name='maturitydt'] maturitydtmax 
    
    
    
    
    
    
//input[@class='range-input__input input__min ' and @name='originationdt'] originationdtmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='originationdt'] originationdtmax 

    
    
    
    
    //dynamic variable
    public WebElement tab(TabName tab) { 
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//label[contains(@class, 'toggle-option__label option__label--selected') and contains(text(), '" + tab + "')]")));
         
    }

    public WebElement sourceCheckByName(String source) { 
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='" + source + "']")));  
             
    }

    public WebElement inputForfiledWithSuggestion(FieldWithSuggestion field) { 
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='" + field + " ']")));  
         
    }
    

    public WebElement suggestionItemByName(FieldWithSuggestion field, String query) { 
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#" + field + " auto-complete ul")))
                .findElement(By.xpath(".//li/em[text()[contains(.,'" + query + "')]]"));
        return wait.until(ExpectedConditions.visibilityOf(item));
        
    }  
    
    @FindBy(xpath = "//legend[text()='Loan Data Source']/following-sibling::div/button[@class='select-multiple__button']")
    public WebElement loanDataSource;
    @FindBy(xpath = "//input[@aria-label='Maturity Year Minimum Range']")
    public WebElement maturityYear;
    @FindBy(xpath = "//input[@aria-label='Original Balance Minimum Range']")
    public WebElement originalBalance;
    @FindBy(xpath = "(//input[@name='yearbuilt'])[1]")
    public WebElement yearBuilt;
    @FindBy(xpath = "//legend[text()='Property Subtype']/following-sibling::div/button[@class='select-multiple__button']")
    public WebElement propertySubtype;
    @FindBy(xpath = "//legend[text()='Amortization Type']/following-sibling::div/button[@class='select-multiple__button']")
    public WebElement amortizationType;
    
}
