package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class SavedSearchFunctionalityTest extends BaseSetupClass {

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Saved Search Validation";

        this.strDTParametersNValues = parameterNValue;


    }


    @Test(groups = {"regression"})
    public void saveSearchfromPropertyList() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().clickModalSearch();
        trepp().newListingPage().verifyMapViewnData();
        trepp().newListingPage().verifyAlertButton();
        trepp().newListingPage().saveSearchCriteria("testSavedSearch");
    }

    @Test(groups = {"regression"})
    public void verify1SavedSearch() throws Exception {

        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifySAVEDSEARCHES();
        trepp().crehomepage().clickSavedSearchButton();
        trepp().crehomepage().verifyOpenSavedSearch("testSavedSearch");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
    }

    @Test(groups = {"regression"})
    public void verify2DeleteSavedSearch() throws Exception {

        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifySAVEDSEARCHES();
        trepp().crehomepage().clickSavedSearchButton();
        trepp().crehomepage().verifyDeleteSavedSearch("testSavedSearch");
    }

    @Test(groups = {"regression"})
    public void verify3EditFunctionalityInSavedSearch() throws Exception {
        saveSearchfromPropertyList();
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifySAVEDSEARCHES();
        trepp().crehomepage().clickSavedSearchButton();
        trepp().crehomepage().verifyEditFunctionalityInSavedSearch();
        verify2DeleteSavedSearch();
    }
}
