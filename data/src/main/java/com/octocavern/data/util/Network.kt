package com.octocavern.data.util

import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.extractErrorMessage(field: String): String =
    JSONObject(this.errorBody()?.string().toString())
        .getString(field)