package cc.dreamcode.utilities.builder;

import lombok.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@SuppressWarnings("unchecked")
public class MapBuilder<K, V> {

    private final Map<K, V> map;

    public MapBuilder() {
        this.map = new HashMap<>();
    }

    public MapBuilder(boolean weak) {
        if (weak) {
            this.map = new WeakHashMap<>();
        }
        else {
            this.map = new HashMap<>();
        }
    }

    public static <K, V> MapBuilder<K, V> builder() {
        return new MapBuilder<>();
    }

    public static <K, V> MapBuilder<K, V> builder(boolean weak) {
        return new MapBuilder<>(weak);
    }

    public static <K, V> Map<K, V> of() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> of(K k1, V v1) {
        return (Map<K, V>) new MapBuilder<>()
                .put(k1, v1)
                .build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        return (Map<K, V>) new MapBuilder<>()
                .put(k1, v1)
                .put(k2, v2)
                .build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return (Map<K, V>) new MapBuilder<>()
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return (Map<K, V>) new MapBuilder<>()
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return (Map<K, V>) new MapBuilder<>()
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .put(k5, v5)
                .build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        return (Map<K, V>) new MapBuilder<>()
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .put(k5, v5)
                .put(k6, v6)
                .build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        return (Map<K, V>) new MapBuilder<>()
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .put(k5, v5)
                .put(k6, v6)
                .put(k7, v7)
                .build();
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
        return (Map<K, V>) new MapBuilder<>()
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .put(k5, v5)
                .put(k6, v6)
                .put(k7, v7)
                .put(k8, v8)
                .build();
    }

    public static <K, V> Map<K, V> ofWeak() {
        return new WeakHashMap<>();
    }

    public static <K, V> Map<K, V> ofWeak(K k1, V v1) {
        return (Map<K, V>) new MapBuilder<>(true)
                .put(k1, v1)
                .build();
    }

    public static <K, V> Map<K, V> ofWeak(K k1, V v1, K k2, V v2) {
        return (Map<K, V>) new MapBuilder<>(true)
                .put(k1, v1)
                .put(k2, v2)
                .build();
    }

    public static <K, V> Map<K, V> ofWeak(K k1, V v1, K k2, V v2, K k3, V v3) {
        return (Map<K, V>) new MapBuilder<>(true)
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .build();
    }

    public static <K, V> Map<K, V> ofWeak(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return (Map<K, V>) new MapBuilder<>(true)
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .build();
    }

    public static <K, V> Map<K, V> ofWeak(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return (Map<K, V>) new MapBuilder<>(true)
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .put(k5, v5)
                .build();
    }

    public static <K, V> Map<K, V> ofWeak(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        return (Map<K, V>) new MapBuilder<>(true)
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .put(k5, v5)
                .put(k6, v6)
                .build();
    }

    public static <K, V> Map<K, V> ofWeak(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        return (Map<K, V>) new MapBuilder<>(true)
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .put(k5, v5)
                .put(k6, v6)
                .put(k7, v7)
                .build();
    }

    public static <K, V> Map<K, V> ofWeak(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
        return (Map<K, V>) new MapBuilder<>(true)
                .put(k1, v1)
                .put(k2, v2)
                .put(k3, v3)
                .put(k4, v4)
                .put(k5, v5)
                .put(k6, v6)
                .put(k7, v7)
                .put(k8, v8)
                .build();
    }

    public MapBuilder<K, V> put(K k, V v) {
        this.map.put(k, v);
        return this;
    }

    public MapBuilder<K, V> put(K[] array, V v) {
        Arrays.stream(array).forEach(k -> this.put(k, v));
        return this;
    }

    public MapBuilder<K, V> putAll(@NonNull Map<? extends K, ? extends V> map) {
        this.map.putAll(map);
        return this;
    }

    public Map<K, V> build() {
        return this.map;
    }
}
