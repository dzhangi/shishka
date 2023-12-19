package com.octocavern.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    )
}

@Composable
fun ProjectsScreenContent(
    uiState: ProjectsUIState = ProjectsUIState(),
) {
    ShishkaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(uiState.projects) { project ->
                    ProjectItem(project)
                }
            }
        }
    }
}

@Composable
fun ProjectItem(project: Project) {
    Surface {
        Column {
            Text(text = project.id.toString())
            Text(text = project.name)
            Text(text = project.description)
        }
    }
}

@Preview
@Composable
fun ProjectsScreenPreview() {
    ProjectsScreenContent()
}