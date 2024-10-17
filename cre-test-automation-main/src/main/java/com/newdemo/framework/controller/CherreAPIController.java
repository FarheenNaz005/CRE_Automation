package com.newdemo.framework.controller;

import java.util.HashMap;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.response.Response;

 public final class CherreAPIController {
    private static final  String ENDPOINT = "https://graphql.cherre.com";
    private static final  String STRING = "YXBpLWNsaWVudC1lODNlZGViYy04NmQ1LTRmNDktYTIwZS04NzZkZDM3ZWQxNDZAY2hlcnJlLmNvbTp3RzVsOXo3R0R3NGxrTFNDdzU3d09vUzZTVWgqdkFIQzVwJFdeSE1DJGlINjk2Q2VMbCo2dW1tUHFxajh2NTNp";
    
    
    private CherreAPIController() {
        throw new UnsupportedOperationException("");
    }
    
    
    private static Response post(String body) {

        HashMap<String, String> headers = new HashMap<String, String>() {
            {
                put("Content-Type", "application/json");
                put("Authorization", "Bearer " + STRING);
            }
        };

        return RestAssured.given().headers(headers).when().body(body).post(ENDPOINT + "/graphql");
    }

    public static String getOneLinerAddress(String address) {

        String query = "{\"query\":\"query ($address: String!) { address(address: $address) { one_line_address }}\",\"variables\":{\"address\":\"" + address + "\"}}";
        Response output = post(query);
        //output.prettyPrint();
        return output.jsonPath().getString("data.address.one_line_address");
    }

    public static JsonArray getCherreData(String onelineAddress) {
        String query = "{\"query\":\"query ($oneLineAddress: String!) {"
                + "\\r\\n        cherre_address(where: {display_address: {_eq: $oneLineAddress}}) {"
                + "\\r\\n         display_address\\r\\n         tax_assessor__property_address {"
                + "\\r\\n          address\\r\\n          tax_assessor_id\\r\\n          mailing_address\\r\\n          usa_owner_unmask__tax_assessor_id {\\r\\n           cherre_usa_owner_unmask_pk\\r\\n           tax_assessor_id\\r\\n           owner_name\\r\\n           mailing_one_line_address\\r\\n           has_confidence\\r\\n           last_seen_date\\r\\n            usa_owner_unmask_corporation__corp_id {\\r\\n             corp_id\\r\\n             industry\\r\\n             website\\r\\n             usa_owner_unmask_contact_info__owner_id {\\r\\n               phone_number_1\\r\\n               phone_number_2\\r\\n               phone_number_3\\r\\n               usa_owner_unmask_contact_info__employee_id {\\r\\n                employee_id\\r\\n                name\\r\\n                title\\r\\n              }\\r\\n             }\\r\\n           }\\r\\n           occurrences_count\\r\\n           owner_id\\r\\n           owner_name\\r\\n           owner_state\\r\\n           owner_type\\r\\n           tax_assessor_id\\r\\n          }\\r\\n         }\\r\\n        }\\r\\n       }\","
                + "\"variables\":{\"oneLineAddress\":\"" + onelineAddress + "\"}}";

        Response output = post(query);
        //output.prettyPrint();
        JsonObject rbody = new JsonParser().parse(output.asString()).getAsJsonObject();
        return rbody.getAsJsonObject("data").getAsJsonArray("cherre_address");
        //return  output.jsonPath().getList("data.cherre_address", JsonObject.class);
    }
}
