package com.newdemo.steps;

import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.Utilites;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;

public class DLAPIDistinct extends BaseAPI {
    String endpoint = "/loanuniverse/distinct";

    @Test(description = "Validate distinct fileds are returned")
    public void distinctWithFields() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/distinctWithFields.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("data[0]", hasKey("datasource"))
                .body("data[0]", hasKey("total_count"))
                .body("data[1]", hasKey("masterpropidtrepp"))
                .body("data[2]", hasKey("city"))
                .body("data[3]", hasKey("curdscr"));
    }

    @Test(description = "Validate distinct fileds are returned only for the search filter")
    public void distinctWithFilters() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/distinctWithFieldsAndFilters.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("data[0]", hasKey("datasource"))
                .body("data[0].total_count", equalTo(2))
                .body("data[2].city", hasSize(10));
    }

    @Test(description = "Validate distinct fileds with options, limit and sort")
    public void distinctWithFieldsOptions() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/distinctWithFieldsOptions.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("data[1]", hasKey("masterpropidtrepp"))
                .body("data[1].masterpropidtrepp", hasSize(200));
    }

    @Test(description = "Validate the JSON schema for distinct fields")
    public void loanuniverseSearchJsonSchemaValidation() throws IOException {
        String body = "{\r\n"
                + "    \"fields\": [{\r\n"
                + "        \"field_name\": \"datasource\"\r\n"
                + "    }, {\r\n"
                + "        \"field_name\": \"masterpropidtrepp\"\r\n"
                + "    }, {\r\n"
                + "        \"field_name\": \"city\"\r\n"
                + "    }, {\r\n"
                + "        \"field_name\": \"curdscr\"\r\n"
                + "    }]\r\n"
                + "}";
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200).assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File(System.getProperty("user.dir") + "/Input/DatalakeAPI/SchemaValidation/distinct_search_json_schema.json")));
    }
}



