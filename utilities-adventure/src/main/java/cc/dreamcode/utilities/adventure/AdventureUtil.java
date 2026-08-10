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

import cc.dreamcode.utilities.StringUtil;
import eu.okaeri.placeholders.context.PlaceholderContext;
import eu.okaeri.placeholders.message.CompiledMessage;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public final class AdventureUtil {

    private static final Pattern ALL_TEXT_PATTERN = Pattern.compile(".*");
    private static final Pattern FIELD_PATTERN = Pattern.compile("\\{(?<content>[^}]+)}");
    private static final Pattern SECTION_COLOR_PATTERN = Pattern.compile("(?i)§([0-9A-FK-OR])");
    private static final Pattern LEGACY_RGB_PATTERN = Pattern.compile("&#([a-fA-F0-9]{6})");
    private static final Pattern URL_PATTERN = Pattern.compile("https?:\\/\\/(www\\.)?[a-zA-Z0-9\\-._~%]{1,256}\\.[a-zA-Z]{1,6}(\\/[a-zA-Z0-9\\-._~%!$&'()*+,;=:@/#?]*)?");

    private static final LegacyComponentSerializer SECTION_SERIALIZER = LegacyComponentSerializer.legacySection()
            .toBuilder()
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build();
    private static final LegacyComponentSerializer LEGACY_SECTION_SERIALIZER = LegacyComponentSerializer.legacySection();

    private static final LegacyComponentSerializer AMPERSAND_SERIALIZER = LegacyComponentSerializer.legacyAmpersand()
            .toBuilder()
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build();
    private static final LegacyComponentSerializer LEGACY_AMPERSAND_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    private static final TextReplacementConfig AMPERSAND_REPLACEMENTS = TextReplacementConfig.builder()
            .match(ALL_TEXT_PATTERN)
            .replacement((result, input) -> AdventureUtil.rgbSupport
                    ? AMPERSAND_SERIALIZER.deserialize(result.group())
                    : LEGACY_AMPERSAND_SERIALIZER.deserialize(result.group()))
            .build();

    private static final TextReplacementConfig CLICKABLE_URL_REPLACEMENT = TextReplacementConfig.builder()
            .match(URL_PATTERN)
            .replacement(url -> url.clickEvent(ClickEvent.openUrl(url.content())))
            .build();

    private static String preProcessText(String text) {
        String replaceText = text;
        replaceText = SECTION_COLOR_PATTERN.matcher(replaceText).replaceAll("&$1");
        replaceText = LEGACY_RGB_PATTERN.matcher(replaceText).replaceAll("<#$1>");
        return replaceText;
    }

    private static final MiniMessage MINI_MESSAGE = MiniMessage.builder()
            .preProcessor(AdventureUtil::preProcessText)
            .postProcessor(component -> component.replaceText(CLICKABLE_URL_REPLACEMENT)
                    .replaceText(AMPERSAND_REPLACEMENTS))
            .build();

    private static final MiniMessage PLACEHOLDER_MINI_MESSAGE = MiniMessage.builder()
            .preProcessor(AdventureUtil::preProcessText)
            .tags(TagResolver.empty())
            .postProcessor(component -> component.replaceText(CLICKABLE_URL_REPLACEMENT))
            .build();

    private static final MiniMessage COLORIZED_PLACEHOLDER_MINI_MESSAGE = MiniMessage.builder()
            .preProcessor(AdventureUtil::preProcessText)
            .tags(TagResolver.builder()
                    .resolver(StandardTags.color())
                    .resolver(StandardTags.decorations())
                    .resolver(StandardTags.rainbow())
                    .resolver(StandardTags.gradient())
                    .resolver(StandardTags.transition())
                    .build())
            .postProcessor(component -> component.replaceText(CLICKABLE_URL_REPLACEMENT)
                    .replaceText(AMPERSAND_REPLACEMENTS))
            .build();

    public static boolean rgbSupport = true;

    public static Component component(@NonNull String text) {
        return MINI_MESSAGE.deserialize(text);
    }

    public static Component component(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        return component(text, Locale.forLanguageTag("pl"), placeholders);
    }

    public static Component component(@NonNull String text, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return component(text, Locale.forLanguageTag("pl"), placeholders, colorizePlaceholders);
    }

    public static Component component(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return component(text, locale, placeholders, true);
    }

    public static Component component(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        final CompiledMessage compiledMessage = CompiledMessage.of(locale, text);
        final PlaceholderContext placeholderContext = StringUtil.getPlaceholders().context(compiledMessage)
                .with(placeholders);

        return component(text, placeholderContext, colorizePlaceholders);
    }

    private static Component component(@NonNull String text, @NonNull PlaceholderContext placeholderContext, boolean colorizePlaceholders) {
        final Component component = MINI_MESSAGE.deserialize(text);
        final Map<String, String> fields = placeholderContext.renderFields();
        final TextReplacementConfig replacementConfig = replacementConfig(fields, colorizePlaceholders);

        return component.replaceText(replacementConfig);
    }

    public static String process(@NonNull String text) {
        final Component component = MINI_MESSAGE.deserialize(text);
        return toLegacySection(component);
    }

    public static String process(@NonNull String text, @NonNull Map<String, Object> placeholders) {
        return process(text, Locale.forLanguageTag("pl"), placeholders);
    }

    public static String process(@NonNull String text, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return process(text, Locale.forLanguageTag("pl"), placeholders, colorizePlaceholders);
    }

    public static String process(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return process(text, locale, placeholders, true);
    }

    public static String process(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        final CompiledMessage compiledMessage = CompiledMessage.of(locale, text);
        final PlaceholderContext placeholderContext = StringUtil.getPlaceholders().context(compiledMessage)
                .with(placeholders);

        return process(text, placeholderContext, colorizePlaceholders);
    }

    private static String process(@NonNull String text, @NonNull PlaceholderContext placeholderContext, boolean colorizePlaceholders) {
        Component component = MINI_MESSAGE.deserialize(text);
        final Map<String, String> fields = placeholderContext.renderFields();
        final TextReplacementConfig replacementConfig = replacementConfig(fields, colorizePlaceholders);

        component = component.replaceText(replacementConfig);
        return toLegacySection(component);
    }

    public static String deprocess(@NonNull String text) {
        final Component component = fromLegacySection(text);
        return MINI_MESSAGE.serialize(component);
    }

    private static TextReplacementConfig replacementConfig(@NonNull Map<String, String> replaceMap, boolean colorizePlaceholders) {
        return TextReplacementConfig.builder()
                .match(FIELD_PATTERN)
                .replacement((result, input) -> {
                    final String value = replaceMap.get(result.group(1));
                    if (value == null) {
                        return Component.text(result.group());
                    }

                    if (colorizePlaceholders) {
                        return COLORIZED_PLACEHOLDER_MINI_MESSAGE.deserialize(value);
                    }

                    return PLACEHOLDER_MINI_MESSAGE.deserialize(value);
                })
                .build();
    }

    public static List<Component> component(@NonNull List<String> texts) {
        return texts.stream()
                .map(AdventureUtil::component)
                .collect(Collectors.toList());
    }

    public static List<Component> component(@NonNull List<String> texts, @NonNull Map<String, Object> placeholders) {
        return texts.stream()
                .map(text -> component(text, placeholders))
                .collect(Collectors.toList());
    }

    public static List<Component> component(@NonNull List<String> texts, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return texts.stream()
                .map(text -> component(text, placeholders, colorizePlaceholders))
                .collect(Collectors.toList());
    }

    public static List<Component> component(@NonNull List<String> texts, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return texts.stream()
                .map(text -> component(text, locale, placeholders))
                .collect(Collectors.toList());
    }

    public static List<Component> component(@NonNull List<String> texts, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return texts.stream()
                .map(text -> component(text, locale, placeholders, colorizePlaceholders))
                .collect(Collectors.toList());
    }

    public static List<String> process(@NonNull List<String> texts) {
        return texts.stream()
                .map(AdventureUtil::process)
                .collect(Collectors.toList());
    }

    public static List<String> process(@NonNull List<String> texts, @NonNull Map<String, Object> placeholders) {
        return texts.stream()
                .map(text -> process(text, placeholders))
                .collect(Collectors.toList());
    }

    public static List<String> process(@NonNull List<String> texts, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return texts.stream()
                .map(text -> process(text, placeholders, colorizePlaceholders))
                .collect(Collectors.toList());
    }

    public static List<String> process(@NonNull List<String> texts, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return texts.stream()
                .map(text -> process(text, locale, placeholders))
                .collect(Collectors.toList());
    }

    public static List<String> process(@NonNull List<String> texts, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        return texts.stream()
                .map(text -> process(text, locale, placeholders, colorizePlaceholders))
                .collect(Collectors.toList());
    }

    public static List<Component> component(@NonNull String... texts) {
        return Arrays.stream(texts)
                .map(AdventureUtil::component)
                .collect(Collectors.toList());
    }

    public static List<String> process(@NonNull String... texts) {
        return Arrays.stream(texts)
                .map(AdventureUtil::process)
                .collect(Collectors.toList());
    }

    public static String toLegacySection(@NonNull Component component) {
        return AdventureUtil.rgbSupport
                ? SECTION_SERIALIZER.serialize(component)
                : LEGACY_SECTION_SERIALIZER.serialize(component);
    }

    public static String toLegacyAmpersand(@NonNull Component component) {
        return AdventureUtil.rgbSupport
                ? AMPERSAND_SERIALIZER.serialize(component)
                : LEGACY_AMPERSAND_SERIALIZER.serialize(component);
    }

    public static Component fromLegacySection(@NonNull String legacyText) {
        return AdventureUtil.rgbSupport
                ? SECTION_SERIALIZER.deserialize(legacyText)
                : LEGACY_SECTION_SERIALIZER.deserialize(legacyText);
    }

    public static Component fromLegacyAmpersand(@NonNull String legacyText) {
        return AdventureUtil.rgbSupport
                ? AMPERSAND_SERIALIZER.deserialize(legacyText)
                : LEGACY_AMPERSAND_SERIALIZER.deserialize(legacyText);
    }

    public static void setRgbSupport(boolean rgbSupport) {
        AdventureUtil.rgbSupport = rgbSupport;
    }
}
