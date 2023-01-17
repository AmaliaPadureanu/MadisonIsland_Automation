package Tests;

import Pages.*;
import Utils.BrowserUtils;
import Utils.GenericUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver driver;

    public static String configFile = "src\\test\\resources\\config.properties";
    //String browser = BrowserUtils.getBrowserFromEnvironmentVariables("autoBrowser");
    String browser = GenericUtils.getBrowser(configFile);
    //String baseURL = "http://demo-store.seleniumacademy.com";
    String baseURL = GenericUtils.getBaseURL(configFile);

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

    @BeforeTest
    public void beforeTest() {
        //driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
        driver = BrowserUtils.getBrowser(browser);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
