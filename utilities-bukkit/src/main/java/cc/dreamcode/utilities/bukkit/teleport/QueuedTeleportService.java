package cc.dreamcode.utilities.bukkit.teleport;

import lombok.NonNull;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.Plugin;

import java.time.Duration;
import java.util.UUID;

public class QueuedTeleportService {

    private final Plugin plugin;
    private final QueuedTeleportCache queuedTeleportCache;

    public QueuedTeleportService(@NonNull Plugin plugin) {
        this.plugin = plugin;
        this.queuedTeleportCache = new QueuedTeleportCache();

        QueuedTeleportTask queuedTeleportTask = new QueuedTeleportTask(plugin, this.queuedTeleportCache);
        plugin.getServer().getScheduler().runTaskTimer(plugin, queuedTeleportTask, 0L, 20L);

        QueuedTeleportController queuedTeleportController = new QueuedTeleportController(this.queuedTeleportCache);
        plugin.getServer().getPluginManager().registerEvents(queuedTeleportController, plugin);
    }

    /**
     * Applies teleport and creates a QueuedTeleport class to the cache by Player unique identifier.
     *
     * @param uuid Unique identifier of the Player.
     * @param queuedTeleport QueueTeleport class created by constructor with params to teleport.
     */
    public void applyTeleport(@NonNull UUID uuid, @NonNull QueuedTeleport queuedTeleport, boolean forceTeleport) {

        final HumanEntity humanEntity = this.plugin.getServer().getPlayer(uuid);
        if (humanEntity == null) {
            return;
        }

        if (!forceTeleport) {
            if (this.queuedTeleportCache.isQueued(uuid)) {
                final QueuedTeleport existingTeleport = this.queuedTeleportCache.get(uuid);

                final Duration duration = queuedTeleport.getCountdown();
                if (!duration.isNegative()) {
                    existingTeleport.getAlreadyInAction().accept(humanEntity);
                    return;
                }
            }
        }

        queuedTeleport.getCountdownNotice().accept(humanEntity);
        this.queuedTeleportCache.apply(uuid, queuedTeleport);
    }

    /**
     * Applies teleport and creates a QueuedTeleport class to the cache by Player unique identifier.
     *
     * @param uuid Unique identifier of the Player.
     * @param queuedTeleport QueueTeleport class created by constructor with params to teleport.
     */
    public void applyTeleport(@NonNull UUID uuid, @NonNull QueuedTeleport queuedTeleport) {
        this.applyTeleport(uuid, queuedTeleport, false);
    }

    /**
     * Applies teleport and creates a QueuedTeleport class to the cache by HumanEntity object.
     *
     * @param humanEntity Object class of the Player.
     * @param queuedTeleport QueueTeleport class created by constructor with params to teleport.
     */
    public void applyTeleport(@NonNull HumanEntity humanEntity, @NonNull QueuedTeleport queuedTeleport, boolean forceTeleport) {
        this.applyTeleport(humanEntity.getUniqueId(), queuedTeleport, forceTeleport);
    }

    /**
     * Applies teleport and creates a QueuedTeleport class to the cache by HumanEntity object.
     *
     * @param humanEntity Object class of the Player.
     * @param queuedTeleport QueueTeleport class created by constructor with params to teleport.
     */
    public void applyTeleport(@NonNull HumanEntity humanEntity, @NonNull QueuedTeleport queuedTeleport) {
        this.applyTeleport(humanEntity.getUniqueId(), queuedTeleport);
    }

    /**
     * Cancel teleport and remove object from the cache by Player unique identifier.
     *
     * @param uuid Unique identifier of the Player.
     */
    public void cancelTeleport(@NonNull UUID uuid) {
        this.queuedTeleportCache.remove(uuid);
    }

    /**
     * Cancel teleport and remove object from the cache by HumanEntity object.
     *
     * @param humanEntity Object class of the Player.
     */
    public void cancelTeleport(@NonNull HumanEntity humanEntity) {
        this.cancelTeleport(humanEntity.getUniqueId());
    }

    public static QueuedTeleportService apply(@NonNull Plugin plugin) {
        return new QueuedTeleportService(plugin);
    }
}
