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
include(":feature:product:ui")
include(":feature:cart:ui")
include(":data:product")
include(":data:cart")
include(":domain:product")
include(":core:network")
include(":core:database")
include(":domain:cart")
