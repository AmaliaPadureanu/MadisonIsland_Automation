package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EditAddressPage extends BasePage {

    WebDriverWait wait;
    @FindBy(how = How.ID, using = "firstname")
    private WebElement firstNameInput;
    @FindBy(how = How.ID, using = "middlename")
    private WebElement middleNameInput;
    @FindBy(how = How.ID, using = "lastname")
    private WebElement lastNameInput;
    @FindBy(how = How.ID, using = "company")
    private WebElement companyInput;
    @FindBy(how = How.ID, using = "telephone")
    private WebElement telephoneInput;
    @FindBy(how = How.ID, using = "fax")
    private WebElement faxInput;
    @FindBy(how = How.ID, using = "street_1")
    private WebElement address1Input;
    @FindBy(how = How.ID, using = "street_2")
    private WebElement address2Input;
    @FindBy(how = How.ID, using = "city")
    private WebElement cityInput;
    @FindBy(how = How.ID, using = "region_id")
    private Select stateDropdown;
    @FindBy(how = How.ID, using = "zip")
    private WebElement zipCodeInput;
    @FindBy(how = How.ID, using = "country")
    private Select countryDropdown;
    @FindBy(how = How.XPATH, using = "//*[@id=\"form-validate\"]/div[3]/button")
    private WebElement saveAddressButton;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[2]")
    private WebElement firstNameError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[4]")
    private WebElement lastNameError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[6]")
    private WebElement telephoneError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[8]")
    private WebElement address1Error;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[10]")
    private WebElement cityError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[11]")
    private WebElement stateError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[12]")
    private WebElement zipCodeError;

    public EditAddressPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void editContactInformation(String firstName, String middleName, String lastName, String company, String telephone, String fax) {
        clearAndSendKeys(firstNameInput, firstName);
        clearAndSendKeys(middleNameInput, middleName);
        clearAndSendKeys(lastNameInput, lastName);
        clearAndSendKeys(companyInput, company);
        clearAndSendKeys(telephoneInput, telephone);
        clearAndSendKeys(faxInput, fax);
        saveAddressButton.click();
    }

    public void editAddress(String address1, String address2, String city, String state, String zipCode, String country) {
        clearAndSendKeys(address1Input, address1);
        clearAndSendKeys(address2Input, address2);
        clearAndSendKeys(cityInput, city);
        stateDropdown.selectByVisibleText(state);
        clearAndSendKeys(zipCodeInput, zipCode);
        countryDropdown.selectByVisibleText(country);
    }

    public boolean checkError(String expectedError, String errorType) {
        if(errorType.equalsIgnoreCase("firstNameError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(firstNameError));
                return expectedError.equalsIgnoreCase(firstNameError.getText());
            } else return true;
        } else if (errorType.equalsIgnoreCase("lastNameError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(lastNameError));
                return expectedError.equalsIgnoreCase(lastNameError.getText());
            } else return true;
        } else if (errorType.equalsIgnoreCase("telephoneError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(telephoneError));
                return expectedError.equalsIgnoreCase(telephoneError.getText());
            }
            else return true;
        } else if (errorType.equalsIgnoreCase("address1Error")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(address1Error));
                return expectedError.equalsIgnoreCase(address1Error.getText());
            }
            else return true;
        } else if (errorType.equalsIgnoreCase("cityError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(cityError));
                return expectedError.equalsIgnoreCase(cityError.getText());
            }
            else return true;
        } else if (errorType.equalsIgnoreCase("stateError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(stateError));
                return expectedError.equalsIgnoreCase(stateError.getText());
            }
            else return true;
        } else if (errorType.equalsIgnoreCase("zipCodeError")) {
            if (expectedError.length() > 0) {
                wait.until(ExpectedConditions.visibilityOf(zipCodeError));
                return expectedError.equalsIgnoreCase(zipCodeError.getText());
            }
            else return true;
        }
        return false;
    }
}
