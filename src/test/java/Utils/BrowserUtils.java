package Utils;

public class BrowserUtils {
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
}
