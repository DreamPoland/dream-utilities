package cc.dreamcode.utilities.bukkit.adventure;

import cc.dreamcode.utilities.StringUtil;
import cc.dreamcode.utilities.bukkit.VersionUtil;
import eu.okaeri.placeholders.context.PlaceholderContext;
import eu.okaeri.placeholders.message.CompiledMessage;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class ColorProcessor {

    private static final char COLOR_CHAR = '§';

    private static final Pattern ALL_TEXT_PATTERN = Pattern.compile(".*");
    private static final Pattern FIELD_PATTERN = Pattern.compile("\\{(?<content>[^}]+)}");
    private static final Pattern SECTION_COLOR_PATTERN = Pattern.compile("(?i)§([0-9A-FK-OR])");
    private static final Pattern LEGACY_RGB_PATTERN = Pattern.compile("&#([a-fA-F0-9]{6})");

    private static final LegacyComponentSerializer AMPERSAND_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();
    private static final LegacyComponentSerializer LEGACY_SECTION_SERIALIZER = LegacyComponentSerializer.legacySection();
    private static final LegacyComponentSerializer SECTION_SERIALIZER = LegacyComponentSerializer.builder()
            .character(COLOR_CHAR)
            .hexColors()
            .build();

    private static final TextReplacementConfig COLOR_REPLACEMENTS = TextReplacementConfig.builder()
            .match(ALL_TEXT_PATTERN)
            .replacement((result, input) -> AMPERSAND_SERIALIZER.deserialize(result.group()))
            .build();

    private static final MiniMessage MINI_MESSAGE = MiniMessage.builder()
            .preProcessor(text -> SECTION_COLOR_PATTERN.matcher(text).replaceAll("&$1"))
            .preProcessor(text -> LEGACY_RGB_PATTERN.matcher(text).replaceAll("<#$1>"))
            .postProcessor(component -> component.replaceText(COLOR_REPLACEMENTS))
            .build();
    private static final MiniMessage PLACEHOLDER_MINI_MESSAGE = MiniMessage.builder()
            .preProcessor(text -> SECTION_COLOR_PATTERN.matcher(text).replaceAll("&$1"))
            .tags(TagResolver.builder().build())
            .build();
    private static final MiniMessage COLORIZED_PLACEHOLDER_MINI_MESSAGE = MiniMessage.builder()
            .preProcessor(text -> SECTION_COLOR_PATTERN.matcher(text).replaceAll("&$1"))
            .preProcessor(text -> LEGACY_RGB_PATTERN.matcher(text).replaceAll("<#$1>"))
            .tags(TagResolver.builder()
                    .resolver(StandardTags.color())
                    .resolver(StandardTags.decorations())
                    .resolver(StandardTags.rainbow())
                    .resolver(StandardTags.gradient())
                    .resolver(StandardTags.transition())
                    .build())
            .postProcessor(component -> component.replaceText(COLOR_REPLACEMENTS))
            .build();

    public static boolean forceRgbSupport = false;

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
        final PlaceholderContext placeholderContext = StringUtil.getPlaceholders().contextOf(compiledMessage)
                .with(placeholders);

        return component(text, placeholderContext, colorizePlaceholders);
    }

    private static Component component(@NonNull String text, @NonNull PlaceholderContext placeholderContext, boolean colorizePlaceholders) {

        final Component component = MINI_MESSAGE.deserialize(text);

        final Map<String, String> fields = renderFields(placeholderContext);
        final TextReplacementConfig replacementConfig = replacementConfig(fields, colorizePlaceholders);

        return component.replaceText(replacementConfig);
    }

    public static String process(@NonNull String text) {

        final Component component = component(text);

        if (ColorProcessor.forceRgbSupport || VersionUtil.isSupported(16)) {
            return SECTION_SERIALIZER.serialize(component);
        }

        return LEGACY_SECTION_SERIALIZER.serialize(component);
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
        final PlaceholderContext placeholderContext = StringUtil.getPlaceholders().contextOf(compiledMessage)
                .with(placeholders);

        return process(text, placeholderContext, colorizePlaceholders);
    }

    private static String process(@NonNull String text, @NonNull PlaceholderContext placeholderContext, boolean colorizePlaceholders) {

        Component component = component(text);

        final Map<String, String> fields = renderFields(placeholderContext);
        final TextReplacementConfig replacementConfig = replacementConfig(fields, colorizePlaceholders);

        component = component.replaceText(replacementConfig);

        if (ColorProcessor.forceRgbSupport || VersionUtil.isSupported(16)) {
            return SECTION_SERIALIZER.serialize(component);
        }

        return LEGACY_SECTION_SERIALIZER.serialize(component);
    }

    private static Map<String, String> renderFields(@NonNull PlaceholderContext placeholderContext) {
        return placeholderContext.renderFields()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getRaw(),
                        Map.Entry::getValue
                ));
    }

    private static TextReplacementConfig replacementConfig(@NonNull Map<String, String> replaceMap, boolean colorizePlaceholders) {
        return TextReplacementConfig.builder()
                .match(FIELD_PATTERN)
                .replacement((result, input) -> {
                    final String value = replaceMap.get(result.group(1));

                    if (colorizePlaceholders) {
                        return COLORIZED_PLACEHOLDER_MINI_MESSAGE.deserialize(value);
                    }

                    return PLACEHOLDER_MINI_MESSAGE.deserialize(value);
                })
                .build();
    }

    public static void setForceRgbSupport(boolean forceRgbSupport) {
        ColorProcessor.forceRgbSupport = forceRgbSupport;
    }
}
