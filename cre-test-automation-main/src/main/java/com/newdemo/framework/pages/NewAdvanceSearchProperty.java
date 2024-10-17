package com.newdemo.framework.pages;


//import org.openqa.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class NewAdvanceSearchProperty {

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
        msaname,
        
    }

    @FindBy(xpath = ".//button[contains(text(),'Cancel')]//i")
    public WebElement cancelbutton;

    @FindBy(xpath = ".//button[contains(text(),'Search')]//i")
    public WebElement searchbutton;

    @FindBy(xpath = "//div[@data-testid='loans-result']")
    public WebElement searchCount;

    @FindBy(xpath = "//div[@class='trepp__progressSpinner']")
    public WebElement loading;

    @FindBy(xpath = "//legend[text()='Property Type']/following-sibling::div/button[@class='select-multiple__button']")
    public WebElement openPropertyTypeDropdown;

    @FindBy(xpath = "//legend[text()='Property State']/following-sibling::div/button[@class='select-multiple__button']")
    public WebElement openPropertyStateDropdown;

    @FindBy(xpath = "//input[@id='citystate']")
    public WebElement propertyCity;
    
    @FindBy(xpath = "//input[@id='zippostalcode']")
    public WebElement zipCode;

    @FindBy(xpath = ".//h2[contains(text(),'Advanced Search')]")
    public WebElement heading;
    
  //input[@id='seccurlessee'] tenant
   
  //input[@id='guarantor'] sponsor  
    
    
  //input[@name='ownername'] ownername
    
  //input[@id='hotelbrand'] Hotalname 
    
  //input[@id='submarket'] submarket
    
  //input[@id='parcelnumber'] parcelnumber
    
    
    
    
    
  //input[@name='operatingstatementsflag'] operatingstatements checkbox
    
  //input[@name='warehousetype'] operatingstatements checkbox
    
  //input[@name='locationtype'] locationtype checkbox
    
  //input[@name='anchortype'] anchortype checkbox
    
    
  //input[@name='shoppingcenterformat'] shoppingcenterformat checkbox  
 
   
    
    
    
      //button[@class='option__button']//i propertysubtypedropdowncheckall
    
        
      //input[@name='isqoz'] Inside Qualified Opportunity Zone?
        
      //input[@class='range-input__input input__max  undefined' and @name='yearbuilt'] yearbuiltmax
        
        
      //input[@class='range-input__input input__min ' and @name='yearbuilt']  yearbuiltmin
        
      //input[@class='range-input__input input__min ' and @name='sqft']  sqftmin   
        
      //input[@class='range-input__input input__max  undefined' and @name='sqft'] sqftmax
        
        //input[@class='range-input__input input__max  undefined' and @name='numunits'] numunitsmax   
        
       //input[@class='range-input__input input__min ' and @name='numunits'] numunitsmin    
        
         //input[@class='range-input__input input__min ' and @name='seccurleaseexpdt'] seccurleaseexpdtmin   
        
         //input[@class='range-input__input input__max  undefined' and @name='seccurleaseexpdt'] seccurleaseexpdtmax  
        
        //input[@class='range-input__input input__min ' and @name='noi'] noimin   
        
        //input[@class='range-input__input input__max  undefined' and @name='noi'] noimax  
        
       
         //input[@class='range-input__input input__min ' and @name='ncf'] ncfmin   
        
        //input[@class='range-input__input input__max  undefined' and @name='ncf'] ncfmax  
        
    //input[@class='range-input__input input__min ' and @name='totalrevenues'] totalrevenuesmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='totalrevenues'] totalrevenuesmax  

    @FindBy(xpath = "//input[@class='range-input__input input__min  undefined' and @name='salesdate']")
    public WebElement salesDateMin;
    
    //input[@class='range-input__input input__max  undefined' and @name='salesdate'] salesdatemax  
    
    
 //input[@class='range-input__input input__min ' and @name='salesprice'] salespricemin   
    
    //input[@class='range-input__input input__max  undefined' and @name='salesprice'] salepricemax  
    
    
 //input[@class='range-input__input input__min ' and @name='mrapprval'] appraisalmin   
    
    //input[@class='range-input__input input__max  undefined' and @name='mrapprval'] appraisalmax  
    
    
    
    
    
    
    
    
    
    
    
   
    
    //dynamic variable
    public WebElement tab(TabName tab) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, 'toggle-option__label') and contains(text(), '" + tab + "')]")));
   
    }

    /*public WebElement sourceCheckByName(String source) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//taui-multi-select[@id='dataSource']//ul/li/a[text()[contains(.,'"  +  source  +  "')]]"))); 
   
    }*/

    public WebElement inputForFiledWithSuggestion(FieldWithSuggestion field) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='"  +  field  +  " ']")));  
         
    }

    public WebElement suggestionItemByName(FieldWithSuggestion field, String query) {
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#" +  field  + " auto-complete ul")))
                .findElement(By.xpath(".//li/em[text()[contains(.,'" + query + "')]]"));
        return wait.until(ExpectedConditions.visibilityOf(item));
        
    }
    
    @FindBy(xpath = "//input[@aria-label='Size (# of Units) Minimum Range']")
    public WebElement sumOfUnits;
    @FindBy(xpath = "(//input[@name='operatingstatementsflag'])[2]")
    public WebElement operatingStatementAvailable;
    @FindBy(xpath = "//input[@id='msaname']")
    public WebElement msa;
    @FindBy(xpath = "//button[@name='View More']/i")
    public WebElement viewMore;
    @FindBy(xpath = "(//input[@name='mrapprval'])[1]")
    public WebElement appraisalValue;
    @FindBy(xpath = "//input[@aria-label='Transaction Date Minimum Range']")
    public WebElement transactionDate;
    @FindBy(xpath = "//button[text()='Uncheck All']")
    public WebElement uncheckAll;
    @FindBy(xpath = "(//input[@name='specservstatus'])[2]")
    public WebElement specialServiced;
    @FindBy(xpath = "//input[@name='curltv']")
    public WebElement ltv;

  
    
}
