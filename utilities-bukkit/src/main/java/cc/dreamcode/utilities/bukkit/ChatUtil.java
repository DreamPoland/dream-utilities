package cc.dreamcode.utilities.bukkit;

import cc.dreamcode.utilities.builder.MapBuilder;
import eu.okaeri.placeholders.context.PlaceholderContext;
import eu.okaeri.placeholders.message.CompiledMessage;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class ChatUtil {

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

    public static String fixColor(@NonNull String text) { // &#843234

        final StringBuilder stringBuilder = new StringBuilder();
        String[] colors = text.split("&");
        Arrays.stream(colors).forEach(splitText -> {

            if (splitText.startsWith("&#")) {
                String hexColor = splitText.substring(2, 8);
                Color color = Color.decode(hexColor);

                if (BukkitReflectionUtil.isSupported(16)) {
                    stringBuilder.append(ChatColor.of(color));
                }
                else {
                    stringBuilder.append(getClosestColor(color));
                }

                stringBuilder.append(splitText.substring(9));
                return;
            }

            stringBuilder.append(splitText);

        });

        return ChatColor.translateAlternateColorCodes('&', stringBuilder.toString());
    }

    public static String fixColor(@NonNull String text, @NonNull Map<String, Object> replaceMap) {
        final CompiledMessage compiledMessage = CompiledMessage.of(text);
        final PlaceholderContext placeholderContext = PlaceholderContext.of(compiledMessage);

        placeholderContext.with(replaceMap);

        return fixColor(placeholderContext.apply());
    }

    public static List<String> fixColor(@NonNull List<String> stringList) {
        return stringList.stream()
                .map(ChatUtil::fixColor)
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull List<String> stringList, @NonNull Map<String, Object> replaceMap) {
        return stringList.stream()
                .map(text -> fixColor(text, replaceMap))
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
                .orElseThrow(() -> new RuntimeException("Cannot resolve closest color to existing chat-color. (" + color + ")"));
    }
}
