package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavigationPage extends BasePage {

    @FindBy(how = How.CSS, using = "#header > div > div.skip-links > div > a")
    WebElement accountLink;

    @FindBy(how = How.CSS, using = "#header-account > div > ul > li.last > a")
    WebElement loginLink;

    @FindBy(how = How.CSS, using = "#header-account > div > ul > li:nth-child(5) > a")
    WebElement registerLink;

    @FindBy(how = How.XPATH, using = "//*[@id=\"header-account\"]/div/ul/li[1]/a")
    WebElement myAccountLink;

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLogin() {
        accountLink.click();
        loginLink.click();
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegister() {
        accountLink.click();
        registerLink.click();
        return new RegisterPage(driver);
    }

    public AccountDashboardPage navigateToAccountDashboard() {
        accountLink.click();
        myAccountLink.click();
        return new AccountDashboardPage(driver);
    }
}
