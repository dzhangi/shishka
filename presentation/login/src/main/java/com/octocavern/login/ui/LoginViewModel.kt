package com.octocavern.login.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octocavern.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchDataUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUIState())
    val state = _state.asStateFlow()

    fun onLogin(login: String, pass: String) {
        viewModelScope.launch {
            _state.update { it.toLoading() }
            delay(2000L)

            _state.update {
                it.copy(
                    isLoading = false,
                    isLoginError = true,
                    isPasswordError = true
                )
            }
        }
        Log.i("LOGIN_TEST", "login: $login | pass: $pass")
    }

    fun onSignUp() {
        Log.i("LOGIN_TEST", "sign up was clicked")
    }
}