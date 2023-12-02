package com.octocavern.data.remote.interceptor

import android.util.Log
import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.util.LogTags.TOKEN_CACHE_INTERCEPTOR
import com.octocavern.data.util.MISC
import com.octocavern.data.util.URL.AUTH_ENDPOINT
import com.octocavern.data.util.URL.AUTH_TOKEN
import com.octocavern.data.util.URL.REFRESH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class TokenCacheInterceptor(private val prefs: ShishkaPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return when {
            !request.url.toString().endsWith(AUTH_ENDPOINT) -> {
                Log.i(
                    TOKEN_CACHE_INTERCEPTOR,
                    "Skip not auth request: request ${request.url} response ${response.code}"
                )
                response
            }

            response.isSuccessful && response.code == 200 -> {
                Log.i(
                    TOKEN_CACHE_INTERCEPTOR,
                    "Start caching processing: request ${request.url} response ${response.code}"
                )
                val bodyStr = response
                    .peekBody(MISC.PEEK_BODY_LIMIT.toLong())
                    .string()
                runCatching { JSONObject(bodyStr) }
                    .onSuccess { jsonObject ->
                        runCatching { jsonObject.getString(REFRESH_TOKEN) }
                            .onSuccess { prefs.saveRefreshToken(it) }
                            .onFailure { it.printStackTrace() }
                        runCatching { jsonObject.getString(AUTH_TOKEN) }
                            .onSuccess { prefs.saveToken(it) }
                            .onFailure { it.printStackTrace() }
                    }.onFailure { it.printStackTrace() }
                response
            }

            else -> {
                Log.i(
                    TOKEN_CACHE_INTERCEPTOR,
                    "Other scenario: request ${request.url} response ${response.code}"
                )
                response
            }
        }
    }
}
