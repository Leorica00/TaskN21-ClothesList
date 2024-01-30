package com.example.taskn21.presentation.event

import com.example.taskn21.presentation.model.Clothes

sealed class ClothesEvent {
    data object AddAllClotheToDataBaseEvent : ClothesEvent()
    data class SelectFavouriteEvent(val item: Clothes) : ClothesEvent()
}