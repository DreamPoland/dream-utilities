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

        QueuedTeleportScheduler queuedTeleportScheduler = new QueuedTeleportScheduler(plugin, this.queuedTeleportCache);
        plugin.getServer().getScheduler().runTaskTimer(plugin, queuedTeleportScheduler, 0L, 2L);

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

                final Duration duration = existingTeleport.getCountdown();
                if (!duration.isNegative()) {
                    existingTeleport.getAlreadyInAction().accept(humanEntity);
                    return;
                }
            }
        }

        final Duration duration = queuedTeleport.getCountdown();
        queuedTeleport.getCountdownNotice().accept(humanEntity, duration);

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
