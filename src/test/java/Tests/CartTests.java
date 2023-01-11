package Tests;

import Pages.NavigationPage;
import Utils.CategoriesOfProducts;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test
    public void addToCart() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        searchResultsPage = navigationPage.navigateToRandomCategoryOfProducts(CategoriesOfProducts.WOMEN);
    }
}
