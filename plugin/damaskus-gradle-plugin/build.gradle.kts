plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))
}

gradlePlugin {
    plugins {
        create("bundle-artifacts") {
            id = "net.subroh0508.damaskus.bundle-artifacts"
            displayName = "Bundle Artifacts"
            description = "Bundle artifacts for Chrome Extension"
            implementationClass = "net.subroh0508.damaskus.gradle.plugin.BundleArtifactsPlugin"
        }
    }
}
