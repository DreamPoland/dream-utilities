package cc.dreamcode.utilities.bukkit.builder;

import cc.dreamcode.utilities.builder.ListBuilder;
import cc.dreamcode.utilities.bukkit.StringColorUtil;
import cc.dreamcode.utilities.bukkit.nbt.ItemNbtUtil;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemBuilder {

    private ItemStack itemStack;

    public ItemBuilder(@NonNull Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder(@NonNull Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    public ItemBuilder(@NonNull ItemStack itemStack, boolean clone) {
        if (clone) {
            this.itemStack = new ItemStack(itemStack);
        }
        else {
            this.itemStack = itemStack;
        }
    }

    public static ItemBuilder of(@NonNull Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder of(@NonNull Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    public static ItemBuilder of(@NonNull ItemStack itemStack) {
        return new ItemBuilder(itemStack, true);
    }

    public static ItemBuilder manipulate(@NonNull ItemStack itemStack) {
        return new ItemBuilder(itemStack, false);
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setType(@NonNull Material material) {
        this.itemStack.setType(material);
        return this;
    }

    public ItemBuilder setType(@NonNull ItemStack itemStack) {
        return this.setType(itemStack, true);
    }

    public ItemBuilder setType(@NonNull ItemStack itemStack, boolean clone) {

        final ItemStack copy;
        if (clone) {
            copy = new ItemStack(itemStack);
        }
        else {
            copy = itemStack;
        }

        copy.setAmount(this.itemStack.getAmount());

        if (this.itemStack.hasItemMeta()) {
            copy.setItemMeta(this.itemStack.getItemMeta());
        }

        this.itemStack = copy;
        return this;
    }

    public ItemBuilder withDurability(int durability) {
        this.itemStack.setDurability((short) durability);
        return this;
    }

    public ItemBuilder setName(@NonNull String name) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.setDisplayName(name);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder startLoreWith(@NonNull List<String> lore) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        if (itemMeta.hasLore()) {
            itemMeta.setLore(new ListBuilder<String>()
                    .addAll(lore)
                    .addAll(Objects.requireNonNull(itemMeta.getLore()))
                    .build());
        }
        else {
            itemMeta.setLore(lore);
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder startLoreWith(@NonNull String... lore) {
        return this.startLoreWith(Arrays.asList(lore));
    }

    public ItemBuilder appendLore(@NonNull List<String> lore) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        if (itemMeta.hasLore()) {
            itemMeta.setLore(new ListBuilder<String>()
                    .addAll(Objects.requireNonNull(itemMeta.getLore()))
                    .addAll(lore)
                    .build());
        }
        else {
            itemMeta.setLore(lore);
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder appendLore(@NonNull String... lore) {
        return this.appendLore(Arrays.asList(lore));
    }

    public ItemBuilder setLore(@NonNull List<String> lore) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setLore(@NonNull String... lore) {
        return this.setLore(Arrays.asList(lore));
    }

    public ItemBuilder addEnchant(@NonNull Enchantment enchantment, int level, boolean ignoreLevelRestriction) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder addEnchant(@NonNull Enchantment enchantment, int level) {
        return this.addEnchant(enchantment, level, true);
    }

    public ItemBuilder addFlags(@NonNull ItemFlag... itemFlag) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        itemMeta.addItemFlags(itemFlag);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder fixColors() {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        if (itemMeta.hasDisplayName()) {
            itemMeta.setDisplayName(StringColorUtil.fixColor(itemMeta.getDisplayName()));
        }

        if (itemMeta.hasLore()) {
            itemMeta.setLore(Objects.requireNonNull(itemMeta.getLore())
                    .stream()
                    .map(StringColorUtil::fixColor)
                    .collect(Collectors.toList()));
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder fixColors(@NonNull Map<String, Object> placeholders) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        if (itemMeta.hasDisplayName()) {
            final String compiledMessage = StringColorUtil.fixColor(itemMeta.getDisplayName(), placeholders);

            itemMeta.setDisplayName(compiledMessage);
        }

        if (itemMeta.hasLore()) {
            itemMeta.setLore(Objects.requireNonNull(itemMeta.getLore())
                    .stream()
                    .map(text -> StringColorUtil.fixColor(text, placeholders))
                    .collect(Collectors.toList()));
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder breakColors() {

        ItemMeta itemMeta = this.itemStack.getItemMeta();
        assert itemMeta != null;

        if (itemMeta.hasDisplayName()) {
            itemMeta.setDisplayName(StringColorUtil.breakColor(itemMeta.getDisplayName()));
        }

        if (itemMeta.hasLore()) {
            itemMeta.setLore(Objects.requireNonNull(itemMeta.getLore())
                    .stream()
                    .map(StringColorUtil::breakColor)
                    .collect(Collectors.toList()));
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder withCustomMeta(@NonNull Function<ItemMeta, ItemMeta> function) {

        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        this.itemStack.setItemMeta(function.apply(itemMeta));

        return this;
    }

    public ItemBuilder withNbt(@NonNull String key, @NonNull String value) {
        this.itemStack = ItemNbtUtil.setValue(this.itemStack, key, value);
        return this;
    }

    public ItemBuilder withNbt(@NonNull Plugin plugin, @NonNull String key, @NonNull String value) {
        this.itemStack = ItemNbtUtil.setValue(plugin, this.itemStack, key, value);
        return this;
    }

    public ItemStack toItemStack() {
        return this.itemStack;
    }
}
