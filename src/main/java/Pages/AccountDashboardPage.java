package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDashboardPage extends BasePage {

    private By afterRegisterMessage = By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div/ul/li/ul/li/span");

    public AccountDashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getAfterRegisterMessage() {
        return find(afterRegisterMessage).getText();
    }
}
