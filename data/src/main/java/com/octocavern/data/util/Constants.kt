package com.octocavern.data.util


object URL {
    const val BASE_URL = "https://api.taiga.io/api/v1/"
    const val AUTH_TOKEN = "auth_token"
    const val REFRESH_TOKEN = "refresh"
    const val AUTH_ENDPOINT = "auth"
    const val REFRESH_ENDPOINT = "auth/refresh"
}

object MISC {
    const val PEEK_BODY_LIMIT = 1024 * 1024
}

object LogTags {
    const val AUTH_INTERCEPTOR = "AUTH_INTERCEPTOR"
    const val TOKEN_CACHE_INTERCEPTOR = "TOKEN_CACHE_INTERCEPTOR"
    const val TOKEN_REFRESH_INTERCEPTOR = "TOKEN_REFRESH_INTERCEPTOR"
}