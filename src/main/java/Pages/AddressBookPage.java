package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBookPage extends BasePage {

    private By firstNameInput = By.id("firstname");
    private By middleNameInput = By.id("middlename");
    private By lastNameInput = By.id("lastname");
    private By companyInput = By.id("company");
    private By telephoneInput = By.id("telephone");
    private By faxInput = By.id("fax");
    private By address1Input = By.id("street_1");
    private By address2Input = By.id("street_2");
    private By cityInput = By.id("city");
    private Select stateDropdown = new Select(find(By.id("region_id")));
    private By zipCodeInput = By.id("zip");
    private Select countryDropdown = new Select(find(By.id("country_id")));
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

    public void editContactInformation(String firstName, String middleName, String lastName, String company, String telephone, String fax) {
        clearAndSendKeys(firstNameInput, firstName);
        clearAndSendKeys(middleNameInput, middleName);
        clearAndSendKeys(lastNameInput, lastName);
        clearAndSendKeys(companyInput, company);
        clearAndSendKeys(telephoneInput, telephone);
        clearAndSendKeys(faxInput, fax);
        find(saveAddressButton).click();
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
}
