package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.AccountResponse;
import tek.tdd.api.models.EndPoints;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DatabaseUtility;

import java.sql.*;

public class GetPrimaryAccountTest extends ApiTestsBase {

    @Test
    public void getAccountAndValidate() {
        RequestSpecification requestSpecification = getDefaultRequest();

        requestSpecification.queryParam("primaryPersonId", 10035);

        Response response = requestSpecification.when().get(EndPoints.GET_PRIMARY_ACCOUNT.getValue());
        ExtentTestManager.getTest().info(response.getHeaders().toString());
        response.then().statusCode(200);

        response.prettyPrint();
        String actualEmail = response.jsonPath().getString("email");

        ExtentTestManager.getTest().info(response.asPrettyString());

        Assert.assertEquals(actualEmail, "jawid776@gmail.com");

    }

    //Activity Sending request to get-primary-account with Id does not exist,
    //Validate Error Message
    @Test
    public void validateGetAccountNotExist() {
        Response response = getDefaultRequest()
                .queryParam("primaryPersonId", 252525)
                .when()
                .get(EndPoints.GET_PRIMARY_ACCOUNT.getValue())
                .then()
                .statusCode(404)
                .extract()
                .response();

        ExtentTestManager.getTest().info(response.asPrettyString());

        String errorMessage = response.body().jsonPath().getString("errorMessage");
        Assert.assertEquals(errorMessage, "Account with id 252525 not exist");
    }


    //Retrieve Latest primary Person from database  call API /get-primary-account
    //Validate Api response with database response
    @Test
    public void getAccountWithDatabaseValidation() throws SQLException {
        String query = "select id, email, first_name from primary_person order by id desc limit 1;";
        DatabaseUtility dbUtility = new DatabaseUtility();

        ResultSet resultSet = dbUtility.executeQuery(query);
        resultSet.next();
        int accountId = resultSet.getInt("id");
        String expectedEmail = resultSet.getString("email");
        String expectedFirstName = resultSet.getString("first_name");

        Response response = getDefaultRequest()
                .queryParam("primaryPersonId", accountId)
                .when()
                .get(EndPoints.GET_PRIMARY_ACCOUNT.getValue())
                .then()
                .statusCode(200)
                .extract()
                .response();
        ExtentTestManager.getTest().info(response.asPrettyString());
        AccountResponse accountResponse = response.body().jsonPath()
                .getObject("", AccountResponse.class);

        Assert.assertEquals(accountResponse.getId(), accountId);
        Assert.assertEquals(accountResponse.getEmail(), expectedEmail);
        Assert.assertEquals(accountResponse.getFirstName(), expectedFirstName);
    }
}