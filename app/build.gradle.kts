plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.lvrmrc.moneybook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lvrmrc.moneybook"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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

    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    val navVersion = "2.7.6"
    val roomVersion = "2.6.1"
    val lifecycleVersion = "2.6.2"
    val materialVersion = "1.1.2"
    val composeVersion = "1.5.4"
    val hiltVersion = "2.50"
    val activityVersion = "1.8.2"

    // Compose
    implementation(composeBom)
    implementation("androidx.compose.material3:material3:$materialVersion")
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview-android:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    // Navigation
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("androidx.activity:activity-ktx:$activityVersion")

    // Lifecyle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.21")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}
