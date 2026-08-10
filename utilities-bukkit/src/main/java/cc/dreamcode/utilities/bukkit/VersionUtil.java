/*
 * Copyright (c) 2026 DreamCode
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cc.dreamcode.utilities.bukkit;

import cc.dreamcode.utilities.ClassUtil;
import cc.dreamcode.utilities.ParseUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Optional;

@UtilityClass
public class VersionUtil {

    public static boolean isSupported(int targetVersion) {
        return getVersion().orElse(-1) >= targetVersion;
    }

    public static boolean isVersionOrHigher(int targetVersion) {
        return getVersion().orElse(-1) >= targetVersion;
    }

    public static boolean isVersionOrHigher(int targetVersion, int subVersion) {
        int currentVersion = getVersion().orElse(-1);
        if (currentVersion > targetVersion) {
            return true;
        }
        if (currentVersion == targetVersion) {
            int currentSub = getMinorVersion().orElse(getPatchVersion().orElse(0));
            return currentSub >= subVersion;
        }
        return false;
    }

    public static boolean isSpigot() {
        return ClassUtil.hasClass("org.spigotmc.SpigotConfig");
    }

    public static boolean isPaper() {
        return ClassUtil.hasClass("com.destroystokyo.paper.PaperConfig") ||
                ClassUtil.hasClass("io.papermc.paper.configuration.Configuration") ||
                ClassUtil.hasClass("io.papermc.paper.PaperServer") ||
                (Bukkit.getServer() != null && Bukkit.getServer().getName().toLowerCase(Locale.ROOT).contains("paper"));
    }

    public static boolean isFolia() {
        return ClassUtil.hasClass("io.papermc.paper.threadedregions.RegionizedServer");
    }

    public static String getMinecraftVersion() {
        try {
            Method getMinecraftVersion = Server.class.getMethod("getMinecraftVersion");
            return (String) getMinecraftVersion.invoke(Bukkit.getServer());
        } catch (Exception ignored) {
        }

        try {
            String bukkitVersion = Bukkit.getBukkitVersion();
            if (bukkitVersion != null && bukkitVersion.contains("-")) {
                return bukkitVersion.split("-")[0];
            }
        } catch (Exception ignored) {
        }

        return "unknown";
    }

    public static Optional<String> getStringVersion() {
        try {
            final String[] nmsVersionSplit = Bukkit.getServer().getClass().getPackage().getName().split("\\.");
            if (nmsVersionSplit.length >= 4) {
                final String nmsVersion = nmsVersionSplit[3];
                if (nmsVersion.startsWith("v")) {
                    return Optional.of(nmsVersion);
                }
            }
        } catch (Exception ignored) {
        }

        String mcVer = getMinecraftVersion();
        if (!"unknown".equals(mcVer)) {
            return Optional.of("v" + mcVer.replace(".", "_"));
        }

        return Optional.empty();
    }

    public static Optional<Integer> getMajorVersion() {
        String mcVer = getMinecraftVersion();
        if (!"unknown".equals(mcVer)) {
            String[] parts = mcVer.split("\\.");
            if (parts.length >= 1) {
                return ParseUtil.parseInteger(parts[0]);
            }
        }
        return Optional.empty();
    }

    public static Optional<Integer> getVersion() {
        String mcVer = getMinecraftVersion();
        if (!"unknown".equals(mcVer)) {
            String[] parts = mcVer.split("\\.");
            if (parts.length >= 2) {
                if ("1".equals(parts[0])) {
                    return ParseUtil.parseInteger(parts[1]);
                }
                return ParseUtil.parseInteger(parts[0]);
            }
            if (parts.length == 1) {
                return ParseUtil.parseInteger(parts[0]);
            }
        }

        final Optional<String> optionalVersion = getStringVersion();
        if (optionalVersion.isPresent()) {
            final String version = optionalVersion.get();
            try {
                String[] parts = version.substring(1).split("_");
                if (parts.length >= 2) {
                    if ("1".equals(parts[0])) {
                        return ParseUtil.parseInteger(parts[1]);
                    }
                    return ParseUtil.parseInteger(parts[0]);
                }
            } catch (Exception ignored) {
            }
        }

        return Optional.empty();
    }

    public static Optional<Integer> getMinorVersion() {
        String mcVer = getMinecraftVersion();
        if (!"unknown".equals(mcVer)) {
            String[] parts = mcVer.split("\\.");
            if (parts.length >= 2) {
                return ParseUtil.parseInteger(parts[1]);
            }
        }
        return Optional.empty();
    }

    public static Optional<Integer> getPatchVersion() {
        String mcVer = getMinecraftVersion();
        if (!"unknown".equals(mcVer)) {
            String[] parts = mcVer.split("\\.");
            if ("1".equals(parts[0])) {
                if (parts.length >= 3) {
                    return ParseUtil.parseInteger(parts[2]);
                }
                return Optional.of(0);
            }
            if (parts.length >= 3) {
                return ParseUtil.parseInteger(parts[2]);
            }
            return Optional.of(0);
        }
        return Optional.empty();
    }
}
