package com.newdemo.steps;

import java.util.LinkedHashSet;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newdemo.framework.base.BaseSetupClass;

public class SummaryPageTest extends BaseSetupClass {
    
    Set<String> listOfAlerts = new LinkedHashSet<String>();

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;
    }

    @Test(groups = { "regression" })
    public void verifySummaryPageRelatedRecords() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("The Standard Hotel");
        trepp().crehomepage().waitForLoading();
        trepp().propertySummaryPageController().clickRelatedRecordTab();
        trepp().propertySummaryPageController().verifyPropertyOpens();
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = { "regression" })
    public void verifySummaryPageOccupancy() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("One Vanderbilt");
        trepp().crehomepage().waitForLoading();
        trepp().propertySummaryPageController().verifyOccupancySectionOnSummaryPage();
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = { "regression" })
    public void verifySummaryPageCommentary() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("The Standard Highline NYC");
        trepp().crehomepage().waitForLoading();
        trepp().propertySummaryPageController().verifyCommentaryTab();
        trepp().propertySummaryPageController().clickCommentaryTab();
        trepp().propertySummaryPageController().verifyHistoricalCommentsSection();
        trepp().propertySummaryPageController().verifyModelOpen();
        trepp().softAssert.assertAll();
    }
    
    // Add and remove property from portfolio
    @Test(groups = { "regression" })
    public void verifyLoanDetailsSummaryPageFunctionality1() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("25 West 45th Street");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");
        trepp().propertySummaryPageController().addAndDeletePropertyToPortfolioOnSummaryPage("CRE4661Port",
                "25 West 45th Street");
        trepp().softAssert.assertAll();
    }

    // Add and remove property alerts
    @Test(groups = { "regression" })
    public void verifyLoanDetailsSummaryPageFunctionality2() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("25 West 45th Street");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");
        trepp().propertySummaryPageController().verifyCreateAlertOnSummaryPage();
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAlert();
        trepp().alertPage().waitForAlertList();
        trepp().alertPage().clickManagerNavLink();
        trepp().crehomepage().waitForLoading();
        String propertyName = trepp().alertPage().searchAlertByName("25 West 45th Street");
        trepp().alertPage().clickEditButton();
        trepp().crehomepage().waitForLoading();
        trepp().alertPage().clickDeleteAlertButton();
        trepp().crehomepage().waitForLoading();
        listOfAlerts = trepp().alertPage().getListOfAlerts();
        trepp().alertPage().verifyDeleteAlert(listOfAlerts, propertyName);
        trepp().crehomepage().waitForLoading();
        trepp().softAssert.assertAll();
    }

    // Add and remove property from deal list
    @Test(groups = { "regression" })
    public void verifyLoanDetailsSummaryPageFunctionality3() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("25 West 45th Street");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");
        trepp().propertySummaryPageController().addAndDeletePropertyToDealListOnSummaryPage("CRE4661DealList");
        trepp().softAssert.assertAll();
    }

    // Verify download icon
    @Test(groups = { "regression" })
    public void verifyLoanDetailsSummaryPageFunctionality4() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress("25 West 45th Street");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");
        trepp().propertySummaryPageController()
                .verifyDownloadIconOnLoanDetailPage("Loan Details - 25 West 45th Street.xlsx");

        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();
    }

}
