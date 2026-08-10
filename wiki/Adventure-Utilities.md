# ✨ Module: `utilities-adventure`

The `utilities-adventure` module is a platform-agnostic library containing utilities for **Kyori Adventure API** and **MiniMessage**. It can be used directly on Velocity, Sponge, Fabric, standalone Java, or transitively via `utilities-bukkit` and `utilities-bungee`.

---

## 📋 Class Index

1. [`AdventureProcessor`](#1-adventureprocessor)
2. [`AdventureUtil`](#2-adventureutil)
3. [`AudienceUtil`](#3-audienceutil)
4. [`BookUtil`](#4-bookutil)
5. [`BossBarUtil`](#5-bossbarutil)
6. [`ComponentUtil`](#6-componentutil)
7. [`PlayerListUtil`](#7-playerlistutil)
8. [`ResourcePackBuilder`](#8-resourcepackbuilder)
9. [`ResourcePackUtil`](#9-resourcepackutil)
10. [`TranslationUtil`](#10-translationutil)

---

## 1. `AdventureProcessor`

### Overview
`cc.dreamcode.utilities.adventure.AdventureProcessor` implements `ColorProcessor` from `utilities` core. It processes legacy ampersand color codes (`&a`, `&l`, `&#RRGGBB`), section symbols (`§a`), MiniMessage tags (`<green>`, `<gradient>`), placeholders (`{player}`), and URLs into compiled colorized text.

### Key Methods
- `String color(@NonNull String text)` — Converts legacy ampersand color codes (`&a`, `&#HEX`), section codes, and MiniMessage tags into colorized section string (`§a`).
- `String color(@NonNull String text, @NonNull Locale locale, @NonNull Map<String, Object> placeholders, boolean colorizePlaceholders)` — Processes text with locale, placeholders, and color coding.
- `String decolor(@NonNull String text)` — Strips color formatting from text.

### Usage Example
```java
ColorProcessor processor = new AdventureProcessor();
String result = processor.process("&aHello <bold>{user}</bold>!", Map.of("user", "Alice"));
```

---

## 2. `AdventureUtil`

### Overview
`cc.dreamcode.utilities.adventure.AdventureUtil` is the primary entry point for converting text to Kyori Adventure `Component` instances or legacy section strings (`process`). It seamlessly supports legacy ampersand color codes (`&a`, `&l`, `&#RRGGBB`), section symbols (`§a`), MiniMessage tags (`<green>`, `<gradient>`), placeholders (`{player}`), and clickable URLs.

### Key Methods
- `Component component(@NonNull String miniMessage)` — Parses a MiniMessage string into a `Component`.
- `Component component(@NonNull String miniMessage, @NonNull Map<String, Object> placeholders)` — Parses MiniMessage with placeholder substitution.
- `Component component(@NonNull String miniMessage, @NonNull Locale locale, @NonNull Map<String, Object> placeholders)` — Parses MiniMessage with locale-sensitive formatting.
- `List<Component> component(@NonNull List<String> miniMessages)` — Converts a list of strings into a list of `Component` objects.
- `List<Component> component(@NonNull List<String> miniMessages, @NonNull Map<String, Object> placeholders)` — List conversion with placeholders.
- `String toLegacySection(@NonNull Component component)` — Serializes a `Component` into a legacy section (`§a`) formatted string.
- `String toLegacyAmpersand(@NonNull Component component)` — Serializes a `Component` into a legacy ampersand (`&a`) formatted string.
- `Component fromLegacySection(@NonNull String legacy)` — Deserializes a section-formatted legacy string into a `Component`.
- `Component fromLegacyAmpersand(@NonNull String legacy)` — Deserializes an ampersand-formatted legacy string into a `Component`.
- `String toPlainText(@NonNull Component component)` — Strips all formatting and returns raw unstyled text.

### Usage Example
```java
// MiniMessage to Component
Component comp = AdventureUtil.component("<gradient:red:blue>Rainbow Title</gradient>");

// Legacy conversion
String legacy = AdventureUtil.toLegacyAmpersand(comp);
Component restored = AdventureUtil.fromLegacyAmpersand("&cRed text");
```

---

## 3. `AudienceUtil`

### Overview
`cc.dreamcode.utilities.adventure.AudienceUtil` provides static helper methods to perform action operations (messaging, titles, actionbars, sounds, bossbars, books, player lists, resource packs) on any Kyori `Audience`.

### Key Methods
- `Audience audience(@NonNull Audience audience)` — Wraps a single Audience.
- `Audience audience(@NonNull Iterable<? extends Audience> audiences)` — Combines multiple Audiences into a group Audience.
- `Audience audience(@NonNull Audience... audiences)` — Combines array of Audiences.
- `void sendMessage(@NonNull Audience audience, @NonNull String text)` — Sends a MiniMessage message.
- `void sendMessage(@NonNull Audience audience, @NonNull String text, @NonNull Map<String, Object> placeholders)` — Message with placeholders.
- `void sendActionBar(@NonNull Audience audience, @NonNull String text)` — Sends an Action Bar message.
- `void sendTitle(@NonNull Audience audience, @NonNull String titleText, @NonNull String subtitleText, long fadeInMs, long stayMs, long fadeOutMs)` — Shows a title with custom timing.
- `void playSound(@NonNull Audience audience, @NonNull String soundName, float volume, float pitch)` — Plays a sound effect by key.
- `void showBossBar(@NonNull Audience audience, @NonNull BossBar bossBar)` — Displays a BossBar.
- `void hideBossBar(@NonNull Audience audience, @NonNull BossBar bossBar)` — Hides a BossBar.
- `void openBook(@NonNull Audience audience, @NonNull Book book)` — Opens a book interface for the audience.
- `void sendPlayerListHeaderAndFooter(@NonNull Audience audience, @NonNull String headerText, @NonNull String footerText)` — Sets TabList header/footer.
- `void sendResourcePacks(@NonNull Audience audience, @NonNull ResourcePackRequest request)` — Sends resource pack requests.

### Usage Example
```java
AudienceUtil.sendMessage(audience, "<green>Task completed successfully!</green>");
AudienceUtil.sendTitle(audience, "<gold>VICTORY</gold>", "<gray>You won!</gray>", 500, 2000, 500);
```

---

## 4. `BookUtil`

### Overview
`cc.dreamcode.utilities.adventure.BookUtil` provides convenient static methods for building and opening interactive Kyori `Book` objects formatted with MiniMessage.

### Key Methods
- `Book createBook(@NonNull Component title, @NonNull Component author, @NonNull List<Component> pages)` — Builds a `Book` from Components.
- `Book createBook(@NonNull String titleMiniMessage, @NonNull String authorMiniMessage, @NonNull List<String> pagesMiniMessage)` — Builds a `Book` from MiniMessage strings.
- `Book createBook(@NonNull String titleMiniMessage, @NonNull String authorMiniMessage, @NonNull List<String> pagesMiniMessage, @NonNull Map<String, Object> placeholders)` — Builds a `Book` with placeholders.
- `void openBook(@NonNull Audience audience, @NonNull Book book)` — Opens the book for an audience.

### Usage Example
```java
Book book = BookUtil.createBook(
    "<gold>Rules</gold>",
    "Server Admin",
    List.of(
        "<green>Page 1:</green> Be polite and have fun!",
        "<red>Page 2:</red> No cheating allowed."
    )
);

BookUtil.openBook(playerAudience, book);
```

---

## 5. `BossBarUtil`

### Overview
`cc.dreamcode.utilities.adventure.BossBarUtil` provides methods for creating, displaying, hiding, and dynamically updating Kyori `BossBar` instances.

### Key Methods
- `BossBar createBossBar(@NonNull Component name, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay)` — Creates a BossBar.
- `BossBar createBossBar(@NonNull String miniMessage, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay)` — Creates a BossBar from MiniMessage.
- `BossBar createBossBar(@NonNull String miniMessage, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay, @NonNull Map<String, Object> placeholders)` — Creates a BossBar with placeholders.
- `BossBar showBossBar(@NonNull Audience audience, @NonNull String miniMessage, float progress, @NonNull BossBar.Color color, @NonNull BossBar.Overlay overlay)` — Creates and immediately displays a BossBar to an audience.
- `void hideBossBar(@NonNull Audience audience, @NonNull BossBar bossBar)` — Hides a BossBar from an audience.
- `void updateName(@NonNull BossBar bossBar, @NonNull Component name)` — Updates the title of an existing BossBar.
- `void updateName(@NonNull BossBar bossBar, @NonNull String miniMessage)` — Updates title via MiniMessage.
- `void updateProgress(@NonNull BossBar bossBar, float progress)` — Updates progress (0.0f to 1.0f).
- `void updateColor(@NonNull BossBar bossBar, @NonNull BossBar.Color color)` — Changes BossBar color.
- `void updateOverlay(@NonNull BossBar bossBar, @NonNull BossBar.Overlay overlay)` — Changes BossBar overlay style.

### Usage Example
```java
BossBar bar = BossBarUtil.showBossBar(audience, "<red>Boss Health: {hp}%</red>", 1.0f, BossBar.Color.RED, BossBar.Overlay.PROGRESS, Map.of("hp", 100));
BossBarUtil.updateProgress(bar, 0.75f);
```

---

## 6. `ComponentUtil`

### Overview
`cc.dreamcode.utilities.adventure.ComponentUtil` provides advanced Component tree manipulation, text replacement, joining, and plain text extraction.

### Key Methods
- `Component replace(@NonNull Component component, @NonNull String match, @NonNull Component replacement)` — Replaces string occurrences in a Component tree with another Component.
- `Component replace(@NonNull Component component, @NonNull Map<String, Object> placeholders)` — Replaces placeholder keys with their values inside a Component tree.
- `Component join(@NonNull List<Component> components, @NonNull Component separator)` — Joins a list of Components with a separator Component.
- `String toPlainText(@NonNull Component component)` — Extracts plain unstyled string content from a Component.

### Usage Example
```java
Component text = AdventureUtil.component("Welcome {player}!");
Component updated = ComponentUtil.replace(text, Map.of("player", Component.text("Bob", NamedTextColor.GOLD)));
```

---

## 7. `PlayerListUtil`

### Overview
`cc.dreamcode.utilities.adventure.PlayerListUtil` manages player list (TabList) headers and footers for any `Audience`.

### Key Methods
- `void sendHeaderAndFooter(@NonNull Audience audience, @NonNull Component header, @NonNull Component footer)` — Sets TabList header and footer.
- `void sendHeaderAndFooter(@NonNull Audience audience, @NonNull String headerMiniMessage, @NonNull String footerMiniMessage)` — Sets header and footer using MiniMessage.
- `void sendHeaderAndFooter(@NonNull Audience audience, @NonNull String headerText, @NonNull String footerText, @NonNull Map<String, Object> placeholders)` — Header and footer with placeholders.
- `void sendHeader(@NonNull Audience audience, @NonNull Component header)` — Sets TabList header only.
- `void sendFooter(@NonNull Audience audience, @NonNull Component footer)` — Sets TabList footer only.

### Usage Example
```java
PlayerListUtil.sendHeaderAndFooter(
    audience, 
    "<gradient:gold:yellow><bold>MY SERVER</bold></gradient>", 
    "<gray>Online: <green>{online}</green></gray>", 
    Map.of("online", 42)
);
```

---

## 8. `ResourcePackBuilder`

### Overview
`cc.dreamcode.utilities.adventure.ResourcePackBuilder` is a fluent builder for creating Kyori `ResourcePackRequest` and `ResourcePackInfo` instances and delivering them to audiences.

### Key Methods
- `static ResourcePackBuilder of(@NonNull String url, @NonNull String hash)` — Initiates a builder with pack URL and SHA-1 hash.
- `ResourcePackBuilder id(@NonNull UUID id)` — Sets a custom UUID for the pack.
- `ResourcePackBuilder prompt(@NonNull Component prompt)` — Sets the prompt Component displayed to the player.
- `ResourcePackBuilder prompt(@NonNull String miniMessagePrompt)` — Sets prompt via MiniMessage.
- `ResourcePackBuilder required(boolean required)` — Sets whether the pack is required to play.
- `ResourcePackInfo buildInfo()` — Builds `ResourcePackInfo`.
- `ResourcePackRequest buildRequest()` — Builds `ResourcePackRequest`.
- `void send(@NonNull Audience audience)` — Builds and sends the pack request to an audience.

### Usage Example
```java
ResourcePackBuilder.of("https://cdn.example.com/pack.zip", "4b2e8a...")
    .prompt("<gold>Please accept the server resource pack!</gold>")
    .required(true)
    .send(playerAudience);
```

---

## 9. `ResourcePackUtil`

### Overview
`cc.dreamcode.utilities.adventure.ResourcePackUtil` provides static methods for creating resource pack requests and removing or clearing packs from an audience.

### Key Methods
- `ResourcePackInfo packInfo(@NonNull UUID id, @NonNull URI uri, @NonNull String hash)` — Builds `ResourcePackInfo`.
- `ResourcePackRequest packRequest(@NonNull ResourcePackInfo packInfo, boolean required, Component prompt)` — Builds `ResourcePackRequest`.
- `void sendResourcePacks(@NonNull Audience audience, @NonNull ResourcePackRequest request)` — Sends a pack request.
- `void removeResourcePacks(@NonNull Audience audience, @NonNull UUID... packIds)` — Removes specific resource packs by ID.
- `void clearResourcePacks(@NonNull Audience audience)` — Clears all custom resource packs for the audience.

### Usage Example
```java
ResourcePackInfo info = ResourcePackUtil.packInfo(UUID.randomUUID(), URI.create("https://cdn.example.com/pack.zip"), "hash123");
ResourcePackRequest request = ResourcePackUtil.packRequest(info, true, AdventureUtil.component("<gold>Download pack!</gold>"));
ResourcePackUtil.sendResourcePacks(audience, request);
ResourcePackUtil.clearResourcePacks(audience);
```

---

## 10. `TranslationUtil`

### Overview
`cc.dreamcode.utilities.adventure.TranslationUtil` integrates Kyori Adventure's built-in localization framework (`TranslationStore`, `GlobalTranslator`, `TranslatableComponent`).

### Key Methods
- `TranslationStore.StringBased createAndRegisterMessageStore(@NonNull String namespace, @NonNull String name, @NonNull Locale defaultLocale)` — Creates and registers a MessageFormat-based translation store into `GlobalTranslator`.
- `TranslationStore.Component createAndRegisterComponentStore(@NonNull String namespace, @NonNull String name, @NonNull Locale defaultLocale)` — Creates and registers a Component-based translation store into `GlobalTranslator`.
- `void registerMessage(@NonNull TranslationStore.StringBased store, @NonNull Locale locale, @NonNull String key, @NonNull String formatPattern)` — Registers a MessageFormat string translation key.
- `void registerMiniMessage(@NonNull TranslationStore.Component store, @NonNull Locale locale, @NonNull String key, @NonNull String miniMessage)` — Registers a MiniMessage component translation key.
- `TranslatableComponent translatable(@NonNull String key, @NonNull ComponentLike... args)` — Creates a `TranslatableComponent`.
- `Component render(@NonNull Component component, @NonNull Locale locale)` — Renders a translatable component into a localized `Component` for a target locale.
- `Component render(@NonNull Component component, @NonNull Locale locale, @NonNull Map<String, Object> placeholders)` — Renders with additional placeholder substitution.

### Usage Example
```java
// Register store
TranslationStore.Component store = TranslationUtil.createAndRegisterComponentStore("myplugin", "lang", Locale.ENGLISH);
TranslationUtil.registerMiniMessage(store, Locale.ENGLISH, "welcome.message", "<green>Welcome <yellow>{player}</yellow>!</green>");
TranslationUtil.registerMiniMessage(store, Locale.forLanguageTag("pl"), "welcome.message", "<green>Witaj <yellow>{player}</yellow>!</green>");

// Render for player locale
TranslatableComponent translatable = TranslationUtil.translatable("welcome.message");
Component rendered = TranslationUtil.render(translatable, player.locale(), Map.of("player", player.getName()));
AudienceUtil.sendMessage(player, AdventureUtil.toLegacySection(rendered));
```
