package cc.dreamcode.utilities.bukkit.builder;

import cc.dreamcode.utilities.bukkit.ChatUtil;
import eu.okaeri.placeholders.context.PlaceholderContext;
import eu.okaeri.placeholders.message.CompiledMessage;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
public class ItemBuilder {
    private ItemStack itemStack = new ItemStack(Material.AIR);

    public ItemBuilder(Material material) {
        if (material == null) {
            this.setItemStack(new ItemStack(Material.DIRT));
            return;
        }

        this.setItemStack(new ItemStack(material));
    }

    public ItemBuilder(Material material, int amount) {
        if (material == null) {
            this.setItemStack(new ItemStack(Material.DIRT, amount));
            return;
        }

        this.setItemStack(new ItemStack(material, amount));
    }

    public ItemBuilder(ItemStack itemStack) {
        if (itemStack == null) {
            this.setItemStack(new ItemStack(Material.DIRT));
            return;
        }

        this.setItemStack(new ItemStack(itemStack));
    }

    public static ItemBuilder of(Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder of(Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    public static ItemBuilder of(ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.setDisplayName(name);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return this.setLore(Arrays.asList(lore));
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level, boolean visible) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.addEnchant(enchantment, level, visible);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        return this.addEnchant(enchantment, level, true);
    }

    public ItemBuilder fixColors() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        if (itemMeta.hasDisplayName()) {
            itemMeta.setDisplayName(ChatUtil.fixColor(itemMeta.getDisplayName()));
        }

        if (itemMeta.hasLore()) {
            itemMeta.setLore(Objects.requireNonNull(itemMeta.getLore())
                    .stream()
                    .map(ChatUtil::fixColor)
                    .collect(Collectors.toList()));
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder fixColors(@NonNull Map<String, Object> replaceMap) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        if (itemMeta.hasDisplayName()) {
            final CompiledMessage compiledMessage = CompiledMessage.of(itemMeta.getDisplayName());
            final PlaceholderContext placeholderContext = PlaceholderContext.of(compiledMessage);

            itemMeta.setDisplayName(ChatUtil.fixColor(placeholderContext.with(replaceMap).apply()));
        }

        if (itemMeta.hasLore()) {
            itemMeta.setLore(Objects.requireNonNull(itemMeta.getLore())
                    .stream()
                    .map(text -> {
                        final CompiledMessage compiledMessage = CompiledMessage.of(text);
                        final PlaceholderContext placeholderContext = PlaceholderContext.of(compiledMessage);

                        return ChatUtil.fixColor(placeholderContext.with(replaceMap).apply());
                    })
                    .collect(Collectors.toList()));
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack toItemStack() {
        return this.itemStack;
    }
}
