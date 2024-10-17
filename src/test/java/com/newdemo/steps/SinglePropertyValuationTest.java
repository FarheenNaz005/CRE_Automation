package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class SinglePropertyValuationTest extends BaseSetupClass {
    String valuationName;

    @Parameters({"ParameterNValue", "valuationName"})
    @BeforeClass
    public void beforeClass(String parameterNValue, @Optional String valuationName) throws Exception {

        this.strDTParametersNValues = parameterNValue;

        this.valuationName = (valuationName != null) ? valuationName : "testValuation";
    }

    @Test(groups = {"regression"})
    public void verify1PropertyValuation() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("1211 Avenue of the Americas");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().clickValuation();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().singlePropertyValutionPage().verifyValuationTab();
        trepp().propertyPage().singlePropertyValutionPage().selectValuation("NEW");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().singlePropertyValutionPage().selectPeriod(10);
        trepp().propertyPage().singlePropertyValutionPage().fillBasePercentage();
        trepp().propertyPage().singlePropertyValutionPage().validateBaseValue();
        trepp().propertyPage().singlePropertyValutionPage().fillDirectCapMethod();
        trepp().propertyPage().singlePropertyValutionPage().validateDirectCapMethod();
        trepp().propertyPage().singlePropertyValutionPage().fillTerminalCapRate("5", "0.1");
        trepp().propertyPage().singlePropertyValutionPage().validateTerminalCapRate("5", "0.1");
        trepp().propertyPage().singlePropertyValutionPage().fillUnleveredIRR("5", "0.1");
        trepp().propertyPage().singlePropertyValutionPage().validateUnleveredIRR("5", "0.1");
        trepp().propertyPage().singlePropertyValutionPage().saveAsValuation(this.valuationName, false);
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify2DownloadPropertyValuation() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("1211 Avenue of the Americas");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().clickValuation();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().singlePropertyValutionPage().selectValuation(this.valuationName);
        trepp().propertyPage().singlePropertyValutionPage().verifyPropertyValuationDownload(this.valuationName);
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }

        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify3DeleteValuation() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickValuations();
        trepp().crehomepage().waitForLoading();
        trepp().valuationManagerPage().goToValuation(this.valuationName);
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().singlePropertyValutionPage().deleteValuation(this.valuationName);
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = {"regression"})
    public void verifyValuationModal() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("One Vanderbilt");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().clickValuation();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().singlePropertyValutionPage().clickAddSource();
        trepp().propertyPage().singlePropertyValutionPage().verifyRevinewColValueUpdated("Laundry");  
        trepp().propertyPage().singlePropertyValutionPage().verifyBaseRateColValueUpdated("1000");  
        trepp().softAssert.assertAll();
    }

}
