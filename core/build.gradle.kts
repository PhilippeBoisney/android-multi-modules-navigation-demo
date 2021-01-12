plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinAndroidExt()
}

android {
    setLibraryConfig()
}

dependencies {
    implementation(Libraries.AndroidX.activityKtx)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.AndroidX.constraintLayout)
    implementation(Libraries.Koin)
}
