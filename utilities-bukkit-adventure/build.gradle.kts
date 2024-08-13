repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(project(":utilities"))

    // -- spigot api -- (base)
    compileOnly("org.spigotmc:spigot:1.16.5-R0.1-SNAPSHOT")

    // -- placeholders --
    implementation("eu.okaeri:okaeri-placeholders-core:5.1.0")

    // -- kyori-adventure --
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.17.0")
}