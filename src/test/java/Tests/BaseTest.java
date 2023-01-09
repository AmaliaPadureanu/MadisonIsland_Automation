package Tests;

import Pages.*;
import Utils.BrowserTypes;
import Utils.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver driver;

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

    @BeforeTest
    public void beforeTest() {
        driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
        driver.manage().window().maximize();
        driver.get("http://demo-store.seleniumacademy.com");
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
