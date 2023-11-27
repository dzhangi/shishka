import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.impl(dependency: Dependency) {
    add("implementation", dependency)
}

fun DependencyHandler.androidTestImpl(dependency: Dependency) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.impl(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.debugImpl(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.testImpl(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandler.androidTestImpl(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}