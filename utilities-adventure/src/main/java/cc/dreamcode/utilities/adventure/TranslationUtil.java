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
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.translation.GlobalTranslator;
import net.kyori.adventure.translation.TranslationStore;
import net.kyori.adventure.translation.Translator;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@UtilityClass
public final class TranslationUtil {

    public static TranslationStore.StringBased createMessageStore(@NonNull Key key, @NonNull Locale defaultLocale) {
        TranslationStore.StringBased store = TranslationStore.messageFormat(key);
        store.defaultLocale(defaultLocale);
        return store;
    }

    public static TranslationStore.StringBased createMessageStore(@NonNull String namespace, @NonNull String value, @NonNull Locale defaultLocale) {
        return createMessageStore(Key.key(namespace, value), defaultLocale);
    }

    public static TranslationStore createComponentStore(@NonNull Key key, @NonNull Locale defaultLocale) {
        TranslationStore store = TranslationStore.component(key);
        store.defaultLocale(defaultLocale);
        return store;
    }

    public static TranslationStore createComponentStore(@NonNull String namespace, @NonNull String value, @NonNull Locale defaultLocale) {
        return createComponentStore(Key.key(namespace, value), defaultLocale);
    }

    public static TranslationStore.StringBased createAndRegisterMessageStore(@NonNull Key key, @NonNull Locale defaultLocale) {
        TranslationStore.StringBased store = createMessageStore(key, defaultLocale);
        GlobalTranslator.translator().addSource(store);
        return store;
    }

    public static TranslationStore.StringBased createAndRegisterMessageStore(@NonNull String namespace, @NonNull String value, @NonNull Locale defaultLocale) {
        return createAndRegisterMessageStore(Key.key(namespace, value), defaultLocale);
    }

    public static TranslationStore createAndRegisterComponentStore(@NonNull Key key, @NonNull Locale defaultLocale) {
        TranslationStore store = createComponentStore(key, defaultLocale);
        GlobalTranslator.translator().addSource(store);
        return store;
    }

    public static TranslationStore createAndRegisterComponentStore(@NonNull String namespace, @NonNull String value, @NonNull Locale defaultLocale) {
        return createAndRegisterComponentStore(Key.key(namespace, value), defaultLocale);
    }

    public static void registerSource(@NonNull Translator translator) {
        GlobalTranslator.translator().addSource(translator);
    }

    public static void unregisterSource(@NonNull Translator translator) {
        GlobalTranslator.translator().removeSource(translator);
    }

    public static void registerMessage(@NonNull TranslationStore.StringBased store, @NonNull Locale locale, @NonNull String key, @NonNull String format) {
        store.register(key, locale, new MessageFormat(format, locale));
    }

    public static void registerComponent(@NonNull TranslationStore store, @NonNull Locale locale, @NonNull String key, @NonNull Component component) {
        store.register(key, locale, component);
    }

    public static void registerMiniMessage(@NonNull TranslationStore store, @NonNull Locale locale, @NonNull String key, @NonNull String miniMessage) {
        store.register(key, locale, AdventureUtil.component(miniMessage));
    }

    public static void registerAllMessages(@NonNull TranslationStore.StringBased store, @NonNull Locale locale, @NonNull Map<String, String> translations) {
        translations.forEach((key, format) -> registerMessage(store, locale, key, format));
    }

    public static void registerAllComponents(@NonNull TranslationStore store, @NonNull Locale locale, @NonNull Map<String, Component> translations) {
        translations.forEach((key, component) -> registerComponent(store, locale, key, component));
    }

    public static void registerAllMiniMessages(@NonNull TranslationStore store, @NonNull Locale locale, @NonNull Map<String, String> translations) {
        translations.forEach((key, miniMessage) -> registerMiniMessage(store, locale, key, miniMessage));
    }

    public static void registerResourceBundle(@NonNull TranslationStore.StringBased store, @NonNull Locale locale, @NonNull ResourceBundle bundle, boolean escapeSingleQuotes) {
        store.registerAll(locale, bundle, escapeSingleQuotes);
    }

    public static TranslatableComponent translatable(@NonNull String key) {
        return Component.translatable(key);
    }

    public static TranslatableComponent translatable(@NonNull String key, ComponentLike... args) {
        return Component.translatable(key, args);
    }

    public static TranslatableComponent translatable(@NonNull String key, List<? extends ComponentLike> args) {
        return Component.translatable(key, args);
    }

    public static TranslatableComponent translatable(@NonNull String key, @NonNull String fallbackMiniMessage) {
        return Component.translatable(key, AdventureUtil.component(fallbackMiniMessage));
    }

    public static Component render(@NonNull Component component, @NonNull Locale locale) {
        return GlobalTranslator.render(component, locale);
    }

    public static Component render(@NonNull Component component, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        Component rendered = GlobalTranslator.render(component, locale);
        return ComponentUtil.replace(rendered, locale, placeholders);
    }
}
