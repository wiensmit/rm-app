import Versions.compose

object Versions {
    // Shared versions
    const val kotlin = "1.8.21"
    const val daggerHilt = "2.47"
    const val navigation = "2.6.0"
    const val compose = "1.4.7"
}

object Projects {
    const val gradleTools = "com.android.tools.build:gradle:8.0.2"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Deps {
    // Android
    const val ktx = "androidx.core:core-ktx:1.10.1"
    const val design = "com.google.android.material:material:1.9.0"
    const val androidAnnotations = "androidx.annotation:annotation:1.6.0"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-kfx:${Versions.navigation}"

    private const val LIFECYCLE = "2.6.1"
    const val architectureComponentsProcess = "androidx.lifecycle:lifecycle-process:$LIFECYCLE"
    const val architectureComponentsViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE"
    const val architectureComponentsRunTime = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE"
    const val architectureComponentsCompiler = "androidx.lifecycle:lifecycle-compiler:$LIFECYCLE"

    // Framework
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
    const val daggerHiltCore = "com.google.dagger:hilt-core:${Versions.daggerHilt}"
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val kotlinxMetadataProcessor = "org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0"
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    private const val RETROFIT = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$RETROFIT"
    const val retrofitScalars = "com.squareup.retrofit2:converter-scalars:$RETROFIT"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$RETROFIT"

    private const val OKHTTP = "4.9.1"
    const val okhttp = "com.squareup.okhttp3:okhttp:$OKHTTP"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:$OKHTTP"
    const val gson = "com.google.code.gson:gson:2.8.6"

    private const val COROUTINES = "1.5.0"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES"

    // Testing
    private const val MOCKK = "1.13.5"
    const val mockk = "io.mockk:mockk:$MOCKK"
    const val mockkAndroid = "io.mockk:mockk-android:$MOCKK"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES"
    const val junit = "junit:junit:4.13.2"
    const val junitCompose = "androidx.compose.ui:ui-test-junit4:1.4.3"
    const val composeUiTest = "androidx.compose.ui:ui-test-manifest:1.4.3"

    // Compose
    const val composeBom = "androidx.compose:compose-bom:2023.05.01"
    const val composeMaterial = "androidx.compose.material:material"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeDebugUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeRuntime = "androidx.compose.runtime:runtime"
    const val composeActivity = "androidx.activity:activity-compose:1.7.2"
    const val composeLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:$LIFECYCLE"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$LIFECYCLE"
    const val composePaging = "androidx.paging:paging-compose:3.2.0"
    const val composePagingRuntime = "androidx.paging:paging-runtime:3.1.1"

    private const val COIL = "2.4.0"
    const val composeCoil = "io.coil-kt:coil-compose:$COIL"
    const val composeCoilSvg = "io.coil-kt:coil-svg:$COIL"

    private const val ACCOMPANIST = "0.30.1"
    const val accompanistPlaceholder = "com.google.accompanist:accompanist-placeholder-material:$ACCOMPANIST"

    // Development
    private const val PLUTO = "2.0.6"
    const val pluto = "com.plutolib:pluto:$PLUTO"
    const val plutoNoop = "com.plutolib:pluto-no-op:$PLUTO"
    private const val PLUTO_NET = "2.0.5"
    const val plutoBundle = "com.plutolib.plugins:bundle-core:$PLUTO_NET"
    const val plutoBundleNoop = "com.plutolib.plugins:bundle-core-no-op:$PLUTO_NET"
}
