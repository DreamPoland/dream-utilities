package cc.dreamcode.utilities.color;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public interface ColorProcessor {

    String color(@NonNull String text);

    default String color(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        return this.color(text, Locale.forLanguageTag("pl"), placeholders, true);
    }

    default String color(@NonNull String text, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return this.color(text, Locale.forLanguageTag("pl"), placeholders, colorizePlaceholders);
    }

    default String color(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return this.color(text, locale, placeholders, true);
    }

    String color(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders);

    default List<String> color(@NonNull List<String> stringList) {
        return stringList.stream()
                .map(this::color)
                .collect(Collectors.toList());
    }

    default List<String> color(@NonNull List<String> stringList, @NonNull Map<String, Object> placeholders) {
        return stringList.stream()
                .map(text -> this.color(text, placeholders))
                .collect(Collectors.toList());
    }

    default List<String> color(@NonNull List<String> stringList, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return stringList.stream()
                .map(text -> this.color(text, placeholders, colorizePlaceholders))
                .collect(Collectors.toList());
    }

    default List<String> color(@NonNull List<String> stringList, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return stringList.stream()
                .map(text -> this.color(text, locale, placeholders))
                .collect(Collectors.toList());
    }

    default List<String> color(@NonNull List<String> stringList, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return stringList.stream()
                .map(text -> this.color(text, locale, placeholders, colorizePlaceholders))
                .collect(Collectors.toList());
    }

    default List<String> color(@NonNull String... strings) {
        return Arrays.stream(strings)
                .map(this::color)
                .collect(Collectors.toList());
    }

    String decolor(@NonNull String text);

    default List<String> decolor(@NonNull List<String> stringList) {
        return stringList.stream()
                .map(this::decolor)
                .collect(Collectors.toList());
    }

    default List<String> decolor(@NonNull String... strings) {
        return Arrays.stream(strings)
                .map(this::decolor)
                .collect(Collectors.toList());
    }
}
