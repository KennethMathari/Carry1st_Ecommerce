pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Carry1st"
include(":app")
include(":common")
include(":feature:product:ui")
include(":feature:product:domain")
include(":feature:product:data")
include(":feature:cart:ui")
include(":feature:cart:data")
include(":feature:cart:domain")
