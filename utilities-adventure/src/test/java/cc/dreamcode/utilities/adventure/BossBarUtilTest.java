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
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BossBarUtilTest {

    @Test
    void testCreateBossBar() {
        BossBar bossBar = BossBarUtil.createBossBar("<red>Event In Progress</red>", 0.75f, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
        assertNotNull(bossBar);
        assertEquals("Event In Progress", ComponentUtil.toPlainText(bossBar.name()));
        assertEquals(0.75f, bossBar.progress());
        assertEquals(BossBar.Color.RED, bossBar.color());
        assertEquals(BossBar.Overlay.PROGRESS, bossBar.overlay());
    }

    @Test
    void testCreateBossBarWithPlaceholders() {
        BossBar bossBar = BossBarUtil.createBossBar("Boss HP: {hp}%", 0.5f, BossBar.Color.PURPLE, BossBar.Overlay.NOTCHED_10, Map.of("hp", 50));
        assertNotNull(bossBar);
        assertEquals("Boss HP: 50%", ComponentUtil.toPlainText(bossBar.name()));
        assertEquals(0.5f, bossBar.progress());
    }

    @Test
    void testShowAndHideBossBar() {
        Audience audience = mock(Audience.class);
        BossBar bossBar = BossBarUtil.showBossBar(audience, "<gold>Welcome</gold>", 1.0f, BossBar.Color.YELLOW, BossBar.Overlay.PROGRESS);
        assertNotNull(bossBar);
        verify(audience).showBossBar(bossBar);

        BossBarUtil.hideBossBar(audience, bossBar);
        verify(audience).hideBossBar(bossBar);
    }

    @Test
    void testUpdateBossBar() {
        BossBar bossBar = BossBarUtil.createBossBar("Initial", 1.0f);
        BossBarUtil.updateTitle(bossBar, "<green>Updated</green>");
        assertEquals("Updated", ComponentUtil.toPlainText(bossBar.name()));

        BossBarUtil.updateProgress(bossBar, 0.25f);
        assertEquals(0.25f, bossBar.progress());

        BossBarUtil.updateColor(bossBar, BossBar.Color.GREEN);
        assertEquals(BossBar.Color.GREEN, bossBar.color());

        BossBarUtil.updateOverlay(bossBar, BossBar.Overlay.NOTCHED_6);
        assertEquals(BossBar.Overlay.NOTCHED_6, bossBar.overlay());
    }
}
