package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressBookPage extends BasePage {

    private By addressWasSavedMessage = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/ul/li");
    private By editAddressLink = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[2]/ol/li/p/a[1]");

    public AddressBookPage(WebDriver driver) {
        super(driver);
    }

    public EditAddressPage goToEditAddressBook() {
        find(editAddressLink).click();
        return new EditAddressPage(driver);
    }

    public String getAddressWasSavedMessage() {
        if(find(addressWasSavedMessage).isDisplayed()) {
            return find(addressWasSavedMessage).getText();
        }
        return "";
    }
}
