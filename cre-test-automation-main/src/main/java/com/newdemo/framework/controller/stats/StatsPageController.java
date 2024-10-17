package com.newdemo.framework.controller.stats;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.stats.StatsPage;

public class StatsPageController extends ReuseableFunctions {
    StatsPage statsPage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public StatsPageController(WebDriver driver) throws Exception {
        super(driver); // driver instance of ReuseableFunctions class that all the page objects inherit
        statsPage = PageFactory.initElements(driver, StatsPage.class);
    }

    /**
     * validation methods
     **/
    public void verifyTrendsLink() throws Exception {
        elementPresentorEnabled(statsPage.navTrendsLink, "Present", "Stats | Trends link");
    }

    public void verifyTreppWireLink() throws Exception {
        elementPresentorEnabled(statsPage.navTreppWireLink, "Present", "Stats | TreppWire link");
    }

    public void verifyPerformanceLink() throws Exception {
        elementPresentorEnabled(statsPage.navPerformanceLink, "Present", "Stats | Performance link");
    }

    public void verifyNewsLink() throws Exception {
        elementPresentorEnabled(statsPage.navNewsLink, "Present", "Stats | News link");
    }

    public void verifyNewIssuanceLink() throws Exception {
        elementPresentorEnabled(statsPage.navNewIssuanceLink, "Present", "Stats | New Issuance link");
    }

    public void verifyCompanyExposureLink() throws Exception {
        elementPresentorEnabled(statsPage.navCompanyExposureLink, "Present", "Stats | Company Exposure link");
    }

    /**
     * Action methods
     **/
    public void clickTrendsLink() throws Exception {
        clickObject(statsPage.navTrendsLink, "Stats | Trends link");
    }

    public void clickTreppWireLink() throws Exception {
        clickObject(statsPage.navTreppWireLink, "Stats | TreppWire link");
    }

    public void clickPerformanceLink() throws Exception {
        clickObject(statsPage.navPerformanceLink, "Stats | Performance link");
    }

    public void clickNewsLink() throws Exception {
        clickObject(statsPage.navNewsLink, "Stats | News link");
    }

    public void clickNewIssuanceLink() throws Exception {
        clickObject(statsPage.navNewIssuanceLink, "Stats | New Issuance link");
    }

    public void clickCompanyExposureLink() throws Exception {
        clickObject(statsPage.navCompanyExposureLink, "Stats | Company Exposure link");
    }
}
