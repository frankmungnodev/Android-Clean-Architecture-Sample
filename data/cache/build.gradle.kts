plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

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
            isMinifyEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    kapt {
        correctErrorTypes = true
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

    // Room
    roomDatabase()

    // DI
    hiltAndroid()

    //Testing
    testImplementation(CommonLibs.junit)
}