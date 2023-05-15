package net.subroh0508.damaskus.gradle.plugin

import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

object WebpackTasks {
    private const val BROWSER = "browser"
    private const val WEBPACK = "webpack"

    fun browserWebpack(
        name: String,
        mode: KotlinWebpackConfig.Mode,
    ) = buildString {
        append(name)
        listOf(
            BROWSER,
            mode.name,
            WEBPACK,
        ).forEach {
            append(it.capitalize())
        }
    }
}
