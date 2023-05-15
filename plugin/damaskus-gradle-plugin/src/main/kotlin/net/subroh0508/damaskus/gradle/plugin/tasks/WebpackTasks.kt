package net.subroh0508.damaskus.gradle.plugin.tasks

import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsBinaryMode
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

object WebpackTasks {
    private const val BROWSER = "browser"
    private const val WEBPACK = "webpack"

    fun browserWebpack(
        name: String,
        mode: KotlinJsBinaryMode,
    ) = buildString {
        append(name)
        listOf(
            BROWSER,
            mode.name.toLowerCase(),
            WEBPACK,
        ).forEach {
            append(it.capitalize())
        }
    }
}
