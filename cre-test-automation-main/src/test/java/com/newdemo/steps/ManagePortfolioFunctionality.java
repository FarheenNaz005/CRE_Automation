package com.newdemo.steps;


import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class ManagePortfolioFunctionality extends BaseSetupClass {
    String portfolioName;
    String portfolioName1;


    @Parameters({"ParameterNValue", "portfolioName"})
    @BeforeClass
    public void beforeClass(String parameterNValue, @Optional String portfolioName) throws Exception {

        String strDescription = "CRE application Manage Portfolio Validation";
        this.portfolioName = (portfolioName != null) ? portfolioName : "regressionPortfolio";
        this.portfolioName1 = (portfolioName1 != null) ? portfolioName1 : "CRE4609";
        this.strDTParametersNValues = parameterNValue;
    }


    @BeforeMethod
    public void beforeTestMethod(Method method) {
        System.err.println("Testmethod: " + method.getName());
    }

    @Test(groups = {"sanity", "regression"})
    public void verify1Portfolio() throws Exception {

        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickPortfolioButton();
        trepp().managePortfolioPage().verifyPortfolioVisible(this.portfolioName);
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify2EvaluationComment() throws Exception {

        trepp().managePortfolioPage().openValuation(this.portfolioName);
        trepp().managePortfolioPage().verifyValuation("Testvaluation_5");
        trepp().managePortfolioPage().addvaluationComment("Testvaluation_5");
    }

    @Test(groups = {"regression"})
    public void verify3EvaluationDelete() throws Exception {

        trepp().crehomepage().closeAnyOpenModal();
        trepp().managePortfolioPage().deleteValuation(this.portfolioName, "Testvaluation_5");
        trepp().crehomepage().waitForLoading();
    }

    @Test(groups = {"regression"})
    public void verify4PortfolioHistory() throws Exception {

        trepp().managePortfolioPage().navigateToHistoryPage(this.portfolioName);
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().verifyHistoryPage();
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify5PortfolioComment() throws Exception {

        trepp().managePortfolioPage().addPortfolioComment(this.portfolioName);
        trepp().managePortfolioPage().editPortfolioComment(this.portfolioName);
        trepp().managePortfolioPage().deletePortfolioComment(this.portfolioName);
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify6PortfolioDownload() throws Exception {

        trepp().crehomepage().closeAnyOpenModal();
        trepp().managePortfolioPage().downloadPortfolio(this.portfolioName);
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().propertyPage().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void verify6PortfolioShare() throws Exception {

        trepp().managePortfolioPage().sharePortfolio(this.portfolioName);
        trepp().softAssert.assertAll();
    }

    @Test(groups = {"sanity", "regression"})
    public void verify7PortfolioDelete() throws Exception {

        //trepp().crehomepage().closeAnyOpenModal();
        trepp().managePortfolioPage().deletePortfolio(this.portfolioName);
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
    }
    
    @Test(groups = {"regression"})
    public void verifyPortfolioMapViewPinsAndClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().selectPortfolioFromList("CRE4548");
        trepp().newStratsPage().verifyPinsAndClickThru();
        trepp().managePortfolioPage().verifyPortfolioInformationPage();
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = {"regression"})
    public void verifySnapshotClickThru() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickPortfolioButton();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(this.portfolioName1);
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().clickSnapshot();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().clickFirstMSA();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().verifyLoanListingListView();
        trepp().softAssert.assertAll();
    }
    
    @Test(groups = { "regression" })
    public void verifyEditPropertyFinancialInPortfolio() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickPortfolioButton();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(this.portfolioName1);
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().clickFirstPropLink();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().clickEditLoan();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().clickpropFinancials();
        trepp().managePortfolioPage().clickOnFoodBev("5000", "6000");
        trepp().managePortfolioPage().clickonRealEstateTx("6093000", "6093399");
        trepp().softAssert.assertAll();
    }

    @Test(groups = { "regression" })
    public void verifyPortfolioRevertToPreset() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().waitForLoading();
        trepp().crehomepage().clickPortfolioButton();
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().openPortfolio(this.portfolioName1);
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().clickList();
        trepp().crehomepage().waitForLoading();
        trepp().newListingPage().selectDealListCheckBox(2);
        trepp().managePortfolioPage().removeAllCheckedColumnPortfolio();
        trepp().managePortfolioPage().verifyFilterAndAddColumnOnPortfolio("Class");
        trepp().managePortfolioPage().addClassToProperty("A", "C");
        trepp().managePortfolioPage().clickGetValutionButtonAndAddDateAndPercentage("3");
        trepp().crehomepage().waitForLoading();
        trepp().managePortfolioPage().clickMoadalButton("34", "45");
        trepp().crehomepage().waitForLoading();
        trepp().softAssert.assertAll();
    }
    
 
}
