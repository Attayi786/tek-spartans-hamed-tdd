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


}