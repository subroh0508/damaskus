package net.subroh0508.damaskus.gradle.plugin.tasks

import net.subroh0508.damaskus.gradle.plugin.ChromeExtensionScripts
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskProvider
import java.io.File

fun Project.buildCopyDevelopmentExecutableArtifact(
    scripts: ChromeExtensionScripts,
): TaskProvider<Copy> = tasks.register(
    scripts.toString() + TASK_COPY_DEVELOPMENT_EXECUTABLE_ARTIFACT.capitalize(),
    Copy::class.java,
) {
    from("$buildDir/$ORIGINAL_FILE_PATH") {
        include("*${scripts.filename}.js")
        include("*${scripts.filename}.js.map")

        rename(".*(${scripts.filename}).(js|js\\.map)", "$1.$2")
    }

    into(getArtifactDir(scripts.path).absolutePath)
}

fun Project.buildCopyCommonResources(): TaskProvider<Copy> = tasks.register(
    TASK_COPY_COMMON_RESOURCES,
    Copy::class.java,
) {
    from("$projectDir/$COMMON_RESOURCES_PATH") {
        include("**/*")
    }
    into(getArtifactDir().absolutePath)
}

private const val TASK_COPY_DEVELOPMENT_EXECUTABLE_ARTIFACT = "copyDevelopmentExecutableArtifact"
private const val TASK_COPY_COMMON_RESOURCES = "copyCommonResources"

private const val FILE_NAME = "artifact"
private const val ORIGINAL_FILE_PATH = "compileSync/js/main/developmentExecutable/kotlin"
private const val COMMON_RESOURCES_PATH = "src/commonMain/resources"

private fun Project.getArtifactDir(
    path: String = "",
): File {
    var artifactDir = project.buildDir
        .takeIf { it.exists() }
        ?.listFiles { dir, name -> dir.isDirectory && name == FILE_NAME }
        ?.firstOrNull()

    if (artifactDir == null) {
        artifactDir = project.mkdir("${project.buildDir}/$FILE_NAME")
    }

    if (path.isBlank()) {
        return artifactDir
    }

    return artifactDir.resolve(path)
}
