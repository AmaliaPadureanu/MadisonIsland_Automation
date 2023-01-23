package Tests;

import Pages.AddressBookPage;
import Pages.NavigationPage;
import Pages.NewsletterSubscriptionsPage;
import Tests.ObjectModels.EditAddressModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyAccountTests extends BaseTest {

   @DataProvider
   public Object[][] validEditAccountInformationDP() {
           return new Object[][] {
                   {"John", "R", "Doe", "jdoe@lo.com"},
                   {"Marta", "Luiza", "Popescu", "test@e.com"}
           };
   }

   @DataProvider
   public Object[][] invalidEditAccountInformationDP() {
      return new Object[][] {
              {"", "", "", "", "This is a required field.", "This is a required field.", "This is a required field.", ""},
              {"", "Luiza", "", "martaluiza@", "", "", "", "Please enter a part following '@'"},
              {"Marta", "Luiza", "", "", "", "This is a required field.", "This is a required field.", ""},
              {"Marta", "Luiza", "", "martaluiza", "", "", "", "Please include an '@' in the email address."},
              {"", "", "", "martaluiza@abc", "This is a required field.", "This is a required field.", "Please enter a valid email address. For example johndoe@domain.com.", ""},
              {"Marta", "Luiza", "", "martaluiza@abc.com.", "", "", "", "'.' is used at a wrong position"},
              {"Marta", "Luiza", "", "martaluiza@abc.com$.", "", "", "", "A part following '@' should not contain the symbol"},
              {"", "", "Popescu", ".martaluiza@abc.com", "This is a required field.", "", "Please enter a valid email address. For example johndoe@domain.com.", ""},
      };
   }

   @DataProvider
   public Object[][] changePasswordDP() {
      return new Object[][] {
              {"Automation", "Automation1", "Automation1"},
              {"Automation1", "Automation", "Automation"}
      };
   }

   @DataProvider
   public Object[][] invalidChangePasswordDP() {
      return new Object[][] {
              {"", "", "", "This is a required field.", "This is a required field.", "This is a required field."},
              {"Automation1", "Automation", "Automation", "", "", ""},
              {"Automation", "Automation123", "Automation321", "", "", "Please make sure your passwords match."},
              {"Automation", "", "Automation123", "", "This is a required field.", "Please make sure your passwords match."}
      };
   }

   @DataProvider(name = "jsonInvalidEditAddressDP")
   public Iterator<Object[]> jsonInvalidDPCollection() throws IOException {
      Collection<Object[]> dp = new ArrayList<>();
      ObjectMapper objectMapper = new ObjectMapper();
      File file = new File("src\\test\\resources\\Data\\invalidAddressData.json");
      EditAddressModel[] editAddressModels = objectMapper.readValue(file, EditAddressModel[].class);

      for (EditAddressModel editAddressModel : editAddressModels) {
         dp.add(new Object[] {editAddressModel});
      }
      return dp.iterator();
   }

   @DataProvider(name = "jsonValidEditAddressDP")
   public Iterator<Object[]> jsonValidDPCollection() throws IOException {
      Collection<Object[]> dp = new ArrayList<>();
      ObjectMapper objectMapper = new ObjectMapper();
      File file = new File("src\\test\\resources\\Data\\validAddressData.json");
      EditAddressModel[] editAddressModels = objectMapper.readValue(file, EditAddressModel[].class);

      for (EditAddressModel editAddressModel : editAddressModels) {
         dp.add(new Object[] {editAddressModel});
      }
      return dp.iterator();
   }

   public void editAddressActions(EditAddressModel editAddressModel) {
      navigationPage = new NavigationPage(driver);
      accountDashboardPage = navigationPage.navigateToAccountDashboard();
      addressBookPage = accountDashboardPage.goToAddressBook();
      editAddressPage = addressBookPage.goToEditAddressBook();
      editAddressPage.editAddress(editAddressModel.getContactInformation().getFirstName(), editAddressModel.getContactInformation().getMiddleName(),
              editAddressModel.getContactInformation().getLastName(), editAddressModel.getContactInformation().getCompany(), editAddressModel.getContactInformation().getTelephone(),
              editAddressModel.getContactInformation().getFax(), editAddressModel.getAddress().getStreetAddress1(), editAddressModel.getAddress().getStreetAddress2(),
              editAddressModel.getAddress().getCity(), editAddressModel.getAddress().getState(), editAddressModel.getAddress().getZipCode(), editAddressModel.getAddress().getCountry());
      String expectedFirstNameError = editAddressModel.getFirstNameError();
      String expectedLastNameError = editAddressModel.getLastNameError();
      String expectedTelephoneError = editAddressModel.getTelephoneError();
      String expectedStreetAddress1Error = editAddressModel.getStreetAddress1Error();
      String expectedCityError = editAddressModel.getCityError();
      String expectedZipCodeError = editAddressModel.getZipCodeError();
      String expectedCountryError = editAddressModel.getCountryError();
      Assert.assertTrue(editAddressPage.checkError(expectedFirstNameError, "firstNameError"));
      Assert.assertTrue(editAddressPage.checkError(expectedLastNameError, "lastNameError"));
      Assert.assertTrue(editAddressPage.checkError(expectedTelephoneError, "telephoneError"));
      Assert.assertTrue(editAddressPage.checkError(expectedStreetAddress1Error, "streetAddress1Error"));
      Assert.assertTrue(editAddressPage.checkError(expectedCityError, "cityError"));
      Assert.assertTrue(editAddressPage.checkError(expectedZipCodeError, "zipCodeError"));
      Assert.assertTrue(editAddressPage.checkError(expectedCountryError, "countryError"));
   }


   @BeforeClass (alwaysRun = true)
    public void beforeClass() {
       navigationPage = new NavigationPage(driver);
       loginPage = navigationPage.navigateToLogin();
       loginPage.loginWith("test@e.com", "Automation");
   }

   @Test (groups = {"regression"})
    public void editNewsletterSubscriptionsTest() {
       navigationPage = new NavigationPage(driver);
       accountDashboardPage = navigationPage.navigateToAccountDashboard();
       Boolean isUserSubscribed = accountDashboardPage.isUserSubscribed();
       accountDashboardPage.goToNewsletterSubscriptions();
       newsletterSubscriptionsPage = PageFactory.initElements(driver, NewsletterSubscriptionsPage.class);
       newsletterSubscriptionsPage.editSubscription();
       Assert.assertEquals(accountDashboardPage.getSubscriptionWasEditedMessage(),
               accountDashboardPage.verifyAfterSubscriptionWasEditedMessage(isUserSubscribed));
   }

   @Test (dataProvider = "changePasswordDP", groups = {"regression"})
   public void changePasswordTest(String currentPassword, String newPassword, String confirmPassword) {
      navigationPage = new NavigationPage(driver);
      accountDashboardPage = navigationPage.navigateToAccountDashboard();
      accountInformationPage = accountDashboardPage.goToChangePasswordSection();
      accountInformationPage.changePassword(currentPassword, newPassword, confirmPassword);
      Assert.assertEquals(accountDashboardPage.getAccountInformationWasEditedMessage(), "The account information has been saved.");
   }

   @Test (dataProvider = "invalidChangePasswordDP", groups = {"regression"})
   public void invalidChangePasswordTest(String currentPassword, String newPassword, String confirmNewPassword,
                                         String currentPasswordWarning, String newPasswordWarning, String confirmNewPasswordWarning) {
      navigationPage = new NavigationPage(driver);
      accountDashboardPage = navigationPage.navigateToAccountDashboard();
      accountInformationPage = accountDashboardPage.goToChangePasswordSection();
      accountInformationPage.changePassword(currentPassword,newPassword,confirmNewPassword);
      Assert.assertEquals(accountInformationPage.verifyCurrentPasswordWarning(), currentPasswordWarning);
      Assert.assertEquals(accountInformationPage.verifyNewPasswordWarning(), newPasswordWarning);
      Assert.assertEquals(accountInformationPage.verifyConfirmNewPasswordWarning(), confirmNewPasswordWarning);
   }

   @Test (dataProvider = "validEditAccountInformationDP", groups = {"regression"})
   public void validEditAccountInformationTest(String firstName, String middleName, String lastName, String email) {
      navigationPage = new NavigationPage(driver);
      accountDashboardPage = navigationPage.navigateToAccountDashboard();
      accountInformationPage = accountDashboardPage.goToAccountInformation();
      accountInformationPage.editContactInformation(firstName, middleName, lastName, email);
      Assert.assertEquals(accountDashboardPage.getAccountInformationWasEditedMessage(), "The account information has been saved.");
      Assert.assertTrue(accountDashboardPage.getContactInformation().contains(firstName));
      Assert.assertTrue(accountDashboardPage.getContactInformation().contains(middleName));
      Assert.assertTrue(accountDashboardPage.getContactInformation().contains(lastName));
      Assert.assertTrue(accountDashboardPage.getContactInformation().contains(email));
   }

   @Test (dataProvider = "invalidEditAccountInformationDP", groups = {"regression"})
   public void invalidEditAccountInformationTest(String firstName, String middleName, String lastName, String email,
                                                 String firstNameWarning, String lastNameWarning, String emailWarning, String emailWarningPopup) {
      navigationPage = new NavigationPage(driver);
      accountDashboardPage = navigationPage.navigateToAccountDashboard();
      accountInformationPage = accountDashboardPage.goToAccountInformation();
      accountInformationPage.editContactInformation(firstName, middleName, lastName, email);
      Assert.assertEquals(accountInformationPage.verifyFirstNameWarning(), firstNameWarning);
      Assert.assertEquals(accountInformationPage.verifyLastNameWarning(), lastNameWarning);
      Assert.assertEquals(accountInformationPage.verifyEmailWarning(), emailWarning);
      Assert.assertTrue(accountInformationPage.verifyEmailMessageFromPopup().contains(emailWarningPopup));
   }

   @Test (dataProvider = "jsonInvalidEditAddressDP", groups = {"regression"})
   public void invalidEditAddressTest(EditAddressModel editAddressModel) {
      editAddressActions(editAddressModel);
   }

   @Test(dataProvider = "jsonValidEditAddressDP", groups = {"regression"})
   public void validEditAddressTest(EditAddressModel editAddressModel) {
      editAddressActions(editAddressModel);
      addressBookPage = new AddressBookPage(driver);
      Assert.assertEquals(addressBookPage.getAddressWasSavedMessage(), "The address has been saved.");
   }


}
