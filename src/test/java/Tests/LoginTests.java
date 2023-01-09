package Tests;

import Pages.ForgotYourPasswordPage;
import Pages.LoginPage;
import Pages.NavigationPage;
import Tests.ObjectModels.LoginModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LoginTests extends BaseTest{

    @DataProvider
    public Object[][] validLoginDP() {
        return new Object[][] {
                {"test@e.com", "Automation"}
        };
    }

    @DataProvider(name = "jsonInvalidLoginDP")
    public Iterator<Object[]> jsonDPCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\invalidLoginData.json");
        LoginModel[] loginModels = objectMapper.readValue(file, LoginModel[].class);

        for (LoginModel loginModel : loginModels) {
            dp.add(new Object[] {loginModel});
        }
        return dp.iterator();
    }

    private void loginActions(LoginModel loginModel) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        LoginPage loginPage1 = navigationPage.navigateToLogin();
        loginPage1.loginWith(loginModel.getAccount().getEmail(), loginModel.getAccount().getPassword());
        String expectedEmailError = loginModel.getEmailError();
        String expectedPasswordError = loginModel.getPasswordError();
        String expectedInvalidUserOrPasswordError = loginModel.getInvalidUserOrPasswordError();
        String expectedInvalidUserOrPasswordErrorPopup = loginModel.getInvalidUserOrPasswordErrorPopup();
        Assert.assertTrue(loginPage1.checkError(expectedEmailError, "userError"));
        Assert.assertTrue(loginPage1.checkError(expectedPasswordError, "passwordError"));
        Assert.assertTrue(loginPage1.checkError(expectedInvalidUserOrPasswordError, "invalidUserOrPasswordError"));
        Assert.assertTrue(loginPage1.checkError(expectedInvalidUserOrPasswordErrorPopup, "invalidUserOrPasswordErrorPopup"));
    }

    @Test(dataProvider = "jsonInvalidLoginDP", priority = 1)
    public void loginWithJsonDataTest(LoginModel loginModel) {
        loginActions(loginModel);
    }

    @Test (dataProvider = "validLoginDP")
    public void validLoginTest(String email, String password) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = navigationPage.navigateToLogin();
        myAccountPage = loginPage.loginWith(email, password);
        Assert.assertTrue(myAccountPage.getPageTitle().equals("My Account"));
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

    @Test (dependsOnMethods = {"validLoginTest"})
    public void logoutTest() {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        loginPage = new LoginPage(driver);
        loginPage.logout();
        Assert.assertEquals(loginPage.getLogoutMessage(), "You have logged out and will be redirected to our homepage in 5 seconds.");
    }
}
