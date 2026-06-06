package cc.dreamcode.utilities.adventure;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.title.Title;

import java.time.Duration;
import java.util.Locale;
import java.util.Map;

@UtilityClass
public final class AudienceUtil {

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
}
