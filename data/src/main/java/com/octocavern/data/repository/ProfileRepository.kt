package com.octocavern.data.repository

import com.octocavern.data.local.ShishkaPrefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val prefs: ShishkaPrefs
) {
    fun isAuthorized(): Boolean {
        return !prefs.getToken().isNullOrEmpty()
    }
}