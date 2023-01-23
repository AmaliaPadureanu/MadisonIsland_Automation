package Pages;

import Utils.GenericUtils;
import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "login:guest")
    private WebElement checkoutAsGuestRadiobutton;
    @FindBy(how = How.ID, using = "onepage-guest-register-button")
    private WebElement continueButton;
    @FindBy(how = How.ID, using = "billing:firstname")
    private WebElement firstNameInput;
    @FindBy(how = How.ID, using = "billing:lastname")
    private WebElement lastNameInput;
    @FindBy(how = How.ID, using = "billing:email")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "billing:street1")
    private WebElement addressInput;
    @FindBy(how = How.ID, using = "billing:city")
    private WebElement cityInput;
    @FindBy(how = How.ID, using = "billing:region_id")
    private WebElement stateInput;
    @FindBy(how = How.ID, using = "billing:postcode")
    private WebElement zipcodeInput;
    @FindBy(how = How.ID, using = "billing:country_id")
    private WebElement countryDropdown;
    @FindBy(how = How.ID, using = "billing:telephone")
    private WebElement telephoneInput;
    @FindBy(how = How.CSS, using = "#billing-buttons-container > button")
    private WebElement continueToShippingButton;
    @FindBy(how = How.ID, using = "s_method_freeshipping_freeshipping")
    private WebElement freeShippingCheckbox;
    @FindBy(how = How.ID, using = "s_method_flatrate_flatrate")
    private WebElement flatRateCheckbox;
    @FindBy(how = How.ID, using = "allow_gift_messages")
    private WebElement addGiftOptions;
    @FindBy(how = How.ID, using = "allow_gift_messages_for_order")
    private WebElement addGiftForOrder;
    @FindBy(how = How.ID, using = "allow_gift_messages_for_items")
    private WebElement addGiftForItems;
    @FindBy(how = How.CSS, using = "#shipping-method-buttons-container > button")
    private WebElement shippingMethodContinueButton;
    @FindBy(how = How.ID, using = "gift-message-whole-from")
    private WebElement addGiftEntireOrderFromInput;
    @FindBy(how = How.ID, using = "gift-message-whole-to")
    private WebElement addGiftEntireOrderToInput;
    @FindBy(how = How.ID, using = "gift-message-whole-message")
    private WebElement addGiftEntireOrderMessageTextarea;
    @FindBy(how = How.CSS, using = "#allow-gift-messages-for-items-container > ol > li > div[class='details'] > div > ul > li > div :first-of-type[id*='from']")
    private WebElement addGiftIndividualItemFromInput;
    @FindBy(how = How.CSS, using = "#allow-gift-messages-for-items-container > ol > li > div[class='details'] > div > ul > li > div :first-of-type[id*='to']")
    private WebElement addGiftIndividualItemToInput;
    @FindBy(how = How.CSS, using = "#allow-gift-messages-for-items-container > ol > li > div[class='details'] > div > ul > li > div > textarea")
    private WebElement addGiftIndividualItemMessageTextarea;
    @FindBy(how = How.CSS, using = "#payment-buttons-container > button")
    private WebElement paymentInformationContinueButton;
    @FindBy(how = How.CSS, using = "#review-buttons-container > button")
    private WebElement placeOrderButton;
    @FindBy(how = How.CSS, using = "h1")
    private WebElement orderSuccessMessage;
    @FindBy(how = How.ID, using = "login-email")
    private WebElement loginEmailInput;
    @FindBy(how = How.ID, using = "login-password")
    private WebElement loginPasswordInput;
    @FindBy(how = How.CSS, using = "#checkout-step-login > div > div.col-2 > div > button")
    private WebElement loginButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void checkoutMethod(Boolean guest, Boolean login, String email, String password) {
        if (guest) {
            checkoutAsGuestRadiobutton.click();
            continueButton.click();
        } else if (login) {
            loginEmailInput.sendKeys(email);
            loginPasswordInput.sendKeys(password);
            loginButton.click();
        }
    }

    public void fillInBillingInformation(String firstName, String lastName, String email, String address, String city,
                                         String state, String zipcode, String country, String telephone) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        stateInput.sendKeys(state);
        zipcodeInput.sendKeys(zipcode);
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText(country);
        telephoneInput.sendKeys(telephone);
        continueToShippingButton.click();
    }

    public void selectShippingMethod(Boolean freeShipping, Boolean flatRate) {
        if (freeShipping) {
            WaitUtils.waitForElementToBeClickable(driver, freeShippingCheckbox, 20);
            freeShippingCheckbox.click();
        } else if (flatRate) {
            WaitUtils.waitForElementToBeClickable(driver, flatRateCheckbox, 20);
            flatRateCheckbox.click();
        }
    }

    public void addGift(Boolean addGiftForEntireOrder, Boolean addGiftForIndividualItems) {
        addGiftOptions.click();

        if (addGiftForEntireOrder) {
            addGiftForOrder.click();
            clearAndSendKeys(addGiftEntireOrderFromInput, GenericUtils.createRandomString(10));
            clearAndSendKeys(addGiftEntireOrderToInput, GenericUtils.createRandomString(10));
            clearAndSendKeys(addGiftEntireOrderMessageTextarea, GenericUtils.createRandomString(20));
            shippingMethodContinueButton.click();
        } else if (addGiftForIndividualItems) {
            addGiftForItems.click();
            clearAndSendKeys(addGiftIndividualItemFromInput, GenericUtils.createRandomString(10));
            clearAndSendKeys(addGiftIndividualItemToInput, GenericUtils.createRandomString(10));
            clearAndSendKeys(addGiftIndividualItemMessageTextarea, GenericUtils.createRandomString(20));
            shippingMethodContinueButton.click();
        }
    }

    public void continueToShippingMethod() {
        continueToShippingButton.click();
    }

    public void continueToPaymentInformation() {
        shippingMethodContinueButton.click();
    }

    public void continueToOrderReview() {
        WaitUtils.waitForElementToBeClickable(driver, paymentInformationContinueButton, 10);
        paymentInformationContinueButton.click();
    }

    public void placeOrder() {
        WaitUtils.waitForElementToBeClickable(driver, placeOrderButton, 15);
        placeOrderButton.click();
    }

    public String getOrderSuccessMessage() {
        return orderSuccessMessage.getText();
    }
}
