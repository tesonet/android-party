import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.versionCatalogs
    get() = extensions.getByType<VersionCatalogsExtension>()

private val Project.libs
    get() = versionCatalogs.named("libs")
internal val Project.tlibs
    get() = versionCatalogs.named("tlibs")

fun Project.libs(alias: String) = libs.findLibrary(alias).get()
fun Project.tlibs(alias: String) = tlibs.findLibrary(alias).get()

internal fun Project.tbundles(alias: String) = tlibs.findBundle(alias).get()

val VersionCatalog.`compose-bom`
    get() = findLibrary("compose-bom").get()
val VersionCatalog.material3
    get() = findLibrary("material3").get()
val VersionCatalog.`ui-tooling-preview`
    get() = findLibrary("ui-tooling-preview").get()
val VersionCatalog.`ui-tooling`
    get() = findLibrary("ui-tooling").get()
val VersionCatalog.`kotlinx-collections-immutable`
    get() = findLibrary("kotlinx-collections-immutable").get()
val VersionCatalog.`slack-lint-checks`
    get() = findLibrary("slack-lint-checks").get()
val VersionCatalog.`lint-rules`
    get() = findBundle("lint-rules").get()