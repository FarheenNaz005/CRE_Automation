package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class CREpropertyEvaluation extends BaseSetupClass {

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Property Valuation and Download automation";

        this.strDTParametersNValues = parameterNValue;


    }

    @Test(groups = {"regression"})
    public void crePropertyEvaluation() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickPortfolioButton();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio("regressionPortfolio");
        trepp().newListingPage().waitForLoading();
        trepp().smallSearchNportfolio().deleteRecordfromPortfolio();
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForHomepageLoan();
        trepp().crehomepage().verifyADVANCEDButton();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectCounty("Nassau");
        trepp().crehomepage().selectyesoperatingstatment();
        trepp().crehomepage().clickModalSearch();
        trepp().newListingPage().waitForLoading();
        trepp().newListingPage().verifyMapViewnData();
        trepp().newListingPage().clickList();
        trepp().newListingPage().selectProperties(5);
        trepp().smallSearchNportfolio().clickaddPortfolio();
        trepp().smallSearchNportfolio().clickviewPortfolio();
        trepp().newListingPage().selectPropertiesPortfolio(5);
        //add class
        trepp().newListingPage().addclass();
        Thread.sleep(5000);

        //Adding  valuation with 5 %
        trepp().smallSearchNportfolio().clickSaveEdits();
        //Trepp().ListingPage().selectProperties(5);
        trepp().smallSearchNportfolio().clickGetValuation();
        trepp().smallSearchNportfolio().verifyandaddTTpercentage("5");
        trepp().smallSearchNportfolio().setvaluationdate();
        trepp().smallSearchNportfolio().clickGetValuationonmodal();
        trepp().smallSearchNportfolio().verifyDownloadValuations();
        trepp().smallSearchNportfolio().verifySaveValuation();
        trepp().smallSearchNportfolio().clickModel();
        trepp().smallSearchNportfolio().verifyModelpopupvalues();
        trepp().smallSearchNportfolio().clickapplyModel();
        trepp().smallSearchNportfolio().verifyModelcolumnnames();
        trepp().smallSearchNportfolio().verifyapprisalValues();
        trepp().smallSearchNportfolio().refreshpage();
        trepp().smallSearchNportfolio().clickModel();
        trepp().smallSearchNportfolio().clickapplyModel();
        trepp().smallSearchNportfolio().selectbclass();
        trepp().smallSearchNportfolio().verifyModelcolumnnames();
        trepp().smallSearchNportfolio().verifyapprisalValues();
        trepp().smallSearchNportfolio().clicksavemodelValuation();
        trepp().smallSearchNportfolio().addnSaveValuationname("5");

        //Adding 2nd valuation with 2 %
        //Trepp().CREHomepage().clickPortfolioButton();
        // Trepp().CREHomepage().selectPortfolio("testanish");
        trepp().newListingPage().selectPropertiesPortfolio(5);
        trepp().smallSearchNportfolio().clickGetValuation();
        trepp().smallSearchNportfolio().verifyandaddTTpercentage("2");
        trepp().smallSearchNportfolio().setvaluationdate();
        trepp().smallSearchNportfolio().clickGetValuationonmodal();
        trepp().smallSearchNportfolio().verifyDownloadValuations();
        trepp().smallSearchNportfolio().verifySaveValuation();
        trepp().smallSearchNportfolio().clickModel();
        trepp().smallSearchNportfolio().verifyModelpopupvalues();
        trepp().smallSearchNportfolio().clickapplyModel();
        trepp().smallSearchNportfolio().verifyModelcolumnnames();
        trepp().smallSearchNportfolio().verifyapprisalValues();
        trepp().smallSearchNportfolio().refreshpage();
        trepp().smallSearchNportfolio().clickModel();
        trepp().smallSearchNportfolio().clickapplyModel();
        trepp().smallSearchNportfolio().selectbclass();
        trepp().smallSearchNportfolio().verifyModelcolumnnames();
        trepp().smallSearchNportfolio().verifyapprisalValues();
        trepp().smallSearchNportfolio().clicksavemodelValuation();
        trepp().smallSearchNportfolio().addnSaveValuationname("2");
        // Verify the Saved list of valaution
        //trepp().CREHomepage().clickPortfolioButton();
        //trepp().CREHomepage().selectPortfolio("testanish");
        trepp().newListingPage().waitForLoading();
        trepp().newListingPage().selectPropertiesPortfolio(5);
        trepp().smallSearchNportfolio().clickGetValuation();
        trepp().smallSearchNportfolio().verifyandaddTTpercentage("2");
        trepp().smallSearchNportfolio().setvaluationdate();
        trepp().smallSearchNportfolio().clickGetValuationonmodal();
        trepp().smallSearchNportfolio().verifyDownloadValuations();
        trepp().smallSearchNportfolio().verifySavedValuationList();
        // Verify Download valuation
        //trepp().smallSearchNportfolio().clicknVerifyDownloadValuation();
    }
}
