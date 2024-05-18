package cc.dreamcode.utilities.builder;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SetBuilder<T> {

    private final Set<T> set = new HashSet<>();

    public static <T> SetBuilder<T> builder() {
        return new SetBuilder<>();
    }

    @SafeVarargs
    public static <T> Set<T> of(T... values) {
        return new SetBuilder<T>()
                .addAll(Arrays.stream(values)
                        .collect(Collectors.toSet()))
                .build();
    }

    public SetBuilder<T> add(T t) {
        this.set.add(t);
        return this;
    }

    public SetBuilder<T> addAll(@NonNull Collection<? extends T> set) {
        this.set.addAll(set);
        return this;
    }

    public Set<T> build() {
        return this.set;
    }
}
