package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;

public class CRENewPageListingFunctionality extends BaseSetupClass {
    private String propertyName = "One Vanderbilt";
    private String mostRT = "Most Recent";

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
            // Trepp().NewListingPage().clickonlistingpagev2();
            trepp().newListingPage().verifyMapViewnData();
            trepp().newListingPage().verifyMapviewListAgainstListView();
            trepp().newListingPage().verifyDownloanAllButton();
            trepp().newListingPage().clickSelectAllPropertyCheckbox();
            trepp().newListingPage().verifyDealListButton();
            trepp().newListingPage().verifyAddPortfolioButton();
            trepp().newListingPage().clickSelectAllPropertyCheckbox2();
            trepp().softAssert.assertAll();
        } catch (Exception e) {
            // BaseSetupClass.priorityntestname.put(2,this.getClass().getSimpleName());
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            // BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }
    }

    @Test(groups = {"regression"})
    public void verifyCREListingAddRemoveColumn() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().selectCounty("Cascade");
        trepp().crehomepage().clickModalSearch();

        trepp().newListingPage().clickonlistingpagev2();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();

        trepp().newListingPage().removeAllCheckedColumn();
        trepp().newListingPage().verifyCountOfUnpinnedColumn(0);
        trepp().newListingPage().verifyFilterAndAddColumn("Expenses");
        trepp().newListingPage().verifyColumnForNullValue("expenses");
        trepp().newListingPage().verifyFilterAndAddColumn2("Owner 1 Mailing Address");

    }

    @Test(groups = {"regression"})
    public void verifyCREListingFilterAndSort() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().selectCounty("Cascade");
        trepp().crehomepage().clickModalSearch();

        trepp().newListingPage().clickonlistingpagev2();
        trepp().newListingPage().clickList();
        trepp().newListingPage().selectview("My Views", "Yogi Test View");
        trepp().newListingPage().removeAllCheckedColumn();
        trepp().newListingPage().verifyCountOfUnpinnedColumn(0);
        trepp().newListingPage().verifyFilterAndAddColumn("Expenses");
        trepp().newListingPage().applyfilter("1000000");
        trepp().newListingPage().verifydata("1000000");

        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verifyDownloadLoanListing() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().selectCounty("Cascade");
        trepp().crehomepage().clickModalSearch();
        trepp().newListingPage().clickList();
        trepp().newListingPage().verifyLoanListingDownloadAll("Market Loan Listing - Trepp.xlsx");
        trepp().newListingPage().clickMapview();
        trepp().newListingPage().verifyLoanListingDownloadAll("Market Loan Listing - Trepp (1).xlsx");

        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }

        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void downloadAndVerifyView() throws Exception {
        try {
            trepp().crehomepage().goToHome();

            trepp().crehomepage().clickSavedSearchButton();
            trepp().crehomepage().verifyOpenSavedSearch("TEST_SAVE");
            trepp().crehomepage().waitForLoading();

            trepp().newListingPage().clickonlistingpagev2();
            trepp().newListingPage().clickList();

            trepp().newListingPage().selectview("My Views", "Yogi Test View");
            trepp().newListingPage().verifyLoanListingDownloadAll("Market Loan Listing - Trepp.xlsx");
            /*trepp().newListingPage().clickv1List();
            trepp().listingPage().clickList();
            trepp().listingPage().selectview("My Views", "Yogi Test View");
            trepp().listingPage().verifyLoanListingDownloadAll("Market Loan Listing - Trepp.csv");
            Utilites.convertCsvToXls(System.getProperty("user.dir") + "/Downloads/", "Market Loan Listing - Trepp.csv",
                    "oldexcelFile");
            trepp().newListingPage().compareFiles("oldexcelFile.xlsx", "Market Loan Listing - Trepp.xlsx", "difference.xlsx");*/

            if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
                trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
            }

        } catch (Exception e) {
            // BaseSetupClass.priorityntestname.put(2,this.getClass().getSimpleName());
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            // BaseSetupClass.priority= "P1";
            throw new AssertionError(error, e);
        }
    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().goToHome();

        trepp().crehomepage().clickSavedSearchButton();
        trepp().crehomepage().verifyOpenSavedSearch("Multi-MSA");
        trepp().crehomepage().waitForLoading();

        trepp().newListingPage().clickonlistingpagev2();
        trepp().newListingPage().clickList();
        trepp().newListingPage().runAssessibilityfunction();
    }

    @Test(groups = {"regression"})
    public void verifyPinColumnChangeOrder() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().selectCounty("Cascade");
        trepp().crehomepage().clickModalSearch();
        trepp().newListingPage().clickonlistingpagev2();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();
        trepp().newListingPage().removeAllCheckedColumn();
        trepp().newListingPage().verifyCountOfUnpinnedColumn(0);
        trepp().newListingPage().verifyFilterAndAddColumn("Expenses");
        trepp().newListingPage().verifyFilterAndAddColumn("LTV");
        trepp().newListingPage().verifyFilterAndAddColumn("County");
        trepp().newListingPage().changeColumnOrder();
        trepp().newListingPage().pinColumnToLeft();
        trepp().newListingPage().verifyPinColumnToLeft();
        trepp().softAssert.assertAll();

    }

    @Test(groups = {"regression"})
    public void verifyCreatingUpdatingDeletingViewInLoanListingPage() throws Exception {
        initialSteps();
        boolean oldFileDeleted = trepp().newListingPage().delteOldViewIfExist("CRE4358Test");
        if (oldFileDeleted)
            initialSteps();

        trepp().newListingPage().removeAllCheckedColumn();
        trepp().newListingPage().verifyCountOfUnpinnedColumn(0);
        trepp().newListingPage().verifyFilterAndAddColumn("Address");
        trepp().newListingPage().verifyFilterAndAddColumn("Loan Name");
        trepp().newListingPage().createNewViewOnLoanListingPage("CRE4358Test");
        trepp().newListingPage().selectview("My Views", "CRE4358Test");
        trepp().newListingPage().verifyOnlySelectedColInViewDisplayed("Address");
        trepp().newListingPage().verifyOnlySelectedColInViewDisplayed("Loan Name");

        trepp().newListingPage().createDefaultView("CRE4358Test");
        initialSteps();
        trepp().newListingPage().verifyDefaultView("CRE4358Test");

        trepp().newListingPage().verifyFilterAndAddColumn("Trepp Real ID");
        trepp().newListingPage().clickSaveViewButton();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyOnlySelectedColInViewDisplayed("Trepp Real ID");
        trepp().newListingPage().selectview("My Views", "CRE4358Test");
        trepp().newListingPage().deleteViewOnLoanListingPage();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyViewDeletedSuccessfullly("My Views", "CRE4358Test");
        trepp().softAssert.assertAll();

    }

    private void initialSteps() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().selectPropertyCity("Los Angeles");
        trepp().crehomepage().clickModalSearchButton();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();
        trepp().crehomepage().waitForLoading();
    }

    @Test(groups = {"regression"})
    public void verifyDataPopulateInLoanListingColumns() throws Exception {
        initialSteps();
        trepp().newListingPage().selectCustomeView("CRE4499");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("address", "Address data : ");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("trepprealid", "Trepp Real ID data : ");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("city", "City data : ");
        trepp().newListingPage().moveToDifferentColumn("city");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("stateregion", "State data : ");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("zippostalcode", "Zip data : ");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("county", "County data : ");
        trepp().newListingPage().moveToDifferentColumn("county");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("loanname", "Loan Name data : ");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("category", "Category data : ");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("yearbuilt", "Year Built data : ");
        trepp().newListingPage().moveToDifferentColumn("yearbuilt");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("propertytypenorm", "Normalized Property Type data : ");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("propertysubtype", "Trepp Subtype data : ");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("submarket", "Trepp Submarket data : ");
        trepp().newListingPage().moveToDifferentColumn("submarket");
        trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("size", "Sq.ft/ No. of Units data : ");
        //trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("numunits", "Number of Units data");
        //trepp().newListingPage().verifyColDataIsNotBlankOnLoanListingPage("secsqft", "Square feet data");
        trepp().softAssert.assertAll();

    }
    
    @Test(groups = {"regression"})
    public void verifyPinAndClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().openSavedDealList("CRE4585");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifymapPinsCountEqualToPropertyShowsOnDealListMapViewPage();
        trepp().newListingPage().verifyPropertyOpensAfterClicking();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");
        trepp().softAssert.assertAll();
        
    }
    
    @Test(groups = { "regression" })
    public void verifyDealListAlerts() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().openSavedDealList("TestDealList");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().deleteALertIfAlreadyPresentForPropertyDeal();
        trepp().newListingPage().createAlertOnDeaLListPage();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyAlertSavedSuccessfully();
        trepp().newListingPage().verifyAlertPresentOnAlertManagerScreen("One Vanderbilt");
        trepp().newListingPage().verifyAlertPresentOnAlertManagerScreen("200 Park Avenue");   
        trepp().softAssert.assertAll();

    }
    
    @Test(groups = { "regression" })
    public void verifyDealListBehaviours() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().openSavedDealList("CRE4586");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyWorkoutOppurtunitiesView("Workout Opportunities", "Multiproperty Loan");
        trepp().newListingPage().verifyDealsCanBeAddedToPortfolio("CRETestPort4586", "CRETestPort4586");
        trepp().newListingPage().verifyPropertyAddedToPortfolioPage("The Cosmopolitan Las Vegas Resort & Casino");
        trepp().newListingPage().verifyPropertyAddedToPortfolioPage("One Vanderbilt");
        trepp().newListingPage().verifyPortfolioDeleted();
        trepp().crehomepage().clickSavedSearchButton();
        trepp().crehomepage().verifyOpenSavedSearch("07030 30B+");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyAddandDeletePropertiesFromDealList("PropertyDeal");
        trepp().softAssert.assertAll();
        
    }
    
    @Test(groups = { "regression" })
    public void verifyMarketSnapshotClickThroughFunctionality() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMarketSnapshotClickThroughToLoanDeatailsPage();
        trepp().newListingPage().closeMarketSnapshotWindow();
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMarketSnapshotClickThroughToLoanListingPage("Details");
        trepp().softAssert.assertAll();
        
    }
    
    @Test(groups = { "regression" })
    public void verifyMarketSnapshotContent() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickMArketSnapshotButton();
        trepp().newListingPage().verifyMarketSnapshotModalHeader("Highlights");
        trepp().newListingPage().verifyMarketSnapshotHighlightsSectionLoads();
        trepp().newListingPage().verifyMarketSnapshotModalHeader("Recent Transactions");
        trepp().newListingPage().verifyMarketSnapshotSectionsLoad("2");
        trepp().newListingPage().verifyMarketSnapshotModalHeader("Income & Expense Benchmark");
        trepp().newListingPage().verifyMarketIncomeAndExpenseBenchmarkSectionLoads();
        trepp().newListingPage().verifyMarketSnapshotModalHeader("Origination Volume & Underwriting Trends");
        trepp().newListingPage().verifyMarketSnapshotSectionsLoad("3");
        trepp().newListingPage().verifyDropdownCheck();
        trepp().softAssert.assertAll();
        
    }
    
    @Test(groups = { "regression" })
    public void verifyTreppHotSearchAndClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyTreppHotSearchOnHomepageClickThruToNewLoanListingPage();
        trepp().softAssert.assertAll();
        
    }
    
    @Test(groups = { "regression" })
    public void verifyContentOfNewIssuancePage() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickNewIssuance();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyTransactionTabDataLoad();
        trepp().newListingPage().clickNewIssuanceTabs("New Issue Trends");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Yearly Volume Comparison", 1);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Quarterly New Issue Volume", 2);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Monthly Average LTV", 3);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Monthly Average Debt Yield", 4);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Monthly Average DSCR", 5);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Monthly Average IO Composition", 6);
        trepp().newListingPage().clickNewIssuanceTabs("Spreads & Rates");
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyNewSpreadsAndRatesTabCMBSSpreadLoads();
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("U.S. Bond Initial Pricing Spreads (2023)", 2);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Secondary Market Conduit Spreads", 3);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("CMBX Spreads", 4);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Balance Sheet Lending Spreads (Trepp-i™)", 5);
        trepp().newListingPage().verifyNewIssuenceTabsDataLoad("Treasury Yields", 6);
        trepp().newListingPage().verifyTreppWireSpreadsLoads();
        trepp().softAssert.assertAll();
        
    }
    
    @Test(groups = {"regression"})
    public void verifyTreppwireTabClickThru() throws Exception {
            trepp().crehomepage().goToHome();
            trepp().crehomepage().waitForLoading();
            trepp().crehomepage().clickStats();
            trepp().crehomepage().waitForLoading();
            trepp().newStratsPage().clickOnTreppWireTab();
            trepp().newStratsPage().clickOnInTheSpotLightTab();
            trepp().newStratsPage().clickOnFirstDeal();
            trepp().crehomepage().waitForLoading();
            trepp().newListingPage().verifyMapViewnData();
            trepp().newListingPage().switchToOldWindow();
            trepp().newStratsPage().clickOnFirstProperty();
            trepp().crehomepage().waitForLoading();
            trepp().newStratsPage().verifyNewDetailsPageOpen("Details");
            trepp().newListingPage().switchToOldWindow();
            trepp().softAssert.assertAll();
    }
  
    @Test(groups = { "regression" })
    public void verifyLoanListingMapView() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickNewAdvanced();
        trepp().newAdvanceSearch().selectPropertyByZipCode("33010");
        trepp().crehomepage().clickModalSearchButton();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        trepp().newListingPage().verifyMapPinsLoad();
        trepp().newListingPage().verifyMapPinPopup();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "My Comments");
        trepp().softAssert.assertAll();
        
    }
    
    @Test(groups = { "regression" })
    public void verifyNewDetailsPageReportingOrderOfFinancialData() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clicksearchbox();
        trepp().crehomepage().searchaddress(propertyName);
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().goToFinancialTabOnNewDtailsPage();
        trepp().newListingPage().verifyMostRecentTab(mostRT);
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = { "regression" })
    public void verifyHotelReportMapViewPinsAndClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().clickOnCompanyExposureTab();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().selectHotel("InterContinental Hotels Group");
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().verifyPinsAndClickThru();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = { "regression" })
    public void verifyTenantsReportMapViewPinsAndClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().clickOnCompanyExposureTab();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().clickTenant();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().selectHotel("Google");
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().verifyPinsAndClickThru();
        trepp().newListingPage().verifySummaryPageOpens("Summary", "Commentary");
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = { "regression" })
    public void verifyCompanyExposurePinsAndClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().clickOnCompanyExposureTab();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().clickCompanyExposurePin();
        trepp().newListingPage().verifyMapViewnData();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().clickOnCompanyExposureTab();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().clickTenant();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().clickCompanyExposurePin();
        trepp().newListingPage().verifyMapViewnData();       
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = { "regression" })
    public void verifyNewIssuanceTransactionTabClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickStats();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickNewIssuance();
        trepp().crehomepage().waitForLoading();
        trepp().newStratsPage().selectDeal();  
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().switchToNewWindow();
        trepp().newListingPage().verifyMapViewnData(); 
        trepp().newListingPage().switchToOldWindow();
        trepp().softAssert.assertAll();
    }

}
