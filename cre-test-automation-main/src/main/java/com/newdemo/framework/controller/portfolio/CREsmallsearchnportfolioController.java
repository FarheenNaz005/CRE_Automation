package com.newdemo.framework.controller.portfolio;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.CREsmallsearchnportfolioPage;
import com.newdemo.framework.pages.LoanHomePage;
import com.newdemo.framework.pages.loan.LoansearchPropertyPage;

public class CREsmallsearchnportfolioController extends ReuseableFunctions {
    LoanHomePage loanHomePage = null;
    public LoadData data = null;
    LoansearchPropertyPage loanDetails = null;
    CREsmallsearchnportfolioPage searchnportfolio = null;

    String propertyname;

    public CREsmallsearchnportfolioController(WebDriver driver) throws Exception {
        super(driver);
        searchnportfolio = PageFactory.initElements(driver, CREsmallsearchnportfolioPage.class);
    }


    public void clickAdvanced() throws Exception {
        waitForElement(searchnportfolio.advancedbutton);
        clickObject(searchnportfolio.advancedbutton, "Advanced search ");

    }

    public void selectState() throws Exception {
        clickObject(searchnportfolio.openStatedropdown, "Open State DropDown Montana ");
        waitForElement(searchnportfolio.montanacheckbox);
        clickObject(searchnportfolio.montanacheckbox, "Select Montana ");

    }

    public void clickList() throws Exception {

        clickObject(searchnportfolio.listproperties, "List view button");

    }


    public void clickModalSearch() throws Exception {
        clickObject(searchnportfolio.searchbuttonModal, "Search button");

    }

    public void clickaddPortfolio() throws Exception {
        clickObject(searchnportfolio.addtoportoflio, "addToPortfolio button");
        clickObject(searchnportfolio.selectfirstportfolio, "first portfolio from the List");
        clickObject(searchnportfolio.addbutton, "Add button on the modal");

    }

    public void clickviewPortfolio() throws Exception {
        waitForElement(searchnportfolio.viewPortfolio);
        clickObject(searchnportfolio.viewPortfolio, "view Portfolio button");

    }

    public void clickviewtestanish() throws Exception {
        waitForElement(searchnportfolio.testanish);
        clickObject(searchnportfolio.testanish, "view testanish Portfolio ");

    }


    public void deleteRecordfromPortfolio() throws Exception {
        Thread.sleep(5000);
        try {
            if (searchnportfolio.checkallsize.size() == 1) {
                clickObject(searchnportfolio.checkall, "checkall record in Portfolio button");
                clickObject(searchnportfolio.deleterecords, "Delete record in Portfolio button");
                clickObject(searchnportfolio.confirmDeleterecords, "Confirm Delete record in Portfolio button");
                waitForElement(searchnportfolio.empyprotfoliMessage);
            }

        } catch (Exception objException) {

        }
    }


    public void verifyMapViewnData() throws Exception {
        waitForElement(searchnportfolio.mapLoad);
        Thread.sleep(5000);
        elementPresentorEnabled(searchnportfolio.mapViewData, "Present", "Map Data present");
        String data = getText(searchnportfolio.mapViewData, " Property Data in Maps ", "");
        if (data.isEmpty()) {
            System.out.println("No data found");
            assertTrue(StringUtils.isNotBlank(data));
        }
    }

    public void verifysamerecords() throws Exception {

        waitForElement(searchnportfolio.checkall);
        WebElement propertrecord = driver.findElement(By.xpath(".//a[contains(text(),'" + propertyname + "')]"));

        elementPresentorEnabled(propertrecord, "Present", "Same Data present");


    }


    public void selectCounty() throws Exception {
        waitForElement(searchnportfolio.county);
        clickObject(searchnportfolio.county, "County clicked");
        clearNTypeValue(searchnportfolio.county, "Nassau", "Nassau");
        Thread.sleep(5000);
        // waitForElement(searchnportfolio.NassauCounty);
        //tags-input[@id='countyState']//ul[@class='suggestion-list']
        System.out.println("Auto Suggest List ::" + searchnportfolio.nassauCounty.size());

        for (int i = 1; i < searchnportfolio.nassauCounty.size(); i++) {
            System.out.println(searchnportfolio.nassauCounty.get(i).getText());

            if (searchnportfolio.nassauCounty.get(i).getText().equals("Nassau, NY")) {
                searchnportfolio.nassauCounty.get(i).click();
                break;
            }

        }
    }

