package net.subroh0508.damaskus.gradle.plugin.internal

import org.gradle.api.DomainObjectCollection
import org.gradle.kotlin.dsl.withType

// https://github.com/gradle/gradle/issues/9832
internal inline fun <reified S : Any> DomainObjectCollection<in S>.configureEach(
    noinline action: S.() -> Unit,
) {
    withType().configureEach(action)
}

