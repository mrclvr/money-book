plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.lvrmrc.moneybook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lvrmrc.moneybook"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation("androidx.compose.ui:ui-tooling-preview-android:1.5.4")
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    val nav_version = "2.7.5"




    implementation(composeBom)
//    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
//    implementation("androidx.core:core-ktx:1.9.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.8.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
//    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    implementation("androidx.compose.material:material-icons-core")
//    // Optional - Add full set of material icons
    implementation("androidx.compose.material:material-icons-extended")
//    // Optional - Add window size utils
//    implementation("androidx.compose.material3:material3-window-size-class")
//
//    // Optional - Integration with activities
//    implementation("androidx.activity:activity-compose:1.7.2")
//    // Optional - Integration with ViewModels
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
//    // Optional - Integration with LiveData
//    implementation("androidx.compose.runtime:runtime-livedata")
//    // Optional - Integration with RxJava
//    implementation("androidx.compose.runtime:runtime-rxjava2")

}