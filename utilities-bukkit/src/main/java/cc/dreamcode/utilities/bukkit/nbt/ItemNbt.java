package cc.dreamcode.utilities.bukkit.nbt;

import lombok.NonNull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public interface ItemNbt {

    Optional<String> getValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key);

    ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value);
}
