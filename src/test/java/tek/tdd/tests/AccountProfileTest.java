package tek.tdd.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;

public class AccountProfileTest extends UIBaseClass {

    /* Activity
    Story 5: navigate to retail app and login with your credential
    Navigate to account page and update Name and phone number.
    Validate your phone number and Name Updated.
    Validate Success toast message displayed.
     */
    @Test
    public void updatePersonalInfoTest() throws InterruptedException {
        validCredentialSignIn();

        clickOnElement(homePage.accountLink);

        accountProfilePage.updateNameAndPhone("John" , "1425361425");

        boolean isToastDisplayed = isElementDisplayed(homePage.toastBody);
        Assert.assertTrue(isToastDisplayed, "Toast Should display");

        String actualUserName = getElementText(accountProfilePage.accountUserNameText);
        Assert.assertEquals(actualUserName, "John");
        Thread.sleep(6000);

        //Reset to original name and phone number
        accountProfilePage.updateNameAndPhone("Mohammad" , "2023233635");
        Thread.sleep(1000);
        String actualUserNameReset = getElementText(accountProfilePage.accountUserNameText);
        Assert.assertEquals(actualUserNameReset, "Mohammad");
    }

}