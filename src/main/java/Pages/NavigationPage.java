package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavigationPage extends BasePage {

    @FindBy(how = How.CSS, using = "#header > div > div.skip-links > div > a")
    @CacheLookup
    WebElement accountLink;

    @FindBy(how = How.CSS, using = "#header-account > div > ul > li.last > a")
    @CacheLookup
    WebElement loginLink;

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLogin() {
        accountLink.click();
        loginLink.click();
        return new LoginPage(driver);
    }
}
