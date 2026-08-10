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
