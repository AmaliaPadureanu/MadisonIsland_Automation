package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewsletterSubscriptionsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "subscription")
    WebElement generalSubscriptionCheckbox;
    @FindBy(how = How.CSS, using = "button[title='Save']")
    WebElement saveButton;

    public NewsletterSubscriptionsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void editSubscription() {
        generalSubscriptionCheckbox.click();
        saveButton.click();
    }
}
