pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ModularSandbox"
include(":app")
include(":data")
include(":presentation:splash")
include(":presentation:login")
include(":core:ui")
include(":domain:auth")