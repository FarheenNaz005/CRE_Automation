package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newdemo.framework.base.BaseSetupClass;

public class StratsNewPageFunctionality extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Homepage Validation";
        this.strDTParametersNValues = parameterNValue;

    }

    @Test(groups = {"regression"})
    public void verifyNewStratsPage() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().selectCounty("Cascade");
        trepp().crehomepage().clickModalSearch();

        long newPageLoadTime = trepp().newStratsPage().clickOnNewStratsLink();
        long oldPageLoadTime = trepp().newStratsPage().clickOnoldStratsLink();


    }

    @Test(groups = {"regression"})
    public void verifyPropertyCollection() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectZipCode("10036");
        trepp().crehomepage().clickModalSearch();
        trepp().newStratsPage().clickOnNewStratsLink();
        trepp().newStratsPage().verifyTableAndChartCollection();


    }

}
