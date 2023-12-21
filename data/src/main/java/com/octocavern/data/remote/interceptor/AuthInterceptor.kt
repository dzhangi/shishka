package com.octocavern.data.remote.interceptor

import android.util.Log
import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.util.LogTags.AUTH_INTERCEPTOR
import com.octocavern.data.util.URL.AUTH_ENDPOINT
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val prefs: ShishkaPrefs,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        return if (originalRequest.url.toString().contains(AUTH_ENDPOINT)) {
            chain.proceed(originalRequest)
        } else {
            Log.i(AUTH_INTERCEPTOR, "Add auth token to request: ${originalRequest.url}")
            val requestWithToken = originalRequest
                .newBuilder()
                .addHeader("Authorization", "Bearer ${prefs.getToken()}")
                .build()
            chain.proceed(requestWithToken)
        }
    }
}