package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;

@Test
public class CREListingFunctionality extends BaseSetupClass {

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Homepage Validation";

        this.strDTParametersNValues = parameterNValue;


    }

    @Test(groups = {"sanity", "regression"})
    public void verifyButtonVisibility() throws InterruptedException, Exception {

        try {
            trepp().crehomepage().goToHome();
            trepp().crehomepage().clickAdvanced();
            trepp().crehomepage().selectState("Montana");
            trepp().crehomepage().selectCounty("Cascade");
            trepp().crehomepage().clickModalSearch();

            trepp().listingPage().verifyMapViewnData();
            trepp().listingPage().verifyMapviewListAgainstListView();
            trepp().listingPage().verifyDownloanAllButton();
            trepp().listingPage().clickSelectAllPropertyCheckbox();
            trepp().listingPage().verifyDealListButton();
            trepp().listingPage().verifyAddPortfolioButton();
            trepp().listingPage().clickSelectAllPropertyCheckbox();
            trepp().softAssert.assertAll();
        } catch (Exception e) {
            //BaseSetupClass.priorityntestname.put(2,this.getClass().getSimpleName());
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            //BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }
    }

    @Test(groups = {"regression"})
    public void verifyCREListingAddRemoveColumn() throws Exception {
        trepp().listingPage().removeAllCheckedColumn();
        trepp().listingPage().verifyCountOfUnpinnedColumn(0);
        trepp().listingPage().verifyFilterAndAddColumn("Expenses");
        trepp().listingPage().verifyColumnForNullValue("expenses");
        trepp().listingPage().verifyFilterAndAddColumn("Owner Address");
        trepp().listingPage().verifyColumnForNullValue("ownerStreetAddress");
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verifyCREListingFilterAndSort() throws Exception {
        trepp().listingPage().selectCustomeView("Yogi Test View");
        trepp().listingPage().verifyColumnFilterCheckbox("Yes", "isMultiProperty");
        trepp().listingPage().addColumnFilterDropdown("Less than", "500000", "curBal");
        trepp().listingPage().verifyDropdownFilteredColumn("Less than", "500000", "curBal");
        trepp().listingPage().resetViewAndFilter();
        trepp().listingPage().addColumnFilterDropdown("Contains", "FedEx", "propName");
        trepp().listingPage().verifyDropdownFilteredColumn("Contains", "FedEx", "propName");
        trepp().listingPage().resetViewAndFilter();
        trepp().listingPage().waitForLoading();
        trepp().listingPage().verifyColumnSort("curBal", "number");
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verifyDownloadLoanListing() throws Exception {
        trepp().listingPage().verifyLoanListingDownloadAll("Market Loan Listing.csv");
        trepp().listingPage().clickMapview();
        trepp().listingPage().verifyLoanListingDownloadAll("Market Loan Listing (1).csv");
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();
    }
}
