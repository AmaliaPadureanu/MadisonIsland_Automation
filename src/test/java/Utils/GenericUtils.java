package Utils;

import java.io.IOException;
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
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return baseURL;
    }

    private static String getValueFromConfigFile(String configFile, String property, String defaultValue) {
        String value = defaultValue;
        try {
            Properties appProperties = new Properties();
            appProperties.load(Files.newInputStream(Paths.get(configFile)));
            value = appProperties.getProperty(property);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return value;
    }

    public static String getBrowserFromConfig(String configFile) {
        return getValueFromConfigFile(configFile, "browser", "CHROME");
    }

    public static boolean getHeadlessModeOption(String configFile) {
        return Boolean.parseBoolean(getValueFromConfigFile(configFile, "headlessMode", "false"));
    }

    public static boolean startMaximized(String configFile) {
        return Boolean.parseBoolean(getValueFromConfigFile(configFile, "startMaximized", "false"));
    }
}
