package Tests;

import Pages.LoginPage;
import Pages.MyAccountPage;
import Pages.NavigationPage;
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

    @BeforeMethod
    public void beforeMethod() {
        driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
        driver.manage().window().maximize();
        driver.get("http://demo-store.seleniumacademy.com");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
