package Tests;

import Pages.*;
import Utils.BrowserUtils;
import Utils.ConstantUtils;
import Utils.GenericUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver driver;

    //String browser = BrowserUtils.getBrowserFromEnvironmentVariables("autoBrowser");
    String browser = GenericUtils.getBrowserFromConfig(ConstantUtils.CONFIG_FILE);
    //String baseURL = "http://demo-store.seleniumacademy.com";
    String baseURL = GenericUtils.getBaseURL(ConstantUtils.CONFIG_FILE);

    LoginPage loginPage;
    MyAccountPage myAccountPage;
    NavigationPage navigationPage;
    ForgotYourPasswordPage forgotYourPasswordPage;
    RegisterPage registerPage;
    WishlistPage wishlistPage;
    HomePage homePage;
    AccountDashboardPage accountDashboardPage;
    NewsletterSubscriptionsPage newsletterSubscriptionsPage;
    AccountInformationPage accountInformationPage;
    AddressBookPage addressBookPage;
    EditAddressPage editAddressPage;
    SearchPage searchPage;
    SearchResultsPage searchResultsPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;

    @BeforeTest (alwaysRun = true)
    public void beforeTest() {
        //driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
        driver = BrowserUtils.getBrowser(browser);
        //driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @AfterTest (alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }
}
