repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(project(":utilities"))

    // -- spigot api -- (base)
    compileOnly("org.spigotmc:spigot:1.16.5-R0.1-SNAPSHOT")
}