    public void verifyandaddTTpercentage(String ttpercentage) throws Exception {
        Thread.sleep(3000);
        clickObject(searchnportfolio.ttpercentage, "ttpercentage clicked");
        clearNTypeValue(searchnportfolio.ttpercentage, ttpercentage, ttpercentage);
        

    }
    public void setvaluationdate() throws Exception {

        waitForElement(searchnportfolio.valuationmodal);
        System.out.println("valuation modal opned");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String data = df.format(new Date());
        String[] todaydate = data.split("/");
        String dd = todaydate[0];
        String mm = todaydate[1];
        String yyyy = todaydate[2];
       
       
        System.out.println(dd);
       
        System.out.println(mm);
       
        System.out.println(yyyy);
       
        clickObject(searchnportfolio.valuationdate, "valuationdate clicked");    
        
        searchnportfolio.valuationdate.sendKeys(yyyy);
        Thread.sleep(3000);
        searchnportfolio.valuationdate.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        searchnportfolio.valuationdate.sendKeys(Keys.ENTER);
        searchnportfolio.valuationdate.sendKeys(Keys.ENTER);
        searchnportfolio.valuationdate.sendKeys(mm);
        Thread.sleep(3000);
        searchnportfolio.valuationdate.sendKeys(dd);
        
    }
   
    
    public void clickGetValuationonmodal() throws Exception {
        elementPresentorEnabled(searchnportfolio.getValuationmoalbutton, "Enabled", "GetValuation Enabled");
        clickObject(searchnportfolio.getValuationmoalbutton, "GetValuation clicked on modal");
        Thread.sleep(3000);
    }

    
    

    public void clickGetValuation() throws Exception {
        elementPresentorEnabled(searchnportfolio.getValuation, "Enabled", "GetValuation Enabled");
        clickObject(searchnportfolio.getValuation, "GetValuation clicked");
        Thread.sleep(3000);
    }

    public void clickSaveEdits() throws Exception {
        elementPresentorEnabled(searchnportfolio.saveEdits, "Enabled", "Save Edits Enabled");
        clickObject(searchnportfolio.saveEdits, "Save Edits clicked");
        Thread.sleep(10000);

    }


    public void clickModel() throws Exception {
        waitForElement(searchnportfolio.model);
        elementPresentorEnabled(searchnportfolio.model, "Enabled", "Model Enabled");
        clickObject(searchnportfolio.model, "Model clicked");
        Thread.sleep(3000);

    }

    public void verifyDownloadValuations() throws Exception {
        elementPresentorEnabled(searchnportfolio.downloadValuations, "", "Download Valuations Enabled");
        //clickObject(searchnportfolio.DownloadValuations, "Download Valuations clicked");

    }

    public void verifySaveValuation() throws Exception {
        elementPresentorEnabled(searchnportfolio.saveValuation, "", "Save Valuation Enabled");
        //clickObject(searchnportfolio.DownloadValuations, "Download Valuations clicked");

    }

    public void verifySavedValuationList() throws Exception {
        waitForElement(searchnportfolio.valuationDropDown);
        clickObject(searchnportfolio.valuationDropDown, "valuation Drop Down clicked");
        for (int i = 1; i < searchnportfolio.valuationList.size(); i++) {

            String values = searchnportfolio.valuationList.get(i).getText();
            if (values.equalsIgnoreCase("testanish_2") || values.equalsIgnoreCase("testanish_5")) {

                System.out.println(values + " : Found in the saved Valuation list");
            }
        }
    }


    public void clicksavemodelValuation() throws Exception {
        //ElementPresentorEnabled(searchnportfolio.SaveValuation,"",  "Save Valuation Enabled");
        clickObject(searchnportfolio.saveValuation, "Save Valuation  clicked");
        //if (!isAlertPresent() == false) {
        //Alert alert = driver.switchTo().alert();

        // }
    }

    public void clicknVerifyDownloadValuation() throws Exception {
        waitForElement(searchnportfolio.downloadValuations);
        clickObject(searchnportfolio.downloadValuations, "Download Valuations Clicked");
        Thread.sleep(1000);
        waitUntilDonwloadCompleted();
    }


    public void addnSaveValuationname(String ttpercentage) throws Exception {
        waitForElement(searchnportfolio.valuationName);
        clickObject(searchnportfolio.valuationName, "valuation Name clicked");

        clearNTypeValue(searchnportfolio.valuationName, "Testvaluation_" + ttpercentage, "Testvaluation_" + ttpercentage);
        driver.switchTo().activeElement();
        clickObject(searchnportfolio.acceptAlert, "Save Valuation Alert clicked");
        Thread.sleep(2000);
        clickObject(searchnportfolio.acceptAlert, "View portfolio clicked");
     
    }

    public void clickapplyModel() throws Exception {
        waitForElement(searchnportfolio.applyModel);
        clickObjectJavascript(searchnportfolio.applyModel, "Apply Model clicked");
        Thread.sleep(3000);
    }

    public void verifyModelpopupvalues() throws Exception {

        System.out.println("model values ::" + searchnportfolio.modelvalues.size());

        for (int i = 1; i < searchnportfolio.modelvalues.size(); i++) {

            String values = searchnportfolio.modelvalues.get(i).getText();

            if (!values.equals(null)) {
                System.out.println(values);
            } else {
                break;
            }
        }
    }

