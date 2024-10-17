package com.newdemo.framework.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.PropertyListingPageV2;
import com.newdemo.framework.pages.StratsNewPage;

public class NewStratsPageController extends ReuseableFunctions {
    StratsNewPage stratsNewPage = null;
    public SoftAssert softAssert;
    public LoadData data;
    PropertyListingPageV2 loanlistingPage = null;

    public NewStratsPageController(WebDriver driver) throws Exception {
        super(driver);
        stratsNewPage = PageFactory.initElements(driver, StratsNewPage.class);
        loanlistingPage = PageFactory.initElements(driver, PropertyListingPageV2.class);
    }

    public long clickOnNewStratsLink() throws Exception {

        //capture time before page load
        long s = System.currentTimeMillis();
        clickObject(stratsNewPage.stratslink, "strats link");
        waitForElement(stratsNewPage.stratsheader);
        //capture time after page load
        long e = System.currentTimeMillis();
        //compute time
        long r = e - s;
        System.out.println("New Page load time in milliseconds: " + r);
        return r;
    }

    public long clickOnoldStratsLink() throws Exception {

        //capture time before page load
        long s = System.currentTimeMillis();
        clickObject(stratsNewPage.oldstratslink, "old strats link");
        waitForElement(stratsNewPage.oldstratsheader);
        //capture time after page load
        long e = System.currentTimeMillis();
        //compute time
        long r = e - s;
        System.out.println("old Page load time in milliseconds: " + r);
        return r;
    }

    public void verifyTableAndChartCollection() throws Exception {
        List<WebElement> list = driver.findElements(By.xpath("//div[@id='accordion-playground-Property-accordion']//button[@class='trepp__accordion__trigger']"));
        int count = list.size();
        for (int i = 0; i < (count - 1); i++) {
            WebElement currentCollection = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='accordion-playground-Property-accordion']//button[@id='accordion-playground-Property-button-" + i + "']")));

            clickObject(currentCollection, "clicked on " + i + "table");
            WebElement currentTable = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id='accordion-playground-Property-region-" + i + "']//table")));
            Assert.assertEquals(true, currentTable.isDisplayed());
            WebElement currentChart = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id='accordion-playground-Property-region-" + i + "']/div/div[2]/div[2]/div")));
            Assert.assertEquals(true, currentChart.isDisplayed());


        }


    }
    
    // 4526
    public void clickOnTreppWireTab() throws Exception {
        waitForElement(stratsNewPage.treppWireTab);
        clickObject(stratsNewPage.treppWireTab, "Trepp wire");
    }
    
    public void clickOnCompanyExposureTab() throws Exception {
        waitForElement(stratsNewPage.companyExposureTab);
        clickObject(stratsNewPage.companyExposureTab, "Company Exposure");
    }

    public void clickOnInTheSpotLightTab() throws Exception {
        waitForElement(stratsNewPage.inTheSpotLight);
        clickObject(stratsNewPage.inTheSpotLight, "inTheSpotLight");
    }

    public void clickOnFirstDeal() throws Exception {
        waitForElement(stratsNewPage.firstDealName);
        clickObject(stratsNewPage.firstDealName, "firstDealName");
        switchToNewWindow();
    }

    public void clickOnFirstProperty() throws Exception {
        waitForElement(stratsNewPage.firstPropertyName);
        clickObject(stratsNewPage.firstPropertyName, "firstPropertyName");
        switchToNewWindow();
    }

    public void verifyNewDetailsPageOpen(String menuTitle) throws Exception {
        WebElement menutitle = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + menuTitle + "']")));
        waitForElement(stratsNewPage.loanTab);
        softAssert.assertTrue(stratsNewPage.loanTab.isDisplayed(), "Details tab(s) not displayed");
        String data = stratsNewPage.loanTab.getText();
        System.out.println("Details tab(s) : " + data);
    }
    
    public void selectHotel(String hotelName) throws Exception {
        WebElement hotel = driver.findElement(By.xpath("(//a[text()='" + hotelName + "'])[1]"));
        scrollIntoView(hotel, "Hotel");
        clickObject(hotel, "Hotel");
    }
    
    public void clickTenant() throws Exception {
        waitForElement(stratsNewPage.tenants);
        clickObject(stratsNewPage.tenants, "Tenants");      
    }
    
    public void clickCompanyExposurePin() throws Exception {
        waitForElement(stratsNewPage.pins);
        clickObject(stratsNewPage.pins, "Company Exposure pin");
    }
    
    public void verifyPinsAndClickThru() throws Exception {
        waitForElement(loanlistingPage.mapPins1);
        moveToElement(loanlistingPage.mapPins1, "map pin");
        clickObject(loanlistingPage.mapPins1, "Map pin");
        waitForElement(stratsNewPage.pinPopHeader);
        softAssert.assertTrue(stratsNewPage.pinPopHeader.isDisplayed(), "property name is not present");
        String propertyName = stratsNewPage.pinPopHeader.getText();
        System.out.println("Map pin pop-up property name: " + propertyName);
        // click property name on popup
        clickObject(stratsNewPage.pinPopHeader, "Property name on map pin pop-up");
    }
    
    public void selectDeal() throws Exception {
        waitForElement(stratsNewPage.selectDeal);
        clickObject(stratsNewPage.selectDeal, "Deal");
        Thread.sleep(3000);
    }

}
