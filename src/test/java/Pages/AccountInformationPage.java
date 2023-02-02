package Pages;

import Utils.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountInformationPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "firstname")
    private WebElement firstNameInput;
    @FindBy(how = How.ID, using = "middlename")
    private WebElement middleNameInput;
    @FindBy(how = How.ID, using = "lastname")
    private WebElement lastNameInput;
    @FindBy(how = How.ID,using = "email")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "current_password")
    private WebElement currentPasswordInput;
    @FindBy(how = How.ID, using = "password")
    private WebElement newPasswordInput;
    @FindBy(how = How.ID, using = "confirmation")
    private WebElement confirmNewPasswordInput;
    @FindBy(how = How.XPATH, using = "//*[@id=\"form-validate\"]/div[3]/button")
    private WebElement saveButton;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[2]")
    private WebElement firstNameError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[4]")
    private WebElement lastNameError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[5]")
    private WebElement emailError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[6]")
    private WebElement currentPasswordWarning;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[7]")
    private WebElement newPasswordWarning;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[8]")
    private WebElement confirmNewPasswordWarning;

    public AccountInformationPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void editContactInformation(String firstName, String middleName, String lastName, String email) {
        clearAndSendKeys(firstNameInput, firstName);
        clearAndSendKeys(middleNameInput, middleName);
        clearAndSendKeys(lastNameInput, lastName);
        clearAndSendKeys(emailInput, email);
        saveButton.click();
    }

    public String verifyCurrentPasswordWarning() {
        if (currentPasswordWarning.isDisplayed()) {
            return currentPasswordWarning.getText();
        }
        return "";
    }

    public String verifyNewPasswordWarning() {
        if (newPasswordWarning.isDisplayed()) {
            return newPasswordWarning.getText();
        }
        return "";
    }

    public String verifyConfirmNewPasswordWarning() {
        if (confirmNewPasswordWarning.isDisplayed()) {
            return confirmNewPasswordWarning.getText();
        }
        return "";
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        clearAndSendKeys(currentPasswordInput, currentPassword);
        clearAndSendKeys(newPasswordInput, newPassword);
        clearAndSendKeys(confirmNewPasswordInput, confirmPassword);
        saveButton.click();
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
            case "emailPopupError" : {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return doesErrorMessageContainExpectedMessage(expectedError, js.executeScript("return arguments[0].validationMessage",emailInput).toString());
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

    private boolean doesErrorMessageContainExpectedMessage(String expectedError, String element) {
        if (expectedError.length() > 0) {
            return element.contains(expectedError);
        }
        return true;
    }
}
