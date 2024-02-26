plugins {
    id("testio.lib-compose-ksp")
    id("testio.publish")
}
android {
    defaultConfig {
        consumerProguardFile("proguard-rules.pro")
    }
}
dependencies {
    // kotlin
    api(libs.kotlinx.collections.immutable)
    // androidx
    implementation(libs.credentials.play.services.auth)
    // compose
    implementation(libs.material.icons.extended)
    api(libs.material3.window.size)
    // compose integration
    implementation(libs.hilt.navigation.compose)
    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // modules
    implementation(project(":lib:ui"))
}
version = "0.1.0-SNAPSHOT"