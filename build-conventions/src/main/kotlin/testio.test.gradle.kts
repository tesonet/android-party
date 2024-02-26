plugins {
    com.android.test
    `kotlin-android`
}
android {
    namespace = namespaceGh
    compileSdk = Config.COMPILE_SDK
    defaultConfig {
        minSdk = 23
        targetSdk = Config.TARGET_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] = "EMULATOR,LOW-BATTERY"
    }
    buildTypes {
        /* This benchmark buildType is used for benchmarking, and should function like your
         release build (for example, with minification on).
         It's signed with a debug key for easy local/CI testing. */
        create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
        }
    }
    targetProjectPath = ":app"
    @Suppress("UnstableApiUsage")
    experimentalProperties["android.experimental.self-instrumenting"] = true
}
kotlin.jvmToolchain(Config.JAVA)
dependencies {
    implementation(tlibs("androidx-test-ext-junit"))
    implementation(tlibs("benchmark-macro-junit4"))
}
androidComponents {
    beforeVariants(selector().all()) {
        it.enable = it.buildType == "benchmark"
    }
}