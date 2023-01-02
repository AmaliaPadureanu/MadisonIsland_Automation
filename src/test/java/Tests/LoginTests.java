package Tests;

import Pages.LoginPage;
import Pages.NavigationPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @Test
    public void login() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        navigationPage.navigateToLogin();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        myAccountPage = loginPage.loginWith("test@e.com", "Automation");
        Assert.assertTrue(myAccountPage.getPageTitle().equals("My Account"));
    }


}
