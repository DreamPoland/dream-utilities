package cc.dreamcode.utilities.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor
public class MutableTriple<A, B, C> {

    private A first;
    private B second;
    private C third;

    public MutableTriple<A, B, C> then(Consumer<MutableTriple<A, B, C>> after) {
        after.accept(this);
        return this;
    }

    public Triple<A, B, C> immutable() {
        return new Triple<>(this.first, this.second, this.third);
    }
}
