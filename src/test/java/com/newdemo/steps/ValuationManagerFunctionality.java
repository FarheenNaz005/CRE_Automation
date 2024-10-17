package com.newdemo.steps;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class ValuationManagerFunctionality extends BaseSetupClass {
    String valuationName;

    @Parameters({"ParameterNValue", "valuationName"})
    @BeforeClass
    public void beforeClass(String parameterNValue, @Optional String valuationName) throws Exception {

        String strDescription = "CRE application Manage Portfolio Validation";
        this.valuationName = (valuationName != null) ? valuationName : "valuationTest";
        this.strDTParametersNValues = parameterNValue;
    }

    @Test(groups = {"regression"})
    public void verify1ValuationManagerScreen() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickValuations();
        trepp().valuationManagerPage().verifyValuationManagerPage();
        trepp().valuationManagerPage().verifyValuationCount();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify2clickRedirectToValuation() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickValuations();
        trepp().valuationManagerPage().goToValuation(this.valuationName);
        trepp().propertyPage().singlePropertyValutionPage().verifySelectedValuation(this.valuationName);
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify3clickRedirectToPropertyPage() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickValuations();
        trepp().valuationManagerPage().goToFirstProperty();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifyloanSummary();
        trepp().softAssert.assertAll();
    }
}
