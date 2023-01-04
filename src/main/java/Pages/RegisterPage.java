package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class RegisterPage extends BasePage {

    private By firstNameInput = By.id("firstname");
    private By middleNameInput = By.id("middlename");
    private By lastNameInput = By.id("lastname");
    private By emailAddress = By.id("email_address");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmation");
    private By subscribeToNewsletterCheckbox = By.id("is_subscribed");
    private By registerButton = By.cssSelector("button[title='Register']");
    private By firstNameWarning = By.xpath("(//div[@class='input-box'])[2]");
    private By lastNameWarning = By.xpath("(//div[@class='input-box'])[4]");
    private By emailWarning = By.xpath("(//div[@class='input-box'])[5]");
    private By passwordWarning = By.xpath("(//div[@class='input-box'])[6]");
    private By confirmPasswordWarning = By.xpath("(//div[@class='input-box'])[7]");

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

    public String verifyFirstNameWarningMessage() {
        if (find(firstNameWarning).isDisplayed()) {
            return find(firstNameWarning).getText();
        }
        return "";
    }

    public String verifyLastNameWarningMessage() {
        if (find(lastNameWarning).isDisplayed()) {
            return find(lastNameWarning).getText();
        }
        return "";
    }

    public String verifyEmailWarningMessage() {
        if (find(emailWarning).isDisplayed()) {
            return find(emailWarning).getText();
        }
        return "";
    }

    public String verifyPasswordWarningMessage() {
        if (find(passwordWarning).isDisplayed()) {
            return find(passwordWarning).getText();
        }
        return "";
    }

    public String verifyConfirmPasswordWarningMessage() {
        if (find(confirmPasswordWarning).isDisplayed()) {
            return find(confirmPasswordWarning).getText();
        }
        return "";
    }

}
