package com.example.pupilmeshtask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pupilmeshtask.doman_layer.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(

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

    fun checkLoggedInUser(onLoggedIn: (String) -> Unit) {
        viewModelScope.launch {
            loginUseCase.loggedInUserEmail.collect { email ->
                email?.let { onLoggedIn(it) }
            }
        }
    }
}