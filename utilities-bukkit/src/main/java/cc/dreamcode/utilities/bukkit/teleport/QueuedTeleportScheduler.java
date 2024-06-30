package cc.dreamcode.utilities.bukkit.teleport;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.Plugin;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class QueuedTeleportScheduler implements Runnable {

    private final Plugin plugin;
    private final QueuedTeleportCache queuedTeleportCache;

    @Override
    public void run() {
        final Set<UUID> uuidToRemove = new HashSet<>();

        this.queuedTeleportCache.getKeysAndValues().forEach((uuid, queuedTeleport) -> {

            final HumanEntity humanEntity = this.plugin.getServer().getPlayer(uuid);
            if (humanEntity == null) {
                uuidToRemove.add(uuid);
                return;
            }

            final Duration duration = queuedTeleport.getCountdown();
            if (duration.isNegative() || duration.isZero()) {
                queuedTeleport.getTaskAfter().accept(humanEntity);
                uuidToRemove.add(uuid);
                return;
            }

            queuedTeleport.getCountdownNotice().accept(humanEntity, duration);
        });

        uuidToRemove.forEach(this.queuedTeleportCache::remove);
    }
}
