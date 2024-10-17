package com.newdemo.steps;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;
import com.newdemo.framework.pages.AdvanceSearchModal;
import com.newdemo.framework.provider.StaticDataProvider;

@Test
public class SearchFunctionalityTest extends BaseSetupClass {
    List<String> properties = new ArrayList<String>();

    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {

        this.strDTParametersNValues = parameterNValue;


    }

    @Test(dataProvider = "suggestion-field-provider", dataProviderClass = StaticDataProvider.class, groups = {"sanity", "regression"})
    public void verify1AutocompleteOnFieldsWithSuggestions(AdvanceSearchModal.TabName tab, AdvanceSearchModal.FieldWithSuggestion field, String query) throws Exception {
        trepp().crehomepage().goToHome();
        trepp().crehomepage().clickAdvanced();
        trepp().advanceSearchModalController().selectTab(tab);
        if (tab.toString().equalsIgnoreCase("loan")) {
            trepp().advanceSearchModalController().expandTab();
        }
        trepp().advanceSearchModalController().selectFromSuggestion(field, query);
        trepp().softAssert.assertAll();
    }


}
