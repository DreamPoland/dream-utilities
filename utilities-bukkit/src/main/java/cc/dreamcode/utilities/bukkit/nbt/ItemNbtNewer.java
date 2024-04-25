package cc.dreamcode.utilities.bukkit.nbt;

import cc.dreamcode.utilities.builder.MapBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
public class ItemNbtNewer implements ItemNbt {

    @Override
    public Map<String, String> getValues(@NonNull Plugin plugin, @NonNull ItemStack itemStack) {

        final MapBuilder<String, String> mapBuilder = new MapBuilder<>();

        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return mapBuilder.build();
        }

        itemMeta.getPersistentDataContainer().getKeys()
                .stream()
                .filter(namespacedKey -> namespacedKey.getNamespace().equals(plugin.getName().toLowerCase(Locale.ROOT)))
                .forEach(namespacedKey -> mapBuilder.put(
                        namespacedKey.getKey(),
                        itemMeta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)
                ));

        return mapBuilder.build();
    }

    @Override
    public Map<String, String> getValues(@NonNull ItemStack itemStack) {

        final MapBuilder<String, String> mapBuilder = new MapBuilder<>();

        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return mapBuilder.build();
        }

        itemMeta.getPersistentDataContainer()
                .getKeys()
                .forEach(namespacedKey -> mapBuilder.put(
                        namespacedKey.getKey(),
                        itemMeta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)
                ));

        return mapBuilder.build();
    }

    @Override
    public ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value) {

        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return itemStack;
        }

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
