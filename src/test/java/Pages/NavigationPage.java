package Pages;

import Utils.CategoriesOfProducts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class NavigationPage extends BasePage {

    @FindBy(how = How.CSS, using = "#header > div > div.skip-links > div > a")
    private WebElement accountLink;

    @FindBy(how = How.CSS, using = "#header-account > div > ul > li.last > a")
    private WebElement loginLink;

    @FindBy(how = How.CSS, using = "#header-account > div > ul > li:nth-child(5) > a")
    private WebElement registerLink;

    @FindBy(how = How.XPATH, using = "//*[@id=\"header-account\"]/div/ul/li[1]/a")
    private WebElement myAccountLink;

    @FindBy(how = How.XPATH, using = "//*[@id=\"nav\"]/ol/li[1]/a")
    private WebElement womenCategory;

    @FindBy(how = How.XPATH, using = "//header[@id='header']//li[1]//ul/li")
    private List<WebElement> womenSubcategories;

    @FindBy(how = How.XPATH, using = "//*[@id=\"nav\"]/ol/li[2]/a")
    private WebElement menCategory;

    @FindBy(how = How.XPATH, using = "//header[@id='header']//li[2]//ul/li")
    private List<WebElement> menSubcategories;

    @FindBy(how = How.XPATH, using = "//*[@id=\"nav\"]/ol/li[3]/a")
    private WebElement accessoriesCategory;

    @FindBy(how = How.XPATH, using = "//header[@id='header']//li[3]//ul/li")
    private List<WebElement> accessoriesSubcategories;

    @FindBy(how = How.XPATH, using = "//*[@id=\"nav\"]/ol/li[4]/a")
    private WebElement homeAndDecorCategory;

    @FindBy(how = How.XPATH, using = "//header[@id='header']//li[4]//ul/li")
    private List<WebElement> homeAndDecorSubcategories;

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLogin() {
        accountLink.click();
        loginLink.click();
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegister() {
        accountLink.click();
        registerLink.click();
        return new RegisterPage(driver);
    }

    public AccountDashboardPage navigateToAccountDashboard() {
        accountLink.click();
        myAccountLink.click();
        return new AccountDashboardPage(driver);
    }

    public SearchResultsPage navigateToRandomCategoryOfProducts(CategoriesOfProducts section) {
        Actions action = new Actions(driver);
        switch (section) {
            case WOMEN -> {
                action.moveToElement(womenCategory).perform();
                womenSubcategories.get(getRandomNumber(1, womenSubcategories.size())).click();
            }
            case MEN -> {
                action.moveToElement(menCategory).perform();
                menSubcategories.get(getRandomNumber(1, menSubcategories.size())).click();
            }
            case ACCESSORIES -> {
                action.moveToElement(accessoriesCategory).perform();
                accessoriesSubcategories.get(getRandomNumber(1, accessoriesSubcategories.size())).click();
            }
            case HOME -> {
                action.moveToElement(homeAndDecorCategory).perform();
                homeAndDecorSubcategories.get(getRandomNumber(1, homeAndDecorSubcategories.size())).click();
            }
        }
        return new SearchResultsPage(driver);
    }
}
