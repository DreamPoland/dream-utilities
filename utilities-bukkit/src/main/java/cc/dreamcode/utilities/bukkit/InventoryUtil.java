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
package cc.dreamcode.utilities.bukkit;

import cc.dreamcode.utilities.Validation;
import cc.dreamcode.utilities.adventure.AdventureUtil;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class InventoryUtil {

    public static Inventory createInventory(InventoryHolder owner, int size, @NonNull ComponentLike title) {
        return Bukkit.createInventory(owner, size, title.asComponent());
    }

    public static Inventory createInventory(InventoryHolder owner, @NonNull InventoryType type, @NonNull ComponentLike title) {
        return Bukkit.createInventory(owner, type, title.asComponent());
    }

    public static Inventory createInventory(int size, @NonNull ComponentLike title) {
        return createInventory(null, size, title);
    }

    public static Inventory createInventory(@NonNull InventoryType type, @NonNull ComponentLike title) {
        return createInventory(null, type, title);
    }

    public static Inventory createInventory(InventoryHolder owner, int size, @NonNull String miniMessage) {
        return createInventory(owner, size, AdventureUtil.component(miniMessage));
    }

    public static Inventory createInventory(InventoryHolder owner, int size, @NonNull String miniMessage, @NonNull Map<String, Object> placeholders) {
        return createInventory(owner, size, AdventureUtil.component(miniMessage, placeholders));
    }

    public static Inventory createInventory(int size, @NonNull String miniMessage) {
        return createInventory(null, size, miniMessage);
    }

    public static Inventory createInventory(int size, @NonNull String miniMessage, @NonNull Map<String, Object> placeholders) {
        return createInventory(null, size, miniMessage, placeholders);
    }

    public static void giveItem(@NonNull Player player, @NonNull ItemStack itemStack) {
        giveItems(player.getInventory(), player.getLocation(), Collections.singletonList(itemStack));
    }

    public static void giveItems(@NonNull Player player, @NonNull List<ItemStack> itemStacks) {
        giveItems(player.getInventory(), player.getLocation(), itemStacks);
    }

    public static void giveItem(@NonNull Player player, @NonNull Location location, @NonNull ItemStack itemStack) {
        giveItems(player.getInventory(), location, Collections.singletonList(itemStack));
    }

    public static void giveItem(@NonNull Inventory inventory, @NonNull Location location, @NonNull ItemStack itemStack) {
        giveItems(inventory, location, Collections.singletonList(itemStack));
    }

    public static void giveItems(@NonNull Inventory inventory, @NonNull Location location, @NonNull List<ItemStack> itemStacks) {
        itemStacks.forEach(itemStack ->
                inventory.addItem(itemStack).values().forEach(noAdded ->
                        Validation.nonNull(location.getWorld(), world -> world.dropItem(location, noAdded))));
    }

    public static void dropItem(@NonNull ItemStack itemStack, @NonNull Location location) {
        dropItems(Collections.singletonList(itemStack), location);
    }

    public static void dropItems(@NonNull List<ItemStack> itemStacks, @NonNull Location location) {
        itemStacks.forEach(itemStack -> Objects.requireNonNull(location.getWorld()).dropItem(location, itemStack));
    }

    public static void addItem(@NonNull ItemStack itemStack, @NonNull Inventory inventory) {
        addItems(Collections.singletonList(itemStack), inventory);
    }

    public static void addItems(@NonNull List<ItemStack> itemStacks, @NonNull Inventory inventory) {
        itemStacks.forEach(inventory::addItem);
    }

    public static boolean hasSpace(@NonNull Inventory inventory, @NonNull ItemStack itemStack) {
        Inventory newInv = Bukkit.createInventory(null, inventory.getSize());
        newInv.setContents(inventory.getContents());

        return newInv.addItem(itemStack.clone()).isEmpty();
    }

    public static boolean hasSpace(@NonNull Inventory inventory, @NonNull List<ItemStack> itemStacks) {
        Inventory newInv = Bukkit.createInventory(null, inventory.getSize());
        newInv.setContents(inventory.getContents());

        return itemStacks.stream()
                .map(itemStack -> newInv.addItem(itemStack).values())
                .allMatch(Collection::isEmpty);
    }

    /**
     * @return If returned list is empty - player has no items with this item param.
     */
    public static List<ItemStack> hasItem(@NonNull Inventory inventory, @NonNull ItemStack itemStack) {
        return Arrays.stream(inventory.getContents())
                .filter(Objects::nonNull)
                .filter(scan -> scan.isSimilar(itemStack))
                .collect(Collectors.toList());
    }

    public static int countItems(@NonNull Inventory inventory, @NonNull ItemStack itemStack) {
        return Arrays.stream(inventory.getContents())
                .filter(Objects::nonNull)
                .filter(scan -> scan.isSimilar(itemStack))
                .mapToInt(ItemStack::getAmount)
                .sum();
    }

    public static int countColorizedItems(@NonNull Inventory inventory, @NonNull ItemStack itemStack) {
        return InventoryUtil.countItems(inventory, ItemBuilder.of(itemStack).fixColors().toItemStack());
    }
}
