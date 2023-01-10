package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EditAddressPage extends BasePage {

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
    private WebElement firstNameWarning;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[4]")
    private WebElement lastNameWarning;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[6]")
    private WebElement telephoneWarning;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[8]")
    private WebElement address1Warning;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[10]")
    private WebElement cityWarning;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[11]")
    private WebElement stateWarning;
    @FindBy(how = How.XPATH, using = "(//div[@class='input-box'])[12]")
    private WebElement zipCodeWarning;

    public EditAddressPage(WebDriver driver) {
        super(driver);
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

    private String capitalizeFirstLetterOfEachWord(final String words) {
        return Stream.of(words.trim().split("\\s"))
                .filter(word -> word.length() > 0)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    public void editAddress(String address1, String address2, String city, String state, String zipCode, String country) {
        clearAndSendKeys(address1Input, address1);
        clearAndSendKeys(address2Input, address2);
        clearAndSendKeys(cityInput, city);
        stateDropdown.selectByVisibleText(capitalizeFirstLetterOfEachWord(state));
        clearAndSendKeys(zipCodeInput, zipCode);
        countryDropdown.selectByVisibleText(capitalizeFirstLetterOfEachWord(country));
    }

    public String verifyFirstNameWarningMessage() {
        if (firstNameWarning.isDisplayed()) {
            return firstNameWarning.getText();
        }
        return "";
    }

    public String verifyLastNameWarningMessage() {
        if (lastNameWarning.isDisplayed()) {
            return lastNameWarning.getText();
        }
        return "";
    }

    public String verifyTelephoneWarningMessage() {
        if (telephoneWarning.isDisplayed()) {
            return telephoneWarning.getText();
        }
        return "";
    }


}
