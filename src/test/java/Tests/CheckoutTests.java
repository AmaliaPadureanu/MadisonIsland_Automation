package Tests;

import Pages.CheckoutPage;
import Pages.NavigationPage;
import Tests.ObjectModels.BillingModel;
import Utils.CategoriesOfProducts;
import Utils.GenericUtils;
import Utils.WaitUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CheckoutTests extends BaseTest {

    @DataProvider(name = "validBillingDP")
    public Iterator<Object[]> SQLDpCollection() {
        Collection<Object[]> dp = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort
                    + "/" + dbSchema, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM checkout_positive");
            while ((resultSet.next())) {
                BillingModel billingModel = new BillingModel(getEscapedElement(resultSet, "firstname"),
                        getEscapedElement(resultSet, "lastname"),
                        getEscapedElement(resultSet, "email"),
                        getEscapedElement(resultSet, "address"),
                        getEscapedElement(resultSet, "city"),
                        getEscapedElement(resultSet, "state"),
                        getEscapedElement(resultSet, "zipcode"),
                        getEscapedElement(resultSet, "country"),
                        getEscapedElement(resultSet, "telephone"));
                dp.add(new Object[] {billingModel});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dp.iterator();
    }

    private String getEscapedElement(ResultSet resultSet, String element) throws SQLException {
        return GenericUtils.replaceElements(resultSet.getString(element), "", "");
    }

    private void billingActions(BillingModel billingModel) {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillInBillingInformation(billingModel.getFirstname(), billingModel.getLastname(), billingModel.getEmail(),
                billingModel.getAddress(), billingModel.getCity(), billingModel.getState(),
                billingModel.getZipcode(), billingModel.getCountry(), billingModel.getTelephone());
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

    @Test (dataProvider = "validBillingDP", groups = {"regression"})
    public void validPlaceOrderAsGuestTest(BillingModel billingModel) {
        addRandomProductToCart();
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.checkoutMethod(true, false, "", "");
        billingActions(billingModel);
        checkoutPage.selectShippingMethod(true, false);
        checkoutPage.addGift(false, true);
        checkoutPage.continueToOrderReview();
        checkoutPage.placeOrder();
        WaitUtils.waitForUrlToBe(driver, "http://demo-store.seleniumacademy.com/checkout/onepage/success/", 10);
        Assert.assertTrue(checkoutPage.getOrderSuccessMessage().equalsIgnoreCase("Your order has been received."));
    }

    @Test (groups = {"smoke", "regression"})
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
}
