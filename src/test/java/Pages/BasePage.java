package Pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Random;

@Getter
public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public int getRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
        return randomNumber;
    }
}
