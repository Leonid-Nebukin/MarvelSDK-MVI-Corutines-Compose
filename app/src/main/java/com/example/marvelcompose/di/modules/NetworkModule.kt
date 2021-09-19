package com.example.marvelcompose.di.modules

import com.example.marvelcompose.BuildConfig
import com.example.marvelcompose.data.api.ApiRequest
import com.example.marvelcompose.service.DebuggingService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideOkHttp(
        debuggingService: DebuggingService
    ): OkHttpClient {
        val okHttp = OkHttpClient.Builder()

        debuggingService.addDebugInterceptors(okHttp)

        okHttp.connectTimeout(20, TimeUnit.SECONDS)
        okHttp.readTimeout(20, TimeUnit.SECONDS)
        okHttp.writeTimeout(20, TimeUnit.SECONDS)

        return okHttp.build()
    }

    @Singleton
    @Provides
    internal fun providesMoshi() =
        Moshi.Builder()
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi) : Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.API_URL)
            .build()

    @Singleton
    @Provides
    fun provideApiRequest(retrofit: Retrofit): ApiRequest = retrofit.create(ApiRequest::class.java)
}