package net.subroh0508.damaskus.gradle.plugin.tasks

import net.subroh0508.damaskus.gradle.plugin.ChromeExtensionScripts
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.getValue
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsBinaryMode

private const val TASK_CHROME_EXTENSION_PRODUCTION_EXECUTABLE_ARTIFACT = "chromeExtensionProductionExecutableArtifacts"

fun Project.chromeExtensionProductionExecutableArtifact(
    copyCommonResources: Copy,
) {
    val task by registerChromeExtensionProductionExecutableArtifact()

    task.dependsOn(copyCommonResources)
    getBrowserProductionWebpackTasks().forEach { (scripts, task) ->
        val copy by buildCopyProductionExecutableArtifact(scripts)

        copyCommonResources.dependsOn(copy)
        copy.dependsOn(task)
    }
}

private fun Project.registerChromeExtensionProductionExecutableArtifact(): TaskProvider<Task> = tasks.register(
    TASK_CHROME_EXTENSION_PRODUCTION_EXECUTABLE_ARTIFACT,
) {
    group = DEFAULT_TASK_GROUP
    description = "Build artifacts for Chrome Extension by production build"
}

internal fun Project.getBrowserProductionWebpackTasks() = ChromeExtensionScripts.values()
    .mapNotNull { scripts ->
        val name = WebpackTasks.browserWebpack(
            scripts.toString(),
            KotlinJsBinaryMode.PRODUCTION,
        )

        tasks.findByName(name)?.let { scripts to it }
    }
