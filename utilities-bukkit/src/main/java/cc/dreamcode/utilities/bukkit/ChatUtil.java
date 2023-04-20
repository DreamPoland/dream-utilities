package cc.dreamcode.utilities.bukkit;

import eu.okaeri.placeholders.context.PlaceholderContext;
import eu.okaeri.placeholders.message.CompiledMessage;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class ChatUtil {
    public static String fixColor(@NonNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
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
}
