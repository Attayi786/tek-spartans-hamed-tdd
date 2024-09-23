package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.TokenRequest;
import tek.tdd.api.models.UserProfileResponse;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DatabaseUtility;

import java.util.List;
import java.util.Map;

public class UserProfileTest extends ApiTestsBase {

    @Test(dataProvider = "users")
    public void validateUserInfo(TokenRequest request) {
        DatabaseUtility databaseUtility = new DatabaseUtility();
        String query =
                "SELECT id, username, full_name, account_type FROM user where username = '{username}';"
                        .replace("{username}", request.getUsername());
        List<Map<String, Object>> expectedDataList = databaseUtility.getResultFromQuery(query);
        Assert.assertFalse(expectedDataList.isEmpty());
        Map<String, Object> expectedData = expectedDataList.get(0);
        Response response = authenticatedRequest(request)
                .when()
                .get(EndPoints.USER_PROFILE.getValue())
                .then()
                .statusCode(200)
                .extract()
                .response();

        ExtentTestManager.getTest().info(response.asPrettyString());

        UserProfileResponse userProfile = response.body().jsonPath()
                .getObject("", UserProfileResponse.class);

        Assert.assertEquals(userProfile.getUsername(), expectedData.get("username").toString());
        Assert.assertEquals(userProfile.getFullName(), expectedData.get("full_name").toString());
        Assert.assertEquals(userProfile.getAccountType().name(), expectedData.get("account_type").toString());
        Assert.assertEquals(userProfile.getId(), Integer.parseInt(expectedData.get("id").toString()));
    }

    @DataProvider
    private TokenRequest[] users() {
        return new TokenRequest[]{
                new TokenRequest("supervisor", "tek_supervisor"),
                new TokenRequest("operator_readonly", "Tek4u2024"),
                new TokenRequest("mohammad2536234", "mohammad2536234"),
        };

    }
}