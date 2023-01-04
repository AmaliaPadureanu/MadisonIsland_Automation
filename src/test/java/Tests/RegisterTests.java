package Tests;

import Pages.NavigationPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterTests extends BaseTest {

    @DataProvider
    public Object[][] validRegisterDP() {
        return new Object[][] {
                {"John", "Edward", "Doe", "jedoe@mail.com", "thisisapassword123", "thisisapassword123", true}
        };
    }

    @Test (dataProvider = "validRegisterDP")
    public void validRegisterTest(String firstName, String middleName, String lastName, String email, String pass,
                                  String confirmPass, Boolean subscribeToNewsletter) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        registerPage = navigationPage.navigateToRegister();
        registerPage.registerUser(firstName, middleName, lastName, email, pass, confirmPass, subscribeToNewsletter);
    }

}
