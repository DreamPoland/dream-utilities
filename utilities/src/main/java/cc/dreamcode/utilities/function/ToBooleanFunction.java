package cc.dreamcode.utilities.function;

@FunctionalInterface
public interface ToBooleanFunction<T> {

    Boolean applyAsBoolean(T value);
}
