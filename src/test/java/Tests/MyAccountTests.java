package Tests;

import Pages.NavigationPage;
import Pages.NewsletterSubscriptionsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MyAccountTests extends BaseTest {

   @DataProvider
   public Object[][] validEditContactInformationDP() {
           return new Object[][] {
                   {"John", "R", "Doe", "jdoe@lo.com"},
                   {"Marta", "Luiza", "Popescu", "test@e.com"}
           };
   }

   @BeforeClass
    public void beforeMethod() {
       navigationPage = PageFactory.initElements(driver, NavigationPage.class);
       loginPage = navigationPage.navigateToLogin();
       loginPage.loginWith("test@e.com", "Automation");
   }

   @Test
    public void editNewsletterSubscriptionsTest() {
       navigationPage = PageFactory.initElements(driver, NavigationPage.class);
       accountDashboardPage = navigationPage.navigateToAccountDashboard();
       Boolean isUserSubscribed = accountDashboardPage.isUserSubscribed();
       accountDashboardPage.goToNewsletterSubscriptions();
       newsletterSubscriptionsPage = PageFactory.initElements(driver, NewsletterSubscriptionsPage.class);
       newsletterSubscriptionsPage.editSubscription();
       Assert.assertEquals(accountDashboardPage.getSubscriptionWasEditedMessage(),
               accountDashboardPage.verifyAfterSubscriptionWasEditedMessage(isUserSubscribed));
   }

   @Test (dataProvider = "validEditContactInformationDP")
   public void validEditAccountInformationTest(String firstName, String middleName, String lastName, String email) {
      navigationPage = PageFactory.initElements(driver, NavigationPage.class);
      accountDashboardPage = navigationPage.navigateToAccountDashboard();
      accountInformationPage = accountDashboardPage.goToAccountInformation();
      accountInformationPage.editContactInformation(firstName, middleName, lastName, email);
      Assert.assertEquals(accountDashboardPage.getContactInformationWasEditedMessage(), "The account information has been saved.");
      Assert.assertTrue(accountDashboardPage.getContactInformation().contains(firstName));
      Assert.assertTrue(accountDashboardPage.getContactInformation().contains(middleName));
      Assert.assertTrue(accountDashboardPage.getContactInformation().contains(lastName));
      Assert.assertTrue(accountDashboardPage.getContactInformation().contains(email));
   }

}
