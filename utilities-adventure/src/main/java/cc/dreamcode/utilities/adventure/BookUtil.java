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
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@UtilityClass
public final class BookUtil {

    public static Book createBook(@NonNull Component title, @NonNull Component author, @NonNull Collection<Component> pages) {
        return Book.book(title, author, pages);
    }

    public static Book createBook(@NonNull Component title, @NonNull Component author, @NonNull Component... pages) {
        return Book.book(title, author, pages);
    }

    public static Book createBook(@NonNull String titleMiniMessage, @NonNull String authorMiniMessage, @NonNull List<String> pagesMiniMessage) {
        Component title = AdventureUtil.component(titleMiniMessage);
        Component author = AdventureUtil.component(authorMiniMessage);
        List<Component> pages = AdventureUtil.component(pagesMiniMessage);
        return Book.book(title, author, pages);
    }

    public static Book createBook(@NonNull String titleMiniMessage, @NonNull String authorMiniMessage, @NonNull List<String> pagesMiniMessage, @NonNull Map<String, Object> placeholders) {
        Component title = AdventureUtil.component(titleMiniMessage, placeholders);
        Component author = AdventureUtil.component(authorMiniMessage, placeholders);
        List<Component> pages = AdventureUtil.component(pagesMiniMessage, placeholders);
        return Book.book(title, author, pages);
    }

    public static Book createBook(@NonNull String titleMiniMessage, @NonNull String authorMiniMessage, @NonNull List<String> pagesMiniMessage, @NonNull Locale locale, @NonNull Map<String, Object> placeholders) {
        Component title = AdventureUtil.component(titleMiniMessage, locale, placeholders);
        Component author = AdventureUtil.component(authorMiniMessage, locale, placeholders);
        List<Component> pages = AdventureUtil.component(pagesMiniMessage, locale, placeholders);
        return Book.book(title, author, pages);
    }

    public static Book createBook(@NonNull String titleMiniMessage, @NonNull String authorMiniMessage, @NonNull String... pagesMiniMessage) {
        return createBook(titleMiniMessage, authorMiniMessage, Arrays.asList(pagesMiniMessage));
    }

    public static Book openBook(@NonNull Audience audience, @NonNull Book book) {
        audience.openBook(book);
        return book;
    }

    public static Book openBook(@NonNull Audience audience, @NonNull Component title, @NonNull Component author, @NonNull Collection<Component> pages) {
        Book book = createBook(title, author, pages);
        audience.openBook(book);
        return book;
    }

    public static Book openBook(@NonNull Audience audience, @NonNull String titleMiniMessage, @NonNull String authorMiniMessage, @NonNull List<String> pagesMiniMessage) {
        Book book = createBook(titleMiniMessage, authorMiniMessage, pagesMiniMessage);
        audience.openBook(book);
        return book;
    }

    public static Book openBook(@NonNull Audience audience, @NonNull String titleMiniMessage, @NonNull String authorMiniMessage, @NonNull List<String> pagesMiniMessage, @NonNull Map<String, Object> placeholders) {
        Book book = createBook(titleMiniMessage, authorMiniMessage, pagesMiniMessage, placeholders);
        audience.openBook(book);
        return book;
    }
}
