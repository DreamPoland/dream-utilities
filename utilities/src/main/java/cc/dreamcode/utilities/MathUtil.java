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

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
