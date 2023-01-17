package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountDashboardPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/div[1]/a")
    private WebElement editNewsletterSubscriptionsLink;
    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/ul/li/ul/li")
    private WebElement subscriptionWasEditedMessage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/div[2]/p")
    private WebElement subscriptionStatus;
    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div/div[1]/a")
    private WebElement editContactInformationLink;
    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/ul/li/ul/li/span")
    WebElement accountInformationWasEditedMessage;
    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div/div[2]/p")
    private WebElement contactInformation;
    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div/div[2]/p/a")
    private WebElement changePasswordLink;
    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/div[4]/div[1]/a")
    private WebElement manageAddressesLink;
    @FindBy(how = How.XPATH, using = "//body//div//div//div//div//div//div//div//ul//li//a[normalize-space()='My Wishlist']")
    private WebElement wishlistLink;
    @FindBy(how = How.XPATH, using = "//body/div/div/div/div/div/div/div[1]/ul[1]")
    private WebElement successfulRegistrationMessage;

    public AccountDashboardPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WishlistPage goToWishlist() {
        wishlistLink.click();
        return new WishlistPage(driver);
    }

    public NewsletterSubscriptionsPage goToNewsletterSubscriptions() {
        editNewsletterSubscriptionsLink.click();
        return new NewsletterSubscriptionsPage(driver);
    }

    public AccountInformationPage goToAccountInformation() {
        editContactInformationLink.click();
        return new AccountInformationPage(driver);
    }

    public String getSubscriptionWasEditedMessage() {
        return subscriptionWasEditedMessage.getText();
    }

    public Boolean isUserSubscribed() {
        if (subscriptionStatus.getText().equals("You are currently subscribed to 'General Subscription'.")) {
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
        return accountInformationWasEditedMessage.getText();
    }

    public String getContactInformation() {
        return contactInformation.getText();
    }

    public AccountInformationPage goToChangePasswordSection() {
        changePasswordLink.click();
        return new AccountInformationPage(driver);
    }

    public AddressBookPage goToAddressBook() {
        manageAddressesLink.click();
        return new AddressBookPage(driver);
    }

    public String getSuccessfulRegistrationMessage() {
        return successfulRegistrationMessage.getText();
    }
}