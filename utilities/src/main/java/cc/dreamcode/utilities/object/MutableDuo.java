package cc.dreamcode.utilities.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor(staticName = "of")
public class MutableDuo<A, B> {

    private A first;
    private B second;

    public MutableDuo<A, B> then(Consumer<MutableDuo<A, B>> after) {
        after.accept(this);
        return this;
    }

    public Duo<A, B> immutable() {
        return Duo.of(this.first, this.second);
    }
}
