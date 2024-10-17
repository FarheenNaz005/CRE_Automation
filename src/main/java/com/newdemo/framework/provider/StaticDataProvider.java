package com.newdemo.framework.provider;

import org.testng.annotations.DataProvider;

import com.newdemo.framework.pages.AdvanceSearchModal.FieldWithSuggestion;
import com.newdemo.framework.pages.AdvanceSearchModal.TabName;

public class StaticDataProvider {

    @DataProvider(name = "source-data-provider")
    public Object[] sourceDataprovider() {
        return new Object[] {"CMBS", "Fannie Mae", "Freddie Mac", "FHA", "Ginnie Mae"};
    }

    @DataProvider(name = "suggestion-field-provider")
    public Object[][] suggestionFieldprovider() {
        return new Object[][]
                {
                        {TabName.general, FieldWithSuggestion.cityState, "New York"},
                        {TabName.general, FieldWithSuggestion.countyState, "New York"},
                        {TabName.general, FieldWithSuggestion.msa, "New York"},
                        {TabName.general, FieldWithSuggestion.zip, "10012"},
                        {TabName.general, FieldWithSuggestion.tenant, "Walmart"},
                        {TabName.general, FieldWithSuggestion.loanSpecialServicer, "Ares Commercial"},
                        {TabName.general, FieldWithSuggestion.submarket, "Detroit Metro Office"},
                        {TabName.loan, FieldWithSuggestion.originator, "Affiliates Mortgage"},
                        {TabName.loan, FieldWithSuggestion.bloombergName, "PAMT 2001-C7A"},
                        {TabName.loan, FieldWithSuggestion.poolnum, "100005982"},
                        {TabName.loan, FieldWithSuggestion.fhaNumber, "45612390"},
                        {TabName.loan, FieldWithSuggestion.loanUniversePropID, "12341383FD0F5C1CDC467E1CE30D8CBF"},
                };
    }

    @DataProvider(name = "nonSecuritizedProperty")
    public Object[][] populateNonSecuritizedPopertyAddress() {
        return new Object[][] {{"2436 Broadway", false}, {"1711 Coney Island Ave", true}}; //{ "36 E 56th St",true },
    }

}
