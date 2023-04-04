package cc.dreamcode.utilities;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
    public static String format(@NonNull Instant instant) {
        return format(instant, ZoneId.of("Poland"));
    }

    public static String format(@NonNull Instant instant, @NonNull ZoneId zoneId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zonedDateTime.format(formatter);
    }

    public static String formatOnlyDate(@NonNull Instant instant) {
        return formatOnlyDate(instant, ZoneId.of("Poland"));
    }

    public static String formatOnlyDate(@NonNull Instant instant, @NonNull ZoneId zoneId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return zonedDateTime.format(formatter);
    }

    public static String formatOnlyTime(@NonNull Instant instant) {
        return formatOnlyTime(instant, ZoneId.of("Poland"));
    }

    public static String formatOnlyTime(@NonNull Instant instant, @NonNull ZoneId zoneId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return zonedDateTime.format(formatter);
    }
}
