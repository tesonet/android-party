plugins {
    id("testio.lib-compose-ksp")
}
dependencies {
    // room
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    // compose
    implementation(libs.material.icons.extended)
    // compose integration
    implementation(libs.hilt.navigation.compose)
    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // module
    implementation(project(":lib:design"))
    implementation(project(":lib:signin"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    // test
    testImplementation(libs.kotlinx.coroutines.test)
    // androidx
    testImplementation(libs.androidx.test.ext.junit)
    // 3rd
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.robolectric)
    // square
    testImplementation(libs.converter.gson)
    testImplementation(platform(libs.okhttp.bom))
    testImplementation(libs.okhttp)
}