package Pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }
}
