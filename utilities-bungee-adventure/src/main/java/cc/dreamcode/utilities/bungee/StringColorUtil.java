package cc.dreamcode.utilities.bungee;

import cc.dreamcode.utilities.bungee.adventure.ColorProcessor;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class StringColorUtil {

    private static final char COLOR_CHAR = '\u00A7';
    private static final char ALT_COLOR_CHAR = '&';

    public static String fixColor(@NonNull String text) {
        return ColorProcessor.process(text);
    }

    public static String fixColor(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        return ColorProcessor.process(text, placeholders);
    }

    public static String fixColor(@NonNull String text, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return ColorProcessor.process(text, placeholders, colorizePlaceholders);
    }

    public static String fixColor(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return ColorProcessor.process(text, locale, placeholders);
    }

    public static String fixColor(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return ColorProcessor.process(text, locale, placeholders, colorizePlaceholders);
    }

    public static List<String> fixColor(@NonNull List<String> stringList) {
        return stringList.stream()
                .map(StringColorUtil::fixColor)
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Map<String, Object> placeholders) {
        return stringList.stream()
                .map(text -> fixColor(text, placeholders))
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return stringList.stream()
                .map(text -> fixColor(text, placeholders, colorizePlaceholders))
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return stringList.stream()
                .map(text -> fixColor(text, locale, placeholders))
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return stringList.stream()
                .map(text -> fixColor(text, locale, placeholders, colorizePlaceholders))
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull String... strings) {
        return Arrays.stream(strings)
                .map(StringColorUtil::fixColor)
                .collect(Collectors.toList());
    }

    public static String breakColor(@NonNull String text) {
        return text.replace(COLOR_CHAR + "", ALT_COLOR_CHAR + "");
    }

    public static List<String> breakColor(@NonNull List<String> stringList) {
        return stringList.stream()
                .map(StringColorUtil::breakColor)
                .collect(Collectors.toList());
    }

    public static List<String> breakColor(@NonNull String... strings) {
        return Arrays.stream(strings)
                .map(StringColorUtil::breakColor)
                .collect(Collectors.toList());
    }
}