    public void verifyModelcolumnnames() throws Exception {
        waitForElement(searchnportfolio.classSelectorDropdown);
        System.out.println("model values ::" + searchnportfolio.modelcolumnames.size());

        for (int i = 1; i < searchnportfolio.modelcolumnames.size(); i++) {

            String values = searchnportfolio.modelcolumnames.get(i).getText();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("return arguments[0].scrollIntoView({inline:'center'});", searchnportfolio.modelcolumnames.get(i));
            System.out.println(values);

            if (values.equals("TT Appraisal Ratio")) {
                System.out.println(values);
            }

        }
       
    }


    public void addclass() throws Exception {
        clickObject(searchnportfolio.columnssidebar, "columnssidebar clicked");
        boolean checkboxxhecked = searchnportfolio.columnssidebarcheckbox.isSelected();
        if (!checkboxxhecked) {

            searchnportfolio.columnssidebarcheckbox.click();
            clickObject(searchnportfolio.columnssidebarcheckbox, "extraodinary fee Box checked");
        }


        System.out.println("columlist List::" + searchnportfolio.columlist.size());

        for (int i = 0; i < searchnportfolio.columlist.size(); i++) {
            System.out.println(searchnportfolio.columlist.get(i).getText());

            if (searchnportfolio.columlist.get(i).getText().equals("Class")) {
                searchnportfolio.columlist.get(i).click();
                break;
            }

        }
        clickObject(searchnportfolio.columnssidebar, "columnssidebar clicked");
        System.out.println("Class List::" + searchnportfolio.classlist.size());

        for (int i = 0; i < searchnportfolio.classlist.size(); i++) {
            System.out.println(searchnportfolio.classlist.get(i).getText());

            if (searchnportfolio.classlist.get(i).getText().equals("-")) {
                //searchnportfolio.classlist.get(i).sendKeys(Keys.ENTER);
                searchnportfolio.classlist.get(i).sendKeys("a");
                //searchnportfolio.classlist.get(i).sendKeys(Keys.ENTER);
//searchnportfolio.classlist.get(i).sendKeys("b");
//searchnportfolio.classlist.get(i).sendKeys(Keys.ENTER);
//searchnportfolio.classlist.get(i).sendKeys("c");
//searchnportfolio.classlist.get(i).sendKeys(Keys.ENTER);
//searchnportfolio.classlist.get(i).sendKeys(Keys.ENTER);
//searchnportfolio.classlist.get(i).sendKeys("a");
//searchnportfolio.classlist.get(i).sendKeys(Keys.ENTER);
//searchnportfolio.classlist.get(i).sendKeys("b");
            }

        }

        for (int i = 0; i < searchnportfolio.classlist.size(); i++) {
            System.out.println(searchnportfolio.classlist.get(i).getText());

            if (searchnportfolio.classlist.get(i).getText().equals("-")) {
                //searchnportfolio.classlist.get(i).sendKeys(Keys.ENTER);
                searchnportfolio.classlist.get(i).sendKeys("b");
                //searchnportfolio.classlist.get(i).sendKeys(Keys.ENTER);

            }

        }

        for (int i = 0; i < searchnportfolio.classlist.size(); i++) {
            System.out.println(searchnportfolio.classlist.get(i).getText());

            if (searchnportfolio.classlist.get(i).getText().equals("-")) {
                searchnportfolio.classlist.get(i).sendKeys("c");


            }

        }
        //searchnportfolio.classlist.get(1).click();
        clickObject(searchnportfolio.columnssidebar, "columnssidebar clicked");
        clickObject(searchnportfolio.columnssidebarunchecked, "columnssidebar clicked");
        clickObject(searchnportfolio.columnssidebar, "columnssidebar clicked");
        //verifyandclicklistofCheckboxes();


    }

    public void selectbclass() throws Exception {
        

            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
           
            
            js.executeScript("return arguments[0].scrollIntoView({inline:'start'});", searchnportfolio.classSelectorDropdown);
            clickObject(searchnportfolio.classSelectorDropdown, "class select dropdown");

            selectFromDropDownVisibleText(searchnportfolio.classSelectorDropdown, "B class", "B");
            Thread.sleep(1000);

    }
    
    
    public void refreshpage() throws Exception {
        
        Thread.sleep(3000);
        driver.navigate().refresh();
        Thread.sleep(3000);

}

    
    
    public void selectAclass() throws Exception {
        

        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return arguments[0].scrollIntoView({inline:'start'});", searchnportfolio.classSelectorDropdown);
        clickObject(searchnportfolio.classSelectorDropdown, "class select dropdown");

        selectFromDropDownVisibleText(searchnportfolio.classSelectorDropdown, "A class", "A");
        Thread.sleep(1000);

}

    public void verifyapprisalValues() throws Exception {

        System.out.println("model values ::" + searchnportfolio.valuationpercentage.size());

        for (int i = 1; i < searchnportfolio.valuationpercentage.size(); i++) {

            String values = searchnportfolio.valuationpercentage.get(i).getText();

            if (!values.equals(null) || !values.equals("0.00%")) {
                System.out.println(values);

            } else {
                break;
            }
        }
    }


}
