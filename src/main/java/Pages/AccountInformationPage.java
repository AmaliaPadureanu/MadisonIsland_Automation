package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountInformationPage extends BasePage {

    private By firstNameInput = By.id("firstname");
    private By middleNameInput = By.id("middlename");
    private By lastNameInput = By.id("lastname");
    private By emailInput = By.id("email");
    private By changePasswordCheckbox = By.id("change_password");
    private By currentPasswordInput = By.id("current_password");
    private By newPasswordInput = By.id("password");
    private By confirmNewPasswordInput = By.id("confirmation");
    private By saveButton = By.xpath("//*[@id=\"form-validate\"]/div[3]/button");

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
}
