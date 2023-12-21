package com.octocavern.project

import com.octocavern.project.model.Project

data class ProjectsUIState(
    val isLoading: Boolean = true,
    val projects: List<Project> = emptyList(),
)