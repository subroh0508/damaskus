package net.subroh0508.damaskus.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class BundleArtifactsPlugin : Plugin<Project> {
    override fun apply(target: Project) = with (target) {
        plugins.apply(KotlinPlugins.MPP)

        val mppExtension = the<KotlinMultiplatformExtension>()

        listOf(
            ChromeExtensionTargets.CONTENT,
            ChromeExtensionTargets.POPUP,
        ).forEach { target ->
            mppExtension.js(target) {
                binaries.executable()
                browser {
                    distribution {
                        name = target
                    }
                    commonWebpackConfig {
                        outputFileName = "$target.js"
                        cssSupport {
                            enabled.set(true)
                        }
                    }
                }
            }
        }
    }
}
