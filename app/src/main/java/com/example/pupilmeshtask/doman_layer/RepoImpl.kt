package com.example.pupilmeshtask.doman_layer

import com.example.pupilmeshtask.UserPreferenceManager
import com.example.pupilmeshtask.data_layer.UserDao
import com.example.pupilmeshtask.data_layer.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val userDao: UserDao,
    private val preferences: UserPreferenceManager

) : Repo {
    override suspend fun getUser(email: String): UserEntity? = userDao.getUserByEmail(email)

    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun saveLoggedInUser(email: String) {
        preferences.saveUserEmail(email)
    }

    override val loggedInUserEmail: Flow<String?> = preferences.userEmail
}