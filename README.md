# Dream-Utilities
[![Build](https://github.com/DreamPoland/dream-utilities/actions/workflows/gradle.yml/badge.svg)](https://github.com/DreamPoland/dream-utilities/actions/workflows/gradle.yml)

Java utilities library for easy developing.

```xml
<repository>
  <id>dreamcode-repository-releases</id>
  <url>https://repo.dreamcode.cc/releases</url>
</repository>
```

```groovy
maven { url "https://repo.dreamcode.cc/releases" }
```

### Core:
```xml
<dependency>
  <groupId>cc.dreamcode</groupId>
  <artifactId>utilities</artifactId>
  <version>1.5.7</version>
</dependency>
```

```groovy
implementation "cc.dreamcode:utilities:1.5.7"
```

### Supported platforms:
- [Bukkit](https://github.com/DreamPoland/dream-utilities/tree/master/utilities-bukkit)
- [Bungee](https://github.com/DreamPoland/dream-utilities/tree/master/utilities-bungee)

```xml
<dependency>
  <groupId>cc.dreamcode</groupId>
  <artifactId>utilities-{platform}</artifactId>
  <version>1.5.7</version>
</dependency>
```
```groovy
implementation "cc.dreamcode:utilities-{platform}:1.5.7"
```

For utilities content, open project modules and see the contents of the classes.
