package Tests;

import Pages.AddressBookPage;
import Pages.NavigationPage;
import Pages.NewsletterSubscriptionsPage;
import Tests.ObjectModels.EditAccountInformationModel;
import Tests.ObjectModels.EditAddressModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.sql.*;
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

   @DataProvider(name = "invalidEditAccountInformationDP")
   public Iterator<Object[]> SQLDpCollectionInvalid() throws SQLException {
      Collection<Object[]> dataProvider = new ArrayList<>();

      Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort
              + "/" + dbSchema, dbUser, dbPassword);
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM editaccountinformation_negative");

      while (resultSet.next()) {
         EditAccountInformationModel editAccountInformationModel = new EditAccountInformationModel(
                 resultSet.getString("firstname"),
                 resultSet.getString("middlename"),
                 resultSet.getString("lastname"),
                 resultSet.getString("email"),
                 resultSet.getString("firstnameError"),
                 resultSet.getString("lastnameError"),
                 resultSet.getString("emailError"),
                 resultSet.getString("emailErrorPopup"));
         dataProvider.add(new Object[] {editAccountInformationModel});
      }

      return dataProvider.iterator();
   }

   @DataProvider(name = "jsonInvalidEditAddressDP")
   public Iterator<Object[]> jsonInvalidDPCollection() throws IOException {
      Collection<Object[]> dataProvider = new ArrayList<>();
      ObjectMapper objectMapper = new ObjectMapper();
      File file = new File("src\\test\\resources\\Data\\invalidAddressData.json");
      EditAddressModel[] editAddressModels = objectMapper.readValue(file, EditAddressModel[].class);

      for (EditAddressModel editAddressModel : editAddressModels) {
         dataProvider.add(new Object[] {editAddressModel});
      }

      return dataProvider.iterator();
   }

   @DataProvider(name = "jsonValidEditAddressDP")
   public Iterator<Object[]> jsonValidDPCollection() throws IOException {
      Collection<Object[]> dataProvider = new ArrayList<>();
      ObjectMapper objectMapper = new ObjectMapper();
      File file = new File("src\\test\\resources\\Data\\validAddressData.json");
      EditAddressModel[] editAddressModels = objectMapper.readValue(file, EditAddressModel[].class);

      for (EditAddressModel editAddressModel : editAddressModels) {
         dataProvider.add(new Object[] {editAddressModel});
      }

      return dataProvider.iterator();
   }

   public void editAddressActions(EditAddressModel editAddressModel) {
      navigationPage = new NavigationPage(driver);
      accountDashboardPage = navigationPage.navigateToAccountDashboard();
      addressBookPage = accountDashboardPage.goToAddressBook();
      editAddressPage = addressBookPage.goToEditAddressBook();
      editAddressPage.editAddress(editAddressModel.getContactInformation().getFirstName(), editAddressModel.getContactInformation().getMiddleName(),
              editAddressModel.getContactInformation().getLastName(), editAddressModel.getContactInformation().getCompany(),
              editAddressModel.getContactInformation().getTelephone(), editAddressModel.getContactInformation().getFax(),
              editAddressModel.getAddress().getStreetAddress1(), editAddressModel.getAddress().getStreetAddress2(),
              editAddressModel.getAddress().getCity(), editAddressModel.getAddress().getState(),
              editAddressModel.getAddress().getZipCode(), editAddressModel.getAddress().getCountry());

      Assert.assertTrue(editAddressPage.checkError(editAddressModel.getFirstNameError(), "firstNameError"));
      Assert.assertTrue(editAddressPage.checkError(editAddressModel.getLastNameError(), "lastNameError"));
      Assert.assertTrue(editAddressPage.checkError(editAddressModel.getTelephoneError(), "telephoneError"));
      Assert.assertTrue(editAddressPage.checkError(editAddressModel.getStreetAddress1Error(), "streetAddress1Error"));
      Assert.assertTrue(editAddressPage.checkError(editAddressModel.getCityError(), "cityError"));
      Assert.assertTrue(editAddressPage.checkError(editAddressModel.getZipCodeError(), "zipCodeError"));
      Assert.assertTrue(editAddressPage.checkError(editAddressModel.getCountryError(), "countryError"));
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
       newsletterSubscriptionsPage = new NewsletterSubscriptionsPage(driver);
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
   public void invalidEditAccountInformationTest(EditAccountInformationModel editAccountInformationModel) {
      navigationPage = new NavigationPage(driver);
      accountDashboardPage = navigationPage.navigateToAccountDashboard();
      accountInformationPage = accountDashboardPage.goToAccountInformation();
      accountInformationPage.editContactInformation(editAccountInformationModel.getFirstname(), editAccountInformationModel.getMiddlename(),
              editAccountInformationModel.getLastname(), editAccountInformationModel.getEmail());

      Assert.assertTrue(accountInformationPage.checkError(editAccountInformationModel.getFirstnameError(), "firstNameError"));
      Assert.assertTrue(accountInformationPage.checkError(editAccountInformationModel.getLastnameError(), "lastNameError"));
      Assert.assertTrue(accountInformationPage.checkError(editAccountInformationModel.getEmailError(), "emailError"));
      Assert.assertTrue(accountInformationPage.checkError(editAccountInformationModel.getEmailErrorPopup(), "emailPopupError"));
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
