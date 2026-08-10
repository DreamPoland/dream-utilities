dependencies {
    api(project(":utilities"))
    api(project(":utilities-adventure"))

    // -- paper api 1.20.6 --
    compileOnly(libs.paper.api)
    compileOnly(libs.adventure.minimessage)

    testImplementation(libs.paper.api)
    testImplementation(libs.adventure.minimessage)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.core)
}