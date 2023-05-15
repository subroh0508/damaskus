package net.subroh0508.damaskus.gradle.plugin

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.NamedDomainObjectProvider
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

object ChromeExtensionTargets {
    const val CONTENT = "content"
    const val POPUP = "popup"
}

val NamedDomainObjectContainer<KotlinSourceSet>.contentMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionTargets.CONTENT}Main")

val NamedDomainObjectContainer<KotlinSourceSet>.popupMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionTargets.POPUP}Main")

val NamedDomainObjectContainer<KotlinSourceSet>.contentTest: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionTargets.CONTENT}Test")

val NamedDomainObjectContainer<KotlinSourceSet>.popupTest: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("${ChromeExtensionTargets.POPUP}Test")
