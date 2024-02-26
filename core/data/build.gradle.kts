plugins {
    id("testio.lib-ksp")
}
android {
    defaultConfig { consumerProguardFile("proguard-rules.pro") }
    buildFeatures {
        buildConfig = true
    }
}
dependencies {
    // androidx
    implementation(libs.datastore.preferences)
    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // square
    implementation(libs.converter.gson)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
}