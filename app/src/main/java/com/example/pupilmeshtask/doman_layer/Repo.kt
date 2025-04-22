package com.example.pupilmeshtask.doman_layer

import com.example.pupilmeshtask.data_layer.room_db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun getUser(email: String): UserEntity?
    suspend fun insertUser(user: UserEntity)
    suspend fun saveLoggedInUser(email: String)


}