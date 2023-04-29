package cc.dreamcode.utilities.bukkit;

import com.google.common.collect.Lists;
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

    public static void give(Player player, ItemStack itemStack, Location location) {
        give(player, Lists.newArrayList(itemStack), location);
    }

    public static void give(Player player, List<ItemStack> itemStacks, Location location) {
        if (player == null) {
            drop(itemStacks, location);
            return;
        }
        itemStacks.forEach(itemStack -> player.getInventory().addItem(itemStack).values().forEach(noAdded -> {
            if (location != null && location.getWorld() != null) {
                location.getWorld().dropItem(location, noAdded);
            }
        }));
    }

    public static void give(Player player, ItemStack itemStack, Inventory inventory) {
        give(player, Lists.newArrayList(itemStack), inventory);
    }

    public static void give(Player player, List<ItemStack> itemStacks, Inventory inventory) {
        if (player == null) {
            addItems(itemStacks, inventory);
            return;
        }
        itemStacks.forEach(itemStack -> player.getInventory().addItem(itemStack).values().forEach(noAdded -> {
            if (inventory != null) {
                addItem(noAdded, inventory);
            }
        }));
    }

    public static void drop(ItemStack itemStack, Location location) {
        if (itemStack == null || location == null) {
            return;
        }
        drop(Lists.newArrayList(itemStack), location);
    }

    public static void drop(List<ItemStack> itemStacks, Location location) {
        if (itemStacks != null && location != null && location.getWorld() != null) {
            itemStacks.forEach(itemStack -> location.getWorld().dropItem(location, itemStack));
        }
    }

    public static void addItem(ItemStack itemStack, Inventory inventory) {
        if (itemStack == null || inventory == null) {
            return;
        }
        addItems(Lists.newArrayList(itemStack), inventory);
    }

    public static void addItems(List<ItemStack> itemStacks, Inventory inventory) {
        if (itemStacks == null || itemStacks.size() == 0 || inventory == null) {
            return;
        }
        itemStacks.forEach(inventory::addItem);
    }

    public boolean hasSpace(Inventory inventory, ItemStack itemStack) {
        Inventory newInv = Bukkit.createInventory(null, inventory.getSize());
        newInv.setContents(inventory.getContents());

        return newInv.addItem(itemStack.clone()).values().size() == 0;
    }

    public boolean hasSpace(Inventory inventory, List<ItemStack> itemStacks) {
        Inventory newInv = Bukkit.createInventory(null, inventory.getSize());
        newInv.setContents(inventory.getContents());

        return itemStacks.stream()
                .map(itemStack -> newInv.addItem(itemStack).values())
                .allMatch(Collection::isEmpty);
    }
}
