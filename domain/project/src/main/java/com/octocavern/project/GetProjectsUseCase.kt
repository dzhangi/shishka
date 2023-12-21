package com.octocavern.project

import com.octocavern.data.repository.ProjectRepository
import com.octocavern.project.model.Project
import com.octocavern.project.model.toDomainModel
import javax.inject.Inject

class GetProjectsUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(): List<Project> {
        return projectRepository.getProjects().map { it.toDomainModel() }
    }
}