package cc.dreamcode.utilities.bungee;

import cc.dreamcode.utilities.StringUtil;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class StringColorUtil {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([0-9A-Fa-f]{6})");

    public static String fixColor(@NonNull String text) {
        return ChatColor.translateAlternateColorCodes('&', processRgb(text));
    }

    public static String fixColor(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        return fixColor(StringUtil.replace(text, placeholders));
    }

    public static String fixColor(@NonNull Locale locale, @NonNull String text, @NonNull Map<String, Object> placeholders) {
        return fixColor(StringUtil.replace(text, locale, placeholders));
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

    public static List<String> fixColor(@NonNull String... strings) {
        return Arrays.stream(strings)
                .map(StringColorUtil::fixColor)
                .collect(Collectors.toList());
    }

    private static Color hexToRgb(@NonNull String hex) {
        return new Color(Integer.parseInt(hex.substring(2), 16));
    }

    private static String processRgb(@NonNull String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);

        AtomicReference<String> atomicText = new AtomicReference<>(text);
        while (matcher.find()) {
            final String hex = matcher.group();
            final Color color = hexToRgb(hex);

            atomicText.set(atomicText.get().replace(hex, ChatColor.of(color) + ""));
        }

        return atomicText.get();
    }
}
