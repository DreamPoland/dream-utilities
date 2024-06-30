package cc.dreamcode.utilities.bukkit;

import cc.dreamcode.utilities.ClassUtil;
import cc.dreamcode.utilities.ParseUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@UtilityClass
public class VersionUtil {

    public static boolean isSupported(int version) {
        return getVersion().orElse(-1) >= version;
    }

    public static boolean isSpigot() {
        return ClassUtil.hasClass("org.spigotmc.SpigotConfig");
    }

    public static boolean isPaper() {
        return ClassUtil.hasClass("com.destroystokyo.paper.PaperConfig") ||
                ClassUtil.hasClass("io.papermc.paper.configuration.Configuration");
    }

    /**
     * WARNING: Not supporting 1.20.5+ - no nms version-package files.
     * @return server version by format v1_20_R3
     */
    public static Optional<String> getStringVersion() {
        final String[] nmsVersionSplit = Bukkit.getServer().getClass().getPackage().getName().split("\\.");

        if (nmsVersionSplit.length >= 4) {
            final String nmsVersion = nmsVersionSplit[3];
            if (nmsVersion.startsWith("v")) {
                return Optional.of(nmsVersion);
            }
            else {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    public static Optional<Integer> getVersion() {
        final Optional<String> optionalVersion = getStringVersion();

        if (!optionalVersion.isPresent()) {
            try {
                Method getMinecraftVersion = Server.class.getMethod("getMinecraftVersion");
                String minecraftVersion = (String) getMinecraftVersion.invoke(Bukkit.getServer());
                String[] minecraftVersionSplit = minecraftVersion.split("\\.");

                if (minecraftVersionSplit.length <= 1) {
                    return Optional.empty();
                }

                String minorVersion = minecraftVersion.split("\\.")[1];
                return ParseUtil.parseInteger(minorVersion);
            }
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                return Optional.empty();
            }
        }

        final String version = optionalVersion.get();

        try {
            return ParseUtil.parseInteger(version.substring(1).split("_")[1]);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }
}
