package cc.dreamcode.utilities.bukkit.teleport;

import lombok.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QueuedTeleportCache {

    private final Map<UUID, QueuedTeleport> queuedTeleportMap = new HashMap<>();

    /**
     * Returns unmodifiable keys and values from cache.
     *
     * @return Unmodifiable map from QueuedTeleportMap.
     */
    public Map<UUID, QueuedTeleport> getKeysAndValues() {
        return Collections.unmodifiableMap(this.queuedTeleportMap);
    }

    /**
     * Checking a Player if does it present in cache by Unique identifier.
     *
     * @param uuid Unique identifier of the Player.
     * @return True if the Player is queued to teleport.
     */
    public boolean isQueued(@NonNull UUID uuid) {
        return this.queuedTeleportMap.containsKey(uuid);
    }

    /**
     * Retrieves a QueuedTeleport class from the cache by their unique identifier.
     *
     * @param uuid Unique identifier of the Player.
     * @return QueueTeleport if found, otherwise null.
     */
    public QueuedTeleport get(@NonNull UUID uuid) {
        return this.queuedTeleportMap.get(uuid);
    }

    /**
     * Applies a QueuedTeleport class to the cache by Player unique identifier.
     *
     * @param uuid Unique identifier of the Player.
     * @param queuedTeleport QueueTeleport class created by constructor with params to teleport.
     */
    public void apply(@NonNull UUID uuid, @NonNull QueuedTeleport queuedTeleport) {
        this.queuedTeleportMap.put(uuid, queuedTeleport);
    }

    /**
     * Removes a Player from queue-teleport cache, if presenting or not.
     *
     * @param uuid Unique identifier of the Player.
     */
    public void remove(@NonNull UUID uuid) {
        this.queuedTeleportMap.remove(uuid);
    }
}
