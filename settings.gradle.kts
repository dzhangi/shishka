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
include(":common:ui")
include(":domain:auth")
include(":presentation:project")
include(":domain:project")
