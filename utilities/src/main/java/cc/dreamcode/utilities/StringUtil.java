package cc.dreamcode.utilities;

import eu.okaeri.placeholders.context.PlaceholderContext;
import eu.okaeri.placeholders.message.CompiledMessage;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class StringUtil {
    public static String join(List<String> stringList) {
        return join(stringList.toArray(new String[0]), "", 0, stringList.size());
    }

    public static String join(List<String> stringList, String separator) {
        return join(stringList.toArray(new String[0]), separator, 0, stringList.size());
    }

    public static String join(List<String> stringList, String separator, int from, int to) {
        return join(stringList.toArray(new String[0]), separator, from, to);
    }

    public static String join(String[] array) {
        return join(array, "", 0, array.length);
    }

    public static String join(String[] array, String separator) {
        return join(array, separator, 0, array.length);
    }

    public static String join(String[] array, String separator, int from, int to) {
        final StringBuilder stringBuilder = new StringBuilder();
        for(int i = from; i < to; i++) {
            if(i > from) stringBuilder.append(separator);
            if(array[i] != null) stringBuilder.append(array[i]);
        }
        return stringBuilder.toString();
    }

    public static String replace(@NonNull String text, @NonNull String from, @NonNull String to) {
        final CompiledMessage compiledMessage = CompiledMessage.of(text);
        final PlaceholderContext placeholderContext = PlaceholderContext.of(compiledMessage);

        placeholderContext.with(from, to);

        return placeholderContext.apply();
    }
    
    public static String replace(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        final CompiledMessage compiledMessage = CompiledMessage.of(text);
        final PlaceholderContext placeholderContext = PlaceholderContext.of(compiledMessage);

        placeholderContext.with(placeholders);

        return placeholderContext.apply();
    }

    public static String generateAlphabetic(int length) {
        final Random random = new Random();

        final int leftLimit = 97;
        final int rightLimit = 122;
        return random.ints(leftLimit, rightLimit + 1)
                .limit(leftLimit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateAlphanumeric(int length) {
        final Random random = new Random();

        final int leftLimit = 48;
        final int rightLimit = 122;
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(leftLimit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateJoiningNumbers(int to) {
        return generateJoiningNumbers(1, to);
    }

    public static String generateJoiningNumbers(int from, int to) {
        return IntStream.rangeClosed(from, to + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
