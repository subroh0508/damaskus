package net.subroh0508.damaskus.gradle.plugin.internal

private const val CAMELIZE_REGEX = """_[a-z]"""

internal fun String.camelize() = replace(CAMELIZE_REGEX.toRegex()) {
    it.value.replace("_", "").toUpperCase()
}

internal fun String.kebabize() = replace("_", "-")
