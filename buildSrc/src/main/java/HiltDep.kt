import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.hiltAndroid() {
    implementation(Hilt.android)
    kapt(Hilt.android_compiler)
}

fun DependencyHandler.hiltCore() {
    implementation(Hilt.core)
    kapt(Hilt.core_compiler)
}

