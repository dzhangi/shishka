package com.octocavern.data.remote

import com.octocavern.data.model.ProjectDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectApi {

    @GET("projects")
    suspend fun getProjects(
        @Query("member") memberId: Long = 609457,
    ): Response<List<ProjectDto>>
}