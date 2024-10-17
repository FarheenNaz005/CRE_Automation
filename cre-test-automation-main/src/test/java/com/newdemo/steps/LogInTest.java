package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;

@Test
public class LogInTest extends BaseSetupClass {

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE Login";
        this.strDTParametersNValues = parameterNValue;
    }

    @Test(groups = {"logInTest"})
    public void logInTest() throws Exception {

        try {
            trepp().login().launchURL();
            // throw new Exception("login test fail");
            trepp().login().clickLoginButton();
            //trepp().login().runAssessibilityfunction();
            trepp().login().waitForUserName();
            //trepp().login().runAssessibilityfunction();
            trepp().login().clickusername();
            trepp().login().typeEmail();
            trepp().login().clickpassword();
            trepp().login().typePassword();
            trepp().login().clickSignin();
            trepp().login().waitForLoan();
            trepp().login().clickLoan();
            trepp().crehomepage().waitForLoading();
            trepp().crehomepage().waitForHomepageLoan();
        } catch (Exception e) {
            BaseSetupClass.priorityntestname.put(1,
                    new String[] {this.getClass().getSimpleName(), BaseSetupClass.session.toString()});
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            // BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }
    }

/*    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().login().launchURL();
        trepp().login().clickLoginButton();
        trepp().login().runAssessibilityfunction();
    }*/
}
