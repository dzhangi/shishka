plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.octocavern.shishka"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.octocavern.shishka"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    buildFeatures {
        compose = true
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    essentials()
    test()
    navigation()
    hilt()
    retrofit()
    compose(platform("androidx.compose:compose-bom:2023.03.00"))
    coroutines()

    implementation(project(mapOf("path" to ":presentation:splash")))
    implementation(project(mapOf("path" to ":presentation:auth")))
    implementation(project(mapOf("path" to ":presentation:project")))
}