package com.example.pupilmeshtask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pupilmeshtask.doman_layer.MangaRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MangaViewModel @Inject constructor(private val repository: MangaRepository) : ViewModel() {

    val mangaList = repository.getMangaPager().cachedIn(viewModelScope)

}
