dependencies {
    api(project(":utilities"))

    api(libs.adventure.api)
    api(libs.adventure.minimessage)
    api(libs.adventure.serializer)
    api(libs.adventure.plain)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.core)
}
