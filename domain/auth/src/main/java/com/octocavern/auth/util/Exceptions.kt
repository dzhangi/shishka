package com.octocavern.auth.util

sealed class AuthError : Exception() {
    data object EmptyLogin : AuthError()
    data object EmptyPassword : AuthError()
}