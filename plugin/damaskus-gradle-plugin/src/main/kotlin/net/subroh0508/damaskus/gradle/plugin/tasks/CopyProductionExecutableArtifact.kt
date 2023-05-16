package net.subroh0508.damaskus.gradle.plugin.tasks

import net.subroh0508.damaskus.gradle.plugin.ChromeExtensionScripts
import net.subroh0508.damaskus.gradle.plugin.internal.getArtifactDir
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskProvider

private const val TASK_COPY_PRODUCTION_EXECUTABLE_ARTIFACT = "copyProductionExecutableArtifact"
private const val TASK_COPY_COMMON_RESOURCES = "copyCommonResources"

fun Project.buildCopyProductionExecutableArtifact(
    scripts: ChromeExtensionScripts,
): TaskProvider<Copy> = tasks.register(
    scripts.toString() + TASK_COPY_PRODUCTION_EXECUTABLE_ARTIFACT.capitalize(),
    Copy::class.java,
) {
    from("$buildDir/${scripts.filename}") {
        include("*${scripts.filename}.js")
        include("*${scripts.filename}.js.map")

        rename(".*(${scripts.filename}).(js|js\\.map)", "$1.$2")
    }

    into(getArtifactDir(scripts.path).absolutePath)
}
