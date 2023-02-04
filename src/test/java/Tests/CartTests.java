package Tests;

import Pages.NavigationPage;
import Utils.CategoriesOfProducts;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test (groups = {"smoke", "regression"})
    public void addRandomClothingItemToCartTest() {
        navigationPage = new NavigationPage(driver);
        searchResultsPage = navigationPage.navigateToRandomSubcategory(CategoriesOfProducts.MEN);
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        String productName = productDetailsPage.getProductName();
        productDetailsPage.chooseRandomColor();
        productDetailsPage.chooseRandomSize();
        cartPage = productDetailsPage.addToCart();
        Assert.assertEquals(cartPage.getCartSuccessMessage(), productName + " was added to your shopping cart.");
    }

    @Test (dependsOnMethods = {"addRandomClothingItemToCartTest"}, groups = {"regression"})
    public void removeProductFromCartTest() {
        navigationPage = new NavigationPage(driver);
        cartPage = navigationPage.navigateToCart();
        int noOfProductsInCart = cartPage.getNoOfProductsInCart();
        cartPage.removeProductFromCart(cartPage.getProductsInCart().get(0));
        Assert.assertTrue(cartPage.getNoOfProductsInCart() == (noOfProductsInCart - 1));
    }

    @Test (dependsOnMethods = {"addRandomClothingItemToCartTest"}, groups = {"regression"})
    public void editProductQuantityTest() {
        navigationPage = new NavigationPage(driver);
        cartPage = navigationPage.navigateToCart();
        WebElement product = cartPage.getProductsInCart().get(0);
        String newQuantity = "3";
        int initialSubtotal = cartPage.getProductSubtotal(product);
        cartPage.editProductQuantity(product, newQuantity);
        Assert.assertEquals((cartPage.getProductSubtotal(cartPage.getProductsInCart().get(0))), initialSubtotal * Integer.valueOf(newQuantity));
    }
}
