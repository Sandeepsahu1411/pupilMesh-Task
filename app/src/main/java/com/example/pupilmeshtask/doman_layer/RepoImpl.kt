package com.example.pupilmeshtask.doman_layer

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pupilmeshtask.data_layer.room_db.database.UserPreferenceManager
import com.example.pupilmeshtask.data_layer.api.ApiService
import com.example.pupilmeshtask.data_layer.room_db.dao.UserDao
import com.example.pupilmeshtask.data_layer.room_db.database.AppDatabase
import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity
import com.example.pupilmeshtask.data_layer.room_db.entity.UserEntity
import com.example.pupilmeshtask.presentation.components.MangaRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val userDao: UserDao,
    private val preferences: UserPreferenceManager,
    private val api: ApiService,
    private val db: AppDatabase

) : Repo {
    override suspend fun getUser(email: String): UserEntity? = userDao.getUserByEmail(email)

    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun saveLoggedInUser(email: String) {
        preferences.saveUserEmail(email)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getMangaRepo(): Flow<PagingData<MangaEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = MangaRemoteMediator(api, db),
            pagingSourceFactory = { db.mangaDao().getAllManga() }
        ).flow
    }

}