import java.util.*

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

private val properties = Properties()
private val localPropertyFile = project.rootProject.file("local.properties")
properties.load(localPropertyFile.inputStream())

val developmentApiSecret = properties.getProperty("DEVELOPMENT_API_SECRET").toString()
val releaseApiSecret = properties.getProperty("RELEASE_API_SECRET").toString()
val apiKey = properties.getProperty("API_KEY").toString()

android {

    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "APP_SECRET", "\"$releaseApiSecret\"")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", "APP_SECRET", "\"$releaseApiSecret\"")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":data:common"))
    implementation(project(":domain"))

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation(Kotlin.stdLib)
    implementation(AndroidXCore.core_ktx)

    //Networking
    implementation(OkHttp.client)
    implementation(OkHttp.logger)

    implementation(Retrofit.core)
    implementation(Retrofit.moshi_converter)

    moshi()
    hiltAndroid()

    //Testing
    testImplementation(CommonLibs.junit)
}