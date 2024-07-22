package com.example.nbcdemo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nbcdemo.data.models.Item
import com.example.nbcdemo.data.models.Shelf
import com.example.nbcdemo.data.models.ShelfType
import com.example.nbcdemo.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    contentPadding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val shelves by viewModel.shelves.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF0089a8), Color(0xFF500053))
                )
            )
    ) {
        LazyColumn {
            items(shelves) { shelf ->
                ShelfComposable(shelf)
            }
        }
    }
}

@Composable
fun ShelfComposable(shelf: Shelf) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = shelf.title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val items = shelf.items ?: return@LazyRow
            items(items) { item ->
                when (item.type) {
                    ShelfType.EPISODE -> EpisodeItem(item)
                    ShelfType.SHOW -> ShowItem(item)
                    ShelfType.LIVE -> LiveItem(item)
                    ShelfType.SHELF -> {

                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: Item) {
    ItemCard(title = episode.title, thumbnailUrl = episode.image)
}

@Composable
fun ShowItem(show: Item) {
    ItemCard(title = show.title, thumbnailUrl = show.image)
}

@Composable
fun LiveItem(live: Item) {
    ItemCard(title = live.title, thumbnailUrl = live.image, isLive = true)
}

@Composable
fun ItemCard(title: String, thumbnailUrl: String, isLive: Boolean = false) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            AsyncImage(
                model = thumbnailUrl,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(8.dp)
            ) {
                if (isLive) {
                    Text(
                        text = "LIVE",
                        color = Color.Red,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}