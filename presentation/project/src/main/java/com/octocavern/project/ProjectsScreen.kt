package com.octocavern.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Card
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
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectItem(
    project: Project,
    onClick: (Project) -> Unit,
) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.padding(4.dp),
            onClick = { onClick(project) }
        ) {
            Row(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LetterAvatar(letter = project.name.first(), modifier = Modifier.size(48.dp))
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Text(
                        text = project.name,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = project.slug)
                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ProjectInfo(icon = Icons.Outlined.Person, count = project.members)
                        Spacer(modifier = Modifier.size(2.dp))
                        ProjectInfo(icon = Icons.Outlined.RemoveRedEye, count = project.watchers)
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectInfo(
    icon: ImageVector,
    count: String,
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
        Text(
            text = count,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun ProjectsScreenPreview() {
    ProjectsScreenContent(
        onItemClick = {},
        uiState = ProjectsUIState(
            false,
            listOf(
                Project(
                    69,
                    "KGBT+",
                    "Some random project",
                    "Project for projecting",
                    "3",
                    "",
                    "5"
                ),
                Project(
                    69,
                    "KGBT+",
                    "Some random project",
                    "Project for projecting",
                    "3",
                    "",
                    "5"
                ),
                Project(
                    69,
                    "KGBT+",
                    "Some random project",
                    "Project for projecting",
                    "3",
                    "",
                    "5"
                ),
                Project(
                    69,
                    "KGBT+",
                    "Some random project",
                    "Project for projecting",
                    "3",
                    "",
                    "5"
                ),
            )
        ),
    )
}