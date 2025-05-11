repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    api(project(":utilities"))

    // -- kyori-adventure --
    api(libs.adventure.minimessage)
    api(libs.adventure.serializer)
}