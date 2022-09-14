object Libraries {

    const val junit4 = "junit:junit:${Versions.junit4}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val libPhoneNumberAndroid =
        "io.michaelrocks:libphonenumber-android:${Versions.libphonenumber_android}"

    const val circleIndicator = "me.relex:circleindicator:${Versions.circleindicator}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    const val ucrop = "com.github.yalantis:ucrop:${Versions.ucrop}"

    const val tapTargetView =
        "com.getkeepsafe.taptargetview:taptargetview:${Versions.taptargetview}"

    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"

    const val lottieCompose = "com.airbnb.android:lottie-compose:${Versions.lottieComposeVersion}"

    const val recyclerviewAnimators =
        "jp.wasabeef:recyclerview-animators:${Versions.recyclerviewAnimators}"

    const val loggingInterceptor = "com.github.ihsanbal:LoggingInterceptor:${Versions.logging}"

    const val segmentAnalytics = "com.segment.analytics.android:analytics:${Versions.segment}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"

    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val launchDarkly =
        "com.launchdarkly:launchdarkly-android-client-sdk:${Versions.launchDarkly}"

    const val diskLruCache = "com.jakewharton:disklrucache:${Versions.diskLruCache}"

    const val coil = "io.coil-kt:coil-base:${Versions.coil}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"

    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"

    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"

    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.desugarJdkLibs}"

    const val sentry = "io.sentry:sentry-android:${Versions.sentry}"

    const val materialDateTimePicker =
        "com.wdullaer:materialdatetimepicker:${Versions.materialDateTimePicker}"

    const val photoView = "com.github.chrisbanes:PhotoView:${Versions.photoView}"

    const val fixture = "com.appmattus.fixture:fixture:${Versions.fixture}"

    const val instabug = "com.instabug.library:instabug:${Versions.instabug}"

    const val intercom = "io.intercom.android:intercom-sdk:${Versions.intercom}"
}

object JetbrainsLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx}"
    const val coroutinesRx2 =
        "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${Versions.kotlinx}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinx}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val coroutinesPlayService =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.kotlinx}"
}

object AndroidxLibraries {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.Androidx.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.Androidx.coreKtx}"
    const val collection =
        "androidx.collection:collection-ktx:${Versions.Androidx.collectionKtx}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.Androidx.recyclerview}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.Androidx.constraintlayout}"
    const val dynamicAnimation =
        "androidx.dynamicanimation:dynamicanimation:${Versions.Androidx.dynamicanimation}"
    const val emojiAppcompat = "androidx.emoji:emoji-appcompat:${Versions.Androidx.emojicompat}"
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.Androidx.paging}"
    const val exifinterface =
        "androidx.exifinterface:exifinterface:${Versions.Androidx.exifinterface}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.Androidx.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.Androidx.navigation}"
    const val preference = "androidx.preference:preference-ktx:${Versions.Androidx.preference}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Androidx.swiperefreshlayout}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Androidx.fragmentKtx}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.Androidx.activityKtx}"
    const val dataStorePreferences =
        "androidx.datastore:datastore-preferences:${Versions.Androidx.dataStorePreferences}"
    const val annotationx = "androidx.annotation:annotation:${Versions.Androidx.annotationx}"
}

object AndroidxTestLibraries {
    const val core = "androidx.test:core-ktx:${Versions.Androidx.testCore}"
    const val testRules = "androidx.test:rules:${Versions.Androidx.testCore}"
    const val testRunner = "androidx.test:runner:${Versions.Androidx.testCore}"
    const val testOrchestrator = "androidx.test:orchestrator:${Versions.Androidx.testCore}"
    const val testJunit = "androidx.test.ext:junit-ktx:${Versions.Androidx.testExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Androidx.espresso}"
    const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.Androidx.espresso}"
    const val espressoIntents =
        "androidx.test.espresso:espresso-intents:${Versions.Androidx.espresso}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.Androidx.archCoreTesting}"

    const val kaspresso = "com.kaspersky.android-components:kaspresso:${Versions.kaspresso}"
}

object LifecyleLibraries {
    const val liveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Androidx.lifecycle}"
    const val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Androidx.lifecycle}"
    const val runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Androidx.lifecycle}"
    const val process =
        "androidx.lifecycle:lifecycle-process:${Versions.Androidx.lifecycle}"
}

object WorkManageLibraries {
    const val runtime = "androidx.work:work-runtime:${Versions.Androidx.workManager}"
    const val runtimeKtx = "androidx.work:work-runtime-ktx:${Versions.Androidx.workManager}"
    const val testing = "androidx.work:work-testing:${Versions.Androidx.workManager}"
}

object KoinLibraries {
    const val androidCompat = "io.insert-koin:koin-android-compat:${Versions.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
    const val workManager = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
}

object MockkLibraries {
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
}

object GoogleLibraries {
    const val material = "com.google.android.material:material:${Versions.Google.material}"
    const val playCore = "com.google.android.play:core:${Versions.Google.playCore}"
    const val playCoreKtx = "com.google.android.play:core-ktx:${Versions.Google.playCoreKtx}"
    const val playServicesAuth =
        "com.google.android.gms:play-services-auth:${Versions.Google.servicesAuth}"
    const val playServicesAuthApiPhone =
        "com.google.android.gms:play-services-auth-api-phone:${Versions.Google.servicesAuthPhone}"
    const val flexbox = "com.google.android.flexbox:flexbox:${Versions.Google.flexbox}"
    const val truth = "com.google.truth:truth:${Versions.Google.truth}"
    const val installReferrer =
        "com.android.installreferrer:installreferrer:${Versions.Google.installReferrer}"
    const val places = "com.google.android.libraries.places:places:${Versions.Google.places}"
}


object ComposeLibraries {
    const val ui = "androidx.compose.ui:ui:${Versions.composeUi}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeUi}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.composeUi}"
    const val material = "androidx.compose.material:material:${Versions.composeUi}"
    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.composeUi}"
    const val uiTest = "androidx.compose.ui:ui-test-manifest:${Versions.composeUi}"
    const val activity = "androidx.activity:activity-compose:${Versions.composeUi}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.composeUi}"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.composeUi}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.nav_version}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha03"
}