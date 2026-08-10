# 🌌 Dream-Utilities

[![Build](https://github.com/DreamPoland/dream-utilities/actions/workflows/gradle.yml/badge.svg)](https://github.com/DreamPoland/dream-utilities/actions/workflows/gradle.yml)
[![License](https://img.shields.io/github/license/DreamPoland/dream-utilities?color=blue)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21%2B-orange.svg)](https://www.oracle.com/java/)
[![Kyori Adventure](https://img.shields.io/badge/Kyori%20Adventure-API-purple.svg)](https://docs.advntr.dev/)
[![PaperMC](https://img.shields.io/badge/PaperMC-1.20.6%2B%20%7C%201.21.4%2B-brightgreen.svg)](https://papermc.io/)

A high-performance, modular Java and Minecraft utility suite designed for rapid plugin development across **Bukkit**, **Paper**, **Spigot**, **Folia**, **BungeeCord**, and **standalone Java** applications.

> [!NOTE]
> `utilities-bukkit` and `utilities-bungee` both automatically embed and implement `utilities-adventure`. This guarantees that full Kyori Adventure, MiniMessage, BossBars, and localization functionality are compiled directly into your plugin — eliminating any dependency on server software shading or missing Adventure libraries!

---

## 🧩 Modules Overview

| Module | Description | Target Platform |
|---|---|---|
| 📦 **`utilities`** | Zero-dependency Java core utilities (`MathUtil`, `TimeUtil`, `Option`, `Countdown`, Tuples) | Any Java 21+ Project |
| ✨ **`utilities-adventure`** | Standalone Kyori Adventure & MiniMessage suite (`ComponentUtil`, `AudienceUtil`, `BossBarUtil`, `BookUtil`, `ResourcePackBuilder`, `TranslationUtil`) | Platform-Agnostic Adventure |
| 🟩 **`utilities-bukkit`** | Full Bukkit/Spigot/Paper/Folia utilities with native Adventure support (`ItemBuilder`, `InventoryUtil`, `VersionUtil`, `ItemNBT`, `QueuedTeleport`) | Bukkit / Spigot / Paper / Folia |
| 🟧 **`utilities-bungee`** | Full BungeeCord proxy utilities with embedded Adventure & color processing (`StringColorUtil`, `DefaultColorProcessor`) | BungeeCord / Waterdog |

---

## ⚡ Quick Examples

### 💬 MiniMessage & Audience Messaging
```java
// Send MiniMessage with automatic placeholder injection to any Audience / Player
AudienceUtil.sendMessage(player, "<green>Welcome <yellow>{player}</yellow>!</green>", Map.of("player", player.getName()));
```

### 🗡️ ItemBuilder with MiniMessage & Placeholders
```java
ItemStack sword = ItemBuilder.of(Material.DIAMOND_SWORD)
    .setName("<gradient:red:gold><bold>Excalibur</bold></gradient>")
    .setLore(List.of(
        "<gray>Damage: <red>+{damage}</red></gray>",
        "<yellow>Owner: {owner}</yellow>"
    ), Map.of("damage", 150, "owner", player.getName()))
    .addEnchant(Enchantment.SHARPNESS, 5)
    .toItemStack();
```

### 📊 BossBar Management
```java
BossBar bossBar = BossBarUtil.showBossBar(
    player, 
    "<red>EVENT: {time}s</red>", 
    1.0f, 
    BossBar.Color.RED, 
    BossBar.Overlay.PROGRESS, 
    Map.of("time", 60)
);
```

### 📦 Resource Pack Delivery
```java
ResourcePackBuilder.of("https://cdn.example.com/pack.zip", "sha1_hash_here")
    .prompt("<gold>Download server resource pack!</gold>")
    .required(true)
    .send(player);
```

### 🌐 Adventure Localization & TranslationStore
```java
TranslationStore store = TranslationUtil.createAndRegisterComponentStore("myplugin", "lang", Locale.ENGLISH);
TranslationUtil.registerMiniMessage(store, Locale.ENGLISH, "welcome.key", "<green>Welcome {player}!</green>");

TranslatableComponent translatable = TranslationUtil.translatable("welcome.key");
Component rendered = TranslationUtil.render(translatable, player.locale(), Map.of("player", player.getName()));
```

### ⚙️ Multi-Engine Version Checking (`VersionUtil`)
```java
if (VersionUtil.isVersionOrHigher(21, 4)) {
    // Works for 1.21.4+ SemVer and 26.x CalVer
}

if (VersionUtil.isFolia()) {
    // Multi-threaded regionized server support
}
```

---

## 📦 Installation

Simply choose the target module for your project platform (**`utilities-bukkit`** for Minecraft server plugins, or **`utilities-bungee`** for BungeeCord proxy plugins).

### Repositories

#### Gradle (`build.gradle.kts`)
```kotlin
repositories {
    maven("https://repo.dreamcode.cc/releases")
}
```

#### Maven (`pom.xml`)
```xml
<repositories>
  <repository>
    <id>dreamcode-repository-releases</id>
    <url>https://repo.dreamcode.cc/releases</url>
  </repository>
</repositories>
```

### Dependencies

#### Option A: Bukkit / Spigot / Paper / Folia Plugins
```kotlin
// Gradle
dependencies {
    implementation("cc.dreamcode:utilities-bukkit:2.0.0")
}
```
```xml
<!-- Maven -->
<dependency>
  <groupId>cc.dreamcode</groupId>
  <artifactId>utilities-bukkit</artifactId>
  <version>2.0.0</version>
</dependency>
```

#### Option B: BungeeCord / Proxy Plugins
```kotlin
// Gradle
dependencies {
    implementation("cc.dreamcode:utilities-bungee:2.0.0")
}
```
```xml
<!-- Maven -->
<dependency>
  <groupId>cc.dreamcode</groupId>
  <artifactId>utilities-bungee</artifactId>
  <version>2.0.0</version>
</dependency>
```

#### Option C: Standalone Kyori Adventure (Velocity, Sponge, Standalone)
```kotlin
// Gradle
dependencies {
    implementation("cc.dreamcode:utilities-adventure:2.0.0")
}
```

---

## 📖 Complete Wiki

For detailed documentation, sitemaps, and advanced API guides, visit the [Dream-Utilities Wiki](https://github.com/DreamPoland/dream-utilities/wiki).

---

## 📄 License

This project is licensed under the [MIT License](LICENSE). Copyright (c) 2026 DreamCode.
