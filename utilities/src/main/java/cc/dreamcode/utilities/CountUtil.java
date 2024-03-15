package cc.dreamcode.utilities;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.Instant;

@UtilityClass
public class CountUtil {

    public static Duration getCountDown(long startEpochMilli, long timeInMills) {
        return Duration.ofMillis(startEpochMilli + timeInMills - Instant.now().toEpochMilli());
    }

    public static Duration getCountDown(long startEpochMilli, @NonNull Duration duration) {
        return getCountDown(startEpochMilli, duration.toMillis());
    }

    public static Duration getCountDown(@NonNull Instant instant, long timeInMills) {
        return Duration.ofMillis(instant.toEpochMilli() + timeInMills - Instant.now().toEpochMilli());
    }

    public static Duration getCountDown(@NonNull Instant instant, @NonNull Duration duration) {
        return getCountDown(instant, duration.toMillis());
    }

    public static boolean isOut(@NonNull Duration duration) {
        return duration.isZero() || duration.isNegative();
    }
}
