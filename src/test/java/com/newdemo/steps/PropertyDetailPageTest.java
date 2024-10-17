package com.newdemo.steps;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.data.LoadData;
import com.newdemo.framework.provider.StaticDataProvider;

@Test
public class PropertyDetailPageTest extends BaseSetupClass {

    String[] newPropDetailPages = {"fannieMaeDetail", "fhaGinnieMaeDetail"};

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;

    }

    @BeforeMethod
    public void beforeTestMethod(Method method) {
        System.err.println("TestMethod: " + method.getName());
    }

    @Test(groups = {"regression"})
    public void verify1PropertyDetailPage() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(new LoadData(this.strDTParametersNValues).strDTSearch);
        trepp().propertyPage().clickdetails();
        trepp().propertyPage().detailPageOld().verifyloanSummary();
        trepp().propertyPage().detailPageOld().verifyTable("General Loan Information");

    }

    @Test(dataProvider = "source-data-provider", dataProviderClass = StaticDataProvider.class, groups = {"regression"})
    public void verify2DownloadExcel(String source) throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("New York");
        trepp().crehomepage().selectSource(source);
        trepp().crehomepage().clickModalSearch();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();

        String propName = trepp().newListingPage().clickFirstProperty();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().clickdetails();
        trepp().crehomepage().waitForLoading();
        Thread.sleep(2000);
        //Trepp().propertyPage().summaryPage().clickOverviewTab();
        //Trepp().propertyPage().summaryPage().clickPropertyNameLink();

        boolean newPage = Arrays.stream(newPropDetailPages)
                .anyMatch(BaseSetupClass.getDriver().getCurrentUrl()::contains);

        if (newPage) {
            trepp().propertyPage().detailPageNew().verifyPropertyName(propName);
            trepp().propertyPage().detailPageNew().verifyExcelDownload();
            trepp().propertyPage().detailPageNew().verifyPDFDownload();
        } else {
            trepp().propertyPage().detailPageOld().verifyPropertyName(propName);
            trepp().propertyPage().detailPageOld().verifyExcelDownload();
            trepp().propertyPage().detailPageOld().verifyPDFDownload();

        }

        if (!BaseSetupClass.executionEnv.equalsIgnoreCase("cloud")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();
    }

    @Test(dataProvider = "source-data-provider", dataProviderClass = StaticDataProvider.class, groups = {"regression"})
    public void verify3TimeTakenByPropertyDetailPage(String source) throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("New York");
        trepp().crehomepage().selectSource(source);
        trepp().crehomepage().clickModalSearch();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();

        String propName = trepp().newListingPage().clickFirstProperty();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().clickdetails();
        //Trepp().propertyPage().summaryPage().clickOverviewTab();
        //Trepp().propertyPage().summaryPage().clickPropertyNameLink();

        boolean newPage = Arrays.stream(newPropDetailPages)
                .anyMatch(BaseSetupClass.getDriver().getCurrentUrl()::contains);
        System.out.println("source: " + source + " | Property Name: " + propName);
        if (newPage) {
            trepp().propertyPage().detailPageNew().verifyPropertyName(propName);
        } else {
            trepp().propertyPage().detailPageOld().verifyPropertyName(propName);

        }
    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("New York");
        trepp().crehomepage().selectSource("CMBS");
        trepp().crehomepage().clickModalSearch();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();

        String propName = trepp().newListingPage().clickFirstProperty();
        trepp().propertyPage().detailPageOld().runAssessibilityfunction();
    }

    @Test(groups = {"regression"})
    public void verifyDealDocumentPropertyDetailPage() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(new LoadData(this.strDTParametersNValues).strDTSearch);
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Deal Documents");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Original Documents");
        trepp().propertyPage().detailPageOld().verifydealTable("Periodic Reports & Notices");

    }

    /*@Test(groups = {"regression"})
    public void verifyTitleDataTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("6222 Fountain");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Title Data");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Property Title Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Site Information");

    }*/

    @Test(groups = {"regression"})
    public void verifyLoanTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("6222 Fountain");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Loan");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("General Loan Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Underwriting Measures");
        trepp().propertyPage().detailPageOld().verifydealTable("General Pool Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Performance Status");
        trepp().propertyPage().detailPageOld().verifydealTable("Collateral Summary");
        trepp().propertyPage().detailPageOld().verifydealTable("General Loan Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Owner Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Programs");
        trepp().propertyPage().detailPageOld().verifydealTable("Modified Loan Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Performance Status");
        trepp().propertyPage().detailPageOld().verifydealTable("Entities");
        trepp().propertyPage().detailPageOld().verifydealTable("Prepayment & Liquidation");
    }

    @Test(groups = {"regression"})
    public void verifyLoanTabForNonAgency() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("One Vanderbilt Avenue");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Loan");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Delinquency");
        trepp().propertyPage().detailPageOld().verifydealTable("Delinquency History");
        trepp().propertyPage().detailPageOld().verifydealTable("Total Exposure");
        trepp().propertyPage().detailPageOld().verifydealTable("General Loan Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Loan Prepayment Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Loan Extension Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Demand/Repurchase/Replacement");
        trepp().propertyPage().detailPageOld().verifydealTable("Underwriting Measures");
    }

    @Test(groups = {"regression"})
    public void verifyPropertyTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("6222 Fountain");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        Thread.sleep(3000);
        trepp().propertyPage().detailPageNew().clickOnTab("Property");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Geographic Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Property Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Property Ownership Information from Cherre©");
        trepp().propertyPage().detailPageOld().verifydealTable("Environmental Risk Score");

    }

    @Test(groups = {"regression"})
    public void verifyLoanPiecesTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("4500 Dublin");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Loan Pieces");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Known Loan Pieces");


    }

    @Test(groups = {"regression"})
    public void verifyResearchTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("611 Fifth Avenue");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Research");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Commentary / In the Spotlight");


    }

    @Test(groups = {"regression"})
    public void verifyFinancialsTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("611 Fifth Avenue");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Financial");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Financials");
        trepp().propertyPage().detailPageOld().verifydealTable("Appraisal/LTV");
        trepp().softAssert.assertAll();


    }

    @Test(groups = {"regression"})
    public void verifyTaxAndSaleDataTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("Mills Pond Park");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Tax & Sale Data");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Property Title Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Site Information");
        trepp().propertyPage().detailPageOld().verifydealTable("Property Characteristics");
        trepp().propertyPage().detailPageOld().verifydealTable("County Tax & Assessment");

    }

    @Test(dataProvider = "source-data-provider", dataProviderClass = StaticDataProvider.class, groups = {"regression"})
    public void verifyTimeTakenByPropertyNewDetailPage(String source) throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("New York");
        trepp().crehomepage().selectSource(source);
        trepp().crehomepage().clickModalSearch();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();

        String propName = trepp().newListingPage().clickFirstProperty();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        boolean newPage = Arrays.stream(newPropDetailPages)
                .anyMatch(BaseSetupClass.getDriver().getCurrentUrl()::contains);
        System.out.println("source: " + source + " | Property Name: " + propName);
        if (newPage) {
            trepp().propertyPage().detailPageNew().verifyPropertyName(propName);
        } else {
            trepp().propertyPage().detailPageOld().verifyPropertyName(propName);


        }
    }

    @Test(groups = {"regression"})
    public void verifyfinancalTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectSource("Fannie Mae");
        trepp().crehomepage().clickModalSearch();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();

        String propName = trepp().newListingPage().clickFirstProperty();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        //Thread.sleep(5000);
        trepp().propertyPage().detailPageNew().clickOnTab("Financial");
        //trepp().propertyPage().detailPageNew().clickOnfinancial();
        boolean newPage = Arrays.stream(newPropDetailPages)
                .anyMatch(BaseSetupClass.getDriver().getCurrentUrl()::contains);
        System.out.println("source: Fannie Mae | Property Name: " + propName);
        if (newPage) {
            trepp().propertyPage().detailPageNew().verifyPropertyName(propName);
        } else {
            trepp().propertyPage().detailPageOld().verifyPropertyName(propName);


        }
    }

    @Test(groups = {"regression"})
    public void verifyAdditionalDebtTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("Spring Creek Towers");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Additional Debt");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Additional Debt Information");
    }

    @Test(groups = {"regression"})
    public void verifyFannieMaeLoanDetailPageData() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("Stuyvesant Town");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().verfiyLoanInfoTableAndUniqueRow("GeneralLoanInformation","General Loan Information" ,"P&I Amount" , "Cross Collateralized (Y/N)");
    }
    @Test(groups = {"regression"})
    public void verifyGinnieMaeLoanDetailPageData() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("NEW YORK PRESBYTERIAN HOSPITAL");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().verfiyLoanInfoTableAndUniqueRow("GeneralPoolInformation","General Pool Information" , "CUSIP");
        trepp().propertyPage().detailPageNew().verfiyLoanInfoTableAndUniqueRow("CollateralSummaryGinnie" ,"Collateral Summary", "Number of Loans");
    }
    @Test(groups = {"regression"})
    public void verifyFHALoanDetailPageData() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("Baychester Murphy Houses");
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().verfiyLoanInfoTableAndUniqueRow("GeneralLoanInformation","General Loan Information" , "FHA Data Source","FHA Loan Status","FHA Number","FHA Program Category");
    }
}
