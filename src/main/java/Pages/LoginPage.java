package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    @CacheLookup
    WebElement emailInput;

    @FindBy(id = "pass")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(id = "send2")
    @CacheLookup
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        if (!driver.getTitle().equals("Customer Login")) {
            throw new IllegalStateException("This is not the Login Page," +
                    " the current page is: " + driver.getCurrentUrl());
        }
    }

    public MyAccountPage loginWith(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new MyAccountPage(driver);
    }


}
