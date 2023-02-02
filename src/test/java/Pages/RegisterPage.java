package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "firstname")
    private WebElement firstNameInput;
    @FindBy(how = How.ID, using = "middlename")
    private WebElement middleNameInput;
    @FindBy(how = How.ID, using = "lastname")
    private WebElement lastNameInput;
    @FindBy(how = How.ID, using = "email_address")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "password")
    private WebElement passwordInput;
    @FindBy(how = How.ID, using = "confirmation")
    private WebElement confirmPasswordInput;
    @FindBy(how = How.ID, using = "is_subscribed")
    private WebElement subscribeToNewsletterCheckbox;
    @FindBy(how = How.CSS, using = "button[title='Register']")
    private WebElement registerButton;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[2]")
    private WebElement firstNameError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[4]")
    private WebElement lastNameError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[5]")
    private WebElement emailError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[6]")
    private WebElement passwordError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[7]")
    private WebElement confirmPasswordError;
    @FindBy(how = How.XPATH, using = "//body/div/div/div/div/div/div/ul/li[1]")
    private WebElement userAlreadyExistsError;

    public RegisterPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void registerUser(String firstName, String middleName, String lastName, String email, String pass,
                                     String confirmPass, Boolean subscribeToNewsletter) {
        firstNameInput.sendKeys(firstName);
        middleNameInput.sendKeys(middleName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(pass);
        confirmPasswordInput.sendKeys(confirmPass);

        if (subscribeToNewsletter) {
            subscribeToNewsletterCheckbox.click();
        }
        registerButton.click();
    }

    public String verifyEmailMessageFromPopup() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return arguments[0].validationMessage", emailInput).toString();
    }

    public boolean checkError(String expectedError, String errorType) {
        switch (errorType)  {
            case "firstNameError" : {
                return isErrorMessageEqualToExpected(expectedError, firstNameError);
            }
            case "lastNameError" : {
                return isErrorMessageEqualToExpected(expectedError, lastNameError);
            }
            case "emailError" : {
                return isErrorMessageEqualToExpected(expectedError, emailError);
            }
            case "passwordError" : {
                return isErrorMessageEqualToExpected(expectedError, passwordError);
            }
            case "confirmPasswordError" : {
                return isErrorMessageEqualToExpected(expectedError, confirmPasswordError);
            }
            case "emailErrorPopup" : {
                if (expectedError.length() > 0) {
                    return verifyEmailMessageFromPopup().contains(expectedError);
                }
                else return true;
            }
            default: return false;
        }
    }

    private boolean isErrorMessageEqualToExpected(String expectedError, WebElement element) {
        if (expectedError.length() > 0) {
            wait.until(ExpectedConditions.visibilityOf(element));
            return expectedError.equalsIgnoreCase(element.getText());
        }
        return true;
    }

    public String getUserAlreadyExistsError() {
        return userAlreadyExistsError.getText();
    }
}
