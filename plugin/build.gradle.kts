plugins {
    alias(libs.plugins.kotlin.mpp) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.buildconfig) apply false
}

tasks.wrapper {
    gradleVersion = "7.6.1"
}
