package cc.dreamcode.utilities.bukkit;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ChatUtil {
    public static String fixColor(@NonNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
