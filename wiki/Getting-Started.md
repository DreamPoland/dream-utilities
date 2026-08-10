# 🚀 Getting Started

This guide explains how to install and add **Dream-Utilities** to your Gradle or Maven projects.

> [!NOTE]
> **Java Requirement:** Java 21 or higher is required for all modules.
> `utilities-bukkit` and `utilities-bungee` both automatically embed and implement `utilities-adventure`. This guarantees that full Kyori Adventure, MiniMessage, BossBars, and localization functionality are compiled directly into your plugin — eliminating any dependency on server software shading or missing Adventure libraries!

---

## 🏬 Repositories

### Gradle (`build.gradle.kts`)
```kotlin
repositories {
    maven("https://repo.dreamcode.cc/releases")
}
```

### Maven (`pom.xml`)
```xml
<repositories>
  <repository>
    <id>dreamcode-repository-releases</id>
    <url>https://repo.dreamcode.cc/releases</url>
  </repository>
</repositories>
```

---

## 📦 Dependency Declaration

Replace `VERSION` with the target release version (e.g., `2.0.0`).

### Option A: Bukkit / Spigot / Paper / Folia Plugins
```kotlin
// Gradle
dependencies {
    implementation("cc.dreamcode.utilities:utilities-bukkit:VERSION")
}
```

```xml
<!-- Maven -->
<dependency>
  <groupId>cc.dreamcode.utilities</groupId>
  <artifactId>utilities-bukkit</artifactId>
  <version>VERSION</version>
</dependency>
```

### Option B: BungeeCord Proxy Plugins
```kotlin
// Gradle
dependencies {
    implementation("cc.dreamcode.utilities:utilities-bungee:VERSION")
}
```

```xml
<!-- Maven -->
<dependency>
  <groupId>cc.dreamcode.utilities</groupId>
  <artifactId>utilities-bungee</artifactId>
  <version>VERSION</version>
</dependency>
```

### Option C: Standalone Adventure (Velocity, Sponge, Fabric, Custom)
```kotlin
// Gradle
dependencies {
    implementation("cc.dreamcode.utilities:utilities-adventure:VERSION")
}
```

### Option D: Pure Java Base Utilities (No Minecraft/Adventure dependencies)
```kotlin
// Gradle
dependencies {
    implementation("cc.dreamcode.utilities:utilities:VERSION")
}
```
