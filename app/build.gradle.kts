import java.util.*

private val properties = Properties()
private val localPropertyFile = project.rootProject.file("local.properties")
properties.load(localPropertyFile.inputStream())

val releaseKeyStorePath = properties.getProperty("RELEASE_KEYSTORE_PATH")
    .toString()
val releaseKeyStorePassword = properties.getProperty("RELEASE_KEYSTORE_PASSWORD")
    .toString()
val releaseKeyAlias = properties.getProperty("RELEASE_KEY_ALIAS")
    .toString()
val releaseKeyPassword = properties.getProperty("RELEASE_KEY_PASSWORD")
    .toString()


plugins {
    id("com.android.application")
    id(Hilt.pluginName)
    kotlin("android")
    kotlin("kapt")
}

android {

    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        applicationId = "com.selftaughtdev.themoviedb"
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildFeatures {
            dataBinding = true
        }

        kapt {
            correctErrorTypes = true
        }
    }

    signingConfigs {
        register("release") {
            storeFile = File(rootDir, releaseKeyStorePath)
            storePassword = releaseKeyStorePassword
            keyAlias = releaseKeyAlias
            keyPassword = releaseKeyPassword
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")
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

hilt {
    enableAggregatingTask = true
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":domain"))
    implementation(project(":data:android"))

    implementation(Kotlin.stdLib)
    implementation(KotlinCoroutine.android)

    //AndroidX
    implementation(AndroidXAppCompat.app_compat)
    implementation(AndroidXCore.core_ktx)
    implementation(AndroidXRecyclerView.recycler_view)
    androidXArch()
    androidxActivity()
    androidxFragment()
    androidXNavigation()

    // Pagination
    implementation(AndroidXPaging.common)
    implementation(AndroidXPaging.runtime)

    // Coil Image Loader
    implementation(Coil.coil)

    // Hilt
    hiltAndroid()

    // Timber
    implementation(CommonLibs.timber)
    implementation(CommonLibs.swipe_refresh_layout)

    //Material
    implementation(Material.material)

    //Constraint Layout
    implementation(AndroidXConstraintLayout.constraint_layout)
}