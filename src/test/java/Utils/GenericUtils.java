package Utils;

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

}
