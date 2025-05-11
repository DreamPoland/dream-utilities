package cc.dreamcode.utilities.bungee;

import cc.dreamcode.utilities.bungee.color.DefaultColorProcessor;
import cc.dreamcode.utilities.color.ColorProcessor;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@UtilityClass
public class StringColorUtil {

    private static ColorProcessor COLOR_PROCESSOR;

    static {
        COLOR_PROCESSOR = new DefaultColorProcessor();
    }

    public static String fixColor(@NonNull String text) {
        return COLOR_PROCESSOR.color(text);
    }

    public static String fixColor(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        return COLOR_PROCESSOR.color(text, placeholders);
    }

    public static String fixColor(@NonNull String text, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return COLOR_PROCESSOR.color(text, placeholders, colorizePlaceholders);
    }

    public static String fixColor(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return COLOR_PROCESSOR.color(text, locale, placeholders);
    }

    public static String fixColor(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return COLOR_PROCESSOR.color(text, locale, placeholders, colorizePlaceholders);
    }

    public static List<String> fixColor(@NonNull List<String> stringList) {
        return COLOR_PROCESSOR.color(stringList);
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Map<String, Object> placeholders) {
        return COLOR_PROCESSOR.color(stringList, placeholders);
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return COLOR_PROCESSOR.color(stringList, placeholders, colorizePlaceholders);
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return COLOR_PROCESSOR.color(stringList, locale, placeholders);
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return COLOR_PROCESSOR.color(stringList, locale, placeholders, colorizePlaceholders);
    }

    public static List<String> fixColor(@NonNull String... strings) {
        return COLOR_PROCESSOR.color(strings);
    }

    public static String breakColor(@NonNull String text) {
        return COLOR_PROCESSOR.decolor(text);
    }

    public static List<String> breakColor(@NonNull List<String> stringList) {
        return COLOR_PROCESSOR.decolor(stringList);
    }

    public static List<String> breakColor(@NonNull String... strings) {
        return COLOR_PROCESSOR.decolor(strings);
    }

    public static void setColorProcessor(@NonNull ColorProcessor colorProcessor) {
        COLOR_PROCESSOR = colorProcessor;
    }
}
