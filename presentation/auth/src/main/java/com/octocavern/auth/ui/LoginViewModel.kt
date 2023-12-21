package com.octocavern.auth.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.octocavern.auth.SignInUseCase
import com.octocavern.auth.util.AuthError
import com.octocavern.shishka.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val loginUseCase: SignInUseCase,
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(LoginUIState())
    val state = _state.asStateFlow()

    private fun getStringFromRes(id: Int): String {
        return getApplication<Application>().resources.getString(id)
    }

    private fun handleError(e: Throwable) {
        when (e) {
            is AuthError.EmptyLogin -> {
                Log.i("LOGIN_TEST", "login error: $e")
                _state.update { state -> state.toLoginError(getStringFromRes(R.string.empty_login)) }
            }

            is AuthError.EmptyPassword -> {
                Log.i("LOGIN_TEST", "password error: $e")
                _state.update { state -> state.toPasswordError(getStringFromRes(R.string.empty_password)) }
            }

            else -> {
                Log.i("LOGIN_TEST", "auth error: ${e.message} | ${e.cause} | ${e.localizedMessage}")
                _state.update { state ->
                    state.toError(
                        e.message ?: getStringFromRes(R.string.something_went_wrong)
                    )
                }
            }
        }
    }

    fun resetState() {
        _state.update { state -> state.toInit() }
    }

    fun signIn(login: String, pass: String) {
        Log.i("LOGIN_TEST", "login: $login | pass: $pass")
        viewModelScope.launch {
            _state.update { state -> state.toLoading() }
            runCatching { loginUseCase(login, pass) }
                .onSuccess { authDetails ->
                    Log.i(
                        "LOGIN_TEST",
                        "auth details: ${authDetails.id} | ${authDetails.fullName} | ${authDetails.email}"
                    )
                    _state.update { state -> state.toSuccess() }
                }
                .onFailure {
                    handleError(it)
                }
        }
    }

    fun signUp() {
        Log.i("LOGIN_TEST", "sign up was clicked")
    }
}