package Tests;

import Pages.LoginPage;
import Pages.NavigationPage;
import Tests.ObjectModels.RegisterModel;
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

public class RegisterTests extends BaseTest {

    @DataProvider(name = "jsonValidRegisterDP")
    public Iterator<Object[]> jsonDPCollectionValid() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\validRegisterData.json");
        RegisterModel[] registerModels = objectMapper.readValue(file, RegisterModel[].class);

        for (RegisterModel registerModel : registerModels) {
            dp.add(new Object[] {registerModel});
        }
        return dp.iterator();
    }

    @DataProvider(name = "jsonInvalidRegisterDP")
    public Iterator<Object[]> jsonDPCollectionInvalid() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\invalidRegisterData.json");
        RegisterModel[] registerModels = objectMapper.readValue(file, RegisterModel[].class);

        for (RegisterModel registerModel : registerModels) {
            dp.add(new Object[] {registerModel});
        }
        return dp.iterator();
    }

    public void registerActions(RegisterModel registerModel) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        registerPage = navigationPage.navigateToRegister();
        registerPage.registerUser(registerModel.getFirstName(), registerModel.getMiddleName(), registerModel.getLastName(),
                registerModel.getEmail(), registerModel.getPassword(), registerModel.getConfirmPassword(), registerModel.getSignUpForNewsletter());
        String expectedFirstNameError = registerModel.getFirstNameError();
        String expectedLastNameError = registerModel.getLastNameError();
        String expectedEmailError = registerModel.getEmailError();
        String expectedPasswordError = registerModel.getPasswordError();
        String expectedConfirmPasswordError = registerModel.getConfirmPasswordError();
        String expectedEmailErrorPopup = registerModel.getEmailErrorPopup();
        Assert.assertTrue(registerPage.checkError(expectedFirstNameError, "firstNameError"));
        Assert.assertTrue(registerPage.checkError(expectedLastNameError, "lastNameError"));
        Assert.assertTrue(registerPage.checkError(expectedEmailError, "emailError"));
        Assert.assertTrue(registerPage.checkError(expectedPasswordError, "passwordError"));
        Assert.assertTrue(registerPage.checkError(expectedConfirmPasswordError, "confirmPasswordError"));
        Assert.assertTrue(registerPage.checkError(expectedEmailErrorPopup, "emailErrorPopup"));
    }

    @Test (dataProvider = "jsonValidRegisterDP")
    public void validRegisterTest(RegisterModel registerModel) {
        registerActions(registerModel);
        loginPage = new LoginPage(driver);
        loginPage.logout();
    }

    @Test (dataProvider = "jsonInvalidRegisterDP", priority = 1)
    public void invalidRegisterTest(RegisterModel registerModel) {
        registerActions(registerModel);
    }

}
