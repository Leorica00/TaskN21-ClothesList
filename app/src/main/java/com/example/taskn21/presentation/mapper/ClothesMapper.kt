package com.example.taskn21.presentation.mapper

import com.example.taskn21.domain.model.GetClothes
import com.example.taskn21.presentation.model.Clothes

fun GetClothes.toPresentation() = Clothes(id, cover, price, title, favorite)

fun Clothes.toDomain() = GetClothes(id, cover, price, title, favorite)