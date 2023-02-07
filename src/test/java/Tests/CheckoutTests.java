package Tests;

import Pages.CheckoutPage;
import Pages.NavigationPage;
import Tests.ObjectModels.BillingModel;
import Utils.CategoriesOfProducts;
import Utils.ConstantUtils;
import Utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CheckoutTests extends BaseTest {

    @DataProvider(name = "validBillingDP")
    public Iterator<Object[]> SQLDpCollection() throws SQLException {
        Collection<Object[]> dataProvider = new ArrayList<>();

            Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort
                    + "/" + dbSchema, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM checkout_positive");

            while (resultSet.next()) {
                BillingModel billingModel = new BillingModel(resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("zipcode"),
                        resultSet.getString("country"),
                        resultSet.getString("telephone"),
                        resultSet.getString("firstnameError"),
                        resultSet.getString("lastnameError"),
                        resultSet.getString("emailError"),
                        resultSet.getString("addressError"),
                        resultSet.getString("cityError"),
                        resultSet.getString("zipcodeError"),
                        resultSet.getString("countryError"),
                        resultSet.getString("telephoneError"));
                dataProvider.add(new Object[] {billingModel});
            }

        return dataProvider.iterator();
    }

    @DataProvider(name = "invalidBillingDP")
    public Iterator<Object[]> SQLDpCollectionInvalid() throws SQLException {
        Collection<Object[]> dataProvider = new ArrayList<>();

            Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort
                    + "/" + dbSchema, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM checkout_negative");

            while (resultSet.next()) {
                BillingModel billingModel = new BillingModel(resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString( "address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("zipcode"),
                        resultSet.getString("country"),
                        resultSet.getString("telephone"),
                        resultSet.getString("firstnameError"),
                        resultSet.getString("lastnameError"),
                        resultSet.getString("emailError"),
                        resultSet.getString("addressError"),
                        resultSet.getString("cityError"),
                        resultSet.getString("zipcodeError"),
                        resultSet.getString("countryError"),
                        resultSet.getString("telephoneError"));
                dataProvider.add(new Object[] {billingModel});
            }

        return dataProvider.iterator();
    }

    private void billingActions(BillingModel billingModel) {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillInBillingInformation(billingModel.getFirstname(), billingModel.getLastname(), billingModel.getEmail(),
                billingModel.getAddress(), billingModel.getCity(), billingModel.getState(),
                billingModel.getZipcode(), billingModel.getCountry(), billingModel.getTelephone());

        Assert.assertTrue(checkoutPage.checkError(billingModel.getFirstnameError(), "firstnameError"));
        Assert.assertTrue(checkoutPage.checkError(billingModel.getLastnameError(), "lastnameError"));
        Assert.assertTrue(checkoutPage.checkError(billingModel.getEmailError(), "emailError"));
        Assert.assertTrue(checkoutPage.checkError(billingModel.getAddressError(), "addressError"));
        Assert.assertTrue(checkoutPage.checkError(billingModel.getCityError(), "cityError"));
        Assert.assertTrue(checkoutPage.checkError(billingModel.getZipcodeError(), "zipcodeError"));
        Assert.assertTrue(checkoutPage.checkError(billingModel.getCountryError(), "countryError"));
        Assert.assertTrue(checkoutPage.checkError(billingModel.getTelephoneError(), "telephoneError"));
    }

    private void addRandomProductToCart(){
        navigationPage = new NavigationPage(driver);
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
        int cartSubtotal = cartPage.getGrandTotal();
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.checkoutMethod(true, false, "", "");
        billingActions(billingModel);
        checkoutPage.selectShippingMethod(cartSubtotal, true, false);
        checkoutPage.addGift(false, true);
        checkoutPage.continueToOrderReview();
        checkoutPage.placeOrder();
        WaitUtils.waitForUrlToBe(driver, "http://demo-store.seleniumacademy.com/checkout/onepage/success/", 10);
        Assert.assertTrue(checkoutPage.getOrderSuccessMessage().equalsIgnoreCase("Your order has been received."));
    }

    @Test (groups = {"smoke", "regression"})
    public void validPlaceOrderAsRegisteredUserTest() {
        addRandomProductToCart();
        int cartSubtotal = cartPage.getGrandTotal();
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.checkoutMethod(false, true, ConstantUtils.USER, ConstantUtils.PASSWORD);
        checkoutPage.continueToShippingMethod();
        checkoutPage.selectShippingMethod(cartSubtotal,true, false);
        checkoutPage.addGift(true, false);
        checkoutPage.continueToOrderReview();
        checkoutPage.placeOrder();
        WaitUtils.waitForUrlToBe(driver, "http://demo-store.seleniumacademy.com/checkout/onepage/success/", 10);
        Assert.assertTrue(checkoutPage.getOrderSuccessMessage().equalsIgnoreCase("Your order has been received."));
    }

    @Test (dataProvider = "invalidBillingDP", groups = {"regression"})
    public void invalidPlaceOrderAsGuestTest(BillingModel billingModel) {
        addRandomProductToCart();
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.checkoutMethod(true, false, "", "");
        billingActions(billingModel);
    }
}
