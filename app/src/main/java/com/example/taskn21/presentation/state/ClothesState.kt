package com.example.taskn21.presentation.state

import com.example.taskn21.presentation.model.Clothes

data class ClothesState(
    val isLoading: Boolean = false,
    val clothes: List<Clothes>? = null,
    val errorMessage: String? = null
)