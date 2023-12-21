package com.octocavern.auth.ui

/**
 * State for login screen
 * @param isLoading loading status
 * @param isLoginError validity of login
 * @param isPasswordError validity of password
 * @param loginErrorMessage login error cause
 * @param passwordErrorMessage password error cause
 * @param isAuthError error from http response
 * @param authErrorMessage response error cause
 */
data class LoginUIState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val isLoginError: Boolean = false,
    val isPasswordError: Boolean = false,
    val loginErrorMessage: String = "",
    val passwordErrorMessage: String = "",
    val isAuthError: Boolean = false,
    val authErrorMessage: String = "",
) {
    fun toInit() = copy(
        isSuccess = false,
        isLoading = false,
        isLoginError = false,
        isPasswordError = false,
        loginErrorMessage = "",
        passwordErrorMessage = "",
        isAuthError = false,
        authErrorMessage = "",
    )

    fun toSuccess() = copy(
        isSuccess = true,
        isLoading = false,
        isLoginError = false,
        isPasswordError = false,
    )

    fun toLoading() = copy(
        isLoading = true,
        isLoginError = false,
        isPasswordError = false,
    )

    fun toLoginError(message: String) = copy(
        isLoading = false,
        isLoginError = true,
        loginErrorMessage = message,
    )

    fun toPasswordError(message: String) = copy(
        isLoading = false,
        isPasswordError = true,
        passwordErrorMessage = message,
    )

    fun toError(message: String) = copy(
        isLoading = false,
        isLoginError = true,
        isPasswordError = true,
        isAuthError = true,
        authErrorMessage = message,
        loginErrorMessage = message,
        passwordErrorMessage = message
    )
}