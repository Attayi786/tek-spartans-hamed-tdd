package tek.tdd.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.tdd.utility.SeleniumUtility;

public class AccountProfilePage extends SeleniumUtility {

    public AccountProfilePage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(className = "account__information-email")
    public WebElement accountEmailInfo;

    @FindBy(name = "name")
    public WebElement nameInput;

    @FindBy(name = "phoneNumber")
    public WebElement phoneNumber;

    @FindBy(id = "personalUpdateBtn")
    public WebElement personalUpdateButton;

    @FindBy(className = "account__information-username")
    public WebElement accountUserNameText;


    public void updateNameAndPhone(String name, String phone) {
        sendText(nameInput, name);
        sendText(phoneNumber, phone);

        clickOnElement(personalUpdateButton);
    }

}