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
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComponentUtilTest {

    @Test
    void testJoin() {
        Component c1 = Component.text("Alpha");
        Component c2 = Component.text("Beta");
        Component separator = Component.text(" - ");

        Component joined = ComponentUtil.join(List.of(c1, c2), separator);
        assertEquals("Alpha - Beta", ComponentUtil.toPlainText(joined));
    }

    @Test
    void testReplaceString() {
        Component original = Component.text("Hello {name}!");
        Component replaced = ComponentUtil.replace(original, "name", "John");
        assertEquals("Hello John!", ComponentUtil.toPlainText(replaced));
    }

    @Test
    void testReplaceComponent() {
        Component original = Component.text("Hello {name}!");
        Component nameComponent = Component.text("John");
        Component replaced = ComponentUtil.replace(original, "name", nameComponent);
        assertEquals("Hello John!", ComponentUtil.toPlainText(replaced));
    }

    @Test
    void testReplaceMap() {
        Component original = Component.text("{greeting} {name}!");
        Map<String, Object> map = Map.of(
                "greeting", "Hi",
                "name", Component.text("Alice")
        );
        Component replaced = ComponentUtil.replace(original, map);
        assertEquals("Hi Alice!", ComponentUtil.toPlainText(replaced));
    }

    @Test
    void testToPlainText() {
        Component component = AdventureUtil.component("<bold><red>Formatted</red></bold>");
        assertEquals("Formatted", ComponentUtil.toPlainText(component));
    }

    @Test
    void testAppend() {
        Component base = Component.text("Start");
        Component extra = Component.text(" End");
        Component result = ComponentUtil.append(base, extra);
        assertEquals("Start End", ComponentUtil.toPlainText(result));
    }
}
