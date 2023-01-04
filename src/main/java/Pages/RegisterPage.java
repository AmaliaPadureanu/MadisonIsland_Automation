package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private By firstNameInput = By.id("firstname");
    private By middleNameInput = By.id("middlename");
    private By lastNameInput = By.id("lastname");
    private By emailAddress = By.id("email_address");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmation");
    private By subscribeToNewsletterCheckbox = By.id("is_subscribed");
    private By registerButton = By.cssSelector("button[title='Register']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public AccountDashboardPage registerUser(String firstName, String middleName, String lastName, String email, String pass,
                                     String confirmPass, Boolean subscribeToNewsletter) {
        find(firstNameInput).sendKeys(firstName);
        find(middleNameInput).sendKeys(middleName);
        find(lastNameInput).sendKeys(lastName);
        find(emailAddress).sendKeys(email);
        find(password).sendKeys(pass);
        find(confirmPassword).sendKeys(confirmPass);
        if (subscribeToNewsletter) {
            find(subscribeToNewsletterCheckbox).click();
        }
        find(registerButton).click();
        return new AccountDashboardPage(driver);
    }
}
