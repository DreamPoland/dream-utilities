package cc.dreamcode.utilities.bukkit;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class ItemUtil {

    public static void giveItem(@NonNull Player player, @NonNull ItemStack itemStack) {
        giveItems(player, player.getLocation(), Collections.singletonList(itemStack));
    }

    public static void giveItems(@NonNull Player player, @NonNull List<ItemStack> itemStacks) {
        giveItems(player, player.getLocation(), itemStacks);
    }

    public static void giveItem(@NonNull Player player, @NonNull Location location, @NonNull ItemStack itemStack) {
        giveItems(player, location, Collections.singletonList(itemStack));
    }

    public static void giveItems(@NonNull Player player, @NonNull Location location, @NonNull List<ItemStack> itemStacks) {
        itemStacks.forEach(itemStack ->
                player.getInventory().addItem(itemStack).values().forEach(noAdded ->
                        location.getWorld().dropItem(location, noAdded)));
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
}
