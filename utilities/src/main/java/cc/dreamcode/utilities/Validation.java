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
