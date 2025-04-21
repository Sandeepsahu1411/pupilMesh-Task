package com.example.pupilmeshtask.doman_layer

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pupilmeshtask.data_layer.api.ApiService
import com.example.pupilmeshtask.data_layer.room_db.AppDatabase
import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaRepository @Inject constructor(
    private val api: ApiService,
    private val db: AppDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getMangaPager(): Flow<PagingData<MangaEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = MangaRemoteMediator(api, db),
            pagingSourceFactory = { db.mangaDao().getAllManga() }
        ).flow
    }
}
