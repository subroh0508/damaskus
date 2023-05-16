package net.subroh0508.damaskus.gradle.plugin

import net.subroh0508.damaskus.gradle.plugin.tasks.ChromeExtensionDevelopmentExecutableArtifacts
import net.subroh0508.damaskus.gradle.plugin.tasks.CompileTasks
import net.subroh0508.damaskus.gradle.plugin.tasks.buildCopyCommonResources
import net.subroh0508.damaskus.gradle.plugin.tasks.buildCopyDevelopmentExecutableArtifact
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsBinaryMode

class BundleArtifactsPlugin : Plugin<Project> {
    override fun apply(target: Project) = with (target) {
        plugins.apply(KotlinPlugins.MPP)

        val mppExtension = the<KotlinMultiplatformExtension>()

        ChromeExtensionScripts.values().forEach { scripts ->
            mppExtension.js(scripts.toString()) {
                binaries.executable()
                browser {
                    distribution {
                        name = scripts.filename
                    }
                    commonWebpackConfig {
                        outputFileName = "${scripts.filename}.js"
                        cssSupport {
                            enabled.set(true)
                        }
                    }
                }
            }
        }

        val chromeExtensionDevelopmentExecutableArtifacts by tasks.register(
            ChromeExtensionDevelopmentExecutableArtifacts.NAME,
            ChromeExtensionDevelopmentExecutableArtifacts::class.java,
        )
        val copyCommonResources by buildCopyCommonResources()

        chromeExtensionDevelopmentExecutableArtifacts.dependsOn(copyCommonResources)
        getDevelopmentCompileExecutableKotlinTasks(target).forEach { (scripts, task) ->
            val copy by buildCopyDevelopmentExecutableArtifact(scripts)

            copyCommonResources.dependsOn(copy)
            copy.dependsOn(task)
        }
    }

    private fun getDevelopmentCompileExecutableKotlinTasks(
        target: Project,
    ) = ChromeExtensionScripts.values().mapNotNull { scripts ->
        val name = CompileTasks.compileExecutableKotlin(
            scripts.toString(),
            KotlinJsBinaryMode.DEVELOPMENT,
        )

        target.tasks.findByName(name)?.let { scripts to it }
    }
}
