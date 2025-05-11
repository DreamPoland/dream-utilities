repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://repo.codemc.io/repository/maven-public")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    api(project(":utilities"))

    // -- bungee api -- (base)
    compileOnly(libs.bungeecord.api)

    // -- placeholders --
    implementation(libs.okaeri.placeholders)

    // -- kyori-adventure --
    implementation(libs.adventure.minimessage)
    implementation(libs.adventure.serializer)
}