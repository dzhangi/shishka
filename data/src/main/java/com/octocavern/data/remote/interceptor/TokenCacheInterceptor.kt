package com.octocavern.data.remote.interceptor

import android.util.Log
import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.util.Constants.Companion.AUTH_ENDPOINT
import com.octocavern.data.util.Constants.Companion.AUTH_TOKEN
import com.octocavern.data.util.Constants.Companion.REFRESH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class TokenCacheInterceptor(private val prefs: ShishkaPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!request.url.toString().endsWith(AUTH_ENDPOINT)) {
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
                            .onSuccess { prefs.saveRefreshToken(it) }
                            .onFailure { it.printStackTrace() }
                        runCatching { jsonObject.getString(AUTH_TOKEN) }
                            .onSuccess { prefs.saveToken(it) }
                            .onFailure { it.printStackTrace() }
                    }
                }
        }
        return response
    }
}
