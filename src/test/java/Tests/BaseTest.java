package Tests;

import Utils.BrowserTypes;
import Utils.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
