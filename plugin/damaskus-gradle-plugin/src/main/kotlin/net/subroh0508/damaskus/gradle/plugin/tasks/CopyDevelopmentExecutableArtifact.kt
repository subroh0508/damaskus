package net.subroh0508.damaskus.gradle.plugin.tasks

import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import java.io.File

fun Project.buildCopyDevelopmentExecutableArtifact(
    target: String
) = tasks.register(
    target + TASK_COPY_DEVELOPMENT_EXECUTABLE_ARTIFACT.capitalize(),
    Copy::class.java,
) {
    from("$buildDir/$ORIGINAL_FILE_PATH") {
        include("*$target.js")
        include("*$target.js.map")

        rename(".*($target).(js|js\\.map)", "$1.$2")
    }

    into(getArtifactDir().absolutePath)
}

fun Project.buildCopyCommonResources() = tasks.register(
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

private fun Project.getArtifactDir(): File {
    val artifactDir = project.buildDir
        .takeIf { it.exists() }
        ?.listFiles { dir, name -> dir.isDirectory && name == FILE_NAME }
        ?.firstOrNull()

    return artifactDir ?: project.mkdir("${project.buildDir}/$FILE_NAME")
}
