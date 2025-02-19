plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.example.myfoods"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myfoods"
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
    dataBinding {
        enable = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //ssp and sdp libraries
    implementation(libs.intuit.sdp)
    implementation(libs.intuit.ssp)
    //retrofits
    implementation(libs.android.retrofit.json)
    implementation(libs.android.retrofit2)
    implementation(libs.android.logging.interceptor)
    //gif library
    implementation(libs.pl.droidsonroids.gif)
    //glide github
    implementation(libs.github.glide)
    //navigation libraries
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    //room database
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    //viewModel
    implementation(libs.androidx.lifecyle.viewmodel)
    //coroutines
    implementation(libs.jetbrains.coroutine.core)
    implementation(libs.jetbrains.coroutine.kotlinx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}