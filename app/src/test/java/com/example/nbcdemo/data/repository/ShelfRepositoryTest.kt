package com.example.nbcdemo.data.repository

import com.example.nbcdemo.data.models.ShelfType
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShelfRepositoryTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeShelfRepository()

    @Test
    fun testGetShelves() = runTest {
        val shelves = repository.getShelves()
        assertNotNull(shelves)
        assertTrue(shelves.isNotEmpty())
        assertEquals(3, shelves.size)
        val firstShelf = shelves.first()
        assertEquals(ShelfType.SHELF, firstShelf.type)

        val firstShelfItems = firstShelf.items
        assertEquals(ShelfType.LIVE, firstShelfItems.first().type)
        assertEquals(ShelfType.EPISODE, firstShelfItems[1].type)
    }
}