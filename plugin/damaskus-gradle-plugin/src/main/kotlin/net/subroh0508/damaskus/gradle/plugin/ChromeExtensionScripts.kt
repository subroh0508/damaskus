package net.subroh0508.damaskus.gradle.plugin

import net.subroh0508.damaskus.gradle.plugin.internal.camelize
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.NamedDomainObjectProvider
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

enum class ChromeExtensionScripts {
    CONTENT, POPUP, SERVICE_WORKER, OPTIONS;

    companion object {
        fun targets() = values().map(ChromeExtensionScripts::toString)
    }

    override fun toString() = name.toLowerCase().camelize()
}

val NamedDomainObjectContainer<KotlinSourceSet>.contentMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionScripts.CONTENT}Main")

val NamedDomainObjectContainer<KotlinSourceSet>.popupMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionScripts.POPUP}Main")

val NamedDomainObjectContainer<KotlinSourceSet>.serviceWorkerMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionScripts.SERVICE_WORKER}Main")

val NamedDomainObjectContainer<KotlinSourceSet>.optionsMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionScripts.OPTIONS}Main")

val NamedDomainObjectContainer<KotlinSourceSet>.contentTest: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionScripts.CONTENT}Test")

val NamedDomainObjectContainer<KotlinSourceSet>.popupTest: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionScripts.POPUP}Test")

val NamedDomainObjectContainer<KotlinSourceSet>.serviceWorkerTest: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionScripts.SERVICE_WORKER}Test")

val NamedDomainObjectContainer<KotlinSourceSet>.optionsTest: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionScripts.OPTIONS}Test")
