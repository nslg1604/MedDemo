plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
//    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}


android {
    namespace = "com.meddemo3"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.meddemo3"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ktor
    implementation ("io.ktor:ktor-client-core:3.0.3") // Основная библиотека Ktor
    implementation ("io.ktor:ktor-client-android:3.0.3") // Android-движок для Ktor
    implementation ("io.ktor:ktor-client-content-negotiation:3.0.3") // Для работы с JSON
    implementation ("io.ktor:ktor-serialization-kotlinx-json:3.0.3") // Сериализация JS
    implementation("io.ktor:ktor-client-cio:3.0.3")

    // Ktor-Moshi для интеграции Moshi с Ktor
    implementation("com.hypercubetools:ktor-moshi:3.0.0")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.moshi:moshi-adapters:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Glide
    // https://github.com/bumptech/glide
//    implementation("com.github.bumptech.glide:glide:4.16.0")
//    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

    // Coil
    implementation ("io.coil-kt:coil:2.2.2")
//    kapt("groupId:artifactId:version")

    // Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
//    ksp("androidx.room:room-compiler:$room_version")

    // If this project only uses Java source, use the Java annotationProcessor
    // No additional plugins are necessary
//    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt ("androidx.room:room-compiler:$room_version") // Для Kotlin

    // optional - Kotlin Extensions and Coroutines support for Room
//    implementation("androidx.room:room-ktx:$room_version")

    // optional - RxJava2 support for Room
//    implementation("androidx.room:room-rxjava2:$room_version")


    // Hilt
    implementation("com.google.dagger:hilt-android:2.55")
    kapt("com.google.dagger:hilt-android-compiler:2.55")
//    hilt {
//        enableAggregatingTask = true
//    }
    implementation("com.google.dagger:hilt-android:2.55")
//    ksp("com.google.dagger:hilt-compiler:2.55")
}