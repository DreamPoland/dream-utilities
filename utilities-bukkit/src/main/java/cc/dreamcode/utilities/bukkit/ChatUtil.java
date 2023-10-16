package cc.dreamcode.utilities.bukkit;

import cc.dreamcode.utilities.StringUtil;
import cc.dreamcode.utilities.builder.MapBuilder;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class ChatUtil {

    private static final Pattern hexPattern = Pattern.compile("&#([0-9A-Fa-f]{6})");
    private static final Map<Color, ChatColor> COLORS = new MapBuilder<Color, ChatColor>()
            .put(new Color(0), ChatColor.getByChar('0'))
            .put(new Color(170), ChatColor.getByChar('1'))
            .put(new Color(43520), ChatColor.getByChar('2'))
            .put(new Color(43690), ChatColor.getByChar('3'))
            .put(new Color(11141120), ChatColor.getByChar('4'))
            .put(new Color(11141290), ChatColor.getByChar('5'))
            .put(new Color(16755200), ChatColor.getByChar('6'))
            .put(new Color(11184810), ChatColor.getByChar('7'))
            .put(new Color(5592405), ChatColor.getByChar('8'))
            .put(new Color(5592575), ChatColor.getByChar('9'))
            .put(new Color(5635925), ChatColor.getByChar('a'))
            .put(new Color(5636095), ChatColor.getByChar('b'))
            .put(new Color(16733525), ChatColor.getByChar('c'))
            .put(new Color(16733695), ChatColor.getByChar('d'))
            .put(new Color(16777045), ChatColor.getByChar('e'))
            .put(new Color(16777215), ChatColor.getByChar('f'))
            .build();

    public static String fixColor(@NonNull String text) {
        return ChatColor.translateAlternateColorCodes('&', processRgb(text));
    }

    public static String fixColor(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        return fixColor(StringUtil.replace(text, placeholders));
    }

    public static List<String> fixColor(@NonNull List<String> stringList) {
        return stringList.stream()
                .map(ChatUtil::fixColor)
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Map<String, Object> placeholders) {
        return stringList.stream()
                .map(text -> fixColor(text, placeholders))
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull String... strings) {
        return Arrays.stream(strings)
                .map(ChatUtil::fixColor)
                .collect(Collectors.toList());
    }

    private static ChatColor getClosestColor(@NonNull Color color) {
        return COLORS.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> Math.pow(color.getRed() - entry.getKey().getRed(), 2) +
                        Math.pow(color.getRed() - entry.getKey().getRed(), 2) +
                        Math.pow(color.getRed() - entry.getKey().getRed(), 2)))
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Cannot find closest rgb color to format chat-color. (" + color + ")"));
    }

    private static Color hexToRgb(@NonNull String hex) {
        return new Color(Integer.parseInt(hex.substring(2), 16));
    }

    private static String processRgb(@NonNull String text) {
        Matcher matcher = hexPattern.matcher(text);

        AtomicReference<String> atomicText = new AtomicReference<>(text);
        while (matcher.find()) {
            final String hex = matcher.group();
            final Color color = hexToRgb(hex);

            if (BukkitReflectionUtil.isSupported(16)) {
                atomicText.set(atomicText.get().replace(hex, ChatColor.of(color) + ""));
            }
            else {
                atomicText.set(atomicText.get().replace(hex, getClosestColor(color) + ""));
            }
        }

        return atomicText.get();
    }
}
