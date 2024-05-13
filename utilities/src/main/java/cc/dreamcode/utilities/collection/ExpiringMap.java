package cc.dreamcode.utilities.collection;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.BiConsumer;

@RequiredArgsConstructor
@AllArgsConstructor
public class ExpiringMap<K, V> implements Map<K, V> {

    private Duration duration;
    private BiConsumer<K, V> consumer;

    private final Timer timer = new Timer();
    private final Map<K, V> map = new HashMap<>();

    public ExpiringMap(@NonNull Duration duration) {
        this.duration = duration;
    }

    public ExpiringMap(@NonNull BiConsumer<K, V> consumer) {
        this.consumer = consumer;
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

        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (consumer != null) {
                    consumer.accept(key, value);
                }

                remove(key);
            }
        }, this.duration.toMillis());

        return value;
    }

    public V put(@NonNull K key, @NonNull V value, @NonNull Duration duration) {
        this.map.put(key, value);

        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, duration.toMillis());

        return value;
    }

    public V put(@NonNull K key, @NonNull V value, @NonNull BiConsumer<K, V> consumer) {
        this.map.put(key, value);

        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                consumer.accept(key, value);
                remove(key);
            }
        }, this.duration.toMillis());

        return value;
    }

    public V put(@NonNull K key, @NonNull V value, @NonNull Duration duration, @NonNull BiConsumer<K, V> consumer) {
        this.map.put(key, value);

        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!containsKey(key)) {
                    return;
                }

                consumer.accept(key, value);
                remove(key);
            }
        }, duration.toMillis());

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

    /**
     * @return The number of tasks removed.
     */
    public int purge() {
        return this.timer.purge();
    }
}
