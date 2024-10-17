package com.newdemo.framework.controller;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.newdemo.framework.controller.loan.CRELoanPageController;
import com.newdemo.framework.controller.loan.ValuationManagerController;
import com.newdemo.framework.controller.portfolio.CREsmallsearchnportfolioController;
import com.newdemo.framework.controller.portfolio.ManagePortfolioPageController;
import com.newdemo.framework.controller.portfolio.PortfolioLoanDetailPageController;
import com.newdemo.framework.controller.portfolio.PortfolioPageController;
import com.newdemo.framework.controller.portfolio.RankingPageController;
import com.newdemo.framework.controller.portfolio.SnapshotpageController;
import com.newdemo.framework.controller.portfolio.StatsPageController;
import com.newdemo.framework.controller.stats.CompanyExposurePageController;
import com.newdemo.framework.controller.stats.NewIssuanceController;
import com.newdemo.framework.controller.stats.StatsNewsPageController;
import com.newdemo.framework.controller.stats.TrendsPageController;
import com.newdemo.framework.controller.stats.TreppWirePageController;
import com.newdemo.framework.controller.loan.PropertyDetailPageNewController;
import com.newdemo.framework.controller.loan.PropertySummaryPageController;
import com.newdemo.framework.data.LoadData;


public class ApplicationController {

    public WebDriver driver = null;

    //====================CONTROLLER OBJECTS=======================

    public String strParametersNValues = "";
    public String strMainParametersNValues = "";
    public SoftAssert softAssert = null;

    public LoadData loginData = null;

    public LoginController login = null;
    public CREHomepageController crehomepage = null;
    public CREListingPageController listingPage = null;
    public CREnewlistingPageController newlistingPage = null;
    public PortfolioPageController portfolioPage = null;
    public PortfolioLoanDetailPageController portfolioLoanDetailPage = null;
    public RankingPageController rankingpage = null;
    public SnapshotpageController snapshotpage = null;
    public ManagePortfolioPageController managePortfolioPage = null;
    public ValuationManagerController valuationManagerPage = null;
    public StatsNewsPageController statsNewspage = null;
    public CRELoanPageController loanPage = null;
    public AlertPageController alertPage = null;
    public StatsPageController statsPage = null;
    public CompanyExposurePageController companyExposure = null;
    public TreppWirePageController treppWirePageController = null;
    public TrendsPageController trendsPageController = null;
    public NewIssuanceController newIssuance = null;
    public CREsmallsearchnportfolioController smallSearchNportfolio = null;
    public LoanOverridesController loanOverridesController = null;
    public LoanByLoanController loanByLoanController = null;
    public NewStratsPageController newStratsPage = null;
    public PropertySummaryPageController propertySummaryPageController = null;

    //common module controller
    public AdvanceSearchModalController advanceSearchModalController = null;

    public NewAdvanceSearchController newAdvanceSearchController = null;

    public PropertyDetailPageNewController propertyDetailPageNewController = null;

    public ApplicationController(WebDriver driver) {
        this.driver = driver;

        this.softAssert = new SoftAssert();
    }


    public LoginController login() throws Exception {
        if (login == null) {
            login = new LoginController(driver);

        }
        login.data = new LoadData(strParametersNValues);

        return login;
    }


    public CREHomepageController crehomepage() throws Exception {
        if (crehomepage == null) {
            crehomepage = new CREHomepageController(driver);
        }
        crehomepage.data = new LoadData(strParametersNValues);
        crehomepage.softAssert = this.softAssert;
        return crehomepage;
    }

    public CREListingPageController listingPage() throws Exception {
        if (listingPage == null) {
            listingPage = new CREListingPageController(driver);
            listingPage.data = new LoadData(strParametersNValues);
            listingPage.softAssert = this.softAssert;
        }

        return listingPage;
    }

    public CREnewlistingPageController newListingPage() throws Exception {
        if (newlistingPage == null) {
            newlistingPage = new CREnewlistingPageController(driver);
            newlistingPage.data = new LoadData(strParametersNValues);
            newlistingPage.softAssert = this.softAssert;
        }

        return newlistingPage;
    }

    public PortfolioPageController portfolioPage() throws Exception {
        if (portfolioPage == null) {
            portfolioPage = new PortfolioPageController(driver);
            portfolioPage.data = new LoadData(strParametersNValues);
            portfolioPage.softAssert = this.softAssert;
        }

        return portfolioPage;
    }

    public RankingPageController rankingpage() throws Exception {
        if (rankingpage == null) {
            rankingpage = new RankingPageController(driver);
            rankingpage.softAssert = this.softAssert;
            rankingpage.data = new LoadData(strParametersNValues);
        }
        return rankingpage;

    }

    public SnapshotpageController snapshotpage() throws Exception {
        if (snapshotpage == null) {
            snapshotpage = new SnapshotpageController(driver);
            snapshotpage.softAssert = this.softAssert;
            snapshotpage.data = new LoadData(strParametersNValues);
        }
        return snapshotpage;

    }

    public LoanOverridesController loanOverridesPage() throws Exception {
        if (loanOverridesController == null) {
            loanOverridesController = new LoanOverridesController(driver);
            loanOverridesController.data = new LoadData(strParametersNValues);
            loanOverridesController.softAssert = this.softAssert;
        }

        return loanOverridesController;
    }

    public LoanByLoanController loanByLoanPage() throws Exception {
        if (loanByLoanController == null) {
            loanByLoanController = new LoanByLoanController(driver);
            loanByLoanController.data = new LoadData(strParametersNValues);
            loanByLoanController.softAssert = this.softAssert;
        }

        return loanByLoanController;
    }

