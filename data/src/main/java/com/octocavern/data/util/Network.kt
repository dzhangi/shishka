package com.octocavern.data.util

import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.extractErrorMessage(field: String): String =
    JSONObject(this.errorBody()?.string().toString())
        .getString(field)

fun String.extractJsonField(field: String): String? {
    val str = runCatching { JSONObject(this).getString(field) }

    return if (str.isSuccess) {
        str.getOrNull()
    } else {
        str.exceptionOrNull()?.printStackTrace()
        null
    }
}
