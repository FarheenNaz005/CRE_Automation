package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;


@Test
public class LogOutTest extends BaseSetupClass {

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Logout automation";

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"sanity", "regression"})
    public void logOutTest() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickMore();
        trepp().crehomepage().clickLogOut();
    }
}
