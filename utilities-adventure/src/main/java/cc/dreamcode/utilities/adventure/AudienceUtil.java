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

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.title.Title;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public final class AudienceUtil {

    public static Audience audience(@NonNull Audience audience) {
        return audience;
    }

    public static Audience audience(@NonNull Iterable<? extends Audience> audiences) {
        return Audience.audience(audiences);
    }

    public static Audience audience(@NonNull Audience... audiences) {
        return Audience.audience(audiences);
    }

    public static void sendMessage(@NonNull Audience audience, @NonNull String text) {
        audience.sendMessage(AdventureUtil.component(text));
    }

    public static void sendMessage(@NonNull Audience audience, @NonNull String text, @NonNull Map<String, Object> placeholders) {
        audience.sendMessage(AdventureUtil.component(text, placeholders));
    }

    public static void sendMessage(@NonNull Audience audience, @NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        audience.sendMessage(AdventureUtil.component(text, locale, placeholders));
    }

    public static void sendActionBar(@NonNull Audience audience, @NonNull String text) {
        audience.sendActionBar(AdventureUtil.component(text));
    }

    public static void sendActionBar(@NonNull Audience audience, @NonNull String text, @NonNull Map<String, Object> placeholders) {
        audience.sendActionBar(AdventureUtil.component(text, placeholders));
    }

    public static void sendTitle(
            @NonNull Audience audience,
            @NonNull String titleText,
            @NonNull String subtitleText,
            long fadeInMs,
            long stayMs,
            long fadeOutMs
    ) {
        Title.Times times = Title.Times.times(
                Duration.ofMillis(fadeInMs),
                Duration.ofMillis(stayMs),
                Duration.ofMillis(fadeOutMs)
        );
        Title title = Title.title(
                AdventureUtil.component(titleText),
                AdventureUtil.component(subtitleText),
                times
        );
        audience.showTitle(title);
    }

    public static void sendTitle(
            @NonNull Audience audience,
            @NonNull String titleText,
            @NonNull String subtitleText,
            @NonNull Map<String, Object> placeholders,
            long fadeInMs,
            long stayMs,
            long fadeOutMs
    ) {
        Title.Times times = Title.Times.times(
                Duration.ofMillis(fadeInMs),
                Duration.ofMillis(stayMs),
                Duration.ofMillis(fadeOutMs)
        );
        Title title = Title.title(
                AdventureUtil.component(titleText, placeholders),
                AdventureUtil.component(subtitleText, placeholders),
                times
        );
        audience.showTitle(title);
    }

    public static void playSound(@NonNull Audience audience, @NonNull String soundName, float volume, float pitch) {
        Sound sound = Sound.sound(Key.key(soundName), Sound.Source.MASTER, volume, pitch);
        audience.playSound(sound);
    }

    public static void playSound(@NonNull Audience audience, @NonNull String soundName, @NonNull Sound.Source source, float volume, float pitch) {
        Sound sound = Sound.sound(Key.key(soundName), source, volume, pitch);
        audience.playSound(sound);
    }

    public static void showBossBar(@NonNull Audience audience, @NonNull BossBar bossBar) {
        audience.showBossBar(bossBar);
    }

    public static void hideBossBar(@NonNull Audience audience, @NonNull BossBar bossBar) {
        audience.hideBossBar(bossBar);
    }

    public static void openBook(@NonNull Audience audience, @NonNull Book book) {
        audience.openBook(book);
    }

    public static void sendPlayerListHeaderAndFooter(@NonNull Audience audience, @NonNull String headerText, @NonNull String footerText) {
        audience.sendPlayerListHeaderAndFooter(AdventureUtil.component(headerText), AdventureUtil.component(footerText));
    }

    public static void sendPlayerListHeaderAndFooter(@NonNull Audience audience, @NonNull String headerText, @NonNull String footerText, @NonNull Map<String, Object> placeholders) {
        audience.sendPlayerListHeaderAndFooter(AdventureUtil.component(headerText, placeholders), AdventureUtil.component(footerText, placeholders));
    }

    public static void sendPlayerListHeader(@NonNull Audience audience, @NonNull String headerText) {
        audience.sendPlayerListHeader(AdventureUtil.component(headerText));
    }

    public static void sendPlayerListFooter(@NonNull Audience audience, @NonNull String footerText) {
        audience.sendPlayerListFooter(AdventureUtil.component(footerText));
    }

    public static void sendResourcePacks(@NonNull Audience audience, @NonNull ResourcePackRequest request) {
        audience.sendResourcePacks(request);
    }

    public static void removeResourcePacks(@NonNull Audience audience, @NonNull UUID... packIds) {
        audience.removeResourcePacks(Arrays.asList(packIds));
    }

    public static void removeResourcePacks(@NonNull Audience audience, @NonNull Iterable<UUID> packIds) {
        audience.removeResourcePacks(packIds);
    }

    public static void clearResourcePacks(@NonNull Audience audience) {
        audience.clearResourcePacks();
    }
}
