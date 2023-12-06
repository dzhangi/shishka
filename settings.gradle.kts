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

rootProject.name = "Shishka"
include(":app")
include(":data")
include(":presentation:splash")
include(":presentation:auth")
include(":core:ui")
include(":domain:auth")
include(":presentation:project")
