package cc.dreamcode.utilities.countdown;

import cc.dreamcode.utilities.MathUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Countdown {

    private final UUID uuid;
    private final Instant start;
    private final Duration duration;
    private final Runnable runnable;

    public Countdown(UUID uuid, Instant start, Duration duration) {
        this.uuid = uuid;
        this.start = start;
        this.duration = duration;
        this.runnable = () -> {
            // complete
        };
    }

    public boolean isComplete() {
        return MathUtil.isNegative(MathUtil.difference(this.start, this.duration));
    }
}
