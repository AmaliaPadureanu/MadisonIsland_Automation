package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Map;

public class BrowserUtils {

    public static WebDriver getBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case ("chrome") : {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case ("firefox") : {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case ("edge") : {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            default: {
                System.out.println("Browser is not supported officially");
                return WebDriverManager.getInstance(browser).getWebDriver();
            }
        }
    }
    public static Browser getBrowser(BrowserTypes browserType) {

        switch (browserType.toString()) {
            case ("CHROME") : {
                return new ChromeBrowser();
            }
            case ("FIREFOX") : {
                return new FirefoxBrowser();
            }
            case ("EDGE") : {
                return new EdgeBrowser();
            }
            default : {
                System.out.println("Browser is not supported");
                return null;
            }
        }
    }

    public static String getBrowserFromEnvironmentVariables(String propertyName) {
        Map<String, String> environmentVariables = System.getenv();

        if (environmentVariables.containsKey(propertyName)) {
            return System.getenv(propertyName).toLowerCase();
        } else {
            return "CHROME";
        }
    }
}
