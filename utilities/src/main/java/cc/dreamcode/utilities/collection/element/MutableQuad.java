package cc.dreamcode.utilities.collection.element;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor
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
        return new Quad<>(this.first, this.second, this.third, this.fourth);
    }
}
