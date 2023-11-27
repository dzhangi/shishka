package com.octocavern.login.ui

data class LoginUIState(
    val isLoading: Boolean = false,
) {
    fun toSuccess() = copy(
        isLoading = false,
    )

    fun toLoading() = copy(
        isLoading = true,
    )

    fun toError() = copy(
        isLoading = false,
    )
}