package com.octocavern.project

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
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
        Surface {
            Text(text = "Projects")
            if (uiState.isLoading) Text(text = "Loading")
        }
    }
}

@Preview
@Composable
fun ProjectsScreenPreview() {
    ProjectsScreenContent()
}