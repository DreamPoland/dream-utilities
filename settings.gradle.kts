pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "dream-utilities"

include(":utilities")
include(":utilities-bukkit")
include(":utilities-bungee")
include(":utilities-adventure")