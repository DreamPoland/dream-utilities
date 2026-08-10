# 🟩 Module: `utilities-bukkit`

The `utilities-bukkit` module provides a comprehensive suite of utilities for Minecraft Bukkit, Spigot, Paper, and Folia server plugin developers, including item builders, inventory helpers, cross-version NBT handling, multi-engine version detection, and teleportation queuing.

---

## 📋 Class Index

1. [`ItemBuilder`](#1-itembuilder)
2. [`InventoryUtil`](#2-inventoryutil)
3. [`VersionUtil`](#3-versionutil)
4. [`StringColorUtil`](#4-stringcolorutil)
5. [`DefaultColorProcessor`](#5-defaultcolorprocessor)
6. [`ItemNbtUtil`, `ItemNbt`, `ItemNbtLegacy`, `ItemNbtNewer`](#6-itemnbtutil-itemnbt-itemnbtlegacy-itemnbtnewer)
7. [`QueuedTeleportService`, `QueuedTeleport`, `QueuedTeleportCache`, `QueuedTeleportController`, `QueuedTeleportScheduler`](#7-queuedteleportservice-queuedteleport-queuedteleportcache-queuedteleportcontroller-queuedteleportscheduler)

---

## 1. `ItemBuilder`

### Overview
`cc.dreamcode.utilities.bukkit.builder.ItemBuilder` is a fluent builder for Bukkit `ItemStack` objects. It supports both legacy string color codes (`&a`) and native Kyori Adventure `Component` / MiniMessage / placeholders.

### Key Methods
- `static ItemBuilder of(@NonNull Material material)` — Creates an ItemBuilder for a material.
- `static ItemBuilder of(@NonNull Material material, int amount)` — Creates an ItemBuilder with amount.
- `static ItemBuilder of(@NonNull ItemStack itemStack)` — Clones an existing `ItemStack`.
- `static ItemBuilder manipulate(@NonNull ItemStack itemStack)` — Wraps an existing `ItemStack` without cloning.
- `ItemBuilder setAmount(int amount)` — Sets item quantity.
- `ItemBuilder setType(@NonNull Material material)` — Sets item material.
- `ItemBuilder setName(@NonNull String name)` — Sets display name (legacy string).
- `ItemBuilder setName(@NonNull ComponentLike name)` — Sets display name using a Kyori `Component`.
- `ItemBuilder setName(@NonNull String miniMessage, @NonNull Map<String, Object> placeholders)` — Sets display name via MiniMessage and placeholders.
- `ItemBuilder setLore(@NonNull List<String> lore)` — Sets lore lines (legacy strings).
- `ItemBuilder setLoreComponents(@NonNull List<? extends ComponentLike> lore)` — Sets lore using Kyori `Component` list.
- `ItemBuilder setLoreStrings(@NonNull List<String> miniMessages)` — Sets lore lines using MiniMessage strings.
- `ItemBuilder setLoreStrings(@NonNull List<String> miniMessages, @NonNull Map<String, Object> placeholders)` — Sets MiniMessage lore with placeholders.
- `ItemBuilder startLoreWith(@NonNull List<String> lore)` — Prepends lore lines to existing lore.
- `ItemBuilder appendLore(@NonNull List<String> lore)` — Appends lore lines to existing lore.
- `ItemBuilder addEnchant(@NonNull Enchantment enchantment, int level, boolean ignoreLevelRestriction)` — Adds an enchantment.
- `ItemBuilder addFlags(@NonNull ItemFlag... itemFlag)` — Adds item flags (e.g. `HIDE_ENCHANTS`, `HIDE_ATTRIBUTES`).
- `ItemBuilder fixColors()` — Translates legacy `&` color codes in name and lore.
- `ItemBuilder fixColors(@NonNull Map<String, Object> placeholders)` — Translates color codes and resolves placeholders.
- `ItemBuilder replacePlaceholders(@NonNull Map<String, Object> placeholders)` — Replaces placeholders inside Component name and lore.
- `ItemBuilder withNbt(@NonNull String key, @NonNull String value)` — Adds custom NBT tag to the item.
- `ItemBuilder withDurability(int durability)` — Sets item durability/damage.
- `ItemStack toItemStack()` — Returns the compiled `ItemStack`.

### Usage Example
```java
ItemStack sword = ItemBuilder.of(Material.DIAMOND_SWORD)
    .setName("<gradient:red:gold><bold>Excalibur</bold></gradient>")
    .setLoreStrings(List.of(
        "<gray>Obrażenia: <red>+{damage}</red></gray>",
        "<yellow>Właściciel: {owner}</yellow>"
    ), Map.of("damage", 150, "owner", player.getName()))
    .addEnchant(Enchantment.SHARPNESS, 5)
    .withNbt("quest_item", "true")
    .toItemStack();
```

---

## 2. `InventoryUtil`

### Overview
`cc.dreamcode.utilities.bukkit.InventoryUtil` provides methods for creating inventories (with Component / MiniMessage titles), adding items safely, checking available space, and dropping items.

### Key Methods
- `static Inventory createInventory(InventoryHolder owner, int size, @NonNull ComponentLike title)` — Creates an inventory with a `Component` title.
- `static Inventory createInventory(InventoryHolder owner, int size, @NonNull String miniMessage)` — Creates an inventory with a MiniMessage title.
- `static Inventory createInventory(InventoryHolder owner, int size, @NonNull String miniMessage, @NonNull Map<String, Object> placeholders)` — Title with placeholders.
- `static void giveItem(@NonNull Player player, @NonNull ItemStack itemStack)` — Gives an item to player inventory or drops it at player location if inventory is full.
- `static void giveItems(@NonNull Player player, @NonNull List<ItemStack> itemStacks)` — Gives multiple items to player.
- `static boolean hasSpace(@NonNull Inventory inventory, @NonNull ItemStack itemStack)` — Checks if inventory has space for an item.
- `static boolean hasSpace(@NonNull Inventory inventory, @NonNull List<ItemStack> itemStacks)` — Checks space for multiple items.
- `static int countItems(@NonNull Inventory inventory, @NonNull ItemStack itemStack)` — Counts matching items in inventory.
- `static List<ItemStack> hasItem(@NonNull Inventory inventory, @NonNull ItemStack itemStack)` — Finds matching item stacks in inventory.

### Usage Example
```java
Inventory gui = InventoryUtil.createInventory(54, "<gradient:blue:aqua><bold>Shop Menu</bold></gradient>");
InventoryUtil.giveItem(player, sword);
```

---

## 3. `VersionUtil`

### Overview
`cc.dreamcode.utilities.bukkit.VersionUtil` provides server version and software platform detection supporting Paper 1.20.5+, Paper 1.21.x+, Folia, Spigot, SemVer (`1.26.2`), and CalVer (`26.2`).

### Key Methods
- `static boolean isPaper()` — Checks if server is running Paper (or Paper-based forks).
- `static boolean isSpigot()` — Checks if server is running Spigot.
- `static boolean isFolia()` — Checks if server is running multi-threaded Folia.
- `static String getMinecraftVersion()` — Returns the server Minecraft version string (e.g., `"1.21.4"`, `"26.2"`).
- `static Optional<Integer> getVersion()` — Returns the primary/minor version number (e.g., `21` for 1.21.4, `26` for 26.2).
- `static Optional<Integer> getMinorVersion()` — Returns secondary version number.
- `static Optional<Integer> getPatchVersion()` — Returns patch version number (e.g., `4` for 1.21.4).
- `static boolean isVersionOrHigher(int targetVersion)` — Checks if server version is `>= targetVersion`.
- `static boolean isVersionOrHigher(int targetVersion, int subVersion)` — Checks version and subversion.

### Usage Example
```java
if (VersionUtil.isVersionOrHigher(21, 4)) {
    // 1.21.4+ features
}
if (VersionUtil.isFolia()) {
    // Folia regionized scheduler logic
}
```

---

## 4. `StringColorUtil`

### Overview
`cc.dreamcode.utilities.bukkit.StringColorUtil` handles legacy ampersand (`&a`, `&l`, `#HEX`) color translation for legacy Bukkit strings.

### Key Methods
- `static String fixColor(@NonNull String text)` — Colorizes legacy ampersand color codes.
- `static String fixColor(@NonNull String text, @NonNull Map<String, Object> placeholders)` — Colorizes text and replaces placeholders.
- `static List<String> fixColor(@NonNull List<String> stringList)` — Colorizes a list of strings.
- `static String breakColor(@NonNull String text)` — Converts section symbol (`§`) colors back into ampersands (`&`).

### Usage Example
```java
String colored = StringColorUtil.fixColor("&aWitaj &e{player}!", Map.of("player", player.getName()));
String plain = StringColorUtil.breakColor(colored);
```

---

## 5. `DefaultColorProcessor`

### Overview
`cc.dreamcode.utilities.bukkit.color.DefaultColorProcessor` implements `ColorProcessor` from core `utilities` for Bukkit legacy string processing.

### Usage Example
```java
ColorProcessor colorProcessor = new DefaultColorProcessor();
String colored = colorProcessor.process("&cDamage: &f100");
```

---

## 6. `ItemNbtUtil`, `ItemNbt`, `ItemNbtLegacy`, `ItemNbtNewer`

### Overview
A cross-version NBT tag reader and writer for Bukkit `ItemStack` objects, automatically delegating between legacy NMS/reflection (`ItemNbtLegacy`) and modern `PersistentDataContainer` (`ItemNbtNewer`).

### Key Methods
- `static ItemStack setValue(@NonNull ItemStack itemStack, @NonNull String key, @NonNull String value)` — Sets custom NBT string value.
- `static ItemStack setValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key, @NonNull String value)` — Sets PersistentDataContainer tag with plugin namespace.
- `static Option<String> getValue(@NonNull ItemStack itemStack, @NonNull String key)` — Reads custom NBT string value.
- `static Option<String> getValue(@NonNull Plugin plugin, @NonNull ItemStack itemStack, @NonNull String key)` — Reads PDC value.
- `static boolean hasKey(@NonNull ItemStack itemStack, @NonNull String key)` — Checks if NBT key exists.

### Usage Example
```java
ItemStack tagged = ItemNbtUtil.setValue(item, "custom_id", "sword_123");
Option<String> id = ItemNbtUtil.getValue(tagged, "custom_id");
```

---

## 7. `QueuedTeleportService`, `QueuedTeleport`, `QueuedTeleportCache`, `QueuedTeleportController`, `QueuedTeleportScheduler`

### Overview
A complete queued teleportation system for Bukkit plugins with movement detection, countdown scheduling, and cancellation logic.

### Key Components
- **`QueuedTeleportService`**: Primary API service for creating and managing teleport requests.
- **`QueuedTeleport`**: Data model representing a pending teleportation request (player, destination location, duration countdown, task).
- **`QueuedTeleportCache`**: Thread-safe storage for pending teleports by player UUID.
- **`QueuedTeleportController`**: Bukkit Listener tracking player movement, damage, and quit events to auto-cancel teleports upon movement.
- **`QueuedTeleportScheduler`**: Scheduler running countdown timers.

### Usage Example
```java
QueuedTeleportService teleportService = new QueuedTeleportService(plugin);

// Request a 5-second delayed teleport
teleportService.applyTeleport(player, targetLocation, Duration.ofSeconds(5), queuedTeleport -> {
    player.sendMessage("Teleporting in 5 seconds... Do not move!");
});
```
