import org.gradle.api.artifacts.ExternalModuleDependency

interface ConfigurableDependency {
    /**
     * Returns the dependency string formatted as "$group:$name:$version"
     */
    fun asString(): String

    fun configure(dependencyConfiguration: ExternalModuleDependency) {}
}

/**
 * Dependencies should implement [AndroidTestImplementable] when they can be used ONLY for `androidTest` configurations.
 */
interface AndroidTestImplementable : ConfigurableDependency

/**
 * Dependencies should implement [TestImplementable] when they can be used ONLY for `test` configurations.
 */
interface TestImplementable : ConfigurableDependency

/**
 * Dependencies should implement [Implementable] when they can be used with any configurations.
 */
interface Implementable : ConfigurableDependency, TestImplementable, AndroidTestImplementable
