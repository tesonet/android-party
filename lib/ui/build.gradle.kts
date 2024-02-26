plugins {
    id("testio.lib-compose")
    id("testio.publish")
}
dependencies {
    // androidx
    implementation(libs.core)
    // compose
    implementation(libs.material.icons.extended)
    api(libs.material)
    // compose integration
    api(libs.navigation.compose)
    implementation(libs.activity.compose)
    // accompanist
    implementation(libs.accompanist.placeholder.material3)
    // material
    implementation(libs.com.google.android.material.material)
    // square
    api(libs.logcat)
}
version = "0.1.0-SNAPSHOT"