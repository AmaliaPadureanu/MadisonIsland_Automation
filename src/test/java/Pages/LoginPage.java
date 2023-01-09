package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    WebDriverWait wait;

    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "pass")
    private WebElement passwordInput;
    @FindBy(id = "send2")
    private WebElement loginButton;
    @FindBy(xpath = "(//div[@class='input-box'])[2]")
    private WebElement emailError;
    @FindBy(xpath = "(//div[@class='input-box'])[3]")
    private WebElement passwordError;
    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/ul/li/ul/li")
    private WebElement invalidEmailOrPasswordError;
    @FindBy(xpath = "//*[@id=\"login-form\"]/div/div[2]/div[1]/ul/li[3]/a")
    private WebElement forgotPasswordLink;
    @FindBy(css = "body div div div div div div ul li ul li span")
    private WebElement resetPasswordMessage;
    @FindBy(css = "#header > div > div.skip-links > div > a")
    private WebElement accountLink;
    @FindBy(css = "a[title='Log Out']")
    private WebElement logOutButton;
    @FindBy(css = "body > div > div.page > div.main-container.col1-layout > div > div > p")
    private WebElement logoutMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public MyAccountPage loginWith(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new MyAccountPage(driver);
    }

    public String verifyEmailMessageFromPopup() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return arguments[0].validationMessage", emailInput).toString();
    }

    public void forgotYourPassword() {
        forgotPasswordLink.click();
    }

    public String getResetPasswordMessage() {
        return resetPasswordMessage.getText();
    }

    public void logout() {
        accountLink.click();
        logOutButton.click();
    }

    public String getLogoutMessage() {
        return logoutMessage.getText();
    }

    public boolean checkError(String expectedError, String errorType) {
        if(errorType.equalsIgnoreCase("userError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(emailError));
                return expectedError.equalsIgnoreCase(emailError.getText());
            } else return true;
        } else if (errorType.equalsIgnoreCase("passwordError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(passwordError));
                return expectedError.equalsIgnoreCase(passwordError.getText());
            } else return true;
        } else if (errorType.equalsIgnoreCase("invalidUserOrPasswordError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(invalidEmailOrPasswordError));
                return expectedError.equalsIgnoreCase(invalidEmailOrPasswordError.getText());
            }
            else return true;
        } else if (errorType.equalsIgnoreCase("invalidUserOrPasswordErrorPopup")) {
            if (expectedError.length() > 0) {
                return verifyEmailMessageFromPopup().contains(expectedError);
            }
            else return true;
        }
        return false;
    }
}
