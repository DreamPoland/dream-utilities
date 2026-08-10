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

import cc.dreamcode.utilities.color.ColorProcessor;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AdventureProcessorTest {

    private final ColorProcessor processor = new AdventureProcessor();

    @Test
    void testColor() {
        String colored = processor.color("<red>Test</red>");
        assertNotNull(colored);
        assertEquals("§cTest", colored);
    }

    @Test
    void testColorWithPlaceholders() {
        String colored = processor.color("<green>User: {user}</green>", Map.of("user", "Alice"));
        assertNotNull(colored);
        assertEquals("§aUser: Alice", colored);
    }

    @Test
    void testDecolor() {
        String decolored = processor.decolor("§cTest");
        assertNotNull(decolored);
        assertEquals("<red>Test", decolored);
    }
}
