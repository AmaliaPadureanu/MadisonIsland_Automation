package Tests;

import Pages.NavigationPage;
import Utils.CategoriesOfProducts;
import Utils.WaitUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @Test
    public void validPlaceOrderAsGuestTest() {
        addRandomProductToCart();
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.checkoutMethod(true, false, "", "");
        checkoutPage.fillInBillionInformation("Doe", "John", "jdoe@gmail.com", "Main St, No. 2,",
                "New York", "New York", "23456", "Bahamas", "9847364758");
        checkoutPage.selectShippingMethod(true, false);
        checkoutPage.addGift(false, true);
        checkoutPage.continueToOrderReview();
        checkoutPage.placeOrder();
        WaitUtils.waitForUrlToBe(driver, "http://demo-store.seleniumacademy.com/checkout/onepage/success/", 10);
        Assert.assertTrue(checkoutPage.getOrderSuccessMessage().equalsIgnoreCase("Your order has been received."));
    }

    @Test
    public void validPlaceOrderAsRegisteredUserTest() {
        addRandomProductToCart();
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.checkoutMethod(false, true, "test@e.com", "Automation");
        checkoutPage.continueToShippingMethod();
        checkoutPage.selectShippingMethod(true, false);
        checkoutPage.addGift(true, false);
        checkoutPage.continueToOrderReview();
        checkoutPage.placeOrder();
        WaitUtils.waitForUrlToBe(driver, "http://demo-store.seleniumacademy.com/checkout/onepage/success/", 10);
        Assert.assertTrue(checkoutPage.getOrderSuccessMessage().equalsIgnoreCase("Your order has been received."));
    }

    private void addRandomProductToCart(){
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        searchResultsPage = navigationPage.navigateToRandomSubcategory(CategoriesOfProducts.WOMEN);
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        String productName = productDetailsPage.getProductName();
        productDetailsPage.chooseRandomColor();
        productDetailsPage.chooseRandomSize();
        cartPage = productDetailsPage.addToCart();
        Assert.assertEquals(cartPage.getCartSuccessMessage(), productName + " was added to your shopping cart.");
    }
}
