package net.subroh0508.damaskus.gradle.plugin.tasks

import net.subroh0508.damaskus.gradle.plugin.internal.getArtifactDir
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskProvider

private const val TASK_COPY_COMMON_RESOURCES = "copyCommonResources"

private const val COMMON_RESOURCES_PATH = "src/commonMain/resources"

fun Project.buildCopyCommonResources(): TaskProvider<Copy> = tasks.register(
    TASK_COPY_COMMON_RESOURCES,
    Copy::class.java,
) {
    from("$projectDir/$COMMON_RESOURCES_PATH") {
        include("**/*")
    }
    into(getArtifactDir().absolutePath)
}
