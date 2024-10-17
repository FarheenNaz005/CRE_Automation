package com.newdemo.steps;


import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.Utilites;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

public class DLAPILoanReportCMBS extends BaseAPI {
    String endpoint = "/loanreports/cmbs/latest";
    String masterloanidtrepp;

    @Test(description = "Search latest CMBS loansreports based on provided fileters, also validate that excluded fields are not part of properties in response")

    public void search1WithFilters() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/LoanReport/CMBS/searchWithFilters.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(10))
                .body("data[0]", not(hasKey("allocbalpct")))
                .body("data[0]", not(hasKey("coupontype")));

        masterloanidtrepp = output.jsonPath().getString("$.data[0].masterloanidtrepp");
    }

    @Test(description = "Validate that fuzzy match is working for property name, address and other fields")
    public void searchWithFuzzyMatch() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/LoanReport/CMBS/searchWithFuzzyMatch.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data", hasSize(10))
                .body("data.propname", everyItem(containsStringIgnoringCase("The")))
                .body("data.address", everyItem(containsStringIgnoringCase("The")));
    }


    @Test(description = "Validate search with specif fields")
    public void search3WithSpecificFields() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/LoanReport/CMBS/searchWithSpecificFields.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data[0]", aMapWithSize(7))
                .body("data[0]", hasKey("loanid"))
                .body("data[0]", hasKey("dealid"))
                .body("data[0]", hasKey("poolnum"))
                .body("data[0]", hasKey("curloanbal"))
                .body("data[0]", hasKey("originator"))
                .body("data[0]", hasKey("year"));
    }

    @Test(description = "Validate Fetch latest loan report by master loan trepp id ")
    public void search4LoanReportWithMasterTrepLoanId() throws IOException {

        Response output = get(endpoint + "/" + masterloanidtrepp, null);
        output.then().statusCode(200)
                .body("$", hasKey("data"));
        //.body("data", hasSize(1))
        //.body("data[0].masterloanidtrepp", equalTo(Integer.parseInt(masterloanidtrepp)));
    }

    @Test(description = "Validate the loan Report CMBS JSON schema")
    public void cmbsLatestSearchJsonSchemaValidation() throws IOException {
        String body = "{\r\n"
                + "    \"filters\": [{\r\n"
                + "        \"originator\": \"BOA\"\r\n"
                + "    }],\r\n"
                + "    \"limit\": 1,\r\n"
                + "    \"offset\": 1,\r\n"
                + "    \"sort\": {\r\n"
                + "        \"masterloanidtrepp\": \"asc\"\r\n"
                + "    }\r\n"
                + "}";
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File(System.getProperty("user.dir") + "/Input/DatalakeAPI/SchemaValidation/cmbs_latest_search_jsonSchema.json")));
    }

}



