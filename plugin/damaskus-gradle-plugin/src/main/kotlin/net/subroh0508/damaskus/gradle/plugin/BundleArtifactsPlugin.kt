package net.subroh0508.damaskus.gradle.plugin

import net.subroh0508.damaskus.gradle.plugin.tasks.ChromeExtensionDevelopmentExecutableArtifacts
import net.subroh0508.damaskus.gradle.plugin.tasks.CompileTasks
import net.subroh0508.damaskus.gradle.plugin.tasks.buildCopyDevelopmentExecutableArtifact
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsBinaryMode

class BundleArtifactsPlugin : Plugin<Project> {
    override fun apply(target: Project) = with (target) {
        plugins.apply(KotlinPlugins.MPP)

        val mppExtension = the<KotlinMultiplatformExtension>()

        listOf(
            ChromeExtensionTargets.CONTENT,
            ChromeExtensionTargets.POPUP,
        ).forEach { jsTarget ->
            mppExtension.js(jsTarget) {
                binaries.executable()
                browser {
                    distribution {
                        name = jsTarget
                    }
                    commonWebpackConfig {
                        outputFileName = "$jsTarget.js"
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

        getDevelopmentCompileExecutableKotlinTasks(target).forEach { (jsTarget, task) ->
            val copy by buildCopyDevelopmentExecutableArtifact(jsTarget)

            chromeExtensionDevelopmentExecutableArtifacts.dependsOn(copy)
            copy.dependsOn(task)
        }
    }

    private fun getDevelopmentCompileExecutableKotlinTasks(
        target: Project,
    ) = listOf(
        ChromeExtensionTargets.CONTENT,
        ChromeExtensionTargets.POPUP,
    ).mapNotNull { jsTarget ->
        val name = CompileTasks.compileExecutableKotlin(
            jsTarget,
            KotlinJsBinaryMode.DEVELOPMENT,
        )

        target.tasks.findByName(name)?.let { jsTarget to it }
    }

    private fun registerCopyExecutableArtifact(
        target: Project,
    ) = with (target) {
        listOf(
            ChromeExtensionTargets.CONTENT,
            ChromeExtensionTargets.POPUP,
        ).map {
            tasks.register(
                "copy${it.capitalize()}ExecutableArtifact",
                Copy::class.java,
            ) {


            }
        }
    }
}
