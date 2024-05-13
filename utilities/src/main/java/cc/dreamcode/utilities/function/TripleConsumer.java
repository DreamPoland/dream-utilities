package cc.dreamcode.utilities.function;

import lombok.NonNull;

@FunctionalInterface
public interface TripleConsumer<A, B, C> {

    void accept(A first, B second, C third);

    default TripleConsumer<A, B, C> then(@NonNull TripleConsumer<? super A, ? super B, ? super C> after) {
        return (a, b, c) -> {
            accept(a, b, c);
            after.accept(a, b, c);
        };
    }
}
