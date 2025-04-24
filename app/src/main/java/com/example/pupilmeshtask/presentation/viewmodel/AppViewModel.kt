package com.example.pupilmeshtask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pupilmeshtask.doman_layer.use_case.GetMangaListUseCase
import com.example.pupilmeshtask.doman_layer.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val  mangaUseCase : GetMangaListUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase.loginOrRegister(email, password)
            _loginResult.value = result
        }
    }

    val mangaList = mangaUseCase().cachedIn(viewModelScope)


}