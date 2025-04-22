package com.example.pupilmeshtask.doman_layer

import com.example.pupilmeshtask.UserPreferenceManager
import com.example.pupilmeshtask.data_layer.api.ApiService
import com.example.pupilmeshtask.data_layer.room_db.dao.UserDao
import com.example.pupilmeshtask.data_layer.room_db.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val userDao: UserDao,
    private val preferences: UserPreferenceManager,
    private val api: ApiService

) : Repo {
    override suspend fun getUser(email: String): UserEntity? = userDao.getUserByEmail(email)

    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun saveLoggedInUser(email: String) {
        preferences.saveUserEmail(email)
    }





}