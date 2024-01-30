package com.example.taskn21.data.remote.mapper

import com.example.taskn21.data.remote.model.ClothesDto
import com.example.taskn21.domain.model.GetClothes

fun ClothesDto.toDomain() = GetClothes(id, cover, price, title, favorite)