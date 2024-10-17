package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;

@Test
public class DealListFunctionality extends BaseSetupClass {

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Deal List Validation";

        this.strDTParametersNValues = parameterNValue;


    }


    @Test(groups = {"sanity", "regression"})
    public void searchAndAddToDealList() throws Exception {
        try {
            trepp().crehomepage().goToHome();
            trepp().crehomepage().selectDealList("Production Deal Test");
            trepp().newListingPage().waitForLoading();
            trepp().newListingPage().clickList();
            trepp().newListingPage().waitForLoading();
            trepp().newListingPage().clickSelectAllPropertyCheckbox();
            trepp().newListingPage().clickOnDeleteButton();
            trepp().crehomepage().goToHome();
            trepp().crehomepage().clickAdvanced();
            trepp().crehomepage().selectState("Alabama");
            trepp().crehomepage().clickModalSearch();
            trepp().newListingPage().verifyMapViewnData();
            trepp().newListingPage().clickList();
            trepp().newListingPage().waitForLoading();
            trepp().newListingPage().selectProperties(5);
            trepp().newListingPage().addToDealList("Production Deal Test");
            trepp().newListingPage().waitForLoading();

        } catch (Exception e) {
            //BaseSetupClass.priorityntestname.put(3,this.getClass().getSimpleName());
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            //  BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }

    }

    @Test(groups = {"regression"})
    public void verifyDownloadDealList() throws Exception {
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().listingPage().verifyLoanListingDownloadAll("Deal List.xlsx");
        trepp().listingPage().clickMapview();
        trepp().listingPage().verifyLoanListingDownloadAll("Deal List (1).xlsx");
        trepp().softAssert.assertAll();
    }
}
