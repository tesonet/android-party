plugins { `maven-publish` }
publishing {
    publications {
        register<MavenPublication>("release") {
            group = groupGh
            afterEvaluate { from(components["release"]) }
        }
    }
    repositories {
        maven("https://maven.pkg.github.com/$userGh/${rootProject.name}") {
            credentials {
                username = propertyOrEnv("gpr.user", "USERNAME")
                password = propertyOrEnv("gpr.key", "TOKEN")
            }
        }
    }
}
fun propertyOrEnv(propertyName: String, envName: String): String? =
    findProperty(propertyName) as String? ?: System.getenv(envName)