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
        return format(instant, zoneId, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(@NonNull Instant instant, @NonNull ZoneId zoneId, @NonNull String pattern) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return zonedDateTime.format(formatter);
    }

    public static String formatOnlyDate(@NonNull Instant instant) {
        return formatOnlyDate(instant, ZoneId.of("Poland"));
    }

    public static String formatOnlyDate(@NonNull Instant instant, @NonNull ZoneId zoneId) {
        return formatOnlyDate(instant, zoneId, "yyyy-MM-dd");
    }

    public static String formatOnlyDate(@NonNull Instant instant, @NonNull ZoneId zoneId, @NonNull String pattern) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return zonedDateTime.format(formatter);
    }

    public static String formatOnlyTime(@NonNull Instant instant) {
        return formatOnlyTime(instant, ZoneId.of("Poland"));
    }

    public static String formatOnlyTime(@NonNull Instant instant, @NonNull ZoneId zoneId) {
        return formatOnlyTime(instant, zoneId, "HH:mm:ss");
    }

    public static String formatOnlyTime(@NonNull Instant instant, @NonNull ZoneId zoneId, @NonNull String pattern) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return zonedDateTime.format(formatter);
    }
}
