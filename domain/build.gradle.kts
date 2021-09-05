plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(Kotlin.stdLib)

    // Coroutine core
    api(KotlinCoroutine.core)

    // Annotation
    implementation(CommonLibs.supportAnnotations)

    // Inject Library
    implementation(CommonLibs.javaxInject)

    testImplementation(CommonLibs.junit)
}