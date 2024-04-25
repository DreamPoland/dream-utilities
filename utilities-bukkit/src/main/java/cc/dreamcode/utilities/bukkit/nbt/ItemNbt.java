package cc.dreamcode.utilities.bukkit.nbt;

import lombok.NonNull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.Optional;

public interface ItemNbt {

    Map<String, String> getValues(@NonNull Plugin plugin, @NonNull ItemStack itemStack);

    Map<String, String> getValues(@NonNull ItemStack itemStack);

    default Optional<String> getValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key) {
        return this.getValues(plugin, itemStack)
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(key) && !entry.getValue().isEmpty())
                .map(Map.Entry::getValue)
                .findAny();
    }

    default Optional<String> getValue(@NonNull ItemStack itemStack, @NonNull String key) {
        return this.getValues(itemStack)
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(key) && !entry.getValue().isEmpty())
                .map(Map.Entry::getValue)
                .findAny();
    }

    ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value);
}
