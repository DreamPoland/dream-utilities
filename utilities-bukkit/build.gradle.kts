repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.reposilite.com/maven-central")
}

dependencies {
    api(project(":utilities"))

    // -- spigot api -- (base)
    compileOnly(libs.spigot.api)
}