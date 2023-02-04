package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotYourPasswordPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "email_address")
    WebElement emailInput;
    @FindBy(how = How.CSS, using = "button[title='Submit']")
    WebElement submitButton;

    public ForgotYourPasswordPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void submitEmailAddress(String email) {
        emailInput.sendKeys(email);
        submitButton.submit();
    }
}
