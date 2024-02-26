plugins {
    com.android.application
    `kotlin-android`
    com.google.devtools.ksp
    dagger.hilt.android.plugin
    id("testio.detekt-all")
}
android {
    namespace = groupGh
    compileSdk = Config.COMPILE_SDK
    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions { kotlinCompilerExtensionVersion = Config.KOTLIN_COMPILER_EXTENSION_VERSION }
    androidResources {
        @Suppress("UnstableApiUsage")
        generateLocaleConfig = true
    }
    lint {
        checkDependencies = true
        xmlReport = false
        textReport = false
        disable += Config.lintDisable
        enable += Config.lintEnable
        checkReleaseBuilds = false
        checkTestSources = true
        abortOnError = false
    }
}
kotlin.jvmToolchain(Config.JAVA)
dependencies {
    // androidx
    implementation(tlibs("appcompat"))
    // compose
    implementation(platform(tlibs.`compose-bom`))
    implementation(tlibs.material3)
    // compose preview
    implementation(tlibs.`ui-tooling-preview`)
    debugImplementation(tlibs.`ui-tooling`)
    // compose integration
    implementation(tlibs("activity-compose"))
    // hilt
    implementation(tlibs("hilt-android"))
    ksp(tlibs("hilt-android-compiler"))
    // square
    debugImplementation(tlibs("leakcanary-android"))
    // lint
    implementation(tlibs.`lint-rules`)
    lintChecks(tlibs.`slack-lint-checks`)
}
// benchmark
android {
    buildTypes {
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
}