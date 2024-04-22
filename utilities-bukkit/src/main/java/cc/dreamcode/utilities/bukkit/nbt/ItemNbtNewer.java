package cc.dreamcode.utilities.bukkit.nbt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

@RequiredArgsConstructor
public class ItemNbtNewer implements ItemNbt {

    @Override
    public Optional<String> getValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key) {
        if (itemStack.hasItemMeta()) {
            return Optional.empty();
        }

        final ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        return Optional.ofNullable(itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.STRING));
    }

    @Override
    public ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value) {
        if (itemStack.hasItemMeta()) {
            return itemStack;
        }

        final ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
