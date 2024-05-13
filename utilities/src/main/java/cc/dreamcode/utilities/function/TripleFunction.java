package cc.dreamcode.utilities.function;

@FunctionalInterface
public interface TripleFunction<R, A, B, C> {

    R apply(A first, B second, C third);
}
