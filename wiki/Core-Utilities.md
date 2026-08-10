# 📦 Module: `utilities` (Core Java)

The core `utilities` module provides zero-dependency, high-performance Java utilities for general programming, math, time formatting, option monads, collection builders, countdown timers, functional interfaces, and tuple structures.

---

## 📋 Class Index

### Base Utilities
1. [`ClassUtil`](#1-classutil)
2. [`DateUtil`](#2-dateutil)
3. [`MathUtil`](#3-mathutil)
4. [`ParseUtil`](#4-parseutil)
5. [`RandomUtil`](#5-randomutil)
6. [`StringUtil`](#6-stringutil)
7. [`TicksUtil`](#7-ticksutil)
8. [`TimeUtil`](#8-timeutil)
9. [`Validation`](#9-validation)

### Monad & Functional
10. [`Option<T>`](#10-optiont)
11. [`ColorProcessor`](#11-colorprocessor)

### Collection Builders
12. [`ListBuilder<T>`](#12-listbuildert)
13. [`MapBuilder<K, V>`](#13-mapbuilderk-v)
14. [`SetBuilder<T>`](#14-setbuildert)

### Countdowns & Timers
15. [`Countdown`](#15-countdown)
16. [`CountdownCache`](#16-countdowncache)

### Tuples (Immutable & Mutable)
17. [`Duo<K, V>`, `MutableDuo<K, V>`](#17-duok-v-mutableduok-v)
18. [`Triple<A, B, C>`, `MutableTriple<A, B, C>`](#18-triplea-b-c-mutabletriplea-b-c)
19. [`Quad<A, B, C, D>`, `MutableQuad<A, B, C, D>`](#19-quada-b-c-d-mutablequada-b-c-d)
20. [`Entry<K, V>`](#20-entryk-v)

### Functional Interfaces
21. [`TripleConsumer`, `TripleFunction`, `TriplePredicate`](#21-tripleconsumer-triplefunction-triplepredicate)
22. [`QuadConsumer`, `QuadFunction`, `QuadPredicate`](#22-quadconsumer-quadfunction-quadpredicate)
23. [`ToBooleanFunction`, `ToStringFunction`](#23-tobooleanfunction-tostringfunction)

---

## 1. `ClassUtil`
- `static boolean hasClass(@NonNull String className)` — Safe reflection check determining if a Java class exists on classpath without throwing `ClassNotFoundException`.

### Usage Example
```java
if (ClassUtil.hasClass("org.spigotmc.SpigotConfig")) {
    System.out.println("Running on Spigot!");
}
```

---

## 2. `DateUtil`
- Formatting `Date` and `Instant` instances into standard date strings.

### Usage Example
```java
String formattedDate = DateUtil.format(new Date()); // e.g. "2026-08-10 19:45:00"
```

---

## 3. `MathUtil`
- `static double round(double value, int places)` — Rounds double to specified decimal places.
- `static float round(float value, int places)` — Rounds float value.
- `static int clamp(int val, int min, int max)` — Restricts integer within min/max bounds.
- `static float clamp(float val, float min, float max)` — Restricts float within bounds.
- `static double clamp(double val, double min, double max)` — Restricts double within bounds.

### Usage Example
```java
double rounded = MathUtil.round(12.34567, 2); // 12.35
int health = MathUtil.clamp(120, 0, 100); // 100
```

---

## 4. `ParseUtil`
- Safe string parsing methods returning `Optional`:
- `static Optional<Integer> parseInteger(String str)`
- `static Optional<Double> parseDouble(String str)`
- `static Optional<Float> parseFloat(String str)`
- `static Optional<Long> parseLong(String str)`
- `static Optional<Boolean> parseBoolean(String str)`

### Usage Example
```java
Optional<Integer> amount = ParseUtil.parseInteger("150"); // Optional[150]
Optional<Integer> invalid = ParseUtil.parseInteger("abc"); // Optional.empty()
```

---

## 5. `RandomUtil`
- `static int getRandomInt(int min, int max)` — Random integer in inclusive range.
- `static double getRandomDouble(double min, double max)` — Random double.
- `static <T> T getRandom(@NonNull List<T> list)` — Random element selection from list.

### Usage Example
```java
int rolled = RandomUtil.getRandomInt(1, 6);
String reward = RandomUtil.getRandom(List.of("Sword", "Shield", "Potion"));
```

---

## 6. `StringUtil`
- `static String join(@NonNull List<String> list, @NonNull String separator)` — Joins strings.
- `static String replace(@NonNull String text, @NonNull Map<String, Object> placeholders)` — String placeholder replacement.

### Usage Example
```java
String text = StringUtil.replace("Hello {user}, balance: {money}$", Map.of("user", "Jan", "money", 500));
```

---

## 7. `TicksUtil`
- Conversions between Minecraft ticks (20 ticks = 1 second) and Java `Duration` / milliseconds.

### Usage Example
```java
long ticks = TicksUtil.toTicks(Duration.ofSeconds(5)); // 100 ticks
Duration duration = TicksUtil.toDuration(200); // Duration of 10 seconds
```

---

## 8. `TimeUtil`
- `static String convertDurationMills(long duration)` — Formats milliseconds into human-readable duration strings (e.g. `1h 30m 15s`).

### Usage Example
```java
String formatted = TimeUtil.convertDurationMills(5415000L); // "1h 30m 15s"
```

---

## 9. `Validation`
- Non-null assertion utilities and consumer execution guards (`Validation.nonNull(...)`).

### Usage Example
```java
Validation.nonNull(player.getLocation().getWorld(), world -> {
    world.createExplosion(location, 0.0f);
});
```

---

## 10. `Option<T>`

### Overview
A custom, lightweight functional Option monad wrapping nullable values with functional mapping, filtering, and default supplier capabilities.

### Key Methods
- `static <T> Option<T> of(T value)` — Option from non-null value.
- `static <T> Option<T> ofNullable(T value)` — Option from nullable value.
- `static <T> Option<T> empty()` — Empty Option.
- `boolean isPresent()` / `boolean isEmpty()` — Presence check.
- `T get()` — Retrieves value or throws NPE.
- `T getOrElse(T fallback)` — Returns value or fallback value.
- `T getOrElseSupply(Supplier<T> supplier)` — Returns value or evaluated fallback.
- `<R> Option<R> map(Function<T, R> mapper)` — Functional transformation.
- `<R> Option<R> flatMap(Function<T, Option<R>> mapper)` — Monadic flatMap.
- `Option<T> filter(Predicate<T> predicate)` — Filters value.
- `void ifPresent(Consumer<T> consumer)` — Executes consumer if value exists.
- `Optional<T> toOptional()` — Converts Option to `java.util.Optional<T>`.

### Usage Example
```java
Option<String> name = Option.ofNullable(getName());
String result = name.map(String::toUpperCase).getOrElse("UNKNOWN");
```

---

## 11. `ColorProcessor`
- Strategy interface for text color processing implementations across platforms.

---

## 12. `ListBuilder<T>`
- Fluent builder for `java.util.List<T>`.

### Usage Example
```java
List<String> items = new ListBuilder<String>()
    .add("Item 1")
    .addAll(List.of("Item 2", "Item 3"))
    .build();
```

---

## 13. `MapBuilder<K, V>`
- Fluent builder for `java.util.Map<K, V>`.

### Usage Example
```java
Map<String, Object> map = new MapBuilder<String, Object>()
    .put("user", "Alice")
    .put("coins", 500)
    .build();
```

---

## 14. `SetBuilder<T>`
- Fluent builder for `java.util.Set<T>`.

### Usage Example
```java
Set<UUID> set = new SetBuilder<UUID>()
    .add(player.getUniqueId())
    .build();
```

---

## 15. `Countdown` & 16. `CountdownCache`
- Countdown timer tracking duration remaining with task callbacks.

### Usage Example
```java
Countdown countdown = Countdown.of(Duration.ofSeconds(30));
long remainingSeconds = countdown.getRemainingSeconds();
```

---

## 17. `Duo<K, V>`, `MutableDuo<K, V>`
- 2-element tuple container (Immutable `Duo`, Mutable `MutableDuo`).

### Usage Example
```java
Duo<String, Integer> duo = Duo.of("Score", 100);
String key = duo.getFirst();
Integer val = duo.getSecond();
```

---

## 18. `Triple<A, B, C>`, `MutableTriple<A, B, C>`
- 3-element tuple container (Immutable `Triple`, Mutable `MutableTriple`).

### Usage Example
```java
Triple<String, Integer, Boolean> triple = Triple.of("Alice", 25, true);
```

---

## 19. `Quad<A, B, C, D>`, `MutableQuad<A, B, C, D>`
- 4-element tuple container (Immutable `Quad`, Mutable `MutableQuad`).

### Usage Example
```java
Quad<String, Integer, Boolean, Double> quad = Quad.of("Pos", 10, true, 99.9);
```

---

## 20. `Entry<K, V>`
- Key-Value pair container.

### Usage Example
```java
Entry<String, Integer> entry = Entry.of("Key", 42);
```

---

## 21. Functional Interfaces (`TripleConsumer`, `TripleFunction`, `TriplePredicate`, `QuadConsumer`, `QuadFunction`, `QuadPredicate`, `ToBooleanFunction`, `ToStringFunction`)
- Generic functional interfaces extending standard `java.util.function` for 3-argument and 4-argument functions.

### Usage Example
```java
TripleConsumer<String, Integer, Boolean> logger = (name, score, active) -> 
    System.out.println(name + ": " + score + " (" + active + ")");
logger.accept("Bob", 150, true);
```
