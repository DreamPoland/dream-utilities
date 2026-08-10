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
