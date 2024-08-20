package tek.tdd.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.tdd.utility.SeleniumUtility;

public class SignInPage extends SeleniumUtility {

    public SignInPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(name = "email")
    public WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(id = "loginBtn")
    public WebElement loginButton;

    @FindBy(className = "error")
    public WebElement errorMessage;

    @FindBy(linkText = "Create New Account")
    public WebElement createNewAccountLink;


    public void doSignIn(String email, String password) {
        sendText(emailInput, email);
        sendText(passwordInput, password);
        clickOnElement(loginButton);
    }
}
