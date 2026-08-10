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
public class TicksUtil {

    public static final int SECOND = 20;
    public static final int MINUTE = SECOND * 60;
    public static final int HOUR = MINUTE * 60;
    public static final int DAY = HOUR * 24;
    public static final int WEEK = DAY * 7;

    public static int ticksOf(@NonNull TimeUnit timeUnit, int value) {
        return ticksOf(timeUnit.toMillis(value));
    }

    public static int ticksOf(@NonNull Duration duration) {
        return ticksOf(duration, true);
    }

    public static int ticksOf(@NonNull Duration duration, boolean failsafe) {
        return ticksOf(duration.toMillis(), failsafe);
    }

    public static int ticksOf(long millis) {
        return ticksOf(millis, true);
    }

    public static int ticksOf(long millis, boolean failsafe) {

        if (millis == 0) {
            return 0;
        }

        if (millis < 50) {
            if (failsafe) {
                return 1;
            }

            throw new IllegalArgumentException("Cannot transform " + millis + " ms to ticks, too low value");
        }

        long ticks = millis / 50L;
        if (ticks > Integer.MAX_VALUE) {
            if (failsafe) {
                return Integer.MAX_VALUE;
            }
            throw new ArithmeticException("ticks overflow: " + ticks + " ticks is too large for an integer");
        }

        return (int) ticks;
    }
}
