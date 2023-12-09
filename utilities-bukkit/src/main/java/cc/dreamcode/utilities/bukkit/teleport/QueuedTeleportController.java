package cc.dreamcode.utilities.bukkit.teleport;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

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

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
            e.setCancelled(true);
            queuedTeleport.getMovedNotice().accept(player);
        }
    }
}
