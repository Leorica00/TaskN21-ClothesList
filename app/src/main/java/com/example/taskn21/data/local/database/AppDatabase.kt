package com.example.taskn21.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskn21.data.local.dao.ClothesDao
import com.example.taskn21.data.local.entity.ClothesEntity

@Database(entities = [ClothesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clothesDao(): ClothesDao
}