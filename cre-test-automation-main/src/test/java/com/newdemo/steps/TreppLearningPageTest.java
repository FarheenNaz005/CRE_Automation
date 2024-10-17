package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class TreppLearningPageTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"regression"})
    public void verifytrepplearningpage() throws Exception {
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clicktrepplearning();
        trepp().crehomepage().switchtotrepplearningiframe();
    }

}
