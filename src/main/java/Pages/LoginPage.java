package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailInput = By.id("email");
    private By passwordInput = By.id("pass");
    private By loginButton = By.id("send2");
    private By emailIsRequiredWarning = By.xpath("(//div[@class='input-box'])[2]");
    private By passwordIsRequiredWarning = By.xpath("(//div[@class='input-box'])[3]");
    private By invalidCredentialsError = By.xpath("//span[normalize-space()='Invalid login or password.']");

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

    public String verifyEmailIsRequiredWarning() {
        if (find(emailIsRequiredWarning).isDisplayed()) {
            return find(emailIsRequiredWarning).getText();
        }
        return "";
    }

    public String verifyPassIsRequiredWarning() {
        if (find(passwordIsRequiredWarning).isDisplayed()) {
            return find(passwordIsRequiredWarning).getText();
        }
        return "";
    }

    public String verifyInvalidCredentialsError() {
        if (find(invalidCredentialsError).isDisplayed()) {
            return find(invalidCredentialsError).getText();
        }
        return "";
    }


}
