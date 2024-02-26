pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
includeBuild("build-conventions")
plugins {
    `gradle-enterprise`
}
gradleEnterprise {
    if (System.getenv("CI") == null) return@gradleEnterprise
    buildScan {
        publishAlways()
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("tlibs") { from(files("gradle/libs.versions.toml")) }
    }
}
include(
    "app", "benchmark",
    "lib:ui", "lib:design", "lib:signin",
    "core:ui", "core:data",
    "feature:main", "feature:signin", "feature:settings"
)