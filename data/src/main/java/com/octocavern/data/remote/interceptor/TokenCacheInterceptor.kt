package com.octocavern.data.remote.interceptor

import android.util.Log
import com.octocavern.data.local.ShishkaPrefs
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class TokenCacheInterceptor(private val prefs: ShishkaPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!request.url.toString().endsWith(LOGIN_ENDPOINT)) {
            Log.i("LOGIN_TEST", "Not auth request: ${request.url}")
            return response
        }

        if (response.isSuccessful && response.code == 200) {
            Log.i("LOGIN_TEST", "response: ${response.code}")
            response
                .peekBody(Long.MAX_VALUE)
                .string()
                .let { bodyStr ->
                    if (bodyStr.isNotEmpty()) {
                        val jsonObject = JSONObject(bodyStr)
                        runCatching { jsonObject.getString(REFRESH_TOKEN) }
                            .onSuccess { if (it.isNotEmpty()) prefs.saveRefreshToken(it) }
                            .onFailure { it.printStackTrace() }
                        runCatching { jsonObject.getString(AUTH_TOKEN) }
                            .onSuccess { if (it.isNotEmpty()) prefs.saveToken(it) }
                            .onFailure { it.printStackTrace() }
                    }
                }
        }
        return response
    }

    companion object {
        private const val AUTH_TOKEN = "auth_token"
        private const val REFRESH_TOKEN = "refresh"
        private const val LOGIN_ENDPOINT = "auth"
    }
}
