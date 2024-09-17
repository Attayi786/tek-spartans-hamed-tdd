package tek.tdd.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tek.tdd.base.UIBaseClass;
import tek.tdd.utility.DataGenerator;

import java.util.Arrays;
import java.util.List;

public class CreateAccountTests extends UIBaseClass {

    @Test
    public void createNewAccountTestPositive() {
        clickOnElement(homePage.signInLink);
        clickOnElement(signInPage.createNewAccountLink);

        String expectedEmail = DataGenerator.generateRandomEmail("Mohammad");

        signUpPage.fillUpCreateAccountForm("Mohammad",
                expectedEmail,
                "Password@123");

        String actualEmail = getElementText(accountProfilePage.accountEmailInfo);

        Assert.assertEquals(actualEmail, expectedEmail,
                "Profile Page should have same email as Created");
    }

    /*
    Story 4.1 (Activity 15 Minute)
    Navigate to Create Account page and Create new Account
    With existing email and validate error message "this email is already exist, please use another email address".
     */
    @Test
    public void createNewAccountWithExistingEmail() {
        clickOnElement(homePage.signInLink);
        clickOnElement(signInPage.createNewAccountLink);

        signUpPage.fillUpCreateAccountForm("Mohammad", "mohammad2536@gmail.com", "Password!321");

        String actualError = getElementText(signUpPage.signUpError);

        Assert.assertEquals(actualError, "this email is already exist, please use another email address",
                "Error Message for Existing Email should match");

    }

    /*
    Activity: Activity
        Story# 4.2
        Navigate to Create new Account page and click sign up button without filling the form
        Validate all errors on all fields.
     */
    @Test
    public void validateFieldErrors1() {
        clickOnElement(homePage.signInLink);
        clickOnElement(signInPage.createNewAccountLink);
        clickOnElement(signUpPage.signUpButton);

        String actualNameError = getElementText(signUpPage.nameError);
        Assert.assertEquals(actualNameError, "Name is a required field");

        String actualEmailError = getElementText(signUpPage.emailError);
        Assert.assertEquals(actualEmailError, "Email is a required field");

        String actualPasswordError = getElementText(signUpPage.passwordError);
        Assert.assertEquals(actualPasswordError, "Password is a required field");

        String actualConfirmError = getElementText(signUpPage.confirmPasswordError);
        Assert.assertEquals(actualConfirmError, "Confirm Password is a required field");
    }

    @Test
    public void validateFieldsError2() {
        clickOnElement(homePage.signInLink);
        clickOnElement(signInPage.createNewAccountLink);
        clickOnElement(signUpPage.signUpButton);
        // Create a list of Expected Errors,
        //Get List of All Error Elements
        //Loop through and validate

        SoftAssert softAssert = new SoftAssert();

        List<String> expectedError = Arrays.asList(
                "Name is a required field",
                "Email is a required field" ,
                "Password is a required field" ,
                "Confirm Password is a required field");

        List< WebElement> actualErrorElements = signUpPage.fieldErrors;

        softAssert.assertEquals(actualErrorElements.size(), expectedError.size());

        for (int i = 0; i < expectedError.size(); i++) {
            softAssert.assertEquals(
                    getElementText(actualErrorElements.get(i)),
                    expectedError.get(i)
            );
        }

        softAssert.assertAll();
    }
}