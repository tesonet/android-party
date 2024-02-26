plugins {
    id("testio.app")
}
android {
    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = "hyperloop"
            storeFile = file("release.keystore")
            storePassword = "hyperloop"
        }
    }
    defaultConfig {
        val version = Version()
        versionCode = version.code
        versionName = version.name
        versionNameSuffix = "+${Version.date}"
        signingConfig = signingConfigs.getByName("release")
    }
    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isIncludeAndroidResources = true
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}
tasks.withType<Test> {
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
    forkEvery = 1
}
dependencies {
    // module
    implementation(project(":lib:design"))
    implementation(project(":core:ui"))
    implementation(project(":feature:main"))
    implementation(project(":feature:signin"))
    implementation(project(":feature:settings"))
    // android test
    // androidx
    androidTestImplementation(libs.uiautomator)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.runner)
    androidTestUtil(libs.orchestrator)
    // compose
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)
    // module
    androidTestImplementation(project(":lib:signin"))
    // benchmark android 14
    implementation(libs.profileinstaller)
}