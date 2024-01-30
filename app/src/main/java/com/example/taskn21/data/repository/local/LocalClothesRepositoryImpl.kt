package com.example.taskn21.data.repository.local

import com.example.taskn21.data.local.dao.ClothesDao
import com.example.taskn21.data.local.mapper.toDomain
import com.example.taskn21.data.local.mapper.toEntity
import com.example.taskn21.domain.model.GetClothes
import com.example.taskn21.domain.repository.local.LocalClothesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalClothesRepositoryImpl @Inject constructor(private val clothesDao: ClothesDao) :
    LocalClothesRepository {
    override fun getClothes(): Flow<List<GetClothes>> {
        return clothesDao.getAll().map { clothes -> clothes.map { it.toDomain() } }
    }

    override suspend fun addClothes(clothes: List<GetClothes>) {
        clothesDao.insertClothes(clothes.map { it.toEntity() })
    }

}