package com.example.pupilmeshtask.presentation.components

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pupilmeshtask.data_layer.api.ApiService
import com.example.pupilmeshtask.data_layer.room_db.database.AppDatabase
import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity

@OptIn(ExperimentalPagingApi::class)
class MangaRemoteMediator(
    private val api: ApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, MangaEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MangaEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(true)
                (state.pages.size + 1)
            }
        }


        return try {
            val response = api.getManga(page = page)
            val mangaList = response.data.map { it.toEntity() }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) db.mangaDao().clearAll()
                db.mangaDao().insertAll(mangaList)
            }
            Log.d("RemoteMediator", "Thumb saved: ${mangaList.firstOrNull()?.title}")


            MediatorResult.Success(endOfPaginationReached = mangaList.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}