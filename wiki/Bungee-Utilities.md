# 🟧 Module: `utilities-bungee`

The `utilities-bungee` module provides BungeeCord proxy color processing and utilities, while transitively embedding `utilities-adventure` for Kyori Adventure support on BungeeCord proxies.

---

## 📋 Class Index

1. [`StringColorUtil`](#1-stringcolorutil)
2. [`DefaultColorProcessor`](#2-defaultcolorprocessor)

---

## 1. `StringColorUtil`

### Overview
`cc.dreamcode.utilities.bungee.StringColorUtil` provides legacy ampersand (`&a`, `&l`, `#HEX`) color translation for BungeeCord `BaseComponent[]` and `ChatColor`.

### Key Methods
- `static String fixColor(@NonNull String text)` — Colorizes ampersand codes for Bungee.
- `static String fixColor(@NonNull String text, @NonNull Map<String, Object> placeholders)` — Colorizes text and substitutes placeholders.
- `static List<String> fixColor(@NonNull List<String> stringList)` — Colorizes list of strings.
- `static String breakColor(@NonNull String text)` — Reverts section symbol (`§`) formatting to ampersands (`&`).
- `static void setColorProcessor(@NonNull ColorProcessor colorProcessor)` — Registers custom ColorProcessor strategy.

### Usage Example
```java
String colored = StringColorUtil.fixColor("&aWelcome to BungeeCord &e{user}!", Map.of("user", player.getName()));
```

---

## 2. `DefaultColorProcessor`

### Overview
`cc.dreamcode.utilities.bungee.color.DefaultColorProcessor` implements `ColorProcessor` from core `utilities` tailored for BungeeCord proxy string formatting.
