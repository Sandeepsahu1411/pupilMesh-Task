package com.example.pupilmeshtask.doman_layer.use_case

import com.example.pupilmeshtask.ResultState
import com.example.pupilmeshtask.data_layer.api.response.MangaData
import com.example.pupilmeshtask.doman_layer.Repo

class GetMangaListUseCase(private val repository: Repo) {
    suspend operator fun invoke(): ResultState<List<MangaData>> {
        return repository.fetchMangaList()
    }
}
