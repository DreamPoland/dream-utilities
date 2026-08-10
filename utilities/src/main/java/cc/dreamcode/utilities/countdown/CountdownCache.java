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
package cc.dreamcode.utilities.countdown;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CountdownCache {

    private final Map<UUID, Countdown> map = new WeakHashMap<>();

    public boolean isComplete(@NonNull UUID uuid) {
        return this.map.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(uuid))
                .map(entry -> entry.getValue().isComplete())
                .findAny()
                .orElse(false);
    }

    public Optional<Countdown> getIfPresent(@NonNull UUID uuid) {
        return Optional.ofNullable(this.map.get(uuid));
    }

    public Countdown put(@NonNull UUID uuid, @NonNull Duration duration) {
        final Countdown countdown = new Countdown(uuid, Instant.now(), duration);

        this.map.put(uuid, countdown);
        return countdown;
    }

    public Countdown put(@NonNull UUID uuid, @NonNull Duration duration, @NonNull Runnable runnable) {
        final Countdown countdown = new Countdown(uuid, Instant.now(), duration, runnable);

        this.map.put(uuid, countdown);
        return countdown;
    }

    public void remove(@NonNull UUID uuid, boolean runnableRun) {
        this.getIfPresent(uuid).ifPresent(countdown -> {
            if (runnableRun && countdown.getRunnable() != null) countdown.getRunnable().run();
        });

        this.map.remove(uuid);
    }

    public List<UUID> uuidToRemove() {
        return this.map.keySet()
                .stream()
                .filter(this::isComplete)
                .collect(Collectors.toList());
    }
}
