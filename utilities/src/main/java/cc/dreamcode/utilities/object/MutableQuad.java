package cc.dreamcode.utilities.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor(staticName = "of")
public class MutableQuad<A, B, C, D> {

    private A first;
    private B second;
    private C third;
    private D fourth;

    public MutableQuad<A, B, C, D> then(Consumer<MutableQuad<A, B, C, D>> after) {
        after.accept(this);
        return this;
    }

    public Quad<A, B, C, D> immutable() {
        return Quad.of(this.first, this.second, this.third, this.fourth);
    }
}
