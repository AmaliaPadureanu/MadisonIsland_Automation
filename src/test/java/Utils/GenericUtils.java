package Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

public class GenericUtils {

    public static String createRandomString(int charCount) {
        StringBuilder sb = new StringBuilder();
        char[] charset =  {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for(int i = 0; i < charCount; i++) {
            Random r = new Random();
            char x = charset[r.nextInt(charset.length)];
            sb.append(x);
        }
        return sb.toString();
    }

    public static String getBaseURL(String configFile) {
        String baseURL = "";
        try {
            Properties appProperties = new Properties();
            appProperties.load(Files.newInputStream(Paths.get(configFile)));
            baseURL = appProperties.getProperty("protocol") + "://" + appProperties.getProperty("hostname") + ":" + appProperties.getProperty("port");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return baseURL;
    }

    private static String getValueFromConfigFile(String configFile, String property, String defaultValue) {
        String value = defaultValue;
        try {
            Properties appProperties = new Properties();
            appProperties.load(Files.newInputStream(Paths.get(configFile)));
            value = appProperties.getProperty(property);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return value;
    }

    public static BrowserTypes getBrowserFromConfig(String configFile) {
        return BrowserTypes.valueOf(getValueFromConfigFile(configFile, "browser", "CHROME"));
    }

    public static boolean getHeadlessModeOption(String configFile) {
        return Boolean.parseBoolean(getValueFromConfigFile(configFile, "headlessMode", "false"));
    }

    public static boolean startMaximized(String configFile) {
        return Boolean.parseBoolean(getValueFromConfigFile(configFile, "startMaximized", "false"));
    }

    public static String getDbHostnameFromConfig(String configFile) {
        return getValueFromConfigFile(configFile, "dbHostname", "");
    }

    public static String getDbPortFromConfig(String configFile) {
        return getValueFromConfigFile(configFile, "dbPort", "");
    }

    public static String getDbUserFromConfig(String configFile) {
        return getValueFromConfigFile(configFile, "dbUser", "");
    }

    public static String getDbPasswordFromConfig(String configFile) {
        return getValueFromConfigFile(configFile, "dbPassword", "");
    }

    public static String getDbSchemaFromConfig(String configFile) {
        return getValueFromConfigFile(configFile, "dbSchema", "");
    }
}
