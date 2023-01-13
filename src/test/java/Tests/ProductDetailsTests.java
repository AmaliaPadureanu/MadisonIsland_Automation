package Tests;

import Pages.NavigationPage;
import Utils.CategoriesOfProducts;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetailsTests extends BaseTest {


    @Test()
    public void filterByPriceTest() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        searchResultsPage = navigationPage.navigateToSubcategory(CategoriesOfProducts.ACCESSORIES, "Shoes");
        searchResultsPage.filterByPrice("300", "399");
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        Assert.assertTrue(verifyPriceFilter(productDetailsPage.getProductPrice(), "300", "399"));
    }

    @Test
    public void filterByOccasion() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        searchResultsPage = navigationPage.navigateToSubcategory(CategoriesOfProducts.WOMEN, "Dresses & Skirts");
        searchResultsPage.filterByOccasion("Career");
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        Assert.assertTrue(productDetailsPage.getProductSpecs().contains("Career"));
    }


    private boolean verifyPriceFilter(int productPrice, String lowerBound, String upperBound) {
        if (upperBound.equals("above")) {
            if (productPrice >= Integer.valueOf(lowerBound)) {
                return true;
            }
        } else {
            if (productPrice >= Integer.valueOf(lowerBound) && productPrice <= Integer.valueOf(upperBound)) {
                return true;
            }
        }
        return false;
    }

}
