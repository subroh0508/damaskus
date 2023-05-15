package net.subroh0508.damaskus.gradle.plugin.tasks

import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsBinaryMode

object CompileTasks {
    private const val EXECUTABLE_KOTLIN = "compile_executable_kotlin"

    fun compileExecutableKotlin(
        target: String,
        mode: KotlinJsBinaryMode,
    ): String {
        val str = EXECUTABLE_KOTLIN.split("_")

        return buildString {
            append(str[0])
            listOf(
                mode.name.toLowerCase(),
                str[1],
                str[2],
                target,
            ).forEach {
                append(it.capitalize())
            }
        }
    }
}
