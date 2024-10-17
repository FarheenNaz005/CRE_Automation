package com.newdemo.steps;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;

@Test
public class AlertFunctionality extends BaseSetupClass {
    List<String> properties = new ArrayList<String>();
    Set<String> listOfAlerts = new LinkedHashSet<String>();

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Alerts Validation";

        this.strDTParametersNValues = parameterNValue;


    }


    @Test(groups = {"regression"})
    public void verify1createAlert() throws Exception {
        try {
            trepp().crehomepage().goToHome();
            trepp().crehomepage().clickAdvanced();
            trepp().crehomepage().selectState("Montana");
            trepp().crehomepage().selectSource("CMBS");
            trepp().crehomepage().clickModalSearch();
            trepp().newListingPage().verifyMapViewnData();
            trepp().newListingPage().clickList();
            trepp().newListingPage().waitForLoading();
            properties = trepp().newListingPage().selectProperties(2);
            trepp().newListingPage().verifyAlertButton();
            trepp().newListingPage().createDealAlert();
            trepp().newListingPage().confirmSaveDeal();
            trepp().newListingPage().waitForLoading();
            trepp().crehomepage().goToHome();
            trepp().newListingPage().waitForLoading();
            trepp().crehomepage().clickAlert();
            trepp().crehomepage().waitForLoading();
            trepp().alertPage().clickManagerNavLink();
            trepp().crehomepage().waitForLoading();
            trepp().alertPage().verifyCreatedAlerts(properties);
            trepp().softAssert.assertAll();
        } catch (Exception e) {
            //BaseSetupClass.priorityntestname.put(3,this.getClass().getSimpleName());
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            //BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }
    }

    @Test(groups = {"sanity", "regression"})
    public void verify2AlertsNotification() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAlert();
        trepp().alertPage().waitForAlertList();
        trepp().alertPage().clickViewAlertsNavLink();
        if (BaseSetupClass.env.equalsIgnoreCase("Production")) {
            trepp().listingPage().clickList();
            if (trepp().listingPage().verifySidePanel()) {
                trepp().propertyPage().summaryPage().clickBackToListing();
            }
            trepp().listingPage().clickMapview();
            trepp().listingPage().verifyMapViewnData();

            trepp().listingPage().verifyLoanListingDownloadAll("Alert View.xlsx");
            trepp().listingPage().clickList();
            trepp().listingPage().verifyLoanListingDownloadAll("Alert View (1).xlsx");
            if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
                trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
            }
        }

        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify3EditAndDeleteAlert() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAlert();
        trepp().alertPage().waitForAlertList();
        trepp().alertPage().clickManagerNavLink();
        trepp().crehomepage().waitForLoading();
        String propertyName = trepp().alertPage().searchAlertByName("");
        trepp().alertPage().clickEditButton();
        trepp().crehomepage().waitForLoading();
        trepp().alertPage().turnOffAlerts();
        trepp().alertPage().verifyEditedAlerts(propertyName);
        trepp().crehomepage().waitForLoading();
        trepp().alertPage().clickEditButton();
        trepp().alertPage().clickDeleteAlertButton();
        trepp().crehomepage().waitForLoading();
        listOfAlerts = trepp().alertPage().getListOfAlerts();
        trepp().alertPage().verifyDeleteAlert(listOfAlerts, propertyName);
        trepp().crehomepage().waitForLoading();
        trepp().softAssert.assertAll();

    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAlert();
        trepp().alertPage().waitForAlertList();
        trepp().alertPage().runAssessibilityfunction();
    }
}
