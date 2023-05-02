package cc.dreamcode.utilities.bukkit;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class ItemUtil {

    public static void give(@NonNull Player player, @NonNull ItemStack itemStack, @NonNull Location location) {
        give(player, Lists.newArrayList(itemStack), location);
    }

    public static void give(@NonNull Player player, @NonNull List<ItemStack> itemStacks, @NonNull Location location) {
        itemStacks.forEach(itemStack -> player.getInventory().addItem(itemStack).values().forEach(noAdded -> {
            location.getWorld().dropItem(location, noAdded);
        }));
    }

    public static void give(@NonNull Player player, @NonNull ItemStack itemStack, @NonNull Inventory inventory) {
        give(player, Lists.newArrayList(itemStack), inventory);
    }

    public static void give(@NonNull Player player, @NonNull List<ItemStack> itemStacks, @NonNull Inventory inventory) {
        itemStacks.forEach(itemStack -> player.getInventory().addItem(itemStack).values().forEach(noAdded -> {
            addItem(noAdded, inventory);
        }));
    }

    public static void drop(@NonNull ItemStack itemStack, @NonNull Location location) {
        drop(Lists.newArrayList(itemStack), location);
    }

    public static void drop(@NonNull List<ItemStack> itemStacks, @NonNull Location location) {
        itemStacks.forEach(itemStack -> location.getWorld().dropItem(location, itemStack));
    }

    public static void addItem(@NonNull ItemStack itemStack, @NonNull Inventory inventory) {
        addItems(Lists.newArrayList(itemStack), inventory);
    }

    public static void addItems(@NonNull List<ItemStack> itemStacks, @NonNull Inventory inventory) {
        itemStacks.forEach(inventory::addItem);
    }

    public static boolean hasSpace(@NonNull Inventory inventory, @NonNull ItemStack itemStack) {
        Inventory newInv = Bukkit.createInventory(null, inventory.getSize());
        newInv.setContents(inventory.getContents());

        return newInv.addItem(itemStack.clone()).values().size() == 0;
    }

    public static boolean hasSpace(@NonNull Inventory inventory, @NonNull List<ItemStack> itemStacks) {
        Inventory newInv = Bukkit.createInventory(null, inventory.getSize());
        newInv.setContents(inventory.getContents());

        return itemStacks.stream()
                .map(itemStack -> newInv.addItem(itemStack).values())
                .allMatch(Collection::isEmpty);
    }
}
