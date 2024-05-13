package cc.dreamcode.utilities.function;

@FunctionalInterface
public interface QuadFunction<R, A, B, C, D> {

    R apply(A first, B second, C third, D fourth);
}
