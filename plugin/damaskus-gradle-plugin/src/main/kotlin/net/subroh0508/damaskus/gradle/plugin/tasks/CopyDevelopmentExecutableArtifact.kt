package net.subroh0508.damaskus.gradle.plugin.tasks

import net.subroh0508.damaskus.gradle.plugin.ChromeExtensionScripts
import net.subroh0508.damaskus.gradle.plugin.internal.findOrRegister
import net.subroh0508.damaskus.gradle.plugin.internal.getArtifactDir
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskProvider
import java.io.File

private const val TASK_COPY_DEVELOPMENT_EXECUTABLE_ARTIFACT = "copyDevelopmentExecutableArtifact"

fun Project.buildCopyDevelopmentExecutableArtifact(
    scripts: ChromeExtensionScripts,
): TaskProvider<Copy> = tasks.register(
    scripts.toString() + TASK_COPY_DEVELOPMENT_EXECUTABLE_ARTIFACT.capitalize(),
    Copy::class.java,
) {
    from("$buildDir/$ORIGINAL_FILE_DIR_PATH") {
        include("*${scripts.filename}.js")
        include("*${scripts.filename}.js.map")

        rename(".*(${scripts.filename}).(js|js\\.map)", "$1.$2")
    }

    into(getArtifactDir(scripts.path).absolutePath)
}

private const val ORIGINAL_FILE_DIR_PATH = "compileSync/js/main/developmentExecutable/kotlin"
