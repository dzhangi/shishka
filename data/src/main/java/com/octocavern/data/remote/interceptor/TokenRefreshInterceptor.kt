package com.octocavern.data.remote.interceptor

import com.google.gson.Gson
import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.model.RefreshTokenRequest
import com.octocavern.data.util.Constants.Companion.AUTH_TOKEN
import com.octocavern.data.util.Constants.Companion.BASE_URL
import com.octocavern.data.util.Constants.Companion.REFRESH_TOKEN
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

        if (localRefreshToken.isNullOrEmpty()) return response

        if (response.code == 401) {
            val refreshRequestBodyStr = gson.toJson(RefreshTokenRequest(prefs.getRefreshToken()!!))
            val refreshRequest = Request.Builder()
                .url("${BASE_URL}auth/refresh")
                .post(refreshRequestBodyStr.toRequestBody("application/json".toMediaType()))
                .build()
            val refreshResponse = chain.proceed(refreshRequest)

            val refreshBodyStr = refreshResponse
                .peekBody(Long.MAX_VALUE)
                .string()

            if (refreshBodyStr.isNotEmpty()) {
                val jsonObject = JSONObject(refreshBodyStr)
                val refreshToken = jsonObject.getString(REFRESH_TOKEN)
                val authToken = jsonObject.getString(AUTH_TOKEN)
                val retryWithTokenRequest = originalRequest
                    .newBuilder()
                    .header("Authorization", "Bearer $authToken")
                    .build()
                response = chain.proceed(retryWithTokenRequest)

                prefs.saveToken(authToken)
                prefs.saveRefreshToken(refreshToken)
            }
        }
        return response
    }
}