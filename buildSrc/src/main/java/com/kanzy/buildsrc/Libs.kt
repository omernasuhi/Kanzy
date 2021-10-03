package com.kanzy.buildsrc

object Libs {

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
        const val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}"

        const val junit = "junit:junit:${Versions.junit}"
        const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

        const val savedstate = "androidx.savedstate:savedstate-ktx:${Versions.savedstate}"
        const val securityCrypto = "androidx.security:security-crypto:${Versions.securityCrypto}"


        object Room {
            const val ktx = "androidx.room:room-ktx:${Versions.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.room}"
        }

        object Lifecycle {
            const val viewModelKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
            const val livedataKtx =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val commonJava8 =
                "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
            const val process = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
        }

    }

    object Coroutine {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
    }

    object Chucker {
        const val debug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
        const val release = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
    }

    object Moshi {
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val kotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
        const val lazy = "com.serjltt.moshi:moshi-lazy-adapters:${Versions.moshiLazy}"
    }

    object Retrofit2 {
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit2}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    }

    object Okhttp3 {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    }

    const val conscrypt = "org.conscrypt:conscrypt-android:${Versions.conscrypt}"
    const val assent = "com.afollestad.assent:core:${Versions.assent}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val androidDebugDb = "com.amitshekhar.android:debug-db:${Versions.debugDb}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val exoplayer = "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer}"

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Firebase {
        const val analytic = "com.google.firebase:firebase-analytics-ktx:${Versions.fbAnalytic}"
        const val crashlytics =
            "com.google.firebase:firebase-crashlytics-ktx:${Versions.fbCrashlytics}"
        const val messaging = "com.google.firebase:firebase-messaging-ktx:${Versions.fbMessaging}"
        const val config = "com.google.firebase:firebase-config-ktx:${Versions.fbConfig}"
        const val database = "com.google.firebase:firebase-database-ktx:${Versions.fbDatabase}"
    }

}