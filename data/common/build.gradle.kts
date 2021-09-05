plugins {
    id("java-library")
    id("kotlin")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(project(":domain"))

    testImplementation(CommonLibs.junit)

    // DI
    hiltCore()

    implementation(Kotlin.stdLib)
    api(KotlinCoroutine.core)

    implementation(CommonLibs.javaxInject)
}