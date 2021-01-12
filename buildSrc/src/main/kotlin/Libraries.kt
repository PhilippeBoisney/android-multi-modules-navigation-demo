/**
 * Lists all the external libraries used by the project.
 */
object Libraries {

    object AndroidX {
        const val activityKtx = "androidx.activity:activity-ktx:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0-alpha02"
        const val material = "com.google.android.material:material:1.1.0"
        const val core = "androidx.core:core-ktx:1.2.0"
    }

    object Kotlin : LibraryGroup(groupName = "org.jetbrains.kotlin", version = "1.4.21") {
        object Coroutines : LibraryGroup(
            groupName = "org.jetbrains.kotlinx",
            version = "1.4.2"
        ), Implementable, Library {
            override val name = "kotlinx-coroutines-android"
        }
    }

    object Lifecycle : LibraryGroup(groupName = "androidx.lifecycle", version = "2.2.0") {
        object ViewModel : LibraryGroupChild(group = Lifecycle, name = "lifecycle-viewmodel-ktx"),
            Implementable
    }

    object Koin : LibraryGroup(groupName = "org.koin", version = "2.2.1"), Implementable, Library {
        override val name: String = "koin-android"
        object ViewModel : LibraryGroupChild(group = Koin, name = "koin-androidx-viewmodel"),
            Implementable
        object Test : LibraryGroupChild(group = Koin, name = "koin-test"), TestImplementable
    }

    const val inline_activity_result = "com.github.florent37:inline-activity-result-kotlin:1.0.4"
}
