package com.octocavern.data.remote.interceptor

import android.util.Log
import com.google.gson.Gson
import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.model.RefreshTokenRequest
import com.octocavern.data.util.LogTags.TOKEN_REFRESH_INTERCEPTOR
import com.octocavern.data.util.MISC.PEEK_BODY_LIMIT
import com.octocavern.data.util.URL.AUTH_ENDPOINT
import com.octocavern.data.util.URL.AUTH_TOKEN
import com.octocavern.data.util.URL.BASE_URL
import com.octocavern.data.util.URL.REFRESH_ENDPOINT
import com.octocavern.data.util.URL.REFRESH_TOKEN
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject

class TokenRefreshInterceptor(
    private val prefs: ShishkaPrefs,
    private val gson: Gson,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        var response = chain.proceed(originalRequest)
        val localRefreshToken = prefs.getRefreshToken()

        val skipIntercepting = localRefreshToken.isNullOrEmpty()
                || originalRequest.url.toString().contains(AUTH_ENDPOINT)

        return when {
            skipIntercepting -> {
                Log.i(
                    TOKEN_REFRESH_INTERCEPTOR,
                    "Skip auth request: request ${originalRequest.url} response ${response.code}"
                )
                response
            }

            response.code == 401 -> {
                Log.i(
                    TOKEN_REFRESH_INTERCEPTOR,
                    "Start refresh processing: request ${originalRequest.url} response ${response.code}"
                )
                val refreshRequestBodyStr =
                    gson.toJson(RefreshTokenRequest(prefs.getRefreshToken()!!))
                val refreshResponse = makeRefreshRequest(refreshRequestBodyStr, chain)

                val refreshBodyStr = refreshResponse
                    .peekBody(PEEK_BODY_LIMIT.toLong())
                    .string()

                runCatching { JSONObject(refreshBodyStr) }
                    .onSuccess { jsonObject ->
                        runCatching { jsonObject.getString(REFRESH_TOKEN) }
                            .onSuccess { prefs.saveRefreshToken(it) }
                            .onFailure { it.printStackTrace() }
                        runCatching { jsonObject.getString(AUTH_TOKEN) }
                            .onSuccess {
                                response =
                                    retryOriginalRequestWithNewToken(originalRequest, it, chain)
                                prefs.saveToken(it)
                            }
                            .onFailure { it.printStackTrace() }
                    }
                response
            }

            else -> response
        }
    }

    private fun makeRefreshRequest(
        refreshRequestBodyStr: String,
        chain: Interceptor.Chain
    ): Response {
        val refreshRequest = Request.Builder()
            .url(BASE_URL + REFRESH_ENDPOINT)
            .post(refreshRequestBodyStr.toRequestBody("application/json".toMediaType()))
            .build()
        return chain.proceed(refreshRequest)
    }

    private fun retryOriginalRequestWithNewToken(
        originalRequest: Request,
        it: String?,
        chain: Interceptor.Chain
    ): Response {
        val retryWithTokenRequest = originalRequest
            .newBuilder()
            .header("Authorization", "Bearer $it")
            .build()
        return chain.proceed(retryWithTokenRequest)
    }
}