package cc.dreamcode.utilities.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor(staticName = "of")
public class Triple<A, B, C> {

    private final A first;
    private final B second;
    private final C third;

    public Triple<A, B, C> then(Consumer<Triple<A, B, C>> after) {
        after.accept(this);
        return this;
    }

    public MutableTriple<A, B, C> mutable() {
        return MutableTriple.of(this.first, this.second, this.third);
    }
}
