package net.subroh0508.damaskus.gradle.plugin.internal

import org.gradle.api.Project
import java.io.File

private const val FILE_NAME = "artifact"

internal fun Project.getArtifactDir(
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
