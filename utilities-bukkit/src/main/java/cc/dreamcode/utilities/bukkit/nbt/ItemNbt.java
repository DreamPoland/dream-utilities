package cc.dreamcode.utilities.bukkit.nbt;

import lombok.NonNull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.Optional;

public interface ItemNbt {

    Map<String, String> getValues(@NonNull Plugin plugin, @NonNull ItemStack itemStack);

    Optional<String> getValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key);

    ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value);
}
