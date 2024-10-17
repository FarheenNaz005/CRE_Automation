package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;
import com.newdemo.framework.data.LoadData;

@Test
public class PropertyPageFunctionality extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;


    }


    @Test(groups = {"sanity", "regression"})
    public void searchAndData() throws Exception {
        try {
            trepp().crehomepage().goToHome();
            trepp().crehomepage().clicksearchbox();
            trepp().crehomepage().searchaddress(new LoadData(this.strDTParametersNValues).strDTSearch);
            trepp().propertyPage().clickdetails();
            trepp().propertyPage().verifyheaderDataInDetailPage();
            //Trepp().propertyPage().verifyLoanDatailInDetailPage();
            trepp().propertyPage().clickComps();
            trepp().propertyPage().verifycompsDatailPage();
            trepp().propertyPage().clickMarket();
            trepp().propertyPage().verifyMarketdispositionData();
            trepp().propertyPage().clickBenchmark();
            trepp().crehomepage().waitForLoading();
            trepp().propertyPage().verifyBenchMarkData();
            trepp().softAssert.assertAll();
        } catch (Exception e) {
            //BaseSetupClass.priorityntestname.put(2,this.getClass().getSimpleName());
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            //  BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }
    }

    @Test(groups = {"regression"})
    public void verify1Comp() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(new LoadData(this.strDTParametersNValues).strDTSearch);
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().clickComps();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().verifycompsDatailPage();
        trepp().propertyPage().verifyCompDownloadAll("Comps Report.xlsx");
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();
    }

}
