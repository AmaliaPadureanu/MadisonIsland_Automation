package Tests;

import Pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @Test
    public void login() {
        driver.get("http://demo-store.seleniumacademy.com/customer/account/login/");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        System.out.println(loginPage.getPageTitle());
        myAccountPage = loginPage.loginWith("test@e.com", "Automation");
        Assert.assertTrue(myAccountPage.getPageTitle().equals("My Account"));
    }


}
