plugins {
    androidApp()
    kotlinAndroid()
}

android {
    setAppConfig()
}

dependencies {
    implementation(Libraries.Koin)
    implementation(project(Modules.Home))

    implementation(project(Modules.ActivityFeature))
    implementation(project(Modules.FragmentFeature))

    implementation(project(Modules.Navigation))

    implementation(project(Modules.Design))
    implementation(project(Modules.Core))
}
