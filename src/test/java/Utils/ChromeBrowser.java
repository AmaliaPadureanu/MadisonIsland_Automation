package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser extends Browser {

    public ChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }
}
