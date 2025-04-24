package com.example.pupilmeshtask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pupilmeshtask.doman_layer.use_case.GetMangaListUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MangaViewModel @Inject constructor(private val useCase: GetMangaListUseCase) : ViewModel() {

    val mangaList = useCase().cachedIn(viewModelScope)

}
