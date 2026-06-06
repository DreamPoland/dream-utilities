package cc.dreamcode.utilities.bukkit;

import cc.dreamcode.utilities.ClassUtil;
import cc.dreamcode.utilities.ParseUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Server;

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

        if (optionalVersion.isPresent()) {
            final String version = optionalVersion.get();
            try {
                return ParseUtil.parseInteger(version.substring(1).split("_")[1]);
            }
            catch (Exception e) {
                // fall through to dynamic check
            }
        }

        try {
            String bukkitVersion = Bukkit.getBukkitVersion();
            Optional<Integer> parsed = parseMinorVersion(bukkitVersion);
            if (parsed.isPresent()) {
                return parsed;
            }
        }
        catch (Exception e) {
            // ignore
        }

        try {
            Method getMinecraftVersion = Server.class.getMethod("getMinecraftVersion");
            String minecraftVersion = (String) getMinecraftVersion.invoke(Bukkit.getServer());
            Optional<Integer> parsed = parseMinorVersion(minecraftVersion);
            if (parsed.isPresent()) {
                return parsed;
            }
        }
        catch (Exception e) {
            // ignore
        }

        return Optional.empty();
    }

    private static Optional<Integer> parseMinorVersion(String versionString) {
        if (versionString == null || versionString.isEmpty()) {
            return Optional.empty();
        }
        int firstDot = versionString.indexOf('.');
        if (firstDot == -1) {
            return Optional.empty();
        }
        StringBuilder minorBuilder = new StringBuilder();
        for (int i = firstDot + 1; i < versionString.length(); i++) {
            char c = versionString.charAt(i);
            if (Character.isDigit(c)) {
                minorBuilder.append(c);
            } else {
                if (!minorBuilder.isEmpty()) {
                    break;
                }
            }
        }
        if (!minorBuilder.isEmpty()) {
            return ParseUtil.parseInteger(minorBuilder.toString());
        }
        return Optional.empty();
    }
}
