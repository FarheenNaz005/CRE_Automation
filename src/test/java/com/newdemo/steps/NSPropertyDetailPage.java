package com.newdemo.steps;

import com.newdemo.framework.provider.StaticDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

public class NSPropertyDetailPage extends BaseSetupClass {
    private String propertyName = "2463 Broadway";
    private String propertyName1 = "322 W 48th St";
    private String propertyName2 = "225 Liberty Street";

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        this.strDTParametersNValues = parameterNValue;
    }
    @Test(groups = {"regression"})
    public void verify1PropertyAndCommentsTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(propertyName);
        trepp().propertyDetailNewPage().verifyPropertyName(propertyName);
        trepp().propertyDetailNewPage().verifyTableNew("Property Information");
        trepp().propertyDetailNewPage().verifyTableNew("Geographic Information");
        trepp().propertyDetailNewPage().verifyTableNew("Property Ownership");
        trepp().propertyDetailNewPage().clickOnTab("My Comments");
        trepp().propertyDetailNewPage().verifyMyCommentHeading();
    }

    @Test(groups = {"regression"})
    public void verifyProformaTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(propertyName1);
        trepp().propertyDetailNewPage().verifyPropertyName(propertyName1);
        trepp().propertyDetailNewPage().clickOnTab("Pro Forma Financials");
        trepp().propertyDetailNewPage().verifyTableProFormaHeaderAndData("Property Information", 1);
        trepp().propertyDetailNewPage().verifyTableProFormaHeaderAndData("Revenues", 2);
        trepp().propertyDetailNewPage().verifyTableProFormaHeaderAndData("Expenses", 3);
        trepp().propertyDetailNewPage().verifyTableProFormaHeaderAndData("Estimated Market Value", 4);
        trepp().propertyDetailNewPage().enterCapRateVal("6");
        trepp().propertyDetailNewPage().verifyCapRateChangesEstimatedMarketValue();
    }

    @Test(groups = {"regression"}, dataProvider = "nonSecuritizedProperty", dataProviderClass = StaticDataProvider.class)
    public void verifyTaxAndSaleTab(String nonSecuritizedProperty, boolean isTaxAndSaleTab) throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(nonSecuritizedProperty);
        trepp().crehomepage().waitForLoading();
        Thread.sleep(3000);
        boolean isTaxAndSaleDataTab = trepp().propertyDetailNewPage().verifyPropertyPageWithTaxAndSaleTabVisibility(nonSecuritizedProperty, isTaxAndSaleTab);
        if (isTaxAndSaleDataTab)
            trepp().propertyDetailNewPage().verifyTransferCharacteristicsTable(nonSecuritizedProperty);
    }
    
    @Test(groups = { "regression" })
    public void verifyLoanPiecesTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddressWithFollowedByName(propertyName2, "LBTY 2016-225L");
        trepp().propertyDetailNewPage().verifyPropertyName(propertyName2);
        trepp().crehomepage().waitForLoading();
        trepp().propertyDetailNewPage().clickDetailsTab();
        trepp().crehomepage().waitForLoading();
        trepp().propertyDetailNewPage().clickOnTab("Loan Pieces");
        trepp().propertyDetailNewPage().clickFirstTrusteeLoanId();
        trepp().propertyDetailNewPage().switchToNewWindow();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        trepp().propertyDetailNewPage().switchToOldWindow();
        trepp().softAssert.assertAll();
    }
    
}
