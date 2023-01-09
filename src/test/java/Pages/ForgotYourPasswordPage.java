package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotYourPasswordPage extends BasePage {

    @FindBy(how = How.ID, using = "email_address")
    WebElement emailInput;

    @FindBy(how = How.CSS, using = "button[title='Submit']")
    WebElement submitButton;

    public ForgotYourPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void submitEmailAddress(String email) {
        emailInput.sendKeys(email);
        submitButton.submit();
    }
}
