package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddressBookPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/ul/li")
    private WebElement addressWasSavedMessage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[2]/ol/li/p/a[1]")
    private WebElement editAddressLink;

    public AddressBookPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public EditAddressPage goToEditAddressBook() {
        editAddressLink.click();
        return new EditAddressPage(driver);
    }

    public String getAddressWasSavedMessage() {
        if(addressWasSavedMessage.isDisplayed()) {
            return addressWasSavedMessage.getText();
        }
        return "";
    }
}
