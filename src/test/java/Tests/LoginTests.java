package Tests;

import Pages.ForgotYourPasswordPage;
import Pages.LoginPage;
import Pages.NavigationPage;
import Tests.ObjectModels.LoginModel;
import Utils.ConstantUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LoginTests extends BaseTest{

    @DataProvider(name = "jsonInvalidLoginDP")
    public Iterator<Object[]> jsonDPCollection() throws IOException {
        Collection<Object[]> dataProvider = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(ConstantUtils.INVALID_LOGIN_DATA_JSON_PATH);
        LoginModel[] loginModels = objectMapper.readValue(file, LoginModel[].class);

        for (LoginModel loginModel : loginModels) {
            dataProvider.add(new Object[] {loginModel});
        }
        return dataProvider.iterator();
    }

    @Test(dataProvider = "jsonInvalidLoginDP", priority = 1, groups = {"regression"})
    public void invalidLoginTest(LoginModel loginModel) {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        loginPage.loginWith(loginModel.getAccount().getEmail(), loginModel.getAccount().getPassword());

        Assert.assertTrue(loginPage.checkError(loginModel.getEmailError(), "userError"));
        Assert.assertTrue(loginPage.checkError(loginModel.getPasswordError(), "passwordError"));
        Assert.assertTrue(loginPage.checkError(loginModel.getInvalidUserOrPasswordError(), "invalidUserOrPasswordError"));
        Assert.assertTrue(loginPage.checkError(loginModel.getInvalidUserOrPasswordErrorPopup(), "invalidUserOrPasswordErrorPopup"));
    }

    @Test (groups = {"smoke", "regression"})
    public void validLoginTest() {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        myAccountPage = loginPage.loginWith(ConstantUtils.USER, ConstantUtils.PASSWORD);
        Assert.assertTrue(myAccountPage.getPageTitle().equals("My Account"));
    }

    @Test (groups = {"regression"})
    public void forgotPasswordTest() {
        navigationPage = new NavigationPage(driver);
        loginPage = navigationPage.navigateToLogin();
        loginPage.forgotYourPassword();
        forgotYourPasswordPage = new ForgotYourPasswordPage(driver);
        forgotYourPasswordPage.submitEmailAddress(ConstantUtils.USER);
        Assert.assertTrue(loginPage.getResetPasswordMessage().contains("you will receive an email with a link to reset your password"));
    }

    @Test (dependsOnMethods = {"validLoginTest"}, groups = {"regression"})
    public void logoutTest() {
        navigationPage = new NavigationPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.logout();
        Assert.assertEquals(loginPage.getLogoutMessage(), "You have logged out and will be redirected to our homepage in 5 seconds.");
    }
}
