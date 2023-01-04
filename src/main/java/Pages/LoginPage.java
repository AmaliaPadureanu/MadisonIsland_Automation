package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private By emailInput = By.id("email");
    private By passwordInput = By.id("pass");
    private By loginButton = By.id("send2");
    private By emailWarning = By.xpath("(//div[@class='input-box'])[2]");
    private By passwordWarning = By.xpath("(//div[@class='input-box'])[3]");
    private By invalidEmailOrPassError = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/ul/li/ul/li");
    private By forgotPasswordLink = By.xpath("//a[normalize-space()='Forgot Your Password?']");
    private By resetPasswordMessage = By.cssSelector("body div div div div div div ul li ul li span");
    private By accountLink = By.cssSelector("#header > div > div.skip-links > div > a");
    private By logOutButton = By.cssSelector("a[title='Log Out']");
    private By logoutMessage = By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > p");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage loginWith(String email, String password) {
        find(emailInput).sendKeys(email);
        find(passwordInput).sendKeys(password);
        find(loginButton).click();
        return new MyAccountPage(driver);
    }

    public String verifyEmailMessage() {
        if (find(emailWarning).isDisplayed()) {
            return find(emailWarning).getText();
        }
        return "";
    }

    public String verifyEmailMessageFromPopup() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement field = find(emailInput);
        return js.executeScript("return arguments[0].validationMessage",field).toString();
    }

    public String verifyPassMessage() {
        if (find(passwordWarning).isDisplayed()) {
            return find(passwordWarning).getText();
        }
        return "";
    }

    public String verifyInvalidCredentialsError() {
        return find(invalidEmailOrPassError).getText();
    }

    public void forgotYourPassword() {
        find(forgotPasswordLink).click();
    }

    public String getResetPasswordMessage() {
        return find(resetPasswordMessage).getText();
    }

    public void logout() {
        find(accountLink).click();
        find(logOutButton).click();
    }

    public String getLogoutMessage() {
        return find(logoutMessage).getText();
    }

}
