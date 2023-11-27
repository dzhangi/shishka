package com.sandbox.login.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandbox.auth.FetchDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchDataUseCase: FetchDataUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUIState())
    val state = _state.asStateFlow()

    fun fetchRandomJoke() {
        viewModelScope.launch {
            _state.update { state -> state.loadingState() }
            runCatching { fetchDataUseCase() }
                .onFailure {
                    Log.e("TEST", it.message ?: "Error fetching joke", it)
                    _state.update { state -> state.errorState() }
                }
                .onSuccess {
                    _state.update { state -> state.successState(it.value) }
                }
        }
    }
}