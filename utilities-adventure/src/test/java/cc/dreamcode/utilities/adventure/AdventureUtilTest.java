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

import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdventureUtilTest {

    @BeforeEach
    void setUp() {
        AdventureUtil.setRgbSupport(true);
    }

    @Test
    void testComponentParsing() {
        Component component = AdventureUtil.component("<red>Hello</red>");
        assertNotNull(component);
        assertEquals("Hello", ComponentUtil.toPlainText(component));
    }

    @Test
    void testComponentWithPlaceholders() {
        Map<String, Object> placeholders = Map.of("name", "World");
        Component component = AdventureUtil.component("Hello {name}!", placeholders);
        assertNotNull(component);
        assertEquals("Hello World!", ComponentUtil.toPlainText(component));
    }

    @Test
    void testMissingPlaceholderDoesNotThrowNpe() {
        Map<String, Object> emptyPlaceholders = Collections.emptyMap();
        Component component = AdventureUtil.component("Hello {missing_key}!", emptyPlaceholders);
        assertNotNull(component);
        assertEquals("Hello <missing:missing_key>!", ComponentUtil.toPlainText(component));
    }

    @Test
    void testProcessAndDeprocess() {
        String input = "<green>Success</green>";
        String processed = AdventureUtil.process(input);
        assertNotNull(processed);
        assertTrue(processed.contains("Success"));

        String deprocessed = AdventureUtil.deprocess(processed);
        assertNotNull(deprocessed);
    }

    @Test
    void testLegacyConversions() {
        Component component = AdventureUtil.component("<blue>Sky</blue>");

        String section = AdventureUtil.toLegacySection(component);
        String ampersand = AdventureUtil.toLegacyAmpersand(component);

        assertNotNull(section);
        assertNotNull(ampersand);

        Component fromSec = AdventureUtil.fromLegacySection(section);
        Component fromAmp = AdventureUtil.fromLegacyAmpersand(ampersand);

        assertEquals("Sky", ComponentUtil.toPlainText(fromSec));
        assertEquals("Sky", ComponentUtil.toPlainText(fromAmp));
    }

    @Test
    void testListProcessing() {
        List<String> inputs = List.of("<red>One</red>", "<blue>Two</blue>");
        List<Component> components = AdventureUtil.component(inputs);
        assertEquals(2, components.size());
        assertEquals("One", ComponentUtil.toPlainText(components.get(0)));
        assertEquals("Two", ComponentUtil.toPlainText(components.get(1)));
    }
}
