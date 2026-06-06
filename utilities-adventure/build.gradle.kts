repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    api(project(":utilities"))

    // -- kyori-adventure --
    api(libs.adventure.minimessage)
    api(libs.adventure.serializer)
}