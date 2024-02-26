plugins {
    id("testio.lib-compose")
    id("testio.publish")
}
dependencies {
    // androidx
    implementation(libs.appcompat)
    implementation(libs.datastore.preferences)
    // 3rd
    implementation(libs.bundles.compose.settings)
    implementation(libs.semver)
    // module
    api(project(":lib:ui"))
}
version = "0.1.0-SNAPSHOT"