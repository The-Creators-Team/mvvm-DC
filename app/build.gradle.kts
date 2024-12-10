plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.creators.mvvm_week4"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.creators.mvvm_week4"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
    /*Solo necesario para ejecutarlo por primera vez error con
    allprojects {
        tasks.matching { it.name.contains("kapt") }.configureEach {
            enabled = false
        }
    }
    */
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime)



    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    // Okhttp
    // define a BOM and its version
    implementation(platform(libs.okhttp.bom))
    // define any required OkHttp artifacts without version
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    // Gson
    implementation(libs.gson)
    // Glide
    implementation(libs.glide)
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}