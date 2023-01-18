package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.Map;

public class BrowserUtils {
    
    public static WebDriver getBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case ("chrome") : {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(GenericUtils.getHeadlessModeOption(ConstantUtils.CONFIG_FILE));
                if (GenericUtils.startMaximized(ConstantUtils.CONFIG_FILE)) {
                    options.addArguments("--start-maximized");
                }
                return new ChromeDriver(options);
            }
            case ("firefox") : {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(GenericUtils.getHeadlessModeOption(ConstantUtils.CONFIG_FILE));
                WebDriver driver = new FirefoxDriver(options);
                if (GenericUtils.startMaximized(ConstantUtils.CONFIG_FILE)) {
                    driver.manage().window().maximize();
                }
                return driver;
            }
            case ("edge") : {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.setHeadless(GenericUtils.getHeadlessModeOption(ConstantUtils.CONFIG_FILE));
                if (GenericUtils.startMaximized(ConstantUtils.CONFIG_FILE)) {
                    options.addArguments("--start-maximized");
                }
                return new EdgeDriver(options);
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
