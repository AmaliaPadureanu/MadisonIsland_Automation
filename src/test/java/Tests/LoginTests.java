package Tests;

import Pages.ForgotYourPasswordPage;
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

    @DataProvider
    public Object[][] invalidCredentialsLoginDP() {
        return new Object[][] {
                {"test@e.com", "pass", "", "Please enter 6 or more characters. Leading or trailing spaces will be ignored."},
//                {"sjshrjieokfrjgkroj", "Automation", "Please enter a valid email address. For example johndoe@domain.com.", ""},
//                {"sjshrjieokfrjgkroj@", "Automation", "Please enter a valid email address. For example johndoe@domain.com.", ""},
                {"asdfghjjjr@233", "Automation", "Please enter a valid email address. For example johndoe@domain.com.", ""},
                {"asdfghjjjr@abc", "Automation", "Please enter a valid email address. For example johndoe@domain.com.", ""},
//                {"asdfghjjjr@abc.", "Automation", "Please enter a valid email address. For example johndoe@domain.com.", ""},
//                {"asdfghjjjr@abc$", "Automation", "Please enter a valid email address. For example johndoe@domain.com.", ""},
                {".asdfghjjjr@abc.com", "Automation", "Please enter a valid email address. For example johndoe@domain.com.", ""},
//                {"@asdfghjjjr@abc.com", "Automation", "Please enter a valid email address. For example johndoe@domain.com.", ""}

        };
    }

    @Test (dataProvider = "validLoginDP", enabled = false)
    public void validLoginTest(String email, String password, String emailIsRequiredWarning, String passIsRequiredWarning) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        myAccountPage = loginPage.loginWith(email, password);
        Assert.assertEquals(loginPage.verifyEmailMessage(), emailIsRequiredWarning);
        //Assert.assertEquals(loginPage.verifyPassMessage(), passIsRequiredWarning);
        Assert.assertTrue(myAccountPage.getPageTitle().equals("My Account"));
    }

    @Test (dataProvider = "incompleteDataLoginDP", enabled = false)
    public void incompleteDataLoginTest(String email, String password, String emailIsRequiredWarning, String passIsRequiredWarning) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        loginPage.loginWith(email, password);
        Assert.assertEquals(loginPage.verifyEmailMessage(), emailIsRequiredWarning);
        Assert.assertEquals(loginPage.verifyPassMessage(), passIsRequiredWarning);
    }

    @Test (dataProvider = "wrongEmailOrPassLoginDP", enabled = false)
    public void invalidDataLoginTest(String email, String password, String wrongEmailOrPassError) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        loginPage.loginWith(email, password);
        Assert.assertEquals(loginPage.verifyInvalidCredentialsError(), wrongEmailOrPassError);
    }

    @Test (dataProvider = "invalidCredentialsLoginDP")
    public void invalidCredentialsTest(String email, String password, String invalidEmailError, String invalidPassError) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        loginPage.loginWith(email, password);
        Assert.assertEquals(loginPage.verifyEmailMessage(), invalidEmailError);
        Assert.assertEquals(loginPage.verifyPassMessage(), invalidPassError);
    }

    @Test
    public void forgotPasswordTest() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        loginPage.forgotYourPassword();
        forgotYourPasswordPage = PageFactory.initElements(driver, ForgotYourPasswordPage.class);
        forgotYourPasswordPage.submitEmailAddress("test@e.com");
        Assert.assertTrue(loginPage.getResetPasswordMessage().contains("you will receive an email with a link to reset your password"));
    }


}
