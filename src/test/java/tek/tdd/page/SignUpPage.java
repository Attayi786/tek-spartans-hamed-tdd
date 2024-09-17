package tek.tdd.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.tdd.utility.SeleniumUtility;

import java.util.List;

public class SignUpPage extends SeleniumUtility {

    public SignUpPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(name = "name")
    public WebElement nameInput;

    @FindBy(name = "email")
    public WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(name = "confirmPassword")
    public WebElement confirmPasswordInput;

    @FindBy(id = "signupBtn")
    public WebElement signUpButton;

    @FindBy(className = "error")
    public WebElement signUpError;

    @FindBy(id = "nameError")
    public WebElement nameError;

    @FindBy(id = "emailError")
    public WebElement emailError;

    @FindBy(id = "passwordError")
    public WebElement passwordError;

    @FindBy(id = "confirmPasswordError")
    public WebElement confirmPasswordError;

    @FindBy(className = "error")
    public List<WebElement> fieldErrors;


    public void fillUpCreateAccountForm(String name, String email, String password) {
        sendText(nameInput, name);
        sendText(emailInput, email);
        sendText(passwordInput, password);
        sendText(confirmPasswordInput, password);

        clickOnElement(signUpButton);
    }
}
