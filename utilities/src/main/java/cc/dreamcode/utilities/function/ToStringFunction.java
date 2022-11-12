package cc.dreamcode.utilities.function;

@FunctionalInterface
public interface ToStringFunction<T> {
    String applyAsString(T value);
}
