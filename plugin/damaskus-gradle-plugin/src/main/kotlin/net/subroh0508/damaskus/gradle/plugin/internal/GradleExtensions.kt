package net.subroh0508.damaskus.gradle.plugin.internal

import org.gradle.api.Action
import org.gradle.api.DomainObjectCollection
import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

// https://github.com/gradle/gradle/issues/9832
internal inline fun <reified S : Any> DomainObjectCollection<in S>.configureEach(
    noinline action: S.() -> Unit,
) {
    withType().configureEach(action)
}

internal inline fun <reified T: Task> TaskContainer.findOrRegister(
    name: String,
    type: Class<T>,
    configurationAction: Action<T>,
) = findByName(name)?.let { named(name) } ?: register(name, type, configurationAction)
