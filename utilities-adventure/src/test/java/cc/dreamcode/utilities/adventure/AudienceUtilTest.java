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
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AudienceUtilTest {

    @Test
    void testSendMessage() {
        Audience audience = mock(Audience.class);
        AudienceUtil.sendMessage(audience, "<green>Hello</green>");

        ArgumentCaptor<Component> captor = ArgumentCaptor.forClass(Component.class);
        verify(audience).sendMessage(captor.capture());

        Component sent = captor.getValue();
        assertNotNull(sent);
        assertEquals("Hello", ComponentUtil.toPlainText(sent));
    }

    @Test
    void testSendMessageWithPlaceholders() {
        Audience audience = mock(Audience.class);
        AudienceUtil.sendMessage(audience, "Hello {user}!", Map.of("user", "Admin"));

        ArgumentCaptor<Component> captor = ArgumentCaptor.forClass(Component.class);
        verify(audience).sendMessage(captor.capture());

        Component sent = captor.getValue();
        assertNotNull(sent);
        assertEquals("Hello Admin!", ComponentUtil.toPlainText(sent));
    }

    @Test
    void testPlaySound() {
        Audience audience = mock(Audience.class);
        AudienceUtil.playSound(audience, "entity.experience_orb.pickup", 1.0f, 1.0f);

        ArgumentCaptor<Sound> soundCaptor = ArgumentCaptor.forClass(Sound.class);
        verify(audience).playSound(soundCaptor.capture());

        Sound sound = soundCaptor.getValue();
        assertNotNull(sound);
        assertEquals("minecraft:entity.experience_orb.pickup", sound.name().asString());
        assertEquals(1.0f, sound.volume());
        assertEquals(1.0f, sound.pitch());
    }

    @Test
    void testAudienceWrap() {
        Audience audience = mock(Audience.class);
        assertNotNull(AudienceUtil.audience(audience));
    }
}
