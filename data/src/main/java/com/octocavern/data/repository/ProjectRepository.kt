package com.octocavern.data.repository

import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.model.ProjectDto
import com.octocavern.data.remote.TaigaApi
import com.octocavern.data.util.extractErrorMessage
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    private val taigaApi: TaigaApi,
    private val prefs: ShishkaPrefs,
) {
    suspend fun getProjects(): List<ProjectDto> {
        val response = taigaApi.getProjects(memberId = prefs.getUserId())

        if (!response.isSuccessful) throw Exception(response.extractErrorMessage("detail"))

        return response.body()!!
    }
}