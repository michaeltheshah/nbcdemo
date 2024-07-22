package com.example.nbcdemo.di

import android.content.Context
import com.example.nbcdemo.data.repository.ShelfRepository
import com.example.nbcdemo.data.repository.ShelfService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideShelfRepository(
        @ApplicationContext context: Context,
        json: Json,
        retrofit: Retrofit
    ): ShelfService = ShelfRepository(context, json, retrofit)
}