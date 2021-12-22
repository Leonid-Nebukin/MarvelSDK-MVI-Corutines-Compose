import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.marvelcompose"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_URL", "\"https://gateway.marvel.com\"")


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val publicKey: String = gradleLocalProperties(rootDir).getProperty("public_key")
        val privateKey: String = gradleLocalProperties(rootDir).getProperty("private_key")

        buildConfigField("String", "PUBLIC_KEY", "\"$publicKey\"")
        buildConfigField("String", "PRIVATE_KEY", "\"$privateKey\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependicies.Compose.composeVersion
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(Dependicies.Android.core)
    implementation(Dependicies.Android.appCompat)
    implementation(Dependicies.Android.material)

    //Compose
    implementation(Dependicies.Compose.ui)
    implementation(Dependicies.Compose.material)
    implementation(Dependicies.Compose.preview)
    implementation(Dependicies.Compose.activity)
    implementation(Dependicies.Compose.coil)
    implementation(Dependicies.Compose.paging)
    debugImplementation(Dependicies.Compose.tooling)

    //Dagger2
    implementation(Dependicies.DependencyInjection.dagger)
    implementation(Dependicies.DependencyInjection.daggerAndroid)
    kapt(Dependicies.DependencyInjection.daggerProcessor)
    kapt(Dependicies.DependencyInjection.daggerCompiler)

    //Retrofit
    implementation(Dependicies.Network.retrofit)
    implementation(Dependicies.Network.retrofitConverter)

    //Okhttp
    implementation(Dependicies.Network.okHttp)
    debugImplementation(Dependicies.Network.okHttpLogging)

    //Moshi
    implementation(Dependicies.Network.moshiKotlin)
    implementation(Dependicies.Network.moshiCodeGen)
    implementation(Dependicies.Network.moshiAdapters)

    //Coroutines
    implementation(Dependicies.Multithreading.coroutines)
    implementation(Dependicies.Kotlin.kotlinReflect)

    testImplementation(Dependicies.Tests.junit)
    androidTestImplementation(Dependicies.Tests.junitExt)
    androidTestImplementation(Dependicies.Tests.espresso)
    androidTestImplementation(Dependicies.Compose.junitTest)
}