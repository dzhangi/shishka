package com.octocavern.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octocavern.auth.FetchDataUseCase
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
            _state.update { state -> state.toLoading() }
            runCatching { fetchDataUseCase() }
                .onFailure {
                    _state.update { state -> state.toError() }
                }
                .onSuccess {
                    _state.update { state -> state.toSuccess() }
                }
        }
    }
}