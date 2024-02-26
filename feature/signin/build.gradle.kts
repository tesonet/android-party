plugins {
    id("testio.lib-compose-ksp")
}
dependencies {
    // compose
    implementation(libs.material.icons.extended)
    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // square
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    // module
    implementation(project(":lib:design"))
    implementation(project(":lib:signin"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
}