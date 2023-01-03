package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailInput = By.id("email");
    private By passwordInput = By.id("pass");
    private By loginButton = By.id("send2");
    private By emailWarning = By.xpath("(//div[@class='input-box'])[2]");
    private By passwordWarning = By.xpath("(//div[@class='input-box'])[3]");
    private By invalidEmailOrPassError = By.xpath("//span[normalize-space()='Invalid login or password.']");
    private By forgotPasswordLink = By.xpath("//a[normalize-space()='Forgot Your Password?']");
    private By resetPasswordMessage = By.cssSelector("body div div div div div div ul li ul li span");

    public LoginPage(WebDriver driver) {
        super(driver);
        if (!driver.getTitle().equals("Customer Login")) {
            throw new IllegalStateException("This is not the Login Page," +
                    " the current page is: " + driver.getCurrentUrl());
        }
    }

    public MyAccountPage loginWith(String email, String password) {
        find(emailInput).clear();
        find(emailInput).sendKeys(email);
        find(passwordInput).clear();
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

    public String verifyPassMessage() {
        if (find(passwordWarning).isDisplayed()) {
            return find(passwordWarning).getText();
        }
        return "";
    }

    public String verifyInvalidCredentialsError() {
        if (find(invalidEmailOrPassError).isDisplayed()) {
            return find(invalidEmailOrPassError).getText();
        }
        return "";
    }

    public void forgotYourPassword() {
        find(forgotPasswordLink).click();
    }

    public String getResetPasswordMessage() {
        return find(resetPasswordMessage).getText();
    }

}
