package com.newdemo.framework.base;

import static io.restassured.config.EncoderConfig.encoderConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {

    //Base URL for the APIs
    private static String baseURI;

    //common cookies for all the request
    private static Set<org.openqa.selenium.Cookie> cookies;

    //common cookies for all the request
    private static HashMap<String, String> headers = new HashMap<String, String>();

    @Parameters("BaseUrl")
    @BeforeSuite
    public void beforeSuite(String baseUrl) {
        setBaseURI(baseUrl);
        System.out.println("baseline envrinment: " + baseURI);
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }

    public static HashMap<String, String> getHeaders() {
        return headers;
    }

    public static void setHeaders(HashMap<String, String> headers) {
        BaseAPI.headers = headers;
    }

    public static void addHeaders(HashMap<String, String> headers) {
        BaseAPI.headers.putAll(headers);
    }

    public static Set<org.openqa.selenium.Cookie> getCookies() {
        return cookies;
    }

    public static void setCookies(Set<org.openqa.selenium.Cookie> cookies) {
        BaseAPI.cookies = cookies;
    }

    public static void addCookies(Set<org.openqa.selenium.Cookie> cookies) {
        BaseAPI.cookies.addAll(cookies);
    }

    public static String getBaseURI() {
        return baseURI;
    }

    public static void setBaseURI(String baseURI) {
        BaseAPI.baseURI = baseURI;
    }

    public static void configClient() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * convert Set<org.openqa.selenium.Cookie> to {@link Cookie}
     *
     * @param Set<org.openqa.selenium.Cookie> seleniumCookies
     * @return {@link Cookie}
     */
    public static Cookies convertSeleniumCookies(Set<org.openqa.selenium.Cookie> seleniumCookies) {
        List<Cookie> restAssuredCookies = new ArrayList<Cookie>();
        for (org.openqa.selenium.Cookie cookie : seleniumCookies) {
            restAssuredCookies.add(new io.restassured.http.Cookie.Builder(cookie.getName(), cookie.getValue()).build());
        }
        return new Cookies(restAssuredCookies);
    }

    /**
     * make a GET request to provided url
     *
     * @param String          url
     * @param HashMap<String, String> headers heades to be included in request. (send null in case of no header)
     * @return {@link Response}
     */
    public static Response get(String url, HashMap<String, String> headers) {
        Response res;
        System.out.println("Http GET --> " + baseURI + url);
        RequestSpecification requestSpec = RestAssured.given().baseUri(baseURI);
        if (headers != null) {
            addHeaders(headers);
        }
        requestSpec = requestSpec.headers(BaseAPI.headers);
        if (cookies != null) {
            requestSpec = requestSpec.cookies(convertSeleniumCookies(cookies));
        }
        res = requestSpec.when().get(url);
        res.then().statusCode(200);
        return res;
    }

    /**
     * make a POST request to provided url
     *
     * @param String          url
     * @param HashMap<String, String> headers heades to be included in request. (send null in case of no header)
     * @param String          body to be sent in post request.
     * @return {@link Response}
     */
    public static Response post(String url, HashMap<String, String> headers, String body) {
        Response res;
        System.out.println("Http POST --> " + baseURI + url);
        RequestSpecification requestSpec = RestAssured.given().baseUri(baseURI);
        if (headers != null) {
            addHeaders(headers);
        }
        requestSpec = requestSpec.headers(BaseAPI.headers);
        if (cookies != null) {
            requestSpec = requestSpec.cookies(convertSeleniumCookies(cookies));
        }
        res = requestSpec
                .contentType(ContentType.JSON)
                .body(body)
                .post(url);
        res.then().statusCode(200);
        return res;
    }

    /**
     * make a DELETE request to provided url
     *
     * @param String          url
     * @param HashMap<String, String> headers headers to be included in request. (send null in case of no header)
     * @return {@link Response}
     */
    public static Response delete(String url, HashMap<String, String> headers) {
        Response res;
        System.out.println("Http DELETE --> " + baseURI + url);
        RequestSpecification requestSpec = RestAssured.given().baseUri(baseURI);
        if (headers != null) {
            requestSpec = requestSpec.headers(headers);
        }
        if (cookies != null) {
            requestSpec = requestSpec.cookies(convertSeleniumCookies(cookies));
        }
        res = requestSpec.when().delete(url);
        res.then().statusCode(200);
        return res;
    }
}
