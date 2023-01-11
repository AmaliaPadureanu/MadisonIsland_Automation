package Tests;

import Pages.SearchPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;

public class SearchTests extends BaseTest {

    @DataProvider(name = "basicValidSearchDP")
    public Object[][] validSearchDP() {
        return new Object[][] {
            {"dress"},
            {"pants"},
            {"shirt"},
            {"blouse"}
        };
    }

    @DataProvider(name = "basicInvalidSearchDP")
    public Object[][] invalidSearchDP() {
        return new Object[][] {
                {"asdfhjhdgsf", "Your search returns no results."},
                {"23454352", "Your search returns no results."},
                {"$%#%$%^$&", "Your search returns no results."},
                {"#@SAFfFS435", "Your search returns no results."}
        };
    }

    @DataProvider(name = "searchAndSortDp")
    public Object[][] searchAndSortDP() {
        return new Object[][] {
                {"dress", "Name"},
                {"pants", "Name"},
                {"blouse", "Price"},
                {"pant", "Name"},
                {"blazer", "Price"}
        };
    }

    @Test (dataProvider = "basicValidSearchDP")
    public void basicValidSearchTest(String product) {
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchResultsPage = searchPage.search(product);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(product));
    }

    @Test (dataProvider = "basicInvalidSearchDP")
    public void basicInvalidSearchTest(String product, String searchResultsNote) {
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchResultsPage = searchPage.search(product);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(product));
        Assert.assertEquals(searchResultsPage.getSearchResultsNote(), searchResultsNote);
    }

    @Test (dataProvider = "searchAndSortDp")
    public void searchAndSortTest(String product, String criteria) {
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchResultsPage = searchPage.search(product);
        List<String> sortedList = sort(searchResultsPage.getItemInfo(criteria));
        searchResultsPage.sortBy(criteria);
        Assert.assertTrue(searchResultsPage.getPageTitle().contains(product));
        Assert.assertEquals(searchResultsPage.getItemInfo(criteria), sortedList);
    }

    private List<String> sort (List<String> list) {
       return list.stream().sorted().collect(Collectors.toList());
    }
}
