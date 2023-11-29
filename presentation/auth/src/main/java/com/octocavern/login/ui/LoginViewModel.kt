package com.octocavern.login.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octocavern.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUIState())
    val state = _state.asStateFlow()

    fun onLogin(login: String, pass: String) {
        Log.i("LOGIN_TEST", "login: $login | pass: $pass")
        viewModelScope.launch {
            _state.update { it.toLoading() }
            val authDetails = loginUseCase(login, pass)
            Log.i(
                "LOGIN_TEST",
                "${authDetails.id} | ${authDetails.fullName} | ${authDetails.email}"
            )
            _state.update { it.toSuccess() }
        }
    }

    fun onSignUp() {
        Log.i("LOGIN_TEST", "sign up was clicked")
    }
}