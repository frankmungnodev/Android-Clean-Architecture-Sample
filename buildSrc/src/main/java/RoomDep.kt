import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.roomDatabase() {
    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.compiler)
}