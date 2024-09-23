package tek.tdd.base;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.TokenRequest;

import java.util.HashMap;
import java.util.Map;

@Listeners({ExtentITestListenerClassAdapter.class})
public class ApiTestsBase extends BaseSetup {
    private static final Logger LOGGER = LogManager.getLogger(ApiTestsBase.class);

    public RequestSpecification getDefaultRequest() {
        LOGGER.info("Sending API call to {}" , RestAssured.baseURI);
        return RestAssured.given().contentType(ContentType.JSON);
    }


    public Map<String, String> getTokenRequestBody(String username, String password ) {
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        return body;
    }

    public RequestSpecification authenticatedRequest(TokenRequest request) {
        //Get Token
        ExtentTestManager.getTest().info(request.toString());
        String token = getDefaultRequest()
                .body(request)
                .when()
                .post(EndPoints.TOKEN.getValue())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getString("token");

        return  getDefaultRequest()
                .header("Authorization", "Bearer " + token);
    }


}