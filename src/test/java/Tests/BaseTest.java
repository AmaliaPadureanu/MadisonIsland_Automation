package Tests;

import Pages.*;
import Utils.BrowserTypes;
import Utils.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
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
