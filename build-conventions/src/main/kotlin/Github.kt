import org.gradle.api.Project

const val userGh = "k4dima"
val Project.groupGh get() = "com.github.$userGh.${rootProject.name}"
val Project.namespaceGh get() = groupGh + path.replace(":", ".")