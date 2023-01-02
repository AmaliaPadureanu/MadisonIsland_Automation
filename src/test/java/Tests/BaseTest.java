package Tests;

import Pages.LoginPage;
import Pages.MyAccountPage;
import Utils.BrowserTypes;
import Utils.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver driver;

    LoginPage loginPage;
    MyAccountPage myAccountPage;

    @BeforeTest
    public void beforeTest() {
        driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
