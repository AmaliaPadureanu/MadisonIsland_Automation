package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewsletterSubscriptionsPage extends BasePage {

    @FindBy(how = How.ID, using = "subscription")
    WebElement generalSubscriptionCheckbox;

    @FindBy(how = How.CSS, using = "button[title='Save']")
    WebElement saveButton;

    public NewsletterSubscriptionsPage(WebDriver driver) {
        super(driver);
    }

    public void editSubscription() {
        generalSubscriptionCheckbox.click();
        saveButton.click();
    }
}
