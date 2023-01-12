package Tests;

import Pages.NavigationPage;
import Utils.CategoriesOfProducts;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test
    public void addToCart() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        searchResultsPage = navigationPage.navigateToRandomCategoryOfProducts(CategoriesOfProducts.WOMEN);
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        cartPage = productDetailsPage.addToCart();
        Assert.assertTrue(cartPage.getCartSuccessMessage().toLowerCase().contains(productDetailsPage.getProductName().toLowerCase() + "was added to your shopping cart."));

    }
}
