package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.data.LoadData;

public class UserPermissionTestCases extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;

    }

    @Test()
    public void verifyCompassIsShowingWithPermisson() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(new LoadData(this.strDTParametersNValues).strDTSearch);
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Research");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifydealTable("Compass");
        trepp().softAssert.assertAll();
    }

    @Test()
    public void verifyCompassIsNotShowing() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(new LoadData(this.strDTParametersNValues).strDTSearch);
        trepp().propertyPage().detailPageNew().switchToDetailv2();
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageNew().clickOnTab("Research");
        trepp().crehomepage().waitForLoading();
        trepp().propertyPage().detailPageOld().verifyTableNotPresent("Compass");
        trepp().softAssert.assertAll();
    }

}
