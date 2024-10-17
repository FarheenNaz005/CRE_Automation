package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class CRECommentaryFunctionality extends BaseSetupClass {

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Commentary Validation";

        this.strDTParametersNValues = parameterNValue;


    }

    @Test(groups = {"regression"})
    public void addComment() throws InterruptedException, Exception {

        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().selectCounty("Cascade");
        trepp().crehomepage().clickModalSearch();

        trepp().newListingPage().verifyMapViewnData();
        trepp().listingPage().clickList();
        trepp().listingPage().waitForLoading();
        trepp().listingPage().verifyDownloanAllButton();

        String propName = trepp().listingPage().clickFirstProperty();

        trepp().listingPage().verifySidePanel();
        trepp().propertyPage().summaryPage().clickOnSummryPage();
        trepp().listingPage().waitForLoading();
        trepp().propertyPage().summaryPage().verifyCommentaryTab();
        trepp().propertyPage().summaryPage().clickCommentaryTab();
        trepp().propertyPage().summaryPage().addComment("Test Comment");
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().selectCounty("Cascade");
        trepp().crehomepage().clickModalSearch();
        trepp().listingPage().clickList();
        trepp().listingPage().waitForLoading();
        trepp().newListingPage().removeAllCheckedColumn();
        trepp().newListingPage().verifyCountOfUnpinnedColumn(0);
        trepp().newListingPage().verifyFilterAndAddColumn("User Comments");
        trepp().newListingPage().verifyUserComment("Test Comment");
        trepp().listingPage().clickFirstProperty();
        trepp().listingPage().verifySidePanel();
        trepp().propertyPage().summaryPage().clickOnSummryPage();
        trepp().listingPage().waitForLoading();
        trepp().propertyPage().summaryPage().verifyCommentaryTab();
        trepp().propertyPage().summaryPage().clickCommentaryTab();
        trepp().propertyPage().summaryPage().verifyComment("Test Comment");
        trepp().softAssert.assertAll();


    }

    @Test(groups = {"regression"})
    public void verifyCommentryTab() throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().crehomepage().selectState("Montana");
        trepp().crehomepage().selectCounty("Cascade");
        trepp().crehomepage().clickModalSearch();

        trepp().newListingPage().verifyMapViewnData();
        trepp().listingPage().clickList();
        trepp().listingPage().waitForLoading();
        trepp().listingPage().verifyDownloanAllButton();
        String propName = trepp().listingPage().clickFirstProperty();
        boolean newpage = trepp().propertyPage().detailPageNew().switchToDetailv2();
        if (newpage) {
            trepp().propertyPage().detailPageNew().clickonmycomment();
            trepp().propertyPage().detailPageNew().verifyComment("Test Comment");

        }
    }


}
