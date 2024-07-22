package com.example.nbcdemo.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShelvesResponse(
    val page: String?,
    val shelves: List<Shelf>?
)

@Serializable
data class Shelf(
    val items: List<Item>,
    val title: String,
    val type: ShelfType
)

@Serializable
data class Item(
    val image: String,
    val labelBadge: String?,
    val subtitle: String?,
    val tagline: String?,
    val title: String,
    val type: ShelfType
)

@Serializable
enum class ShelfType {
    @SerialName("Show")
    SHOW,
    @SerialName("Episode")
    EPISODE,
    @SerialName("Live")
    LIVE,
    @SerialName("Shelf")
    SHELF,
}