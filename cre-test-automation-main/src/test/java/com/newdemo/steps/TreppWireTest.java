package com.newdemo.steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.newdemo.framework.base.BaseSetupClass;

@Test
public class TreppWireTest extends BaseSetupClass {
    @Parameters("ParameterNValue")
    @BeforeClass
    public void beforeClass(String parameterNValue) throws Exception {
        String strDescription = "CRE application Stats | Trepp Wire Validation";
        this.strDTParametersNValues = parameterNValue;
    }

    @Test(groups = {"regression"})
    public void verify1TreppWireTab() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyTreppWireLink();
        trepp().statsPage().clickTreppWireLink();

    }

    @Test(groups = {"regression"})
    public void verify2Commentary() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyTreppWireLink();
        trepp().statsPage().clickTreppWireLink();
        trepp().treppWirePage().verifyCommentaryTab();

    }

    @Test(groups = {"regression"})
    public void verify3SendMail() throws Exception {
        trepp().treppWirePage().verifySendEmail();

    }

    @Test(groups = {"regression"})
    public void verify4Archieve() throws Exception {
        trepp().treppWirePage().verifyArchieve();

    }


    @Test(groups = {"regression"})
    public void verify5Spotlight() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyTreppWireLink();
        trepp().statsPage().clickTreppWireLink();
        trepp().treppWirePage().openSpotlightTab();
        trepp().treppWirePage().selectTopicAndClickOnDisplay();

    }

    @Test(groups = {"regression"})
    public void verify6Statistics() throws Exception {
        trepp().crehomepage().clickStats();
        trepp().statsPage().verifyTreppWireLink();
        trepp().statsPage().clickTreppWireLink();
        trepp().treppWirePage().openStatisticsTab();
        trepp().treppWirePage().verifyStatisticsTables();
        trepp().treppWirePage().verifyDownload("treppwire_spotlight.csv");
        if (BaseSetupClass.executionEnv.equalsIgnoreCase("local")) {
            trepp().newIssuance().deleteFile(BaseSetupClass.downloadFilepath, "*");
        }
        trepp().softAssert.assertAll();
        trepp().treppWirePage().verifyPrint();


    }

}
