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
