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

    plugins {
        kotlin("android") version "1.9.20"
        kotlin("plugin.serialization")
        kotlin("android")
        kotlin("android.extensions")
        id("kapt")
        id("com.google.gms.google-services") apply false
        id("com.google.devtools.ksp") version "1.9.21-1.0.16" apply false
        id("com.google.dagger.hilt.android") version "2.50" apply false
        id("com.google.firebase.crashlytics") version "2.9.9" apply false
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "NBC Demo"
include(":app")
 