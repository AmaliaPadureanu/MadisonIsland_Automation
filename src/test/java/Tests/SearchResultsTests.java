package Tests;

import Pages.NavigationPage;
import Utils.CategoriesOfProducts;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchResultsTests extends BaseTest {

    @Test (groups = {"regression"})
    public void filterByPriceTest() {
        navigationPage = new NavigationPage(driver);
        searchResultsPage = navigationPage.navigateToSubcategory(CategoriesOfProducts.ACCESSORIES, "Shoes");
        searchResultsPage.filterByPrice("300", "399");
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        Assert.assertTrue(verifyPriceFilter(productDetailsPage.getProductPrice(), "300", "399"));
    }

    @Test (groups = {"regression"})
    public void filterByColorTest() {
        navigationPage = new NavigationPage(driver);
        searchResultsPage = navigationPage.navigateToSubcategory(CategoriesOfProducts.MEN, "Pants & Denim");
        searchResultsPage.filterByColor("Indigo");
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        Assert.assertTrue(productDetailsPage.getAvailableColors().contains("Indigo"));
    }

    @Test (groups = {"regression"})
    public void filterByOccasionTest() {
        navigationPage = new NavigationPage(driver);
        searchResultsPage = navigationPage.navigateToSubcategory(CategoriesOfProducts.WOMEN, "Dresses & Skirts");
        searchResultsPage.filterByOccasion("Career");
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        Assert.assertTrue(productDetailsPage.getProductSpecs().contains("Career"));
    }

    @Test (groups = "regression")
    public void filterBySizeTest() {
        navigationPage = new NavigationPage(driver);
        searchResultsPage = navigationPage.navigateToSubcategory(CategoriesOfProducts.MEN, "Pants & Denim");
        searchResultsPage.filterBySize("33");
        productDetailsPage = searchResultsPage.clickOnRandomProduct();
        Assert.assertTrue(productDetailsPage.getAvailableSizes().contains("33"));
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
