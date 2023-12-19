package com.octocavern.auth.ui

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest

object NavRequests {
    val PROJECTS_SCREEN = NavDeepLinkRequest.Builder
        .fromUri("android-app://com.octocavern.app/projects_fragment".toUri())
        .build()
}