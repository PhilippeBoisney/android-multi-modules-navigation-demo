plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinAndroidExt()
}

android {
    setLibraryConfig()
}

dependencies {
    implementation(project(Modules.Core))
    implementation(Libraries.Kotlin.Coroutines)
    implementation(Libraries.Lifecycle.ViewModel)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.AndroidX.core)
    implementation(Libraries.Koin)
    implementation(Libraries.inline_activity_result)
}
