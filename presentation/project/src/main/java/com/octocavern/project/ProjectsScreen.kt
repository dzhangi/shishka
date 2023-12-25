package com.octocavern.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material.icons.outlined.VpnKey
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.octocavern.project.model.Project
import com.octocavern.ui.theme.ShishkaTheme

@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel,
    navController: NavController,
) {
    val uiState by viewModel.state.collectAsState()

    ProjectsScreenContent(
        uiState = uiState,
        onItemClick = { viewModel.onProjectClick(it) }
    )
}

@Composable
fun ProjectsScreenContent(
    uiState: ProjectsUIState,
    onItemClick: (Project) -> Unit,
) {
    ShishkaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(uiState.projects) { project ->
                    ProjectItem(
                        project = project,
                        onClick = onItemClick,
                        isLoading = uiState.isLoading,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectItem(
    isLoading: Boolean,
    project: Project,
    onClick: (Project) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
        onClick = { onClick(project) },
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ShimmerWrapper(
                isLoading = isLoading,
                shimmerWidth = 48.dp,
                shimmerHeight = 48.dp
            ) {
                LetterAvatar(letter = project.name.first(), modifier = Modifier.size(48.dp))
            }

            Column(modifier = Modifier.padding(horizontal = 8.dp)) {

                ShimmerWrapper(isLoading = isLoading, shimmerHeight = 20.dp, shimmerWidth = 160.dp) {
                    Text(
                        text = project.name,
                        fontWeight = FontWeight.Bold
                    )
                }

                ShimmerWrapper(isLoading = isLoading, shimmerHeight = 20.dp, shimmerWidth = 230.dp) {
                    Text(text = project.slug)
                }

                Spacer(modifier = Modifier.size(4.dp))

                ShimmerWrapper(
                    isLoading = isLoading,
                    shimmerHeight = 16.dp,
                    shimmerWidth = 64.dp
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ProjectInfoBadges(icon = Icons.Outlined.Person, count = project.members)
                        Spacer(modifier = Modifier.size(2.dp))
                        ProjectInfoBadges(
                            icon = Icons.Outlined.RemoveRedEye,
                            count = project.watchers
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        if (project.isPrivate) {
                            ProjectInfoBadges(icon = Icons.Outlined.Public)
                        } else {
                            ProjectInfoBadges(icon = Icons.Outlined.VpnKey)
                        }
                    }
                }
            }
        }
    }
    Divider(thickness = 0.5.dp, color = Color.Black)
}

@Composable
fun ProjectInfoBadges(
    icon: ImageVector,
    count: String = "",
    iconDescription: String = "",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            imageVector = icon,
            contentDescription = iconDescription,
        )
        if (count.isNotEmpty()) {
            Text(
                text = count,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun ProjectsScreenPreview() {
    ProjectsScreenContent(
        onItemClick = {},
        uiState = ProjectsUIState(
            true,
            listOf(
                Project(
                    69,
                    "KGBT+",
                    "Some random project",
                    "Project for projecting",
                    "1",
                    "",
                    "5",
                    false
                ),
                Project(
                    69,
                    "KGBT+",
                    "Some random project",
                    "Project for projecting",
                    "44",
                    "",
                    "5",
                    true
                ),
                Project(
                    69,
                    "KGBT+",
                    "Some random project",
                    "Project for projecting",
                    "13",
                    "",
                    "58",
                    false,
                ),
                Project(
                    69,
                    "KGBT+",
                    "Some random project",
                    "Project for projecting",
                    "235",
                    "",
                    "124",
                    false
                ),
            )
        ),
    )
}