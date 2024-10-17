package com.newdemo.framework.controller.loan;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.pages.loan.LoansearchPropertyPage;

public class CRELoanPageController extends ReuseableFunctions {
    LoansearchPropertyPage loanDetails = null;
    PropertySummaryPageController summaryPageController = null;
    PropertyDetailPageOldController oldDetailPageController = null;
    PropertyDetailPageNewController newDetailPageController = null;
    SinglePropertyValuationController spvController = null;
    public LoadData data = null;
    public SoftAssert softAssert = null;
    File file1 = new File(System.getProperty("user.dir") + "/Config/comparescript.py");
    File newfile = new File(System.getProperty("user.dir") + "/Downloads/difference.csv");
    File oldfile = new File(System.getProperty("user.dir") + "/Downloads/Test1.csv");

    public CRELoanPageController(WebDriver driver) throws Exception {
        super(driver);
        loanDetails = PageFactory.initElements(driver, LoansearchPropertyPage.class);
    }

    public PropertySummaryPageController summaryPage() throws Exception {
        if (summaryPageController == null) {
            summaryPageController = new PropertySummaryPageController(driver);
            summaryPageController.softAssert = this.softAssert;
            summaryPageController.data = this.data;
        }

        return summaryPageController;
    }

    public PropertyDetailPageOldController detailPageOld() throws Exception {
        if (oldDetailPageController == null) {
            oldDetailPageController = new PropertyDetailPageOldController(driver);
            oldDetailPageController.softAssert = this.softAssert;
            oldDetailPageController.data = this.data;
        }

        return oldDetailPageController;
    }

    public PropertyDetailPageNewController detailPageNew() throws Exception {
        if (newDetailPageController == null) {
            newDetailPageController = new PropertyDetailPageNewController(driver);
            newDetailPageController.softAssert = this.softAssert;
            newDetailPageController.data = this.data;
        }

        return newDetailPageController;
    }

    public SinglePropertyValuationController singlePropertyValutionPage() throws Exception {
        if (spvController == null) {
            spvController = new SinglePropertyValuationController(driver);
            spvController.softAssert = this.softAssert;
            spvController.data = this.data;
        }

        return spvController;
    }

    public void clickdetails() throws Exception {
        waitForElement(loanDetails.details);
        Thread.sleep(5000);
        clickObject(loanDetails.details, "Details");

    }

    public void clickMarket() throws Exception {
        waitForElement(loanDetails.market);
        clickObject(loanDetails.market, "Market");

    }

    public void clickComps() throws Exception {
        waitForElement(loanDetails.comps);
        clickObject(loanDetails.comps, "comps");

    }

    public void clickValuation() throws Exception {
        waitForElement(loanDetails.valuation);
        clickObject(loanDetails.valuation, "valuation");

    }

    public void clickBenchmark() throws Exception {
        waitForElement(loanDetails.benchmarks);
        clickObject(loanDetails.benchmarks, "Benchmarks");
    }

    public void verifyLoanDatailInDetailPage() throws Exception {
        elementPresentorEnabled(loanDetails.trdatatable, "Present", "tables on LoanDetails");
        List<String> data = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
                        .xpath(".//div[@id='c_loanProp_0']//table[@class='column-table']//tbody//tr//td[@class='first-col']//div[@class='DataFmt']//table//tbody//tr//td")))
                .stream().map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("Loan Data:" + " " + data);
        System.out.println(data.get(5));
        if (data.isEmpty()) {
            System.out.println("Data on the details page not loaded");
        }
    }

    public void verifyheaderDataInDetailPage() throws Exception {

        List<String> data = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(".//table[@class='summary-table loan-header']//tbody//tr[2]//td")))
                .stream().map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("Header Data:" + " " + data);
        if (data.isEmpty()) {
            System.out.println("Header Data not loaded");

        }

    }

    /* comp tab */
    public void verifycompsDatailPage() throws Exception {
        Thread.sleep(3000);
        waitForElement(loanDetails.balance);
        List<String> data = new WebDriverWait(driver, 20)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.xpath(".//table[@class='table']//tr//td[2]")))
                .stream().map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("comps Data:" + " " + data);
        if (data.isEmpty()) {
            System.out.println("comps Data not loaded");
        }

    }

    public void verifyCompDownloadAll(String filename) throws Exception {
        //ElementPresentorEnabled(LoanDetails.compDownloadButton, "present", "Download All button on comp");
        clickObjectJavascript(loanDetails.compDownloadButton, "download all button");
        Thread.sleep(1000);
        softAssert.assertTrue(isFileExist(BaseSetupClass.downloadFilepath, filename),
                "file " + filename + " doesn't exist in download folder");
    }


    public void verifyMarketdispositionData() throws Exception {

        List<String> data = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
                        .xpath(".//market-snapshot-disposition[@class='ng-isolate-scope']//table[@class='table table-condensed table-hover table-striped']//tr//td")))
                .stream().map(element -> element.getText()).collect(Collectors.toList());
        System.out.println("Market Data:" + " " + data);
        //System.out.println(data.get(1));
        if (data.isEmpty()) {
            System.out.println("comps Data not loaded");
        }

    }

    public void verifyBenchMarkData() throws Exception {

//List<String> data = new WebDriverWait(driver, 20)
//.until(ExpectedConditions
//.visibilityOfAllElementsLocatedBy(By.xpath(".//table[@id='market-fin-table']//tr/td")))
//.stream().map(element -> element.getText()).collect(Collectors.toList());


        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//table[@id='market-fin-table']//tr/td")));

//for (WebElement data : dataEle) {
//System.out.println("BenchMark Data:" + " " + data.getText());
//}

        //System.out.println(data.get(1));
        try {
            if (loanDetails.bechmarkDatList.isEmpty()) {
                System.out.println("BenchMark Data not loaded");
            }
        } catch (Exception e) {
        }

    }

}
