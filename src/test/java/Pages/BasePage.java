package Pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void clearAndSendKeys(By locator, String text) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }
}