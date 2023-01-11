package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountInformationPage extends BasePage {

    private By firstNameInput = By.id("firstname");
    private By middleNameInput = By.id("middlename");
    private By lastNameInput = By.id("lastname");
    private By emailInput = By.id("email");
    private By currentPasswordInput = By.id("current_password");
    private By newPasswordInput = By.id("password");
    private By confirmNewPasswordInput = By.id("confirmation");
    private By saveButton = By.xpath("//*[@id=\"form-validate\"]/div[3]/button");
    private By firstNameWarning = By.xpath("(//div[@class='input-box'])[2]");
    private By lastNameWarning = By.xpath("(//div[@class='input-box'])[4]");
    private By emailWarning = By.xpath("(//div[@class='input-box'])[5]");
    private By currentPasswordWarning = By.xpath("(//div[@class='input-box'])[6]");
    private By newPasswordWarning = By.xpath("(//div[@class='input-box'])[7]");
    private By confirmNewPasswordWarning = By.xpath("(//div[@class='input-box'])[8]");
    private By invalidCurrentPasswordMessage = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/ul");

    public AccountInformationPage(WebDriver driver) {
        super(driver);
    }

    public void editContactInformation(String firstName, String middleName, String lastName, String email) {
        clearAndSendKeys(firstNameInput, firstName);
        clearAndSendKeys(middleNameInput, middleName);
        clearAndSendKeys(lastNameInput, lastName);
        clearAndSendKeys(emailInput, email);
        find(saveButton).click();
    }

    public String verifyFirstNameWarning() {
        if (find(firstNameWarning).isDisplayed()) {
            return find(firstNameWarning).getText();
        }
        return "";
    }

    public String verifyLastNameWarning() {
        if (find(lastNameWarning).isDisplayed()) {
            return find(lastNameWarning).getText();
        }
        return "";
    }

    public String verifyEmailWarning() {
        if (find(emailWarning).isDisplayed()) {
            return find(emailWarning).getText();
        }
        return "";
    }

    public String verifyCurrentPasswordWarning() {
        if (find(currentPasswordWarning).isDisplayed()) {
            return find(currentPasswordWarning).getText();
        }
        return "";
    }

    public String verifyNewPasswordWarning() {
        if (find(newPasswordWarning).isDisplayed()) {
            return find(newPasswordWarning).getText();
        }
        return "";
    }

    public String verifyConfirmNewPasswordWarning() {
        if (find(confirmNewPasswordWarning).isDisplayed()) {
            return find(confirmNewPasswordWarning).getText();
        }
        return "";
    }

    public String verifyEmailMessageFromPopup() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement field = find(emailInput);
        return js.executeScript("return arguments[0].validationMessage",field).toString();
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        clearAndSendKeys(currentPasswordInput, currentPassword);
        clearAndSendKeys(newPasswordInput, newPassword);
        clearAndSendKeys(confirmNewPasswordInput, confirmPassword);
        find(saveButton).click();
    }

}
