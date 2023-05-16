package net.subroh0508.damaskus.gradle.plugin

import net.subroh0508.damaskus.gradle.plugin.tasks.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class BundleArtifactsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply(KotlinPlugins.MPP)

        val mppExtension = target.the<KotlinMultiplatformExtension>()

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

        target.chromeExtensionExecutableArtifact()
    }
}
