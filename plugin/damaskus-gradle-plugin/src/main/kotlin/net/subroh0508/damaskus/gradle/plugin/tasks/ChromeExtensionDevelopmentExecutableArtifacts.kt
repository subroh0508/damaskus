package net.subroh0508.damaskus.gradle.plugin.tasks

import org.gradle.api.DefaultTask

open class ChromeExtensionDevelopmentExecutableArtifacts : DefaultTask() {
    companion object {
        const val NAME = "chromeExtensionDevelopmentExecutableArtifacts"
    }

    init {
        group = DEFAULT_TASK_GROUP
        description = "Build artifacts for Chrome Extension by development build"
    }
}
