package com.example.pupilmeshtask.doman_layer

import androidx.paging.PagingData
import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity
import com.example.pupilmeshtask.data_layer.room_db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun insertUser(user: UserEntity)
    suspend fun getUser(email: String): UserEntity?
    suspend fun saveLoggedInUser(email: String)
    fun getMangaRepo(): Flow<PagingData<MangaEntity>>

}