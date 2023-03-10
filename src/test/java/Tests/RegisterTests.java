package Tests;

import Pages.AccountDashboardPage;
import Pages.LoginPage;
import Pages.NavigationPage;
import Tests.ObjectModels.RegisterModel;
import Utils.ConstantUtils;
import Utils.GenericUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RegisterTests extends BaseTest {

    @DataProvider(name = "jsonInvalidRegisterDP")
    public Iterator<Object[]> jsonDPCollectionInvalid() throws IOException {
        Collection<Object[]> dataProvider = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(ConstantUtils.INVALID_REGISTER_DATA_JSON_PATH);
        RegisterModel[] registerModels = objectMapper.readValue(file, RegisterModel[].class);

        for (RegisterModel registerModel : registerModels) {
            dataProvider.add(new Object[] { registerModel });
        }

        return dataProvider.iterator();
    }

    @Test (groups = {"regression"})
    public void validRegisterTest() {
        navigationPage = new NavigationPage(driver);
        registerPage = navigationPage.navigateToRegister();

        String firstName = GenericUtils.createRandomString(10);
        String lastName = GenericUtils.createRandomString(10);
        String email = GenericUtils.createRandomString(6) + "@gmail.com";
        String password = GenericUtils.createRandomString(10);

        registerPage.registerUser(firstName, "", lastName, email, password, password, true);
        accountDashboardPage = new AccountDashboardPage(driver);
        Assert.assertEquals(accountDashboardPage.getSuccessfulRegistrationMessage(), "Thank you for registering with Madison Island.");

        loginPage = new LoginPage(driver);
        loginPage.logout();

        navigationPage.navigateToRegister();
        registerPage.registerUser(firstName,"", lastName, email, password, password, true);
        Assert.assertEquals(registerPage.getUserAlreadyExistsError(), "There is already an account with this email address. " +
                "If you are sure that it is your email address, click here to get your password and access your account.");
    }

    @Test (dataProvider = "jsonInvalidRegisterDP", priority = 1, groups = {"regression"})
    public void invalidRegisterTest(RegisterModel registerModel) {
        navigationPage = new NavigationPage(driver);
        registerPage = navigationPage.navigateToRegister();
        registerPage.registerUser(registerModel.getFirstName(), registerModel.getMiddleName(), registerModel.getLastName(),
                registerModel.getEmail(), registerModel.getPassword(), registerModel.getConfirmPassword(), registerModel.getSignUpForNewsletter());

        Assert.assertTrue(registerPage.checkError(registerModel.getFirstNameError(), "firstNameError"));
        Assert.assertTrue(registerPage.checkError(registerModel.getLastNameError(), "lastNameError"));
        Assert.assertTrue(registerPage.checkError(registerModel.getEmailError(), "emailError"));
        Assert.assertTrue(registerPage.checkError(registerModel.getPasswordError(), "passwordError"));
        Assert.assertTrue(registerPage.checkError(registerModel.getConfirmPasswordError(), "confirmPasswordError"));
        Assert.assertTrue(registerPage.checkError(registerModel.getEmailErrorPopup(), "emailErrorPopup"));
    }
}
