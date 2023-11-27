plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    /**
     * ???
     * https://stackoverflow.com/questions/70550883/warning-the-following-options-were-not-recognized-by-any-processor-dagger-f
     */
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sandbox.modularsandbox"
    compileSdk = 34

    defaultConfig {
        minSdk = 29
        targetSdk = 33

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
    hilt()
    navigation()
    compose(platform("androidx.compose:compose-bom:2023.03.00"))
    retrofit()
    coroutines()

    implementation(project(mapOf("path" to ":domain:auth")))
    implementation(project(mapOf("path" to ":core:ui")))
}