package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.base.ReuseableFunctions;

@Test
public class CREHomePage extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        String strDescription = "CRE application Homepage Validation";

        this.strDTParametersNValues = parameterNValue;
    }


    @Test(groups = {"sanity", "regression"})
    public void homePage() throws Exception {
        try {
            //Trepp().CREHomepage().waitForLoading();
            trepp().crehomepage().waitForHomepageLoan();
            //Trepp().CREHomepage().VerifyTrendsNRankings();
            //Trepp().CREHomepage().VerifyTrendsNRankingsMarket();
            trepp().crehomepage().verifyADVANCEDButton();
            trepp().crehomepage().verifySAVEDSEARCHES();
            trepp().crehomepage().verifymultifamily();
            trepp().crehomepage().textfromfootnote();
            trepp().crehomepage().verifyOFFICE();
            trepp().crehomepage().verifyRETAIL();
            trepp().crehomepage().verifyINDUSTRIAL();
            trepp().crehomepage().verifyWeeklyChange();
            trepp().crehomepage().verifyNews();
            trepp().crehomepage().verifyNewsCREDirect();
            trepp().crehomepage().verifyNewsTreppWire();
            trepp().crehomepage().verifyTreppWirePodcast();
            //Trepp().CREHomepage().verifyNewsTwitter();
            trepp().crehomepage().verifyTreppLearning();
            //Trepp().CREHomepage().verifyTreppTutorialList();
            trepp().crehomepage().verifysearchbox();
            trepp().crehomepage().verifyMyWork();
            //trepp().crehomepage().clickMyWork();
            trepp().crehomepage().verifyPortfolios();
            trepp().crehomepage().verifyDealLists();
            trepp().crehomepage().verifyStats();
            trepp().crehomepage().verifyMore();
            trepp().crehomepage().clickMore();
            trepp().crehomepage().verifySettings();
            //Trepp().CREHomepage().VerifyAPPS();
            trepp().crehomepage().verifyLogout();
            trepp().crehomepage().verifyKnowledgeBase();
        } catch (Exception e) {
            BaseSetupClass.priorityntestname.put(1, new String[] {this.getClass().getSimpleName(), BaseSetupClass.session.toString()});
            String error = ReuseableFunctions.objUtilitess.getExceptionNDisplay(e, true);
            throw new AssertionError(error, e);
        }
    }

    @Test(groups = {"accessibility"})
    public void accessibilityTest() throws Exception {
        trepp().crehomepage().clickTreppLogo();
        trepp().crehomepage().waitForHomepageLoan();
        trepp().crehomepage().runAssessibilityfunction();
    }
    
    @Test(groups = {"regression"})
    public void verifyTreppWireProdcastSection() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().verifyTreppWireProdcast();       
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = {"regression"})
    public void verifyExposureSection() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().verifyExposureSectionOnHomePageHotelReport();
        trepp().crehomepage().verifyExposureSectionOnHomePageTenantsReport();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verifyUserProfile() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().goToProfileSectionOfUser();
        trepp().crehomepage().editAndVerifyLastNameField("TL04New");
        trepp().crehomepage().verifyChangePasswordModel();
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = {"regression"})
    public void verifyTreppWireTreppTalkCRENewsToggleAndClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().verifyTreppWireSection();
        trepp().crehomepage().verifyTreppTalkSection();
        trepp().crehomepage().verifyCRENewsSection();
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = {"regression"})
    public void verifyTrendsAndRankingsWidget() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().verifyTrendsAndRanking();
        trepp().softAssert.assertAll();
    }


} 
