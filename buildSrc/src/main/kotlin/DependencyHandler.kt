import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.add

/**
 * Put in this file all the ways the libraries configurations can be added in the [DependencyHandler].
 */

fun DependencyHandler.implementation(dependency: Implementable) {
    add("implementation", dependency.asString())
}
