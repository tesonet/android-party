plugins {
    com.android.library
    `kotlin-android`
}
android {
    namespace = namespaceGh
    compileSdk = Config.COMPILE_SDK
    defaultConfig { minSdk = Config.MIN_SDK }
    lint {
        xmlReport = false
        textReport = false
        htmlReport = false
        disable += Config.lintDisable
        enable += Config.lintEnable
        checkTestSources = true
        abortOnError = false
    }
}
kotlin.jvmToolchain(Config.JAVA)
dependencies {
    // lint
    implementation(tlibs.`lint-rules`)
    lintChecks(tlibs.`slack-lint-checks`)
}