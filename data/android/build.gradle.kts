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
            isMinifyEnabled = false
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
    implementation(project(":domain"))
    api(project(":data:common"))
    api(project(":data:cache"))

    //Uncomment fakenetwork and comment network for your development environment
    //debugApi(project(":data:fakenetwork"))
    debugApi(project(":data:network"))
    releaseApi(project(":data:network"))

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation(CommonLibs.timber)
    implementation(AndroidXPaging.common)
    implementation(Kotlin.stdLib)
    implementation(AndroidXCore.core_ktx)

    // DI
    hiltAndroid()

    // Testing
    testImplementation(CommonLibs.junit)
}
