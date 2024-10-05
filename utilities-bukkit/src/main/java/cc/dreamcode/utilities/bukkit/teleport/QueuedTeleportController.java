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
