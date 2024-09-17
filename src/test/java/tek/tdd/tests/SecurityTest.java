package tek.tdd.tests;

import com.aventstack.extentreports.service.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;

public class SecurityTest extends UIBaseClass {

    /*
    User Story 1
    Navigate to sign in page and sing in with valid username and password.
    Validate user successfully signed in.
     */
    @Test
    public void validateSingIn() {
        clickOnElement(homePage.signInLink);

        ExtentTestManager.getTest()
                .info("Sign In with credential");

        signInPage.doSignIn("mohammad2536@gmail.com", "Password@123");
        boolean isDisplayed = isElementDisplayed(homePage.accountLink);

        Assert.assertTrue(isDisplayed, "Looking for account Link to be displayed after login");
    }

    /*
    Story 2: Navigate to sign in page and sign in with Invalid username and valid password.
    Validate error message displays "wrong username or password"
    Story 3: Navigate to sign in page and sign in with valid user and invalid password
    Validate error message display  "wrong username or password"
    push to your gitHub account
     */
    @Test(dataProvider = "InvalidTestData")
    public void negativeSignInTests(String email, String password) {
        clickOnElement(homePage.signInLink);
        signInPage.doSignIn(email, password);

        String actualErrorMessage = getElementText(signInPage.errorMessage);
        Assert.assertEquals(actualErrorMessage, "wrong username or password", "Error Message should match");
    }

    @DataProvider(name = "InvalidTestData")
    private String[][] invalidTestData() {
        return new String[][]{
                {"NoAVALIDEmail@email.com" , "Password@123"},
                {"Nomail@gmail.com", "WrongPassword"},
                {"mohammad2536@gmail.com" , "WrongPassword"},
        };
    }
}