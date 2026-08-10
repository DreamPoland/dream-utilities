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
package cc.dreamcode.utilities.bukkit.teleport;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

@RequiredArgsConstructor
public class QueuedTeleportController implements Listener {

    private final QueuedTeleportCache queuedTeleportCache;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        final Player player = e.getPlayer();

        if (!this.queuedTeleportCache.isQueued(player.getUniqueId())) {
            return;
        }

        final QueuedTeleport queuedTeleport = this.queuedTeleportCache.get(player.getUniqueId());
        if (!queuedTeleport.isCancelOnMove() || e.getTo() == null) {
            return;
        }

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() ||
                e.getFrom().getBlockY() != e.getTo().getBlockY() ||
                e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
            queuedTeleport.getMovedNotice().accept(player);
            this.queuedTeleportCache.remove(player.getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.PLUGIN) ||
                event.getCause().equals(PlayerTeleportEvent.TeleportCause.UNKNOWN)) {
            return;
        }

        final Player player = event.getPlayer();

        if (!this.queuedTeleportCache.isQueued(player.getUniqueId())) {
            return;
        }

        final QueuedTeleport queuedTeleport = this.queuedTeleportCache.get(player.getUniqueId());
        if (!queuedTeleport.isCancelOnMove()) {
            return;
        }

        queuedTeleport.getMovedNotice().accept(player);
        this.queuedTeleportCache.remove(player.getUniqueId());
    }
}
