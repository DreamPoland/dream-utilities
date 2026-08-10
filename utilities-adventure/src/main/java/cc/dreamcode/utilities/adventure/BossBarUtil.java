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

package cc.dreamcode.utilities.adventure;

import cc.dreamcode.utilities.MathUtil;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;

import java.util.Locale;
import java.util.Map;

@UtilityClass
public final class BossBarUtil {

    public static BossBar createBossBar(@NonNull Component title, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay) {
        return BossBar.bossBar(title, MathUtil.clamp(progress, 0.0f, 1.0f), color, overlay);
    }

    public static BossBar createBossBar(@NonNull String miniMessage, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay) {
        return createBossBar(AdventureUtil.component(miniMessage), progress, color, overlay);
    }

    public static BossBar createBossBar(@NonNull String miniMessage, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay, @NonNull Map<String, Object> placeholders) {
        return createBossBar(AdventureUtil.component(miniMessage, placeholders), progress, color, overlay);
    }

    public static BossBar createBossBar(@NonNull String miniMessage, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return createBossBar(AdventureUtil.component(miniMessage, locale, placeholders), progress, color, overlay);
    }

    public static BossBar createBossBar(@NonNull String miniMessage, float progress) {
        return createBossBar(miniMessage, progress, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
    }

    public static BossBar createBossBar(@NonNull String miniMessage, float progress, @NonNull Map<String, Object> placeholders) {
        return createBossBar(miniMessage, progress, BossBar.Color.RED, BossBar.Overlay.PROGRESS, placeholders);
    }

    public static BossBar showBossBar(@NonNull Audience audience, @NonNull Component title, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay) {
        BossBar bossBar = createBossBar(title, progress, color, overlay);
        audience.showBossBar(bossBar);
        return bossBar;
    }

    public static BossBar showBossBar(@NonNull Audience audience, @NonNull String miniMessage, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay) {
        BossBar bossBar = createBossBar(miniMessage, progress, color, overlay);
        audience.showBossBar(bossBar);
        return bossBar;
    }

    public static BossBar showBossBar(@NonNull Audience audience, @NonNull String miniMessage, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay, @NonNull Map<String, Object> placeholders) {
        BossBar bossBar = createBossBar(miniMessage, progress, color, overlay, placeholders);
        audience.showBossBar(bossBar);
        return bossBar;
    }

    public static void hideBossBar(@NonNull Audience audience, @NonNull BossBar bossBar) {
        audience.hideBossBar(bossBar);
    }

    public static void updateTitle(@NonNull BossBar bossBar, @NonNull Component title) {
        bossBar.name(title);
    }

    public static void updateTitle(@NonNull BossBar bossBar, @NonNull String miniMessage) {
        bossBar.name(AdventureUtil.component(miniMessage));
    }

    public static void updateTitle(@NonNull BossBar bossBar, @NonNull String miniMessage, @NonNull Map<String, Object> placeholders) {
        bossBar.name(AdventureUtil.component(miniMessage, placeholders));
    }

    public static void updateProgress(@NonNull BossBar bossBar, float progress) {
        bossBar.progress(MathUtil.clamp(progress, 0.0f, 1.0f));
    }

    public static void updateColor(@NonNull BossBar bossBar, @NonNull BossBar.Color color) {
        bossBar.color(color);
    }

    public static void updateOverlay(@NonNull BossBar bossBar, @NonNull BossBar.Overlay overlay) {
        bossBar.overlay(overlay);
    }
}
