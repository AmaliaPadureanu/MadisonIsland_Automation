package Tests;

import Pages.NavigationPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @DataProvider
    public Object[][] validLoginDP() {
        return new Object[][] {
                {"test@e.com", "Automation", "", ""}
        };
    }

    @DataProvider
    public Object[][] incompleteDataLoginDP() {
        return new Object[][] {
                {"test@e.com", "", "", "This is a required field."},
                {"", "Automation", "This is a required field.", ""},
                {"", "", "This is a required field.", "This is a required field."}
        };
    }

    @DataProvider
    public Object[][] wrongEmailOrPassLoginDP() {
        return new Object[][] {
                {"test@e.com", "wrongPass", "Invalid login or password."},
                {"testwr@e.com", "Automation", "Invalid login or password."}
        };
    }

    @Test (dataProvider = "validLoginDP")
    public void validLoginTest(String email, String password, String emailIsRequiredWarning, String passIsRequiredWarning) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        myAccountPage = loginPage.loginWith(email, password);
        Assert.assertEquals(loginPage.verifyEmailIsRequiredWarning(), emailIsRequiredWarning);
        //Assert.assertEquals(loginPage.verifyPassIsRequiredWarning(), passIsRequiredWarning);
        Assert.assertTrue(myAccountPage.getPageTitle().equals("My Account"));
    }

    @Test (dataProvider = "incompleteDataLoginDP")
    public void incompleteDataLoginTest(String email, String password, String emailIsRequiredWarning, String passIsRequiredWarning) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        loginPage.loginWith(email, password);
        Assert.assertEquals(loginPage.verifyEmailIsRequiredWarning(), emailIsRequiredWarning);
        Assert.assertEquals(loginPage.verifyPassIsRequiredWarning(), passIsRequiredWarning);
    }

    @Test (dataProvider = "wrongEmailOrPassLoginDP")
    public void invalidDataLoginTest(String email, String password, String invalidCredentialsError) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        loginPage.loginWith(email, password);
        Assert.assertEquals(loginPage.verifyInvalidCredentialsError(), invalidCredentialsError);
    }


}
