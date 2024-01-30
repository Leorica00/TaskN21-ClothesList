package com.example.taskn21.domain.repository.remote

import com.example.taskn21.data.remote.common.Resource
import com.example.taskn21.domain.model.GetClothes
import kotlinx.coroutines.flow.Flow

interface RemoteClothesRepository {
    suspend fun getClothes(): Flow<Resource<List<GetClothes>>>
}