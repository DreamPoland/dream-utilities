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
package cc.dreamcode.utilities.bukkit.builder;

import cc.dreamcode.utilities.adventure.AdventureUtil;
import cc.dreamcode.utilities.adventure.ComponentUtil;
import cc.dreamcode.utilities.builder.ListBuilder;
import cc.dreamcode.utilities.bukkit.StringColorUtil;
import cc.dreamcode.utilities.bukkit.nbt.ItemNbtUtil;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    public ItemBuilder(@NonNull ItemStack itemStack, int amount, boolean clone) {
        if (clone) {
            this.itemStack = new ItemStack(itemStack);
        }
        else {
            this.itemStack = itemStack;
        }

        this.itemStack.setAmount(amount);
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

    public static ItemBuilder of(@NonNull ItemStack itemStack, int amount) {
        return new ItemBuilder(itemStack, amount, true);
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
        if (itemMeta == null) {
            return this;
        }

        itemMeta.setDisplayName(name);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setName(@NonNull ComponentLike name) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }
        itemMeta.displayName(name.asComponent());
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setName(@NonNull String miniMessage, @NonNull Map<String, Object> placeholders) {
        return this.setName(AdventureUtil.component(miniMessage, placeholders));
    }

    public ItemBuilder startLoreWith(@NonNull List<String> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        if (itemMeta.getLore() != null && !itemMeta.getLore().isEmpty()) {
            itemMeta.setLore(new ListBuilder<String>()
                    .addAll(lore)
                    .addAll(itemMeta.getLore())
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

    public ItemBuilder startLoreWithComponents(@NonNull List<? extends ComponentLike> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }
        List<Component> newLoreComponents = lore.stream().map(ComponentLike::asComponent).collect(Collectors.toList());
        List<Component> existingLore = itemMeta.lore();
        if (existingLore != null && !existingLore.isEmpty()) {
            itemMeta.lore(new ListBuilder<Component>()
                    .addAll(newLoreComponents)
                    .addAll(existingLore)
                    .build());
        } else {
            itemMeta.lore(newLoreComponents);
        }
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder startLoreWithComponents(@NonNull ComponentLike... lore) {
        return this.startLoreWithComponents(Arrays.asList(lore));
    }

    public ItemBuilder appendLore(@NonNull List<String> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        if (itemMeta.getLore() != null && !itemMeta.getLore().isEmpty()) {
            itemMeta.setLore(new ListBuilder<String>()
                    .addAll(itemMeta.getLore())
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

    public ItemBuilder appendLoreComponents(@NonNull List<? extends ComponentLike> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }
        List<Component> newLoreComponents = lore.stream().map(ComponentLike::asComponent).collect(Collectors.toList());
        List<Component> existingLore = itemMeta.lore();
        if (existingLore != null && !existingLore.isEmpty()) {
            itemMeta.lore(new ListBuilder<Component>()
                    .addAll(existingLore)
                    .addAll(newLoreComponents)
                    .build());
        } else {
            itemMeta.lore(newLoreComponents);
        }
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder appendLoreComponents(@NonNull ComponentLike... lore) {
        return this.appendLoreComponents(Arrays.asList(lore));
    }

    public ItemBuilder setLore(@NonNull List<String> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setLore(@NonNull String... lore) {
        return this.setLore(Arrays.asList(lore));
    }

    public ItemBuilder setLoreComponents(@NonNull List<? extends ComponentLike> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }
        List<Component> components = lore.stream().map(ComponentLike::asComponent).collect(Collectors.toList());
        itemMeta.lore(components);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLoreComponents(@NonNull ComponentLike... lore) {
        return this.setLoreComponents(Arrays.asList(lore));
    }

    public ItemBuilder setLoreStrings(@NonNull List<String> miniMessages) {
        return this.setLoreComponents(AdventureUtil.component(miniMessages));
    }

    public ItemBuilder setLoreStrings(@NonNull List<String> miniMessages, @NonNull Map<String, Object> placeholders) {
        return this.setLoreComponents(AdventureUtil.component(miniMessages, placeholders));
    }

    public ItemBuilder replacePlaceholders(@NonNull Map<String, Object> placeholders) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }
        Component nameComponent = itemMeta.displayName();
        if (nameComponent != null) {
            itemMeta.displayName(ComponentUtil.replace(nameComponent, placeholders));
        }
        List<Component> lore = itemMeta.lore();
        if (lore != null && !lore.isEmpty()) {
            List<Component> updatedLore = lore.stream()
                    .map(comp -> ComponentUtil.replace(comp, placeholders))
                    .collect(Collectors.toList());
            itemMeta.lore(updatedLore);
        }
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addEnchant(@NonNull Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder addEnchant(@NonNull Enchantment enchantment, int level) {
        return this.addEnchant(enchantment, level, true);
    }

    public ItemBuilder addFlags(@NonNull ItemFlag... itemFlag) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        itemMeta.addItemFlags(itemFlag);
        this.itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder fixColors() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        if (itemMeta.getDisplayName() != null) {
            itemMeta.setDisplayName(StringColorUtil.fixColor(itemMeta.getDisplayName()));
        }

        if (itemMeta.getLore() != null && !itemMeta.getLore().isEmpty()) {
            itemMeta.setLore(itemMeta.getLore()
                    .stream()
                    .map(StringColorUtil::fixColor)
                    .collect(Collectors.toList()));
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder fixColors(@NonNull Map<String, Object> placeholders) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        if (itemMeta.getDisplayName() != null) {
            final String compiledMessage = StringColorUtil.fixColor(itemMeta.getDisplayName(), placeholders);

            itemMeta.setDisplayName(compiledMessage);
        }

        if (itemMeta.getLore() != null && !itemMeta.getLore().isEmpty()) {
            itemMeta.setLore(itemMeta.getLore()
                    .stream()
                    .map(text -> StringColorUtil.fixColor(text, placeholders))
                    .collect(Collectors.toList()));
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder fixColors(@NonNull Map<String, Object> placeholders, boolean colorizePlaceholders) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        if (itemMeta.getDisplayName() != null) {
            final String compiledMessage = StringColorUtil.fixColor(itemMeta.getDisplayName(), placeholders, colorizePlaceholders);

            itemMeta.setDisplayName(compiledMessage);
        }

        if (itemMeta.getLore() != null && !itemMeta.getLore().isEmpty()) {
            itemMeta.setLore(itemMeta.getLore()
                    .stream()
                    .map(text -> StringColorUtil.fixColor(text, placeholders, colorizePlaceholders))
                    .collect(Collectors.toList()));
        }

        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder breakColors() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta == null) {
            return this;
        }

        if (itemMeta.getDisplayName() != null) {
            itemMeta.setDisplayName(StringColorUtil.breakColor(itemMeta.getDisplayName()));
        }

        if (itemMeta.getLore() != null && !itemMeta.getLore().isEmpty()) {
            itemMeta.setLore(itemMeta.getLore()
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
