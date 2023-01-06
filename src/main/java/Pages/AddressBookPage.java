package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressBookPage extends BasePage {

    private By firstNameInput = By.id("firstname");
    private By middleNameInput = By.id("middlename");
    private By lastNameInput = By.id("lastname");
    private By companyInput = By.id("company");
    private By telephoneInput = By.id("telephone");
    private By faxInput = By.id("fax");
    private By streetAddress1Input = By.id("street_1");
    private By getStreetAddress2Input = By.id("street_2");
    private By cityInput = By.id("city");
    private By stateDropdown = By.id("region_id");
    private By zipCodeInput = By.id("zip");
    private By countryDropdown = By.id("country_id");
    private By saveAddressButton = By.xpath("//*[@id=\"form-validate\"]/div[3]/button");
    private By firstNameWarning = By.id("advice-required-entry-firstname");
    private By lastNameWarning = By.id("advice-required-entry-lastname");
    private By telephoneWarning = By.id("advice-required-entry-telephone");
    private By getStreetAddress1Warning = By.id("advice-required-entry-street_1");
    private By cityWarning = By.id("advice-required-entry-city");
    private By zipCodeWarning = By.id("advice-required-entry-zip");

    public AddressBookPage(WebDriver driver) {
        super(driver);
    }
}
