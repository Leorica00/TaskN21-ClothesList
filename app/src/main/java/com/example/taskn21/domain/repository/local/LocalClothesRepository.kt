package com.example.taskn21.domain.repository.local

import com.example.taskn21.domain.model.GetClothes
import kotlinx.coroutines.flow.Flow

interface LocalClothesRepository {
    fun getClothes(): Flow<List<GetClothes>>
    suspend fun addClothes(clothes: List<GetClothes>)
}