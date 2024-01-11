package cc.dreamcode.utilities.map;

import lombok.NonNull;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class ExpireMap<K, V> implements Map<K, V> {

    private final Duration duration;

    private final Map<K, V> map = new HashMap<>();

    public ExpireMap(@NonNull Duration duration) {
        this.duration = duration;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.map.get(key);
    }

    public Optional<V> getIfPresent(@NonNull Object key) {
        return Optional.ofNullable(this.get(key));
    }

    @Override
    public V put(@NonNull K key, @NonNull V value) {
        this.map.put(key, value);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, this.duration.toMillis());

        return value;
    }

    @Override
    public V remove(Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(@NonNull Map<? extends K, ? extends V> m) {
        this.map.putAll(m);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }
}
