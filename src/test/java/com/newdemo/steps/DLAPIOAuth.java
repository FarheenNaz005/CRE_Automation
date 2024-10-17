package com.newdemo.steps;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newdemo.framework.base.BaseAPI;

import io.restassured.response.Response;

public class DLAPIOAuth extends BaseAPI {
    String endpoint = "/oauth/token";
    String username = "yogendra@binmile.com";
    String password = "Trepp@0123";

    @Parameters("BaseUrl")
    @BeforeClass
    public void beforeClass(String baseUrl) {
        setBaseURI(baseUrl);
    }

    @Test(groups = {"DL.oauth"})
    public void oAuthGetToken() {
        String body = "{"
                + "\"username\": \"" + username + "\",\r\n"
                + "    \"password\": \"" + password + "\"}";

        Response output = post(endpoint, null, body);

        output.then().statusCode(200)
                .body("$", Matchers.hasKey("access_token"));
        String bearerToken = output.body().path("access_token");
        System.out.println(bearerToken);
        addHeaders(new HashMap<String, String>() {{
            put("Authorization", "Bearer " + bearerToken);
        }});
    }

}
