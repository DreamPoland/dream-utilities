/*
 * Copyright (c) 2026 DreamCode
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cc.dreamcode.utilities.bukkit.nbt;

import cc.dreamcode.utilities.builder.MapBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
public class ItemNbtNewer implements ItemNbt {

    private static final Class<?> NAMESPACED_KEY_CLASS;
    private static final Class<?> PERSISTENT_DATA_CONTAINER_CLASS;
    private static final Class<?> PERSISTENT_DATA_TYPE_CLASS;
    private static final Object STRING_DATA_TYPE;
    private static final Constructor<?> NAMESPACED_KEY_CONSTRUCTOR;

    private static final Method GET_PERSISTENT_DATA_CONTAINER_METHOD;
    private static final Method CONTAINER_GET_KEYS_METHOD;
    private static final Method CONTAINER_GET_METHOD;
    private static final Method CONTAINER_SET_METHOD;
    private static final Method NAMESPACED_KEY_GET_NAMESPACE_METHOD;
    private static final Method NAMESPACED_KEY_GET_KEY_METHOD;

    static {
        try {
            NAMESPACED_KEY_CLASS = Class.forName("org.bukkit.NamespacedKey");
            PERSISTENT_DATA_CONTAINER_CLASS = Class.forName("org.bukkit.persistence.PersistentDataContainer");
            PERSISTENT_DATA_TYPE_CLASS = Class.forName("org.bukkit.persistence.PersistentDataType");
            STRING_DATA_TYPE = PERSISTENT_DATA_TYPE_CLASS.getField("STRING").get(null);
            NAMESPACED_KEY_CONSTRUCTOR = NAMESPACED_KEY_CLASS.getConstructor(Plugin.class, String.class);

            GET_PERSISTENT_DATA_CONTAINER_METHOD = ItemMeta.class.getMethod("getPersistentDataContainer");
            CONTAINER_GET_KEYS_METHOD = PERSISTENT_DATA_CONTAINER_CLASS.getMethod("getKeys");
            CONTAINER_GET_METHOD = PERSISTENT_DATA_CONTAINER_CLASS.getMethod("get", NAMESPACED_KEY_CLASS, PERSISTENT_DATA_TYPE_CLASS);
            CONTAINER_SET_METHOD = PERSISTENT_DATA_CONTAINER_CLASS.getMethod("set", NAMESPACED_KEY_CLASS, PERSISTENT_DATA_TYPE_CLASS, Object.class);
            NAMESPACED_KEY_GET_NAMESPACE_METHOD = NAMESPACED_KEY_CLASS.getMethod("getNamespace");
            NAMESPACED_KEY_GET_KEY_METHOD = NAMESPACED_KEY_CLASS.getMethod("getKey");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ItemNbtNewer reflection", e);
        }
    }

    @Override
    @SneakyThrows
    public Map<String, String> getValues(@NonNull Plugin plugin, @NonNull ItemStack itemStack) {

        final MapBuilder<String, String> mapBuilder = new MapBuilder<>();

        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return mapBuilder.build();
        }

        final Object container = GET_PERSISTENT_DATA_CONTAINER_METHOD.invoke(itemMeta);
        @SuppressWarnings("unchecked")
        final java.util.Set<Object> keys = (java.util.Set<Object>) CONTAINER_GET_KEYS_METHOD.invoke(container);

        final String pluginNamespace = plugin.getName().toLowerCase(Locale.ROOT);

        for (Object namespacedKey : keys) {
            final String namespace = (String) NAMESPACED_KEY_GET_NAMESPACE_METHOD.invoke(namespacedKey);
            if (namespace.equals(pluginNamespace)) {
                final String key = (String) NAMESPACED_KEY_GET_KEY_METHOD.invoke(namespacedKey);
                final String value = (String) CONTAINER_GET_METHOD.invoke(container, namespacedKey, STRING_DATA_TYPE);
                mapBuilder.put(key, value);
            }
        }

        return mapBuilder.build();
    }

    @Override
    @SneakyThrows
    public Map<String, String> getValues(@NonNull ItemStack itemStack) {

        final MapBuilder<String, String> mapBuilder = new MapBuilder<>();

        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return mapBuilder.build();
        }

        final Object container = GET_PERSISTENT_DATA_CONTAINER_METHOD.invoke(itemMeta);
        @SuppressWarnings("unchecked")
        final java.util.Set<Object> keys = (java.util.Set<Object>) CONTAINER_GET_KEYS_METHOD.invoke(container);

        for (Object namespacedKey : keys) {
            final String key = (String) NAMESPACED_KEY_GET_KEY_METHOD.invoke(namespacedKey);
            final String value = (String) CONTAINER_GET_METHOD.invoke(container, namespacedKey, STRING_DATA_TYPE);
            mapBuilder.put(key, value);
        }

        return mapBuilder.build();
    }

    @Override
    @SneakyThrows
    public ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value) {

        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return itemStack;
        }

        final Object container = GET_PERSISTENT_DATA_CONTAINER_METHOD.invoke(itemMeta);
        final Object namespacedKey = NAMESPACED_KEY_CONSTRUCTOR.newInstance(plugin, key);
        CONTAINER_SET_METHOD.invoke(container, namespacedKey, STRING_DATA_TYPE, value);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
