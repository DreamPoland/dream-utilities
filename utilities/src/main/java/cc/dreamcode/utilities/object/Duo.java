package cc.dreamcode.utilities.object;

import lombok.Data;

import java.util.function.Consumer;

@Data
public class Duo<A, B> {

    private final A first;
    private final B second;

    public Duo<A, B> then(Consumer<Duo<A, B>> after) {
        after.accept(this);
        return this;
    }

    public MutableDuo<A, B> mutable() {
        return new MutableDuo<>(this.first, this.second);
    }
}
