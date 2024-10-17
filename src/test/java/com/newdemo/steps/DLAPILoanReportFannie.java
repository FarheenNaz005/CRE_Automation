package com.newdemo.steps;


import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.Utilites;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

public class DLAPILoanReportFannie extends BaseAPI {
    String endpoint = "/loans/fannie/latest";

    @Test(description = "Search latest Fannie Mae loansreports based on provided fileters, also validate that excluded fields are not part of properties in response")
    public void searchWithFiltersAnsFields() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/LoanReport/Fannie/searchWithFiltersAndFields.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data[0]", aMapWithSize(5))
                .body("data[0]", not(hasKey("allocbalpct")))
                .body("data[0]", not(hasKey("coupontype")));
    }

    @Test(description = "Validate that pagination and sort is working as expected.")
    public void searchWithPaginationAndSort() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/LoanReport/Fannie/searchWithPaginationAndSort.json");
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("data", hasSize(10))
                .body("data.propertyvalue", everyItem(greaterThan(799999)))
                .body("data[0].propertyvalue", equalTo(800000));
    }

    @Test(description = "Validate the search latest Fannie Mae loansreports JSON Schema")
    public void loanReportFannieJsonSchemaValidation() throws IOException {
        String body = "{\r\n"
                + "    \"filters\": [\r\n"
                + "        {\r\n"
                + "            \"loanuniverseloanid\": \"EF6637981294DBED46BEA0471AE61300\",\r\n"
                + "            \"not_equals\": false\r\n"
                + "        }\r\n"
                + "    ]\r\n"
                + "}";
        Response output = post(endpoint + "/search", null, body.toString());
        output.then().assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File(System.getProperty("user.dir") + "/Input/DatalakeAPI/SchemaValidation/loanReportFannie_JsonSchema.json")));

    }
}



