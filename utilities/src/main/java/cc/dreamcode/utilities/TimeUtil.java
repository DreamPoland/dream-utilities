package cc.dreamcode.utilities;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class TimeUtil {

    public static String format(long mills) {

        long days = TimeUnit.MILLISECONDS.toDays(mills);
        long hours = TimeUnit.MILLISECONDS.toHours(mills) - (days * 24);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(mills) - (days * 24 * 60) - (hours * 60);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(mills) - (days * 24 * 60 * 60) - (hours * 60 * 60) - (minutes * 60);
        long millisecondsFinal = mills - (days * 24 * 60 * 60 * 1000)  - (hours * 60 * 60 * 1000) - (minutes * 60 * 1000) - (seconds * 1000);

        if (millisecondsFinal == 1000) {
            millisecondsFinal = millisecondsFinal - 1;
        }

        if (days != 0) {
            if (millisecondsFinal == 0L) {
                return days + "d " + hours + "h " + minutes + "min " + seconds + "s";
            }

            return days + "d " + hours + "h " + minutes + "min " + seconds + "s " + millisecondsFinal + "ms";
        }

        if (hours != 0) {
            if (millisecondsFinal == 0L) {
                return hours + "h " + minutes + "min " + seconds + "s";
            }

            return hours + "h " + minutes + "min " + seconds + "s " + millisecondsFinal + "ms";
        }

        if (minutes != 0) {
            if (millisecondsFinal == 0L) {
                return minutes + "min " + seconds + "s";
            }

            return minutes + "min " + seconds + "s " + millisecondsFinal + "ms";
        }

        if (seconds != 0) {
            if (millisecondsFinal == 0L) {
                return seconds + "s";
            }

            return seconds + "s " + millisecondsFinal + "ms";
        }

        return millisecondsFinal + "ms";
    }

    public static String format(@NonNull Duration duration) {
        return format(duration.toMillis());
    }

    public static String formatSec(long seconds) {

        long days = TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (days * 24);
        long minutes = TimeUnit.SECONDS.toMinutes(seconds) - (days * 24 * 60) - (hours * 60);
        long secs = seconds - (days * 24 * 60 * 60) - (hours * 60 * 60) - (minutes * 60);

        if (days != 0) {
            return days + "d " + hours + "h " + minutes + "min " + secs + "s";
        }

        if (hours != 0) {
            return hours + "h " + minutes + "min " + secs + "s";
        }

        if (minutes != 0) {
            return minutes + "min " + secs + "s";
        }

        return secs + "s";
    }

    public static String formatSec(@NonNull Duration duration) {
        return formatSec(duration.getSeconds());
    }
}
