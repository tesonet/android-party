plugins {
    id("testio.lib")
}
android {
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = Config.KOTLIN_COMPILER_EXTENSION_VERSION }
}
dependencies {
    // kotlin
    implementation(tlibs.`kotlinx-collections-immutable`)
    // compose
    implementation(platform(tlibs.`compose-bom`))
    implementation(tlibs.material3)
    // compose preview
    implementation(tlibs.`ui-tooling-preview`)
    debugImplementation(tlibs.`ui-tooling`)
}