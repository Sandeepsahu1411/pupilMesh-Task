package com.example.pupilmeshtask.doman_layer

import com.example.pupilmeshtask.ResultState
import com.example.pupilmeshtask.UserPreferenceManager
import com.example.pupilmeshtask.data_layer.api.API_KEY
import com.example.pupilmeshtask.data_layer.api.ApiProvider
import com.example.pupilmeshtask.data_layer.api.ApiService
import com.example.pupilmeshtask.data_layer.api.response.MangaData
import com.example.pupilmeshtask.data_layer.api.response.MangaResponse
import com.example.pupilmeshtask.data_layer.room_db.dao.UserDao
import com.example.pupilmeshtask.data_layer.room_db.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
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

    override val loggedInUserEmail: Flow<String?> = preferences.userEmail

    override suspend fun fetchMangaList(): ResultState<List<MangaData>> {

        val response = api.fetchManga()
        return if (response.isSuccessful) {
            val mangaResponse = response.body()
            if (mangaResponse != null) {
                ResultState.Success(mangaResponse.data)
            } else {
                ResultState.Error("Manga data is null")
            }
        } else {
            ResultState.Error("Failed to fetch manga data")
        }

    }



}