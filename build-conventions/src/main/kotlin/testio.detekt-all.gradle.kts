import io.gitlab.arturbosch.detekt.Detekt

plugins {
    io.gitlab.arturbosch.detekt
}
detekt {
    source.setFrom(rootDir)
    ignoreFailures = true
    config.setFrom(resourceAsFile("detekt.yml"))
    parallel = true
    buildUponDefaultConfig = true
    allRules = true
}
tasks.withType<Detekt>().configureEach {
    reports {
        xml.required = false
        html.required = true
        txt.required = false
        sarif.required = false
        md.required = false
    }
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}
dependencies {
    detektPlugins(tbundles("detekt"))
}

fun resourceAsFile(name: String): File {
    val stream = this::class.java.classLoader.getResourceAsStream(name)!!
    val split = name.split(".")
    val file = File.createTempFile(split[0], split[1])
    file.deleteOnExit()
    file.outputStream().use { stream.copyTo(it) }
    return file
}