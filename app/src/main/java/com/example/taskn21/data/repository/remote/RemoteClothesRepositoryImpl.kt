package com.example.taskn21.data.repository.remote

import com.example.taskn21.data.remote.common.HandleResponse
import com.example.taskn21.data.remote.common.Resource
import com.example.taskn21.data.remote.mapper.base.asResource
import com.example.taskn21.data.remote.mapper.toDomain
import com.example.taskn21.data.remote.service.ClothesApiService
import com.example.taskn21.domain.model.GetClothes
import com.example.taskn21.domain.repository.remote.RemoteClothesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteClothesRepositoryImpl @Inject constructor(
    private val clothesApiService: ClothesApiService,
    private val handleResponse: HandleResponse
) : RemoteClothesRepository {

    override suspend fun getClothes(): Flow<Resource<List<GetClothes>>> =
        handleResponse.safeApiCall {
            clothesApiService.getClothes()
        }.asResource { clothes ->
            clothes.map { it.toDomain() }
        }
}