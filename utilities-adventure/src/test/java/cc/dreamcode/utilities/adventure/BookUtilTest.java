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

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.inventory.Book;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BookUtilTest {

    @Test
    void testCreateBook() {
        Book book = BookUtil.createBook(
                "<gold>Rulebook</gold>",
                "<green>Admin</green>",
                List.of("<gray>Rule 1</gray>", "<yellow>Rule 2</yellow>")
        );

        assertNotNull(book);
        assertEquals("Rulebook", ComponentUtil.toPlainText(book.title()));
        assertEquals("Admin", ComponentUtil.toPlainText(book.author()));
        assertEquals(2, book.pages().size());
        assertEquals("Rule 1", ComponentUtil.toPlainText(book.pages().get(0)));
        assertEquals("Rule 2", ComponentUtil.toPlainText(book.pages().get(1)));
    }

    @Test
    void testCreateBookWithPlaceholders() {
        Book book = BookUtil.createBook(
                "Welcome {player}",
                "Server",
                List.of("Hello {player}, your rank is {rank}."),
                Map.of("player", "Alice", "rank", "VIP")
        );

        assertNotNull(book);
        assertEquals("Welcome Alice", ComponentUtil.toPlainText(book.title()));
        assertEquals("Server", ComponentUtil.toPlainText(book.author()));
        assertEquals("Hello Alice, your rank is VIP.", ComponentUtil.toPlainText(book.pages().get(0)));
    }

    @Test
    void testOpenBook() {
        Audience audience = mock(Audience.class);
        Book book = BookUtil.openBook(
                audience,
                "Guide",
                "Author",
                List.of("Page 1")
        );

        assertNotNull(book);
        verify(audience).openBook(book);
    }
}
