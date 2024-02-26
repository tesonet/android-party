plugins {
    `kotlin-dsl`
    `maven-publish`
    `version-catalog`
}
dependencies {
    implementation(libs.agp)
    implementation(libs.kgp)
    implementation(libs.hgp)
    implementation(libs.ksp)
    implementation(libs.detekt)
}
group = "com.github.k4dima.testio"
version = "0.1.0-SNAPSHOT"
// build
java {
    withJavadocJar()
    withSourcesJar()
}
// catalog
catalog {
    versionCatalog {
        from(files("../gradle/libs.versions.toml"))
    }
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
            artifactId = "catalog"
        }
    }
}