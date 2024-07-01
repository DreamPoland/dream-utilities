package cc.dreamcode.utilities.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor(staticName = "of")
public class Quad<A, B, C, D> {

    private final A first;
    private final B second;
    private final C third;
    private final D fourth;

    public Quad<A, B, C, D> then(Consumer<Quad<A, B, C, D>> after) {
        after.accept(this);
        return this;
    }

    public MutableQuad<A, B, C, D> mutable() {
        return MutableQuad.of(this.first, this.second, this.third, this.fourth);
    }
}
