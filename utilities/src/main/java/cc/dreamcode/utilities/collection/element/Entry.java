package cc.dreamcode.utilities.collection.element;

import java.util.Map;
import java.util.function.Consumer;

public class Entry<K, V> implements Map.Entry<K, V> {

    private final K key;
    private V value;

    public Entry(K key) {
        this.key = key;
    }

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Entry<K, V> then(Consumer<Entry<K, V>> after) {
        after.accept(this);
        return this;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }
}
