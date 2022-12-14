plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    alias(libs.plugins.hilt)
}
android {
    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = "hyperloop"
            storeFile = file("keystore")
            storePassword = "hyperloop"
        }
    }
    namespace = "com.k4dima.party"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("release")
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        dataBinding = true
    }
    testOptions.unitTests.isIncludeAndroidResources = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions.jvmTarget = "11"
}
dependencies {
    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // androidx
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.fragment:fragment-ktx:1.5.5")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // google
    implementation("com.google.android.material:material:1.7.0")
    // firebase
    implementation(platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    // 3rd
    implementation("com.jakewharton.timber:timber:5.0.1")
    // square
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")
    // junit
    testImplementation("junit:junit:4.13.2")
    // androidx
    testImplementation("androidx.test.ext:junit:1.1.4")
    testImplementation("androidx.test.ext:truth:1.5.0")
    testImplementation("androidx.test.espresso:espresso-intents:3.5.0")
    testImplementation("androidx.test.espresso:espresso-core:3.5.0")
    // 3rd
    testImplementation("org.robolectric:robolectric:4.6.1")
    // androidx
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.4")
    androidTestImplementation("androidx.test.ext:truth:1.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.2.0")
}
kapt {
    correctErrorTypes = true
}