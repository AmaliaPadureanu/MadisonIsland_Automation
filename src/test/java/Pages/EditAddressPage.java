package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EditAddressPage extends BasePage {

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
    private Select countryDropdown = new Select(find(By.id("country")));
    private By saveAddressButton = By.xpath("//*[@id=\"form-validate\"]/div[3]/button");
    private By firstNameWarning = By.xpath("(//div[@class='input-box'])[2]");
    private By lastNameWarning = By.xpath("(//div[@class='input-box'])[4]");
    private By telephoneWarning = By.xpath("(//div[@class='input-box'])[6]");
    private By address1Warning = By.xpath("(//div[@class='input-box'])[8]");
    private By cityWarning = By.xpath("(//div[@class='input-box'])[10]");
    private By stateWarning = By.xpath("(//div[@class='input-box'])[11]");
    private By zipCodeWarning = By.xpath("(//div[@class='input-box'])[12]");

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

    public String verifyFirstNameWarningMessage() {
        if (find(firstNameWarning).isDisplayed()) {
            return find(firstNameWarning).getText();
        }
        return "";
    }

    public String verifyLastNameWarningMessage() {
        if (find(lastNameWarning).isDisplayed()) {
            return find(lastNameWarning).getText();
        }
        return "";
    }

    public String verifyTelephoneWarningMessage() {
        if (find(telephoneWarning).isDisplayed()) {
            return find(telephoneWarning).getText();
        }
        return "";
    }

    public String verifyAddressWarningMessage() {
        if (find(address1Warning).isDisplayed()) {
            return find(address1Warning).getText();
        }
        return "";
    }

    public String verifyCityWarningMessage() {
        if (find(cityWarning).isDisplayed()) {
            return find(cityWarning).getText();
        }
        return "";
    }

    public String verifyZipCodeWarningMessage() {
        if (find(zipCodeWarning).isDisplayed()) {
            return find(zipCodeWarning).getText();
        }
        return "";
    }
}
