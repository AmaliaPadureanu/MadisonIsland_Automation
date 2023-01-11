package Tests;

import Pages.SearchPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {

    @DataProvider(name = "validSearchDP")
    public Object[][] validSearchDP() {
        return new Object[][] {
            {"dress"},
            {"pants"},
            {"shirt"},
            {"blouse"}
        };
    }

    @DataProvider(name = "invalidSearchDP")
    public Object[][] invalidSearchDP() {
        return new Object[][] {
                {"asdfhjhdgsf", "Your search returns no results."},
                {"23454352", "Your search returns no results."},
                {"$%#%$%^$&", "Your search returns no results."},
                {"#@SAFfFS435", "Your search returns no results."}
        };
    }

    @Test (dataProvider = "validSearchDP")
    public void validSearchTest(String product) {
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchResultsPage = searchPage.search(product);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(product));
    }

    @Test (dataProvider = "invalidSearchDP")
    public void invalidSearchTest(String product, String searchResultsNote) {
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchResultsPage = searchPage.search(product);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(product));
        Assert.assertEquals(searchResultsPage.getSearchResultsNote(), searchResultsNote);
    }
}
