package com.example.taskn21.di

import com.example.taskn21.data.local.dao.ClothesDao
import com.example.taskn21.data.remote.common.HandleResponse
import com.example.taskn21.data.remote.service.ClothesApiService
import com.example.taskn21.data.repository.local.LocalClothesRepositoryImpl
import com.example.taskn21.data.repository.remote.RemoteClothesRepositoryImpl
import com.example.taskn21.domain.repository.local.LocalClothesRepository
import com.example.taskn21.domain.repository.remote.RemoteClothesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClothesRepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteClothesRepository(clothesApiService: ClothesApiService, handleResponse: HandleResponse): RemoteClothesRepository =
        RemoteClothesRepositoryImpl(
            clothesApiService = clothesApiService,
            handleResponse = handleResponse,
        )

    @Singleton
    @Provides
    fun provideLocalClothesRepository(clothesDao: ClothesDao): LocalClothesRepository = LocalClothesRepositoryImpl(clothesDao = clothesDao)
}