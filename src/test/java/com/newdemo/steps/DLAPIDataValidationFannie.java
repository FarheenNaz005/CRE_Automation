package com.newdemo.steps;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

import java.io.IOException;

import org.testng.annotations.Test;

import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.Utilites;

import io.restassured.response.Response;

public class DLAPIDataValidationFannie extends BaseAPI {

    String endpoint = "/properties/fannie/latest/search";
    String endpoint1 = "/loans/fannie/latest/search";
    String endpoint2 = "/loanlinks/fannie/latest/search";
    String endpoint3 = "/transactions/fannie/latest/search";

    @Test(description = "Search Fannie properties with offset & limit")
    public void searchFanniePropLatest() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/FannieRequestBody/FanniePropLatestSearch.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("match"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(10))
                .body("data[0]", hasKey("fanniepropertyid"))
                .body("data[0]", hasKey("propertyvalueperunit"));

    }

    @Test(description = "Search Fannie properties based on provided filters and validate the data")
    public void searchFanniePropWithFilter() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "\\Input\\DatalakeAPI\\FannieRequestBody\\fanniePropSearchWithFiltersAndFields.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("match"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(1))
                .body("data[0]", hasKey("fanniepropertyid"))
                .body("data[0]", hasKey("loaddt"))
                .body("data[0]", not(hasKey("propertyvalueperunit")))
                .body("data[0]", not(hasKey("noincfindicator")));

    }

    @Test(description = "Search Fannie loans with offset & limit")
    public void searchFannieLoanLatest() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/FannieRequestBody/FanniePropLatestSearch.json");
        Response output = post(endpoint1, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("match"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(10))
                .body("data[0]", hasKey("propertyvalue"))
                .body("data[0]", hasKey("loanuniverseloanid"))
                .body("data[0]", hasKey("fannieloanid"));
    }

    @Test(description = "Search Fannie loanlink with offset & limit")
    public void searchFannieLoanlinkLatest() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/FannieRequestBody/FanniePropLatestSearch.json");
        Response output = post(endpoint2, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("match"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(10))
                .body("data[0]", hasKey("bloombergname"))
                .body("data[0]", hasKey("loanlinksfannieid"))
                .body("data[0]", hasKey("loaddt"));
    }

    @Test(description = "Search Fannie loanlink based on provided filters and validate the data")
    public void searchFannieLoanlinkWithFilter() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "\\Input\\DatalakeAPI\\FannieRequestBody\\fannieLoanlinkSearchWithFilter.json");
        Response output = post(endpoint2, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("match"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(1))
                .body("data[0]", hasKey("loanlinksfannieid"))
                .body("data[0]", hasKey("loaddt"))
                .body("data[0]", not(hasKey("propertyvalueperunit")))
                .body("data[0]", not(hasKey("noincfindicator")));
    }

    @Test(description = "Search Fannie transactions with offset & limit")
    public void searchFannieTransactionLatest() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/FannieRequestBody/FanniePropLatestSearch.json");
        Response output = post(endpoint3, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("match"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(10))
                .body("data[0]", hasKey("loaddt"))
                .body("data[0]", hasKey("trustee"))
                .body("data[0]", hasKey("securitystatus"));
    }

    @Test(description = "Search Fannie transactions based on provided filters and validate the data")
    public void searchFannieTransactionWithFilter() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "\\Input\\DatalakeAPI\\FannieRequestBody\\fannieTransactionSearchWithFilter.json");
        Response output = post(endpoint3, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("match"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(1))
                .body("data[0]", hasKey("trustee"))
                .body("data[0]", hasKey("recorddt"))
                .body("data[0]", not(hasKey("resecuritization")))
                .body("data[0]", not(hasKey("cusip")));
    }


}
