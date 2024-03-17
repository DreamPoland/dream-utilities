package cc.dreamcode.utilities.bukkit;

import cc.dreamcode.utilities.ClassUtil;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

    public static Optional<String> getStringVersion() {
        final AtomicReference<String> versionReference = new AtomicReference<>();

        Arrays.stream(Package.getPackages())
                .filter(aPackage -> aPackage.getName().startsWith("org.bukkit.craftbukkit.v"))
                .findAny()
                .ifPresent(aPackage -> {
                    final String version = aPackage.getName().split("\\.")[3];

                    try {
                        Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
                        versionReference.set(version);
                    }
                    catch (ClassNotFoundException ignored) { }
                });

        return Optional.ofNullable(versionReference.get());
    }

    public static Optional<Integer> getVersion() {
        final Optional<String> optionalVersion = getStringVersion();

        if (!optionalVersion.isPresent()) {
            return Optional.empty();
        }

        final String version = optionalVersion.get();
        try {
            return Optional.of(Integer.parseInt(version.substring(1).split("_")[1]));
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }
}
