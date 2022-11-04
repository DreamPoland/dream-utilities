package cc.dreamcode.utilities.functions;

@FunctionalInterface
public interface ToBooleanFunction<T> {
    Boolean applyAsBoolean(T value);
}
