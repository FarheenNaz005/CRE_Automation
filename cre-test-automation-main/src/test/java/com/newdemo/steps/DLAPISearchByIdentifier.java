package com.newdemo.steps;

import com.newdemo.framework.base.BaseAPI;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

public class DLAPISearchByIdentifier extends BaseAPI {
    String endpoint = "/loanuniverse";

    @Test(description = "Validate search by LoanUniversePropID")
    public void searchWithLoanUniversePropID() throws IOException {
        String loanUniversePropId = "FD0DB2A52D8A78DA71D2D5150D8FA5B0";
        Response output = get(endpoint + "/" + loanUniversePropId, null);
        output.then().statusCode(200)
                .body("data[0].loanuniversepropid", equalTo(loanUniversePropId));
        //.body("data", hasSize(1));
    }

    @Test(description = "Validate the Loan search by identifier PropID JSON schema")
    public void loanuniverseSearchPropIdJsonSchemaValidation() throws IOException {
        String loanUniversePropId = "FD0DB2A52D8A78DA71D2D5150D8FA5B0";
        Response output = get(endpoint + "/" + loanUniversePropId, null);
        output.then().assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File(System.getProperty("user.dir") + "/Input/DatalakeAPI/SchemaValidation/loanuniverse_searchByIdentifierID.json")));
    }

    @Test(description = "Validate search by loanUniverseLoanId")
    public void searchWithLoanUniverseLoanID() throws IOException {
        String loanUniverseLoanId = "14B5949A05A28CA4708E1A623CF12AD2";
        Response output = get(endpoint + "/" + loanUniverseLoanId, null);
        output.then().statusCode(200)
                .body("data[0].loanuniverseloanid", equalTo(loanUniverseLoanId));
    }

    @Test(description = "Validate search by treppPropId")
    public void searchWithTreppPropID() throws IOException {
        String treppPropId = "457100001.P1636";
        Response output = get(endpoint + "/" + treppPropId, null);
        output.then().statusCode(200)
                .body("data[0].masterpropidtrepp", equalTo(treppPropId))
                .body("data", hasSize(1));
    }

    @Test(description = "Validate search by treppLoanId")
    public void searchWithTreppLoanID() throws IOException {
        String treppLoanId = "457100001";
        Response output = get(endpoint + "/" + treppLoanId, null);
        output.then().statusCode(200)
                .body("data[0].masterloanidtrepp", equalTo(Integer.parseInt(treppLoanId)));
    }

    @Test(description = "Validate search by multiple identifier")
    public void searchWithmultipleIdentifier() throws IOException {
        String id1 = "B9767D248154C56B9E3D7125555DD7AD";
        String id2 = "156F633FB209ED5BF6E67949A3BA5250";
        Response output = get(endpoint + "?identifiers=" + id1 + "&identifiers=" + id2, null);
        output.then().statusCode(200)
                .body("data", hasSize(2));
    }

    @Test(description = "Validate search by identifier with offset and limit")
    public void searchWithOffsetAndLimit() throws IOException {
        String treppLoanId = "457100001";
        int offset = 2;
        int limit = 10;
        Response output = get(endpoint + "/" + treppLoanId + "?offset=" + offset + "&limit=" + limit, null);
        output.then().statusCode(200)
                .body("data[0].masterloanidtrepp", equalTo(Integer.parseInt(treppLoanId)))
                .body("data", hasSize(10));
    }

    @Test(description = "Validate search by identifier with specific fields")
    public void searchWithSpecificFields() throws IOException {
        String treppLoanId = "457100001";
        int limit = 10;
        Response output = get(endpoint + "/" + treppLoanId + "?limit=" + limit + "&fields=address", null);
        output.then().statusCode(200)
                .body("data[0]", hasKey("address"))
                .body("data[0]", not(hasKey("masterloanidtrepp")));
    }

}



