plugins {
    alias(libs.plugins.kotlin.mpp)
}

kotlin {
    targets {
        js("content", IR) {
            binaries.executable()
            browser {
                distribution {
                    name = "content"
                }
                commonWebpackConfig {
                    outputFileName = "content.js"
                    cssSupport {
                        enabled.set(true)
                    }
                }
            }
        }

        js("popup", IR) {
            binaries.executable()
            browser {
                distribution {
                    name = "popup"
                }
                commonWebpackConfig {
                    outputFileName = "popup.js"
                    cssSupport {
                        enabled.set(true)
                    }
                }
            }
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        named("contentMain") {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.346")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.346")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.9.3-pre.346")
            }
        }

        named("popupMain") {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.346")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.346")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.9.3-pre.346")
            }
        }
    }
}
