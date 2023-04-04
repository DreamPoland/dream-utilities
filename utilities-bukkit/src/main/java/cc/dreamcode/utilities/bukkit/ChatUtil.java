package cc.dreamcode.utilities.bukkit;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ChatUtil {
    public static String fixColor(@NonNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> fixColor(@NonNull List<String> stringList) {
        return stringList.stream()
                .map(ChatUtil::fixColor)
                .collect(Collectors.toList());
    }

    public static List<String> fixColor(@NonNull String... strings) {
        return Arrays.stream(strings)
                .map(ChatUtil::fixColor)
                .collect(Collectors.toList());
    }
}
