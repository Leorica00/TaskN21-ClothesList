package com.example.taskn21.data.local.mapper

import com.example.taskn21.data.local.entity.ClothesEntity
import com.example.taskn21.domain.model.GetClothes

fun ClothesEntity.toDomain() = GetClothes(id, cover, price, title, favourite)

fun GetClothes.toData() = ClothesEntity(id, cover, price, title, favorite)