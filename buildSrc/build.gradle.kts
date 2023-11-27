plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    implementation("com.android.tools.build:gradle:8.1.2")
    implementation("com.squareup:javapoet:1.13.0")
}