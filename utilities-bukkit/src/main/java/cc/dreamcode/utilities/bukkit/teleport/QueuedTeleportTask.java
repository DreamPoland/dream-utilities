package cc.dreamcode.utilities.bukkit.teleport;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.Plugin;

import java.time.Duration;

@RequiredArgsConstructor
public class QueuedTeleportTask implements Runnable {

    private final Plugin plugin;
    private final QueuedTeleportCache queuedTeleportCache;

    @Override
    public void run() {
        this.queuedTeleportCache.getKeysAndValues().forEach((uuid, queuedTeleport) -> {

            final HumanEntity humanEntity = this.plugin.getServer().getPlayer(uuid);
            if (humanEntity == null) {
                this.queuedTeleportCache.remove(uuid);
                return;
            }

            final Duration duration = queuedTeleport.getCountdown();
            if (duration.isNegative() || duration.isZero()) {
                queuedTeleport.getTaskAfter().accept(humanEntity);
                this.queuedTeleportCache.remove(uuid);
                return;
            }

            queuedTeleport.getCountdownNotice().accept(humanEntity);
        });
    }
}
