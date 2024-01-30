package com.example.taskn21.domain.use_case

import com.example.taskn21.data.remote.common.Resource
import com.example.taskn21.data.remote.mapper.base.asResource
import com.example.taskn21.domain.repository.remote.RemoteClothesRepository
import com.example.taskn21.presentation.mapper.toPresentation
import com.example.taskn21.presentation.model.Clothes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClothesFromApiUseCase @Inject constructor(private val remoteClothesRepository: RemoteClothesRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Clothes>>> {
        return remoteClothesRepository.getClothes()
            .asResource { clothes -> clothes.map { it.toPresentation() } }
    }
}
