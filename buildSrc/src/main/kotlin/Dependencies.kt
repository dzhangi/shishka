import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeLifecycle =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeLifeCycle}"
    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
}

fun DependencyHandler.essentials() {
    impl(Dependencies.androidxCore)
    impl(Dependencies.androidxAppCompat)
    impl(Dependencies.material)
    impl(Dependencies.constraintLayout)
}

fun DependencyHandler.test() {
    testImpl(Dependencies.junit)
    androidTestImpl(Dependencies.junitExt)
    androidTestImpl(Dependencies.espresso)
}

fun DependencyHandler.coroutines() {
    impl(Dependencies.coroutineCore)
    impl(Dependencies.coroutineAndroid)
}

fun DependencyHandler.navigation() {
    impl(Dependencies.navigation)
    impl(Dependencies.navigationUI)
}

fun DependencyHandler.hilt() {
    impl(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.retrofit() {
    impl(Dependencies.retrofit)
    impl(Dependencies.converterGson)
    impl(Dependencies.loggingInterceptor)
}

fun DependencyHandler.compose(composeBom: Dependency) {
    impl(Dependencies.composeActivity)
    impl(composeBom)
    impl(Dependencies.composeUi)
    impl(Dependencies.composeUiGraphics)
    impl(Dependencies.composeUiToolingPreview)
    impl(Dependencies.composeMaterial3)
    impl(Dependencies.composeLifecycle)

    androidTestImpl(composeBom)
    androidTestImpl(Dependencies.composeUiTestJunit4)
    debugImpl(Dependencies.composeUiTooling)
    debugImpl(Dependencies.composeUiTestManifest)
}