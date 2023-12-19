package com.octocavern.project.model

import com.octocavern.data.model.ProjectDto

data class Project(
    val id: Int,
    val name: String,
    val description: String,
)

fun ProjectDto.toDomainModel() = Project(
    id = id,
    name = name,
    description = description,
)
