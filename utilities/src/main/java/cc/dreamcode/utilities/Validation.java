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

import java.util.function.Consumer;

@UtilityClass
public class Validation {

    public static <T> void nonNull(T t, @NonNull Consumer<T> consumer) {
        if (t == null) {
            return;
        }

        consumer.accept(t);
    }

    public static void isNull(Object ob, @NonNull Runnable runnable) {
        if (ob != null) {
            return;
        }

        runnable.run();
    }

    public static void isTrue(boolean b, @NonNull Runnable runnable) {
        if (!b) {
            return;
        }

        runnable.run();
    }

    public static void isFalse(boolean b, @NonNull Runnable runnable) {
        if (b) {
            return;
        }

        runnable.run();
    }
}
