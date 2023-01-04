package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private By firstName = By.id("firstname");
    private By middleName = By.id("middlename");
    private By lastName = By.id("lastname");
    private By emailAddress = By.id("email_address");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmation");
    private By subscribeToNewsletterCheckbox = By.id("is_subscribed");
    private By registerButton = By.cssSelector("button[title='Register']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
}
