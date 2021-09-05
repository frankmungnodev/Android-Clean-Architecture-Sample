import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidXNavigation() {
    implementation(AndroidXNavigation.fragment)
    implementation(AndroidXNavigation.ui)
}

object AndroidXNavigation {
    private const val version = "2.4.0-alpha06"

    const val gradle_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
    const val ui = "androidx.navigation:navigation-ui-ktx:$version"
}