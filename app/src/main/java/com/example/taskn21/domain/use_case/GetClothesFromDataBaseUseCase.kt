package com.example.taskn21.domain.use_case

import com.example.taskn21.domain.repository.local.LocalClothesRepository
import com.example.taskn21.presentation.mapper.toPresentation
import com.example.taskn21.presentation.model.Clothes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetClothesFromDataBaseUseCase @Inject constructor(private val localClothesRepository: LocalClothesRepository) {
    operator fun invoke(): Flow<List<Clothes>> {
        return localClothesRepository.getClothes().map { it.map { it.toPresentation() } }
    }
}