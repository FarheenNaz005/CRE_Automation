package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class InsightsPageTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"regression"})
    public void verifyinsightsPage() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickinsights();
        trepp().crehomepage().switchtoinsightiframe();
    }

    @Test(groups = {"regression"})
    public void verifyInsightPageOfficeTabCharts() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickinsights();
        trepp().crehomepage().clickOfficeTabAndVerifyCharts();
        trepp().softAssert.assertAll();

    }

}
