import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.ktlint: PluginDependencySpec
    get() = id("org.jlleitschuh.gradle.ktlint").version("9.4.1")

/**
 * Plugin that allows to define a module an "Android app" one
 */
fun PluginDependenciesSpec.androidApp(): PluginDependencySpec =
    id("com.android.application")

/**
 * Plugin that allows to define a module as a "library" one.
 */
fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec =
    id("com.android.library")

/**
 * Android Kotlin module
 */
fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec =
    kotlin("android")

/**
 * Plugin that contains useful Kotlin extensions for Android Development
 */
fun PluginDependenciesSpec.kotlinAndroidExt(): PluginDependencySpec =
    kotlin("android.extensions")
