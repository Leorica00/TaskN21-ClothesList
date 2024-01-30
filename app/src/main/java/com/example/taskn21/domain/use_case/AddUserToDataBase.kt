package com.example.taskn21.domain.use_case

import com.example.taskn21.domain.repository.local.LocalClothesRepository
import com.example.taskn21.presentation.mapper.toDomain
import com.example.taskn21.presentation.model.Clothes
import javax.inject.Inject

class AddUserToDataBase @Inject constructor(private val localClothesRepository: LocalClothesRepository) {
    suspend operator fun invoke(clothes: List<Clothes>){
        localClothesRepository.addClothes(clothes.map { it.toDomain() })
    }
}