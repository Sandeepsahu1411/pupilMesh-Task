package com.example.pupilmeshtask.data_layer.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pupilmeshtask.data_layer.room_db.dao.MangaDao
import com.example.pupilmeshtask.data_layer.room_db.dao.UserDao
import com.example.pupilmeshtask.data_layer.room_db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mangaDao(): MangaDao
}
