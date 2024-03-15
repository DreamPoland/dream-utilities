package cc.dreamcode.utilities;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class GenerateUtil {

    public static String generateAlphabetic(int length) {
        final Random random = new Random();

        final int leftLimit = 97;
        final int rightLimit = 122;
        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateAlphanumeric(int length) {
        final Random random = new Random();

        final int leftLimit = 48;
        final int rightLimit = 122;
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
