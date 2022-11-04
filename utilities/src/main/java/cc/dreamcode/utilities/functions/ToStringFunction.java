package cc.dreamcode.utilities.functions;

@FunctionalInterface
public interface ToStringFunction<T> {
    String applyAsString(T value);
}
