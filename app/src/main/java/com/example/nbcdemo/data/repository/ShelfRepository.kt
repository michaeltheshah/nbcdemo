package com.example.nbcdemo.data.repository

import android.content.Context
import com.example.nbcdemo.data.models.Shelf
import com.example.nbcdemo.data.models.ShelvesResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import javax.inject.Inject

class ShelfRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val json: Json,
    retrofit: Retrofit,
): ShelfService {
    val service = retrofit.create(ShelfService::class.java)

    override suspend fun getShelves(): List<Shelf> {
        return withContext(Dispatchers.IO) {
            val jsonString = context.assets.open("homepage.json").bufferedReader().use { it.readText() }
            val shelvesData = json.decodeFromString<ShelvesResponse>(jsonString)
            shelvesData.shelves ?: emptyList()
        }
    }

    suspend fun getMoreShelves(): List<Shelf> {
        return withContext(Dispatchers.IO) {
            val jsonString = context.assets.open("homepage_more_shelves.json").bufferedReader().use { it.readText() }
            val shelvesData = json.decodeFromString<ShelvesResponse>(jsonString)
            shelvesData.shelves ?: emptyList()
        }
    }
}