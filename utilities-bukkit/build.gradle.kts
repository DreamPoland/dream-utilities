repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    api(project(":utilities"))

    // -- spigot api -- (base)
    compileOnly(libs.spigot.api)

    // -- placeholders --
    api(libs.okaeri.placeholders)
}