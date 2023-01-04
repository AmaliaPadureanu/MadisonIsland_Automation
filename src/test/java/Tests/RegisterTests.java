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
                {"John", "Edward", "Doe", "jjedoe@qq.com", "thisisapassword123", "thisisapassword123", true},
                {"Sarah", "Jessica", "Doe", "ssjdoe@ww.com", "thisisapassword123", "thisisapassword123", false},
                {"Alex", "Andrew", "Jones", "aaajones@ww.com", "thisisapassword123", "thisisapassword123", true},
                {"Sam", "", "Smith", "ssmithhs@ww.com", "thisisapassword123", "thisisapassword123", false},
            };
    }

    @DataProvider
    public Object[][] invalidRegisterDP() {
        return new Object[][] {
                {"", "Edward", "Doe", "jddoee@p.com", "thisisapassword123", "thisisapassword123", true, "This is a required field.", "", "", "", "", ""},
                {"John", "Edward", "", "jjddoee@p.com", "thisisapassword123", "thisisapassword123", false, "", "This is a required field.", "", "", "", ""},
                {"John", "Edward", "Doe", "", "thisisapassword123", "thisisapassword123", true, "", "", "This is a required field.", "", "", ""},
                {"John", "Edward", "Doe", "jjjdddooe@j.com", "", "thisisapassword123", false, "", "", "", "", "This is a required field.", "Please make sure your passwords match."},
                {"John", "Edward", "Doe", "jjjjddddoee@qk.com", "thisisapassword123", "", true, "", "", "", "", "", "This is a required field."},
                {"John", "Edward", "Doe", "jjjjjdddddoooe@hq.com", "thisisapassword123", "thisisanotherpassword", false, "", "", "", "", "", "Please make sure your passwords match."},
                {"", "", "", "", "", "", false, "This is a required field.", "This is a required field.", "This is a required field.", "", "This is a required field.", "This is a required field."},
                {"John", "Edward", "Doe", "@jddoee@p.com", "thisisapassword123", "thisisapassword123", true, "", "", "", "Please enter a part followed by '@'.", "", ""},
                {"John", "Edward", "Doe", "johndoedoe", "thisisapassword123", "thisisapassword123", true, "", "", "", "Please include an '@' in the email address.", "", ""},
                {"John", "Edward", "Doe", "johndoedoe@", "thisisapassword123", "thisisapassword123", true, "", "", "", "Please enter a part following '@'", "", ""},
                {"John", "Edward", "Doe", "johndoedoe@abc.", "thisisapassword123", "thisisapassword123", true, "", "", "", "'.' is used at a wrong position", "", ""},
                {"John", "Edward", "Doe", "johndoedoe@abc$", "thisisapassword123", "thisisapassword123", true, "", "", "", "A part following '@' should not contain the symbol", "", ""},
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

    @Test (dataProvider = "invalidRegisterDP")
    public void invalidRegisterTest(String firstName, String middleName, String lastName, String email, String pass,
                                  String confirmPass, Boolean subscribeToNewsletter, String firstNameWarning,
                                  String lastNameWarning, String emailWarning, String emailFromPopupWarning, String passWarning,
                                  String confirmPassWarning) {
        navigationPage = PageFactory.initElements(driver, NavigationPage.class);
        registerPage = navigationPage.navigateToRegister();
        registerPage.registerUser(firstName, middleName, lastName, email, pass, confirmPass, subscribeToNewsletter);
        Assert.assertEquals(registerPage.verifyFirstNameWarningMessage(), firstNameWarning);
        Assert.assertEquals(registerPage.verifyLastNameWarningMessage(), lastNameWarning);
        Assert.assertEquals(registerPage.verifyEmailWarningMessage(), emailWarning);
        Assert.assertTrue(registerPage.verifyEmailMessageFromPopup().contains(emailFromPopupWarning));
        Assert.assertEquals(registerPage.verifyPasswordWarningMessage(), passWarning);
        Assert.assertEquals(registerPage.verifyConfirmPasswordWarningMessage(), confirmPassWarning);
    }

}
