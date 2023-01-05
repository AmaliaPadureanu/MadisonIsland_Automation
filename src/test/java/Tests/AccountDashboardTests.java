package Tests;

import Pages.NavigationPage;
import Pages.NewsletterSubscriptionsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountDashboardTests extends BaseTest {

   @BeforeMethod
    public void beforeMethod() {
       navigationPage = PageFactory.initElements(driver, NavigationPage.class);
       loginPage = navigationPage.navigateToLogin();
       loginPage.loginWith("test@e.com", "Automation");
   }

   @Test
    public void editNewsletterSubscriptions() {
       navigationPage = PageFactory.initElements(driver, NavigationPage.class);
       accountDashboardPage = navigationPage.navigateToAccountDashboard();
       Boolean isUserSubscribed = accountDashboardPage.isUserSubscribed();
       accountDashboardPage.goToNewsletterSubscriptions();
       newsletterSubscriptionsPage = PageFactory.initElements(driver, NewsletterSubscriptionsPage.class);
       newsletterSubscriptionsPage.editSubscription();
       Assert.assertEquals(accountDashboardPage.getSubscriptionWasEditedMessage(),
               accountDashboardPage.verifyAfterSubscriptionWasEditedMessage(isUserSubscribed));
   }

}
