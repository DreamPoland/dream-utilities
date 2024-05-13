package cc.dreamcode.utilities.collection.element;

import lombok.Data;

import java.util.Map;
import java.util.function.Consumer;

@Data
public class Entry<K, V> implements Map.Entry<K, V> {

    private final K key;
    private V value;

    public Entry<K, V> then(Consumer<Entry<K, V>> after) {
        after.accept(this);
        return this;
    }
}
