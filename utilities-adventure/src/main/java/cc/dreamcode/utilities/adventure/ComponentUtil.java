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
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@UtilityClass
public final class ComponentUtil {

    public static Component of(@NonNull String miniMessage) {
        return AdventureUtil.component(miniMessage);
    }

    public static Component of(@NonNull String miniMessage, @NonNull Map<String, Object> placeholders) {
        return AdventureUtil.component(miniMessage, placeholders);
    }

    public static Component of(@NonNull String miniMessage, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return AdventureUtil.component(miniMessage, locale, placeholders);
    }

    public static List<Component> ofList(@NonNull List<String> miniMessages) {
        return AdventureUtil.component(miniMessages);
    }

    public static List<Component> ofList(@NonNull List<String> miniMessages, @NonNull Map<String, Object> placeholders) {
        return AdventureUtil.component(miniMessages, placeholders);
    }

    public static List<Component> ofList(@NonNull List<String> miniMessages, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        return AdventureUtil.component(miniMessages, locale, placeholders);
    }

    public static Component join(@NonNull List<? extends ComponentLike> components, @NonNull ComponentLike separator) {
        if (components.isEmpty()) {
            return Component.empty();
        }
        return Component.join(JoinConfiguration.separator(separator), components);
    }

    public static Component join(@NonNull ComponentLike[] components, @NonNull ComponentLike separator) {
        return join(Arrays.asList(components), separator);
    }

    public static Component replace(@NonNull Component component, @NonNull Pattern pattern, @NonNull Component replacement) {
        return component.replaceText(TextReplacementConfig.builder()
                .match(pattern)
                .replacement(x -> replacement)
                .build());
    }

    public static Component replace(@NonNull Component component, @NonNull String literalText, @NonNull Component replacement) {
        return replace(component, Locale.forLanguageTag("pl"), literalText, (Object) replacement);
    }

    public static Component replace(@NonNull Component component, @NonNull String from, @NonNull Object to) {
        return replace(component, Locale.forLanguageTag("pl"), from, to);
    }

    public static Component replace(@NonNull Component component, @NonNull Locale locale, @NonNull String from, @NonNull Object to) {
        String searchLiteral = (from.startsWith("{") && from.endsWith("}")) ? from : "{" + from + "}";
        Component replacementComponent = (to instanceof ComponentLike)
                ? ((ComponentLike) to).asComponent()
                : AdventureUtil.component(String.valueOf(to), locale, Collections.emptyMap());

        return component.replaceText(TextReplacementConfig.builder()
                .matchLiteral(searchLiteral)
                .replacement(replacementComponent)
                .build());
    }

    public static Component replace(@NonNull Component component, @NonNull Map<String, Object> placeholders) {
        return replace(component, Locale.forLanguageTag("pl"), placeholders);
    }

    public static Component replace(@NonNull Component component, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        Component result = component;
        for (Map.Entry<String, Object> entry : placeholders.entrySet()) {
            result = replace(result, locale, entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static String toPlainText(@NonNull Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }

    public static String toLegacySection(@NonNull Component component) {
        return AdventureUtil.toLegacySection(component);
    }

    public static String toLegacyAmpersand(@NonNull Component component) {
        return AdventureUtil.toLegacyAmpersand(component);
    }

    public static Component fromLegacySection(@NonNull String legacyText) {
        return AdventureUtil.fromLegacySection(legacyText);
    }

    public static Component fromLegacyAmpersand(@NonNull String legacyText) {
        return AdventureUtil.fromLegacyAmpersand(legacyText);
    }

    public static Component append(@NonNull Component base, @NonNull ComponentLike... toAppend) {
        TextComponent.Builder builder = Component.text().append(base);
        for (ComponentLike componentLike : toAppend) {
            builder.append(componentLike);
        }
        return builder.build();
    }
}
