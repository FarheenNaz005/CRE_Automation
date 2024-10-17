package com.newdemo.framework.controller.stats;

import java.text.ParseException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.base.Utilites;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.stats.StatsNewsPage;

public class StatsNewsPageController extends ReuseableFunctions {
    StatsNewsPage statsNewsPage = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;

    public StatsNewsPageController(WebDriver driver) throws Exception {
        super(driver);
        statsNewsPage = PageFactory.initElements(driver, StatsNewsPage.class);
    }

    public void verifyArtical() {
        softAssert.assertTrue(statsNewsPage.allartical.isDisplayed(), "pdf viewer is visible on page.");

    }

    public void filterarticalbydaterange(String startdate, String enddate) throws Exception {
        clearNTypeValue(statsNewsPage.startdate, startdate, "start date input");
        clearNTypeValue(statsNewsPage.enddate, enddate, "end date input");
        clickObject(statsNewsPage.displayButton, "display button");

    }

    public void clickOndateCheckbox() throws Exception {

        clickObject(statsNewsPage.datecheckbox, "date check box button");
    }

    public void verifydateinrange(String string, String string2) throws ParseException {
        for (int i = 0; i < statsNewsPage.date.size(); i++) {
            if (!statsNewsPage.date.get(i).getText().equalsIgnoreCase("")) {
                boolean status = Utilites.checkdateinrange(string, string2, statsNewsPage.date.get(i).getText());

                softAssert.assertEquals(status, true);
                System.out.println(statsNewsPage.date.get(i).getText());
            }

        }

    }

    public void clickOndealCheckBox() throws Exception {
        clickObject(statsNewsPage.dealcheckbox, "deal check box button");

    }

    public void filterbydealname(String value) throws Exception {
        clearNTypeValue(statsNewsPage.dealnameinput, value, "start date input");
        clickObject(statsNewsPage.displayButton, "display button");

    }

    public void waitForNewsLoading() {
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(statsNewsPage.loadingNews));
        } catch (StaleElementReferenceException e) {
            System.err.println(e.getMessage());
        }
    }

    public void clickOnFirstNewsLink() throws Exception {
        clickObject(statsNewsPage.firstNewsArticle, "First News Article Link");

    }


    public void verifyNewsModel() {
        softAssert.assertTrue(statsNewsPage.newsPopup.isDisplayed(), "News article pop-up model not present");
        if (statsNewsPage.newsPopup.isDisplayed()) {
            System.out.println("News pop-up model verified");
        }
    }
}
