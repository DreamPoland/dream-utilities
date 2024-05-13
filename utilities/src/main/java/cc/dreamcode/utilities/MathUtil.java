package cc.dreamcode.utilities;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.Instant;

@UtilityClass
public class MathUtil {

    public static double round(double number, int places) {
        double factor = Math.pow(10, places);
        return Math.round(number * factor) / factor;
    }

    public static Duration difference(long startEpochMilli, long timeInMills) {
        return Duration.ofMillis(startEpochMilli + timeInMills - Instant.now().toEpochMilli());
    }

    public static Duration difference(long startEpochMilli, @NonNull Duration duration) {
        return MathUtil.difference(startEpochMilli, duration.toMillis());
    }

    public static Duration difference(@NonNull Instant instant, long timeInMills) {
        return Duration.ofMillis(instant.toEpochMilli() + timeInMills - Instant.now().toEpochMilli());
    }

    public static Duration difference(@NonNull Instant instant, @NonNull Duration duration) {
        return MathUtil.difference(instant, duration.toMillis());
    }

    public static boolean isNegative(@NonNull Duration duration) {
        return duration.isZero() || duration.isNegative();
    }
}
