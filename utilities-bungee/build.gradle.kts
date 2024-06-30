repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(project(":utilities"))

    // -- bungee api -- (base)
    compileOnly("net.md-5:bungeecord-api:1.20-R0.1-SNAPSHOT")

    // -- placeholders --
    implementation("eu.okaeri:okaeri-placeholders-core:5.0.1")
}