package cc.dreamcode.utilities.countdown;

import cc.dreamcode.utilities.collection.ExpiringMap;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class CountdownCache {

    private final ExpiringMap<UUID, Countdown> map = new ExpiringMap<>((uuid, countdown) -> {
        if (countdown.getRunnable() != null) {
            countdown.getRunnable().run();
        }
    });

    public boolean isComplete(@NonNull UUID uuid) {
        return this.map.getIfPresent(uuid)
                .map(Countdown::isComplete)
                .orElse(false);
    }

    public Optional<Countdown> getIfPresent(@NonNull UUID uuid) {
        return this.map.getIfPresent(uuid);
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
}
