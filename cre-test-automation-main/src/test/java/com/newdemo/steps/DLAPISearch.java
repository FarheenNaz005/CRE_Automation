package com.newdemo.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newdemo.framework.base.BaseAPI;
import com.newdemo.framework.base.Utilites;
import com.newdemo.framework.model.LoanuniverseModel;
import com.opencsv.CSVWriter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

public class DLAPISearch extends BaseAPI {
    String endpoint = "/loanuniverse/search";

    private static <T> Set<T> findDuplicates(List<T> list) {
        Set<T> seen = new HashSet<>();
        return list.stream()
                .filter(e -> !seen.add(e))
                .collect(Collectors.toSet());
    }


    @Test(description = "Search loans/properties based on provided fileters, also validate that excluded fields are not part of properties in response")
    public void searchWithFilters() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/searchWithFilters.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("total"))
                .body("$", hasKey("offset"))
                .body("$", hasKey("offsets"))
                .body("$", hasKey("data"))
                .body("data", hasSize(100))
                .body("data[0]", not(hasKey("allocbalpct")))
                .body("data[0]", not(hasKey("coupontype")));
    }

    @Test(description = "Validate that fuzzy match is working for property name, address and other fields")
    public void searchWithFuzzyMatch() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/searchWithFuzzyMatch.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data", hasSize(7));
    }

    @Test(description = "Validate sort by fieled in search")
    public void searchWithSort() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/searchWithSort.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data[0].datasource", equalTo("CMBS"));
    }

    @Test(description = "Validate search with specific fields")
    public void searchWithSpecificFields() throws IOException {
        String body = Utilites
                .readTextFileAndGetAsString(System.getProperty("user.dir") + "/Input/DatalakeAPI/searchWithSpecificFields.json");
        Response output = post(endpoint, null, body.toString());
        output.then().statusCode(200)
                .body("$", hasKey("data"))
                .body("data[0]", aMapWithSize(5))
                .body("data[0]", hasKey("propname"));
    }

    @Test(description = "Validate the JSON schema")
    public void loanuniverseSearchJsonSchemaValidation() throws IOException {
        String body = "{\r\n"
                + "    \"offset\": 1,\r\n"
                + "    \"limit\": \"1\",\r\n"
                + "    \"sort\": {\r\n"
                + "        \"pinid\": \"asc\"\r\n"
                + "    }\r\n"
                + "}";
        Response output = post(endpoint, null, body.toString());
        output.then().assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File(System.getProperty("user.dir") + "/Input/DatalakeAPI/SchemaValidation/loanuniverse_search_JsonSchema.json")));
    }

    @Test
    public void checkDuplicateLoanuniversepropid() throws IOException {
        List<String> idsList = new ArrayList<String>();
        for (int i = 1; i <= 150; i++) {
            LoanuniverseModel body = new LoanuniverseModel();
            body.setOffset(i);
            body.setLimit("100");
            // Converting a Java class object to a JSON payload as string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonModel = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            System.out.println(body);
            Response output = post(endpoint, null, jsonModel);
            output.then().statusCode(200);
            List<String> ids = output.jsonPath().getList("data.loanuniversepropid");
            idsList.addAll(ids);
            DLAPISearch dlapiSearch = new DLAPISearch();
        }
        Set<String> duplicates = DLAPISearch.findDuplicates(idsList);
        System.out.println("duplicates loanuniversepropid: " + duplicates);
        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[] {"Ids", "Dublicate_id"});
        for (String k : idsList) {
            data.add(new String[] {k});
            System.out.println(k);
        }
        File file = new File(".\\Input\\loanuniversepropid.csv");

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
