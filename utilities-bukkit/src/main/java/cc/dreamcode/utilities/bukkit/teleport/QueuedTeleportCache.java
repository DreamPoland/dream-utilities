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

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class QueuedTeleportCache {

    private final Map<UUID, QueuedTeleport> queuedTeleportMap = new WeakHashMap<>();

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
