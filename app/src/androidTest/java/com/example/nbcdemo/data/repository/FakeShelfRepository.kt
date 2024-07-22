package com.example.nbcdemo.data.repository

import com.example.nbcdemo.data.models.Shelf
import com.example.nbcdemo.data.models.ShelvesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeShelfRepository @Inject constructor(): ShelfService {
    private val json: Json = Json {
        ignoreUnknownKeys = true
        allowStructuredMapKeys = true
        encodeDefaults = false
        explicitNulls = false
        isLenient = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getShelves(): List<Shelf> {
        return withContext(Dispatchers.IO) {
            val stream = javaClass.getResourceAsStream("/homepage.json") ?: throw IllegalStateException("homepage.json could not be found")
            val shelvesData = json.decodeFromStream<ShelvesResponse>(stream)
            shelvesData.shelves ?: emptyList()
        }
    }
}