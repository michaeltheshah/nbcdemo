package com.example.nbcdemo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private val json = Json {
        ignoreUnknownKeys = true
        allowStructuredMapKeys = true
        encodeDefaults = false
        explicitNulls = false
        isLenient = true
    }

    @Provides
    @Singleton
    fun providesJson(): Json {
        return json
    }

    // TODO: Ideally, there would be dependency injection for the Retrofit object
    // that handles network requests. This was left here for a potential future implementation
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder()
                chain.proceed(request.build())
            }
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.nbc.com")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}