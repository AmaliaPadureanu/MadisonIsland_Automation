package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class SearchResultsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[1]/p")
    private WebElement searchResultsNote;

    @FindBy(how = How.XPATH, using = "//h2[@class='product-name']")
    private List<WebElement> searchItemsNames;

    @FindBy(how = How.XPATH, using = "//div[@class='price-box']")
    private List<WebElement> searchItemsPrices;

    @FindBy(how = How.XPATH, using = "(//select[@title='Sort By'])[1]")
    private WebElement sortBySelect;

    @FindBy(how = How.XPATH, using = "//li[@class='item last']/a")
    private List<WebElement> productsReturnedBySearch;

    @FindBy(how = How.XPATH, using = "//dd[1]//ol[1]//li")
    private List<WebElement> priceFilterOptions;

    @FindBy(how = How.XPATH, using = "//dd[2]//ol[1]//li//img")
    private List<WebElement> colorFilterOptions;

    @FindBy(how = How.XPATH, using = "//dd[3]//ol[1]//li")
    private List<WebElement> occasionFilterOptions;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getSearchResultsNote() {
        return searchResultsNote.getText();
    }

    public void sortBy(String option) {
        Select sortBy = new Select(sortBySelect);
        sortBy.selectByVisibleText(option);
    }

    public LinkedList<String> getItemInfo(String option) {
        if (option == "Name") {
            LinkedList<String> searchResultsTitles = new LinkedList<>();
            for (WebElement item : searchItemsNames) {
                searchResultsTitles.add(item.getText());
            }
            return searchResultsTitles;
        } else if (option == "Price") {
            LinkedList<String> searchResultsPrices = new LinkedList<>();
            for (WebElement item : searchItemsPrices) {
                if (item.getText().length() < 7) {
                    searchResultsPrices.add(item.getText().substring(1, 3));
                } else {
                    searchResultsPrices.add(item.getText().substring(1, 4));
                }
            }
            return searchResultsPrices;
        }
        return null;
    }

    public ProductDetailsPage clickOnRandomProduct() {
        productsReturnedBySearch.get(getRandomNumber(0, productsReturnedBySearch.size())).click();
        return new ProductDetailsPage(driver);
    }


    public void filterByPrice(String lowerBound, String upperBound) {
        for (WebElement option : priceFilterOptions) {
            if(option.getText().contains(lowerBound) && option.getText().contains(upperBound)) {
                option.click();
                break;
            }
        }
    }

    public void filterByColor(String color) {
        for (WebElement option : colorFilterOptions) {
            if (option.getAttribute("title").equals(color)) {
                option.click();
                break;
            }
        }
    }


    public void filterByOccasion(String occasion) {
        for (WebElement option : occasionFilterOptions) {
            if(option.getText().contains(occasion)) {
                option.click();
                break;
            }
        }
    }
}
