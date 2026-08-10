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
