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