    public ManagePortfolioPageController managePortfolioPage() throws Exception {
        if (managePortfolioPage == null) {
            managePortfolioPage = new ManagePortfolioPageController(driver);
            managePortfolioPage.data = new LoadData(strParametersNValues);
            managePortfolioPage.softAssert = this.softAssert;
        }

        return managePortfolioPage;
    }

    public ValuationManagerController valuationManagerPage() throws Exception {
        if (valuationManagerPage == null) {
            valuationManagerPage = new ValuationManagerController(driver);
            valuationManagerPage.data = new LoadData(strParametersNValues);
            valuationManagerPage.softAssert = this.softAssert;
        }

        return valuationManagerPage;
    }

    public CRELoanPageController propertyPage() throws Exception {
        if (loanPage == null) {
            loanPage = new CRELoanPageController(driver);
            loanPage.data = new LoadData(strParametersNValues);
            loanPage.softAssert = this.softAssert;
        }
        return loanPage;
    }

    public AlertPageController alertPage() throws Exception {
        if (alertPage == null) {
            alertPage = new AlertPageController(driver);
            alertPage.data = new LoadData(strParametersNValues);
            alertPage.softAssert = this.softAssert;
        }
        return alertPage;
    }

    public CREsmallsearchnportfolioController smallSearchNportfolio() throws Exception {
        if (smallSearchNportfolio == null) {
            smallSearchNportfolio = new CREsmallsearchnportfolioController(driver);
        }
        smallSearchNportfolio.data = new LoadData(strParametersNValues);
        return smallSearchNportfolio;
    }

    public PortfolioLoanDetailPageController portfolioLoanDetailPage() throws Exception {
        if (portfolioLoanDetailPage == null) {
            portfolioLoanDetailPage = new PortfolioLoanDetailPageController(driver);
            portfolioLoanDetailPage.softAssert = this.softAssert;
            portfolioLoanDetailPage.data = new LoadData(strParametersNValues);
        }
        portfolioLoanDetailPage.data = new LoadData(strParametersNValues);
        return portfolioLoanDetailPage;
    }

    public StatsPageController statsPage() throws Exception {
        if (statsPage == null) {
            statsPage = new StatsPageController(driver);
            statsPage.softAssert = this.softAssert;
            statsPage.data = new LoadData(strParametersNValues);
        }

        return statsPage;
    }

    public StatsNewsPageController statsNewsPage() throws Exception {
        if (statsNewspage == null) {
            statsNewspage = new StatsNewsPageController(driver);
            statsNewspage.softAssert = this.softAssert;
            statsNewspage.data = new LoadData(strParametersNValues);
        }

        return statsNewspage;
    }

    public CompanyExposurePageController companyExposure() throws Exception {
        if (companyExposure == null) {
            companyExposure = new CompanyExposurePageController(driver);
            companyExposure.softAssert = this.softAssert;
            companyExposure.data = new LoadData(strParametersNValues);
        }

        return companyExposure;
    }

    public TreppWirePageController treppWirePage() throws Exception {
        if (treppWirePageController == null) {
            treppWirePageController = new TreppWirePageController(driver);
            treppWirePageController.softAssert = this.softAssert;
            treppWirePageController.data = new LoadData(strParametersNValues);
        }

        return treppWirePageController;
    }

    public TrendsPageController trendsPage() throws Exception {
        if (trendsPageController == null) {
            trendsPageController = new TrendsPageController(driver);
            trendsPageController.softAssert = this.softAssert;
            trendsPageController.data = new LoadData(strParametersNValues);
        }

        return trendsPageController;
    }

    public NewIssuanceController newIssuance() throws Exception {
        if (newIssuance == null) {
            newIssuance = new NewIssuanceController(driver);
            newIssuance.softAssert = this.softAssert;
            newIssuance.data = new LoadData(strParametersNValues);
        }

        return newIssuance;
    }

    public AdvanceSearchModalController advanceSearchModalController() throws Exception {
        if (advanceSearchModalController == null) {
            advanceSearchModalController = new AdvanceSearchModalController(driver);
            advanceSearchModalController.softAssert = this.softAssert;
            advanceSearchModalController.data = new LoadData(strParametersNValues);
        }
        return advanceSearchModalController;
    }

    public NewAdvanceSearchController newAdvanceSearch() throws Exception {
        if (newAdvanceSearchController == null) {
            newAdvanceSearchController = new NewAdvanceSearchController(driver);
            newAdvanceSearchController.softAssert = this.softAssert;
            newAdvanceSearchController.data = new LoadData(strParametersNValues);
        }
        return newAdvanceSearchController;
    }

    public NewStratsPageController newStratsPage() throws Exception {
        if (newStratsPage == null) {
            newStratsPage = new NewStratsPageController(driver);
            newStratsPage.softAssert = this.softAssert;
            newStratsPage.data = new LoadData(strParametersNValues);
        }
        return newStratsPage;
    }

    public PropertyDetailPageNewController propertyDetailNewPage() throws Exception {

        if (propertyDetailPageNewController == null) {
            propertyDetailPageNewController = new PropertyDetailPageNewController(driver);
            propertyDetailPageNewController.data = new LoadData(strParametersNValues);
            propertyDetailPageNewController.softAssert = this.softAssert;
        }
        return propertyDetailPageNewController;
    }
    
    public PropertySummaryPageController propertySummaryPageController() throws Exception {

        if (propertySummaryPageController == null) {
            propertySummaryPageController = new PropertySummaryPageController(driver);
            propertySummaryPageController.data = new LoadData(strParametersNValues);
            propertySummaryPageController.softAssert = this.softAssert;
        }
        return propertySummaryPageController;
    }
    

}
