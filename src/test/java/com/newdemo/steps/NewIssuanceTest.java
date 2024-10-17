package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class NewIssuanceTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Stats | New Issuance Validation";

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"regression"})
    public void verify1NewIssuanceTab() throws Exception {
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyNewIssuanceLink();
        trepp().statsPage().clickNewIssuanceLink();
        trepp().crehomepage().waitForLoading();
        trepp().newIssuance().verifyDownloadButton();
        trepp().newIssuance().verifyVolumeAmount();
        trepp().newIssuance().verifyDealCount();
        //trepp().newIssuance().verifyTransanctionTab();
        trepp().newIssuance().openNewIssueTrendsTab();
        trepp().crehomepage().waitForLoading();
        trepp().newIssuance().verifyNewIssueTrendsTab();
        Thread.sleep(1000);
        trepp().newIssuance().openSpreadsNRatesTab();
        //trepp().newIssuance().verifySpreadsNRatesTab();
        trepp().crehomepage().waitForLoading();
        trepp().newIssuance().openTransanctionTab();
        trepp().crehomepage().waitForLoading();
        //trepp().newIssuance().verifySpreadNRatesDisableWithAllPopulation();
        //trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify2DownloadNewIssuanceReport() throws Exception {
        trepp().crehomepage().waitForLoading();
        trepp().newIssuance().verifyDownloadReport("NewIssuances.xlsx");
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().newIssuance().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyNewIssuanceLink();
        trepp().statsPage().clickNewIssuanceLink();
        trepp().crehomepage().waitForLoading();
        trepp().newIssuance().runAssessibilityfunction();
    }
    
    @Test(groups = {"regression"})
    public void verifyDropdownAndToggle() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyNewIssuanceLink();
        trepp().statsPage().clickNewIssuanceLink();
        trepp().crehomepage().waitForLoading();
        trepp().newIssuance().verifyPopulationDropdown();
        trepp().newIssuance().verifyDataLoads();
        trepp().newIssuance().verifyDealTypeDropdown();
        trepp().newIssuance().verifyDataLoads();
        trepp().newIssuance().verifyVintageeDropdown();
        trepp().newIssuance().verifyDataLoads();
        trepp().newIssuance().verifyCreditAndPricingView();        
        trepp().softAssert.assertAll();
    }
}
