package com.example.nbcdemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbcdemo.data.models.Shelf
import com.example.nbcdemo.data.repository.ShelfRepository
import com.example.nbcdemo.data.repository.ShelfService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ShelfService
) : ViewModel() {

    private val _shelves = MutableStateFlow<List<Shelf>>(emptyList())
    val shelves: StateFlow<List<Shelf>> = _shelves.asStateFlow()

    init {
        loadShelves()
    }

    private fun loadShelves() {
        viewModelScope.launch {
            _shelves.value += repository.getShelves()
        }
    }
}