package com.example.pupilmeshtask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import coil.util.CoilUtils.result
import com.example.pupilmeshtask.ResultState
import com.example.pupilmeshtask.data_layer.api.response.MangaData
import com.example.pupilmeshtask.doman_layer.use_case.GetMangaListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MangaViewModel @Inject constructor(private val useCase: GetMangaListUseCase) : ViewModel() {

    private val _mangaState = MutableStateFlow<ResultState<List<MangaData>>>(ResultState.Loading)
    val mangaState: StateFlow<ResultState<List<MangaData>>> = _mangaState

//
//    init {
//        fetchManga()
//    }
fun fetchManga() {
    viewModelScope.launch {
        Log.d("MangaViewModel", "Fetching manga started")
        _mangaState.value = ResultState.Loading
        val result = useCase.invoke()
        Log.d("MangaViewModel", "Result received: $result")
        _mangaState.value = when (result) {
            is ResultState.Success -> {
                Log.d("MangaViewModel", "Success with data: ${result.data.size}")
                ResultState.Success(result.data)
            }

            is ResultState.Error -> {
                Log.d("MangaViewModel", "Error: ${result.message}")
                ResultState.Error(result.message)
            }

            else -> ResultState.Error("Unknown error")
        }
    }
}

}
