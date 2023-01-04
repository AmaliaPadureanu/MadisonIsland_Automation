package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotYourPasswordPage extends BasePage {

    @FindBy(how = How.ID, using = "email_address")
    @CacheLookup
    WebElement emailInput;

    @FindBy(how = How.CSS, using = "button[title='Submit']")
    @CacheLookup
    WebElement submitButton;

    public ForgotYourPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void submitEmailAddress(String email) {
        emailInput.sendKeys(email);
        submitButton.submit();
    }
}
