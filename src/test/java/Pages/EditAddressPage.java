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
    @FindBy(how = How.ID, using = "region")
    private WebElement stateInput;
    @FindBy(how = How.ID, using = "zip")
    private WebElement zipCodeInput;
    @FindBy(how = How.ID, using = "country")
    private WebElement countryDropdown;
    @FindBy(how = How.XPATH, using = "//*[@id=\"form-validate\"]/div[3]/button")
    private WebElement saveAddressButton;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[2]")
    private WebElement firstNameError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[4]")
    private WebElement lastNameError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[6]")
    private WebElement telephoneError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[8]")
    private WebElement streetAddress1Error;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[10]")
    private WebElement cityError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[11]")
    private WebElement stateError;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[12]")
    private WebElement zipCodeError;
    @FindBy(how = How.ID, using = "advice-validate-select-country")
    private WebElement countryError;

    public EditAddressPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void editAddress(String firstName, String middleName, String lastName, String company, String telephone,
                            String fax, String address1, String address2, String city, String state, String zipCode, String country) {
        clearAndSendKeys(firstNameInput, firstName);
        clearAndSendKeys(middleNameInput, middleName);
        clearAndSendKeys(lastNameInput, lastName);
        clearAndSendKeys(companyInput, company);
        clearAndSendKeys(telephoneInput, telephone);
        clearAndSendKeys(faxInput, fax);
        clearAndSendKeys(address1Input, address1);
        clearAndSendKeys(address2Input, address2);
        clearAndSendKeys(cityInput, city);
        clearAndSendKeys(stateInput, state);
        clearAndSendKeys(zipCodeInput, zipCode);
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText(country);
        saveAddressButton.click();
    }

    public boolean checkError(String expectedError, String errorType) {
        switch (errorType)  {
            case "firstNameError" : {
                return isErrorMessageEqualToExpected(expectedError, firstNameError);
            }
            case "lastNameError" : {
                return isErrorMessageEqualToExpected(expectedError, lastNameError);
            }
            case "telephoneError" : {
                return isErrorMessageEqualToExpected(expectedError, telephoneError);
            }
            case "streetAddress1Error" : {
                return isErrorMessageEqualToExpected(expectedError, streetAddress1Error);
            }
            case "cityError" : {
                return isErrorMessageEqualToExpected(expectedError, cityError);
            }
            case "stateError" : {
                return isErrorMessageEqualToExpected(expectedError, stateError);
            }
            case "zipCodeError" : {
                return isErrorMessageEqualToExpected(expectedError, zipCodeError);
            }
            case "countryError" : {
                return isErrorMessageEqualToExpected(expectedError, countryError);
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
}
