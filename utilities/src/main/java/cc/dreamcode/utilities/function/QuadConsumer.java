package cc.dreamcode.utilities.function;

import lombok.NonNull;

@FunctionalInterface
public interface QuadConsumer<A, B, C, D> {

    void accept(A first, B second, C third, D fourth);

    default QuadConsumer<A, B, C, D> then(@NonNull QuadConsumer<? super A, ? super B, ? super C, ? super D> after) {
        return (a, b, c, d) -> {
            accept(a, b, c, d);
            after.accept(a, b, c, d);
        };
    }
}
