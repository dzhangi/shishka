package com.octocavern.project.model

import com.octocavern.data.model.ProjectDto

data class Project(
    val id: Int,
    val slug: String,
    val name: String,
    val description: String,
    val members: String,
    val iconUrl: String,
    val watchers: String,
)

fun ProjectDto.toDomainModel() = Project(
    id = id,
    name = name,
    slug = slug,
    description = description,
    members = members.size.toString(),
    iconUrl = logoSmallUrl ?: "",
    watchers = totalWatchers.toString(),
)
