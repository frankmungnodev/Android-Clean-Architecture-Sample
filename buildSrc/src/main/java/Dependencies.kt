object BuildConfig {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionBuild = 0

    const val versionName =
        "$versionMajor.$versionMinor.$versionPatch"
    const val versionCode =
        versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100 + versionBuild

}

object CommonLibs {
    const val android_gradle_plugin = "com.android.tools.build:gradle:7.0.1"

    const val supportAnnotations = "androidx.annotation:annotation:1.1.0"
    const val swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val junit = "junit:junit:4.13"
    const val javaxInject = "javax.inject:javax.inject:1"
}

//region AndroidX
object AndroidXAppCompat {
    const val app_compat = "androidx.appcompat:appcompat:1.3.1"
}

object AndroidXRecyclerView {
    private const val version = "1.2.1"

    const val recycler_view = "androidx.recyclerview:recyclerview:$version"
    const val selection = "androidx.recyclerview:recyclerview-selection:$version"
}

object AndroidXCore {
    private const val version = "1.6.0"

    const val core = "androidx.core:core:$version"
    const val core_ktx = "androidx.core:core-ktx:$version"
}

object AndroidXConstraintLayout {
    private const val version = "2.1.0"

    const val constraint_layout = "androidx.constraintlayout:constraintlayout:$version"
}

object AndroidXPaging {
    private const val version = "3.0.1"

    const val common = "androidx.paging:paging-common:$version"
    const val runtime = "androidx.paging:paging-runtime:$version"
}
// End Region

object Room {
    private const val version = "2.3.0"

    const val runtime = "androidx.room:room-runtime:$version"
    const val compiler = "androidx.room:room-compiler:$version"
    const val ktx = "androidx.room:room-ktx:$version"
}

object Material {
    const val material = "com.google.android.material:material:1.4.0"
}

object Kotlin {
    private const val version = "1.5.30"

    const val ktx = "androidx.core:core-ktx:$version"
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
}

object KotlinCoroutine {
    private const val version = "1.5.1"

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
}

object KtLint {
    const val version = "9.3.0"
    const val name = "org.jlleitschuh.gradle.ktlint"

    const val plugin = "org.jlleitschuh.gradle:ktlint-gradle:$version"
}

object OkHttp {
    private const val version = "4.8.1"

    const val client = "com.squareup.okhttp3:okhttp:$version"
    const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:$version"
}

object Retrofit {
    private const val version = "2.9.0"

    const val core = "com.squareup.retrofit2:retrofit:$version"
    const val moshi_converter = "com.squareup.retrofit2:converter-moshi:$version"
}

// DI
object Hilt {
    private const val version = "2.38.1"

    const val pluginName = "dagger.hilt.android.plugin"
    const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"

    const val core = "com.google.dagger:hilt-core:$version"
    const val core_compiler = "com.google.dagger:hilt-compiler:$version"

    const val android = "com.google.dagger:hilt-android:$version"
    const val android_compiler = "com.google.dagger:hilt-android-compiler:$version"
    const val android_lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02"
}

object Coil {
    private const val version = "1.3.2"

    const val coil = "io.coil-kt:coil:$version"
}