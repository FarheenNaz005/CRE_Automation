package com.newdemo.steps;


import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.Utilites;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class DLAPIDealReportCMBS extends BaseAPI {
    String endpoint = "/dealreports/cmbs/latest";

    @Test(description = "Search latest CMBS Deal Report based on provided fileters, also validate that excluded fields are not part of properties in response")
    public void searchWithFiltersAnsFields() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/DealReport/CMBS/searchWithFiltersAndFields.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data[0]", aMapWithSize(6))
                .body("data.underwriter", everyItem(equalToIgnoringCase("JP Morgan")))
                .body("data[0]", not(hasKey("allocbalpct")))
                .body("data[0]", not(hasKey("coupontype")));
    }

    @Test(description = "Search latest CMBS Deal Report based on provided fileters, also validate that excluded fields are not part of properties in response")
    public void searchWithFuzzyMatch() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/DealReport/CMBS/searchWithFuzzyMatch.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data.bloombergname", everyItem(containsString("FHMS")));
    }

    @Test(description = "Validate that pagination and sort is working as expected.")
    public void searchWithPaginationAndSort() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/DealReport/CMBS/searchWithPaginationAndSort.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("data", hasSize(100));
    }

    @Test(description = "Validate that distinct is working as expected.")
    public void distinctWithFields() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/DealReport/CMBS/distinctWithFields.json");
        Response output = post(endpoint + "/distinct", null, body.toString());
        output.then().statusCode(200)
                .body("data[0]", hasKey("underwriter"))
                .body("data[0]", hasKey("total_count"))
                .body("data[1]", hasKey("dealcategory"))
                .body("data[1].dealcategory.size()", is(4))
                .body("data", hasSize(4));
    }


    @Test(description = "Validate the CMBS deal report JSON schema")
    public void cmbsDealReportJsonSchemaValidation() throws IOException {
        String body = "{\r\n"
                + "   \"filters\": [{\r\n"
                + "       \"underwriter\": \"JP Morgan\"\r\n"
                + "   }],\r\n"
                + "   \"offset\": 2,\r\n"
                + "   \"limit\": 1,\r\n"
                + "   \"sort\": {\"curdistribdt\": \"desc\"},\r\n"
                + "   \"fields\": [\"dealname\", \"bloombergname\", \"underwriter\", \"businessdaytype\",\r\n"
                + "   \"dealcategory\", \"staticpool\"]\r\n"
                + "}";
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File(System.getProperty("user.dir") + "/Input/DatalakeAPI/SchemaValidation/CMBSdealReportJsonSchema.json")));
    }

}



