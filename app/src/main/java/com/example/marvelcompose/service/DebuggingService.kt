package com.example.marvelcompose.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DebuggingService @Inject constructor() {

    fun addDebugInterceptors(builder: OkHttpClient.Builder) {
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }
}