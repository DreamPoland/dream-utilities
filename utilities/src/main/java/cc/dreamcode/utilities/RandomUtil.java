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

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomUtil {

    public static int nextInteger(final int min, final int max) {
        if (max < min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        return new Random().ints(min, max)
                .findAny()
                .orElse(min);
    }

    public static int nextInteger(final int max) {
        return RandomUtil.nextInteger(0, max);
    }

    public static long nextLong(final long min, final long max) {
        if (max < min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        return new Random().longs(min, max)
                .findAny()
                .orElse(min);
    }

    public static long nextLong(final long max) {
        if (max < 0L) {
            throw new IllegalArgumentException("Max value cannot be smaller than 0.");
        }

        return RandomUtil.nextLong(0L, max);
    }

    public static double nextDouble(final double min, final double max) {
        if (max < min) {
            throw new IllegalArgumentException("Max value cannot be smaller than min.");
        }

        return new Random().doubles(min, max)
                .findAny()
                .orElse(min);
    }

    public static double nextDouble(final double max) {
        if (max < 0.0D) {
            throw new IllegalArgumentException("Max value cannot be smaller than 0.");
        }

        return RandomUtil.nextDouble(0.0D, max);
    }

    public static boolean nextBoolean() {
        return RandomUtil.nextInteger(1) >= 1;
    }

    public static boolean chance(final double percent) {
        return percent >= 100.0 || percent >= RandomUtil.nextDouble(0.0, 100.0);
    }

    public static String alphabetic(int length) {
        final Random random = new Random();

        final int leftLimit = 97;
        final int rightLimit = 122;
        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String alphanumeric(int length) {
        final Random random = new Random();

        final int leftLimit = 48;
        final int rightLimit = 122;
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
