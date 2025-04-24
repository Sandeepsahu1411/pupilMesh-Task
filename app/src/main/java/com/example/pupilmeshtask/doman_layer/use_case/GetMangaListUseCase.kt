package com.example.pupilmeshtask.doman_layer.use_case

import androidx.paging.PagingData
import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity
import com.example.pupilmeshtask.doman_layer.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMangaListUseCase @Inject constructor(private val repository: Repo) {
     operator fun invoke(): Flow<PagingData<MangaEntity>> {
        return repository.getMangaRepo()
    }
}
