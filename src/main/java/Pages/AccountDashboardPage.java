package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDashboardPage extends BasePage {

    private By afterRegisterMessage = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/ul/li/ul/li/span");
    private By editNewsletterSubscriptionsLink = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/div[1]/a");
    private By subscriptionWasEditedMessage = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/ul/li/ul/li");
    private By subscriptionStatus = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/div[2]/p");
    public AccountDashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getAfterRegisterMessage() {
        return find(afterRegisterMessage).getText();
    }

    public NewsletterSubscriptionsPage goToNewsletterSubscriptions() {
        find(editNewsletterSubscriptionsLink).click();
        return new NewsletterSubscriptionsPage(driver);
    }

    public String getSubscriptionWasEditedMessage() {
        return find(subscriptionWasEditedMessage).getText();
    }

    public Boolean isUserSubscribed() {
        if (find(subscriptionStatus).getText().equals("You are currently subscribed to 'General Subscription'.")) {
            return true;
        }
        return false;
    }

    public String verifyAfterSubscriptionWasEditedMessage(Boolean userIsSubscribed) {
        if (userIsSubscribed) {
            return "The subscription has been removed.";
        }
        return "The subscription has been saved.";
    }
}