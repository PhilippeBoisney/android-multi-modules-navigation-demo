plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinAndroidExt()
}

android {
    setLibraryConfig()
}

dependencies {
    implementation(project(Modules.Navigation))
    implementation(project(Modules.Core))

    implementation(Libraries.AndroidX.activityKtx)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.AndroidX.constraintLayout)
    implementation(Libraries.Koin)
    implementation(Libraries.Koin.ViewModel)
}
