package cc.dreamcode.utilities.bukkit.nbt;

import cc.dreamcode.utilities.bukkit.VersionUtil;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.Optional;

@UtilityClass
public class ItemNbtUtil {

    private static final ItemNbt ITEM_NBT;
    private static Plugin plugin;

    static {
        ITEM_NBT = VersionUtil.isSupported(14)
                ? new ItemNbtNewer()
                : new ItemNbtLegacy();
    }

    public static Map<String, String> getValuesByPlugin(@NonNull Plugin plugin, @NonNull ItemStack itemStack) {
        return ITEM_NBT.getValues(plugin, itemStack);
    }

    public static Map<String, String> getValuesByPlugin(@NonNull ItemStack itemStack) {
        return ITEM_NBT.getValues(ItemNbtUtil.plugin, itemStack);
    }

    public static Map<String, String> getValues(@NonNull ItemStack itemStack) {
        return ITEM_NBT.getValues(itemStack);
    }

    public static Optional<String> getValueByPlugin(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key) {
        return ITEM_NBT.getValue(plugin, itemStack, key);
    }

    public static Optional<String> getValueByPlugin(@NonNull ItemStack itemStack, @NonNull String key) {
        return ITEM_NBT.getValue(ItemNbtUtil.plugin, itemStack, key);
    }

    public static Optional<String> getValue(@NonNull ItemStack itemStack, @NonNull String key) {
        return ITEM_NBT.getValue(itemStack, key);
    }

    public static ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value) {
        return ITEM_NBT.setValue(plugin, itemStack, key, value);
    }

    public static ItemStack setValue(@NonNull ItemStack itemStack, @NonNull String key, @NonNull String value) {
        return ITEM_NBT.setValue(ItemNbtUtil.plugin, itemStack, key, value);
    }

    public static void setPlugin(Plugin plugin) {
        ItemNbtUtil.plugin = plugin;
    }
}
