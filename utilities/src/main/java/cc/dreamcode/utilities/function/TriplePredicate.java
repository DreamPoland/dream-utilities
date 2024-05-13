package cc.dreamcode.utilities.function;

@FunctionalInterface
public interface TriplePredicate<A, B, C> {

    boolean test(A first, B second, C third);
}
