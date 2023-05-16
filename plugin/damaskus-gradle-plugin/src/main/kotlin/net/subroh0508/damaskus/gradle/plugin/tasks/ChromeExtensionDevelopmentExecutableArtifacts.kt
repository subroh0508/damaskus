package net.subroh0508.damaskus.gradle.plugin.tasks

import net.subroh0508.damaskus.gradle.plugin.ChromeExtensionScripts
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.getValue
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsBinaryMode

private const val TASK_CHROME_EXTENSION_DEVELOPMENT_EXECUTABLE_ARTIFACT = "chromeExtensionDevelopmentExecutableArtifacts"

fun Project.chromeExtensionDevelopmentExecutableArtifact(
    copyCommonResources: Copy,
) {
    val task by registerChromeExtensionDevelopmentExecutableArtifact()

    task.dependsOn(copyCommonResources)
    getCompileDevelopmentExecutableKotlinTasks().forEach { (scripts, task) ->
        val copy by buildCopyDevelopmentExecutableArtifact(scripts)

        copyCommonResources.dependsOn(copy)
        copy.dependsOn(task)
    }
}

private fun Project.registerChromeExtensionDevelopmentExecutableArtifact(): TaskProvider<Task> = tasks.register(
    TASK_CHROME_EXTENSION_DEVELOPMENT_EXECUTABLE_ARTIFACT,
) {
    group = DEFAULT_TASK_GROUP
    description = "Build artifacts for Chrome Extension by development build"
}

private fun Project.getCompileDevelopmentExecutableKotlinTasks() = ChromeExtensionScripts.values()
    .mapNotNull { scripts ->
        val name = CompileTasks.compileExecutableKotlin(
            scripts.toString(),
            KotlinJsBinaryMode.DEVELOPMENT,
        )

        tasks.findByName(name)?.let { scripts to it }
    }
