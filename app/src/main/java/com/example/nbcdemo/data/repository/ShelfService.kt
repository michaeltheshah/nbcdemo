package com.example.nbcdemo.data.repository

import com.example.nbcdemo.data.models.Shelf
import retrofit2.Response

interface ShelfService {
    suspend fun getShelves(): List<Shelf>
}