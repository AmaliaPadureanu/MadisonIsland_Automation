package Tests;

import Pages.NavigationPage;
import Utils.CategoriesOfProducts;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WishlistTests extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        loginPage.loginWith("test@e.com", "Automation");
    }

    @Test
    public void addProductToWishlistTest() {
        navigationPage = new NavigationPage(driver);
        searchResultsPage = navigationPage.navigateToSubcategory(CategoriesOfProducts.HOME, "Bed & Bath");
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        String productName = productDetailsPage.getProductName();
        wishlistPage = productDetailsPage.addToWishlist();
        System.out.println(wishlistPage.getWishlistSuccessMessage());
        Assert.assertTrue(wishlistPage.getWishlistSuccessMessage().contains(productName + " has been added to your wishlist"));
    }

    @Test
    public void removeProductFromWishlistTest() {
        navigationPage = new NavigationPage(driver);
        accountDashboardPage = navigationPage.navigateToAccountDashboard();
        wishlistPage = accountDashboardPage.goToWishlist();
        int itemsInWishlistBefore = wishlistPage.getItemsInWishlist().size();
        wishlistPage.removeItemFromWishlist();
        Assert.assertTrue(wishlistPage.getItemsInWishlist().size() == itemsInWishlistBefore - 1);
    }
  }
