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

    public static <T> void isNull(T t, @NonNull Runnable runnable) {
        if (t != null) {
            return;
        }

        runnable.run();
    }

    public static <T> void isTrue(boolean b, @NonNull Runnable runnable) {
        if (!b) {
            return;
        }

        runnable.run();
    }

    public static <T> void isFalse(boolean b, @NonNull Runnable runnable) {
        if (b) {
            return;
        }

        runnable.run();
    }
}
