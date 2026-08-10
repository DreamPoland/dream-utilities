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
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PlayerListUtilTest {

    @Test
    void testSendHeaderAndFooter() {
        Audience audience = mock(Audience.class);
        PlayerListUtil.sendHeaderAndFooter(audience, "<gold>Header</gold>", "<gray>Footer</gray>");

        ArgumentCaptor<Component> headerCaptor = ArgumentCaptor.forClass(Component.class);
        ArgumentCaptor<Component> footerCaptor = ArgumentCaptor.forClass(Component.class);

        verify(audience).sendPlayerListHeaderAndFooter(headerCaptor.capture(), footerCaptor.capture());

        assertEquals("Header", ComponentUtil.toPlainText(headerCaptor.getValue()));
        assertEquals("Footer", ComponentUtil.toPlainText(footerCaptor.getValue()));
    }

    @Test
    void testSendHeaderAndFooterWithPlaceholders() {
        Audience audience = mock(Audience.class);
        PlayerListUtil.sendHeaderAndFooter(
                audience,
                "Welcome {player}",
                "Online: {online}",
                Map.of("player", "Alice", "online", 100)
        );

        ArgumentCaptor<Component> headerCaptor = ArgumentCaptor.forClass(Component.class);
        ArgumentCaptor<Component> footerCaptor = ArgumentCaptor.forClass(Component.class);

        verify(audience).sendPlayerListHeaderAndFooter(headerCaptor.capture(), footerCaptor.capture());

        assertEquals("Welcome Alice", ComponentUtil.toPlainText(headerCaptor.getValue()));
        assertEquals("Online: 100", ComponentUtil.toPlainText(footerCaptor.getValue()));
    }

    @Test
    void testSendHeaderOnly() {
        Audience audience = mock(Audience.class);
        PlayerListUtil.sendHeader(audience, "<blue>Welcome</blue>");

        ArgumentCaptor<Component> headerCaptor = ArgumentCaptor.forClass(Component.class);
        verify(audience).sendPlayerListHeader(headerCaptor.capture());

        assertEquals("Welcome", ComponentUtil.toPlainText(headerCaptor.getValue()));
    }

    @Test
    void testSendFooterOnly() {
        Audience audience = mock(Audience.class);
        PlayerListUtil.sendFooter(audience, "<red>Goodbye</red>");

        ArgumentCaptor<Component> footerCaptor = ArgumentCaptor.forClass(Component.class);
        verify(audience).sendPlayerListFooter(footerCaptor.capture());

        assertEquals("Goodbye", ComponentUtil.toPlainText(footerCaptor.getValue()));
    }
}
