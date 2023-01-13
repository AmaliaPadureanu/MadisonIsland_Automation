package Tests;

import Pages.NavigationPage;
import Utils.CategoriesOfProducts;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WishlistTests extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        loginPage.loginWith("test@e.com", "Automation");
    }

    @Test
    public void addProductToWishlistTest() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        searchResultsPage = navigationPage.navigateToSubcategory(CategoriesOfProducts.HOME, "Bed & Bath");
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        String productName = productDetailsPage.getProductName();
        wishlistPage = productDetailsPage.addToWishlist();
        System.out.println(wishlistPage.getWishlistSuccessMessage());
        Assert.assertTrue(wishlistPage.getWishlistSuccessMessage().contains(productName + " has been added to your wishlist"));
    }
  }
