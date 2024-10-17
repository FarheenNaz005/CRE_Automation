package com.newdemo.steps;

import com.newdemo.framework.base.BaseAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.hamcrest.Matchers.hasKey;

public class DLAPIPropReport extends BaseAPI {


    @Test(description = "Validate CMBS")
    public void validateCmbsLatestSearch() throws IOException {
        String endpoint = "/propertyreports/cmbs/latest/search";
        String body = "{\r\n"
                + "    \"filters\": [\r\n"
                + "        {\r\n"
                + "            \"msaname\": \"Atlanta-Sandy Springs-Roswell, GA\"\r\n"
                + "        },\r\n"
                + "        {\r\n"
                + "            \"proptypenorm\": \"OT-Unclassified\"\r\n"
                + "        }\r\n"
                + "    ],\r\n"
                + "    \"offset\": 1,\r\n"
                + "    \"limit\": 10,\r\n"
                + "    \"sort\": {\r\n"
                + "       \"balpersqft\": \"asc\" \r\n"
                + "    },\r\n"
                + "    \"fields\": [\"address\", \"allocbalpct\",\"balloonbal\",\r\n"
                + "    \"balpersqftorunit\", \"city\", \"county\"]\r\n"
                + "}";
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("data[0]", hasKey("address"))
                .body("data[0]", hasKey("balpersqftorunit"))
                .body("data[1]", hasKey("city"))
                .body("data[2]", hasKey("county"))
                .body("data[3]", hasKey("balloonbal"))
                .body("data[3]", hasKey("allocbalpct"));

    }

    @Test(description = "Validate FANNIE")
    public void validateFANNIELatestSearch() throws IOException {
        String endpoint = "/properties/fannie/latest/search";
        String body = "{\r\n"
                + "    \"offset\" :1,\r\n"
                + "    \"limit\": 10\r\n"
                + "}";
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("data[0]", hasKey("noincfindicator"))
                .body("data[0]", hasKey("propertyvalueperunit"))
                .body("data[1]", hasKey("loanuniversepropid"))
                .body("data[2]", hasKey("physicaloccupancypct"))
                .body("data[3]", hasKey("loanuniverseloanid"))
                .body("data[3]", hasKey("loaddt"));
    }

    @Test(description = "Validate GINNIE")
    public void validateGINNIELatestSearch() throws IOException {
        String endpoint = "/properties/ginnie/latest/search";
        String body = "{\r\n"
                + "    \"offset\" :1,\r\n"
                + "    \"limit\": 10\r\n"
                + "}";
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("data[0]", hasKey("dl_timestamp"))
                .body("data[0]", hasKey("dltimestamp"))
                .body("data[1]", hasKey("loanuniversepropid"))
                .body("data[2]", hasKey("fhamasterid"))
                .body("data[3]", hasKey("fhanumber"))
                .body("data[3]", hasKey("propertyid"));
    }

    @Test(description = "Validate FHA")
    public void validateFHALatestSearch() throws IOException {
        String endpoint = "/properties/fha/latest/search";
        String body = "{\r\n"
                + "    \"offset\" :1,\r\n"
                + "    \"limit\": 10\r\n"
                + "}";
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("data[0]", hasKey("associated_financing_number"))
                .body("data[0]", hasKey("associatedfinancingnumber"))
                .body("data[1]", hasKey("contract_number"))
                .body("data[2]", hasKey("contractnumber"))
                .body("data[3]", hasKey("contract_rent_amount"))
                .body("data[3]", hasKey("contractrentamount"));
    }

}
