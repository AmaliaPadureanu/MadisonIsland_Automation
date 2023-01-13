package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountDashboardPage extends BasePage {

    private By editNewsletterSubscriptionsLink = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/div[1]/a");
    private By subscriptionWasEditedMessage = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/ul/li/ul/li");
    private By subscriptionStatus = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/div[2]/p");
    private By editContactInformationLink = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div/div[1]/a");
    private By accountInformationWasEditedMessage = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/ul/li/ul/li/span");
    private By contactInformation = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div/div[2]/p");
    private By changePasswordLink = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div/div[2]/p/a");
    private By manageAddressesLink = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[4]/div[1]/a");
    private By wishlistLink = By.xpath("//body//div//div//div//div//div//div//div//ul//li//a[normalize-space()='My Wishlist']");

    public AccountDashboardPage(WebDriver driver) {
        super(driver);
    }

    public WishlistPage goToWishlist() {
        find(wishlistLink).click();
        return new WishlistPage(driver);
    }

    public NewsletterSubscriptionsPage goToNewsletterSubscriptions() {
        find(editNewsletterSubscriptionsLink).click();
        return new NewsletterSubscriptionsPage(driver);
    }

    public AccountInformationPage goToAccountInformation() {
        find(editContactInformationLink).click();
        return new AccountInformationPage(driver);
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

    public String getAccountInformationWasEditedMessage() {
        return find(accountInformationWasEditedMessage).getText();
    }

    public String getContactInformation() {
        return find(contactInformation).getText();
    }

    public AccountInformationPage goToChangePasswordSection() {
        find(changePasswordLink).click();
        return new AccountInformationPage(driver);
    }

    public AddressBookPage goToAddressBook() {
        find(manageAddressesLink).click();
        return new AddressBookPage(driver);
    }
}
