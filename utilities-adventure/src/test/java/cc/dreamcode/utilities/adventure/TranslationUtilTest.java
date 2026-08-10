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

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.translation.TranslationStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TranslationUtilTest {

    private TranslationStore.StringBased messageStore;
    private TranslationStore componentStore;

    @BeforeEach
    void setUp() {
        messageStore = TranslationUtil.createAndRegisterMessageStore(Key.key("dreamcode", "message_test"), Locale.ENGLISH);
        componentStore = TranslationUtil.createAndRegisterComponentStore(Key.key("dreamcode", "component_test"), Locale.ENGLISH);
    }

    @AfterEach
    void tearDown() {
        if (messageStore != null) {
            TranslationUtil.unregisterSource(messageStore);
        }
        if (componentStore != null) {
            TranslationUtil.unregisterSource(componentStore);
        }
    }

    @Test
    void testRegisterAndRenderMessageStore() {
        TranslationUtil.registerMessage(messageStore, Locale.ENGLISH, "welcome.message", "Welcome to the server, {0}!");
        TranslationUtil.registerMessage(messageStore, Locale.forLanguageTag("pl"), "welcome.message", "Witaj na serwerze, {0}!");

        TranslatableComponent component = TranslationUtil.translatable("welcome.message", Component.text("Jan"));

        Component renderedEn = TranslationUtil.render(component, Locale.ENGLISH);
        assertEquals("Welcome to the server, Jan!", ComponentUtil.toPlainText(renderedEn));

        Component renderedPl = TranslationUtil.render(component, Locale.forLanguageTag("pl"));
        assertEquals("Witaj na serwerze, Jan!", ComponentUtil.toPlainText(renderedPl));
    }

    @Test
    void testRegisterAndRenderComponentStoreWithMiniMessage() {
        TranslationUtil.registerMiniMessage(componentStore, Locale.ENGLISH, "rank.prefix", "<red>[ADMIN]</red> <white>{player}</white>");

        TranslatableComponent component = TranslationUtil.translatable("rank.prefix");
        Component rendered = TranslationUtil.render(component, Locale.ENGLISH, Map.of("player", "Alice"));

        assertNotNull(rendered);
        assertEquals("[ADMIN] Alice", ComponentUtil.toPlainText(rendered));
    }

    @Test
    void testRegisterAllMessages() {
        TranslationUtil.registerAllMessages(messageStore, Locale.ENGLISH, Map.of(
                "command.success", "Command executed successfully!",
                "command.error", "An error occurred!"
        ));

        TranslatableComponent component = TranslationUtil.translatable("command.success");
        Component rendered = TranslationUtil.render(component, Locale.ENGLISH);

        assertNotNull(rendered);
        assertEquals("Command executed successfully!", ComponentUtil.toPlainText(rendered));
    }
}
