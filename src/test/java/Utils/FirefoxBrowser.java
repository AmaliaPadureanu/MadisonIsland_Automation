package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser extends Browser {

    public FirefoxBrowser() {
        WebDriverManager.firefoxdriver().setup();
        this.driver = new FirefoxDriver();
    }
}
