package cc.dreamcode.utilities.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor(staticName = "of")
public class Duo<A, B> {

    private final A first;
    private final B second;

    public Duo<A, B> then(Consumer<Duo<A, B>> after) {
        after.accept(this);
        return this;
    }

    public MutableDuo<A, B> mutable() {
        return MutableDuo.of(this.first, this.second);
    }
}
