object Dependicies {

    object Android {
        const val appCompat = "androidx.appcompat:appcompat:1.4.0"
        const val material = "com.google.android.material:material:1.4.0"
        const val core = "androidx.core:core-ktx:1.7.0"
    }

    object Compose {
        const val composeVersion = "1.0.2"
        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
        const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val activity = "androidx.activity:activity-compose:1.4.0"
        const val junitTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
        const val coil = "io.coil-kt:coil-compose:1.4.0"
        const val paging = "androidx.paging:paging-compose:1.0.0-alpha14"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-rc01"
    }

    object Network {
        private const val retrofitVersion = "2.9.0"
        private const val moshiVersion = "1.12.0"
        private const val okHttpVersion = "3.14.9"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
        const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$moshiVersion"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    const val gradle = "com.android.tools.build:gradle:7.0.4"

    object Multithreading {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1"
    }

    object DependencyInjection {
        private const val daggerVersion = "2.37"

        const val dagger = "com.google.dagger:dagger:${daggerVersion}"
        const val daggerAndroid = "com.google.dagger:dagger-android-support:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        const val daggerProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
    }

    object Kotlin {
        private const val kotlinVersion = "1.5.31"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
    }

    object Tests {
        const val junit = "junit:junit:4.13"
        const val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
        const val junitExt = "androidx.test.ext:junit:1.1.3"
    }
}