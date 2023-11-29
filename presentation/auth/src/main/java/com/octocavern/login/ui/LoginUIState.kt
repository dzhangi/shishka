package com.octocavern.login.ui

data class LoginUIState(
    val isLoading: Boolean = false,
    val isLoginError: Boolean = false,
    val isPasswordError: Boolean = false,
) {
    fun toSuccess() = copy(
        isLoading = false,
        isLoginError = false,
        isPasswordError = false,
    )

    fun toLoading() = copy(
        isLoading = true,
        isLoginError = false,
        isPasswordError = false,
    )

    fun toError() = copy(
        isLoading = false,
    )
}