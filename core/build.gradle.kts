import Dependencies.AndroidX
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Network
import Dependencies.View
import ProjectLib.libCountrySearch

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    daggerHilt
}

android {
    compileSdkVersion(Config.Version.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Config.Version.minSdkVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }
}

dependencies {

    implementation(project(libCountrySearch))

    implementation(AndroidX.lifeCycleCommon)
    implementation(View.appCompat)
    implementation(View.fragment)
    implementation(DI.daggerHiltAndroid)
    implementation(Network.moshi)
    implementation(Coroutines.core)
    implementation("io.coil-kt:coil:1.1.1")
    implementation("io.coil-kt:coil-svg:1.1.1")


    kapt(DI.AnnotationProcessor.daggerHilt)
}
