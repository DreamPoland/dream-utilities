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

    public static String formatMin(long seconds) {

        long days = TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (days * 24);
        long minutes = TimeUnit.SECONDS.toMinutes(seconds) - (days * 24 * 60) - (hours * 60);

        if (days != 0) {
            return days + "d " + hours + "h " + minutes + "min";
        }

        if (hours != 0) {
            return hours + "h " + minutes + "min";
        }

        return minutes + "min";
    }

    public static String formatMin(@NonNull Duration duration) {
        return formatMin(duration.getSeconds());
    }

    public static String formatHours(long seconds) {

        long days = TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (days * 24);

        if (days != 0) {
            return days + "d " + hours + "h";
        }

        return hours + "h";
    }

    public static String formatHours(@NonNull Duration duration) {
        return formatHours(duration.getSeconds());
    }

    public static String formatDays(long seconds) {
        long days = TimeUnit.SECONDS.toDays(seconds);
        return days + "d";
    }

    public static String formatDays(@NonNull Duration duration) {
        return formatDays(duration.getSeconds());
    }

    public static String formatPlayingTime(long seconds) {
        long hours = TimeUnit.SECONDS.toHours(seconds);
        long minutes = TimeUnit.SECONDS.toMinutes(seconds) - hours * 60L;
        return hours != 0L ? hours + "h " + minutes + "min" : minutes + "min";
    }

    public static String formatPlayingTime(@NonNull Duration duration) {
        return formatPlayingTime(duration.getSeconds());
    }
}
