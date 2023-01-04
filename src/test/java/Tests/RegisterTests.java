package Tests;

import Pages.NavigationPage;
import Utils.BrowserTypes;
import Utils.BrowserUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterTests extends BaseTest {

    @BeforeMethod
    public void beforeTest() {
        driver = BrowserUtils.getBrowser(BrowserTypes.CHROME).getDriver();
        driver.manage().window().maximize();
        driver.get("http://demo-store.seleniumacademy.com");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validRegisterDP() {
        return new Object[][] {
                {"John", "Edward", "Doe", "jedoe@asd.com", "thisisapassword123", "thisisapassword123", true},
                {"Sarah", "Jessica", "Doe", "sjdoe@asd.com", "thisisapassword123", "thisisapassword123", false},
                {"Alex", "Andrew", "Jones", "aajones@asd.com", "thisisapassword123", "thisisapassword123", true}
            };
    }

    @Test (dataProvider = "validRegisterDP")
    public void validRegisterTest(String firstName, String middleName, String lastName, String email, String pass,
                                  String confirmPass, Boolean subscribeToNewsletter) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        registerPage = navigationPage.navigateToRegister();
        accountDashboardPage = registerPage.registerUser(firstName, middleName, lastName, email, pass, confirmPass, subscribeToNewsletter);
        Assert.assertEquals(accountDashboardPage.getAfterRegisterMessage(), "Thank you for registering with Madison Island.");
    }

}
