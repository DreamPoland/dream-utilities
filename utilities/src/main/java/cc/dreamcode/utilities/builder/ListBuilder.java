package cc.dreamcode.utilities.builder;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class ListBuilder<T> {

    private final List<T> list = new ArrayList<>();

    public ListBuilder<T> add(T t) {
        this.list.add(t);
        return this;
    }

    public ListBuilder<T> addAll(@NonNull Collection<? extends T> list) {
        this.list.addAll(list);
        return this;
    }

    public List<T> build() {
        return this.list;
    }

}
