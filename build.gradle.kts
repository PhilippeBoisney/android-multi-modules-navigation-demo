// Top-level build file where you can add configuration options common to all sub-projects/modules.

import org.jlleitschuh.gradle.ktlint.BaseKtlintCheckTask
import org.jlleitschuh.gradle.ktlint.KtlintFormatTask
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    ktlint
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

ktlint {
    version.set("0.36.0")
    android.set(true)
    filter {
        exclude("**/build/**")
        exclude("**/resources/**")
        exclude("**/generated/**")
    }
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.HTML)
    }
}

tasks.withType<KtlintFormatTask> {
    setSource(files(rootDir))
}

tasks.withType<BaseKtlintCheckTask> {
    setSource(files(rootDir))
}
