package com.newdemo.framework.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.AdvanceSearchModal;
import com.newdemo.framework.pages.AdvanceSearchModal.TabName;

public class AdvanceSearchModalController extends ReuseableFunctions {
    AdvanceSearchModal advanceSearchModal = null;
    LoadData data = null;
    SoftAssert softAssert = null;

    public AdvanceSearchModalController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        // from
        advanceSearchModal = PageFactory.initElements(driver, AdvanceSearchModal.class);

    }

    /**
     * Advance Search start
     **/

    public void selectTab(TabName tab) throws Exception {
        clickObject(advanceSearchModal.tab(tab), "Select " + tab);
    }

    public void expandTab() throws Exception {
        clickObject(advanceSearchModal.moreLessButton, "more/less criteria button");
    }

    public void selectFromSuggestion(AdvanceSearchModal.FieldWithSuggestion field, String query) throws Exception {
        WebElement inputEle = advanceSearchModal.inputForfiledWithSuggestion(field);
        waitForElement(inputEle);
        clearNTypeValue(inputEle, query, query + " searchbox");
        WebElement suggestionItem = advanceSearchModal.suggestionItemByName(field, query);
        waitForElement(suggestionItem);
        clickObject(suggestionItem, "Select " + query);
    }

    public void selectState(String state) throws Exception {
        waitForElement(advanceSearchModal.openStatedropdown);
        Thread.sleep(1000);
        clickObject(advanceSearchModal.openStatedropdown, "Open State DropDown ");
        WebElement stateCheckbox = driver.findElement(
                By.xpath(".//taui-multi-select[@id='state']//ul/li/a[text()[contains(.,'" + state + "')]]"));
        waitForElement(stateCheckbox);
        clickObject(stateCheckbox, "Select " + state);
        clickObject(advanceSearchModal.openStatedropdown, "Close State DropDown ");
    }

    public void selectSource(String source) throws Exception {
        waitForElement(advanceSearchModal.dataSourcedropdownButton);
        clickObject(advanceSearchModal.dataSourcedropdownButton, "data source dropdown");
        clickObject(advanceSearchModal.dataSourcedropdownUncheckALLButton, "data source dropdown | uncheck all button");
        clickObject(advanceSearchModal.sourceCheckByName(source), "data source check box for: " + source);
    }

    public void selecityesoperatingstatment() throws Exception {
        selectTab(TabName.property);
        waitForElement(advanceSearchModal.yesOperatingStatment);
        clickObject(advanceSearchModal.yesOperatingStatment, "yes selected from operating ststment");
    }

    public void clickModalSearch() throws Exception {
        clickObject(advanceSearchModal.searchbuttonModal, "Search button");

    }

    /** Advance Search End **/
}
