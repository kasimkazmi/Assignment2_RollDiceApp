
plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.assignment2_rolldiceapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.assignment2_rolldiceapp"
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
}

dependencies {
   // added
    implementation("androidx.room:room-runtime:2.3.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation ("com.google.android.material:material:1.3.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation ("androidx.room:room-runtime:2.3.0")

    annotationProcessor ("androidx.room:room-compiler:2.3.0")
//
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor(libs.room.compiler)
}