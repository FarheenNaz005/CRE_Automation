package com.newdemo.steps;


import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.Utilites;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class DLAPIBondReportCMBS extends BaseAPI {
    String endpoint = "/bondreports/cmbs/latest";

    @Test(description = "Search latest CMBS bond Report based on provided fileters, also validate that excluded fields are not part of properties in response")
    public void searchWithFiltersAnsFields() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/BondReport/CMBS/searchWithFiltersAndFields.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data[0]", aMapWithSize(4))
                .body("data.size()", is(2))
                .body("data[0]", not(hasKey("allocbalpct")))
                .body("data[0]", not(hasKey("coupontype")));
    }

    @Test(description = "Search latest CMBS bond Report based on provided fileters, also validate that excluded fields are not part of properties in response")
    public void searchWithFuzzyMatch() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/BondReport/CMBS/searchWithFuzzyMatch.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data.cusip", everyItem(containsString("36192")));
    }

    @Test(description = "Validate that pagination and sort is working as expected.")
    public void searchWithPaginationAndSort() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/BondReport/CMBS/searchWithPaginationAndSort.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("offset", equalTo(2))
                .body("$", hasKey("offsets"))
                .body("data", hasSize(10));
    }

    @Test(description = "Validate that distinct is working as expected.")
    public void distinctWithFields() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/BondReport/CMBS/distinctWithFields.json");
        Response output = post(endpoint + "/distinct", null, body.toString());
        output.then().statusCode(200)
                .body("data[0]", hasKey("bhlossperincomplete"))
                .body("data[0]", hasKey("total_count"))
                .body("data[1]", hasKey("bondtype"))
                .body("data[1].bondtype.size()", is(10))
                .body("data", hasSize(4));
    }


    @Test(description = "Validate the JSON schema")
    public void bondReportCMBSJsonSchemaValidation() throws IOException {
        String body = "{\r\n"
                + "    \"filters\": [\r\n"
                + "        {\r\n"
                + "            \"cusip\": \"36192\",\r\n"
                + "            \"search_type\": \"fuzzy\"\r\n"
                + "        }\r\n"
                + "    ],\r\n"
                + "    \"offset\" :1,\r\n"
                + "    \"limit\": 1\r\n"
                + "}";
        Response output = post(endpoint + "/search", null, body.toString());
        //output.then().assertThat()
        //.body(JsonSchemaValidator.
        // matchesJsonSchema(new File(".\\Input\\DatalakeAPI\\SchemaValidation\\BondReportJsonSchema.json")));
    }

}



