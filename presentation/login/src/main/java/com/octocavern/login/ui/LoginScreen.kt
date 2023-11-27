package com.octocavern.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.octocavern.ui.component.SimpleButton
import com.octocavern.ui.theme.ShishkaTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController,
) {
    val uiState by viewModel.state.collectAsState()

    LoginScreenContent(
        uiState = uiState,
        onBack = { navController.navigateUp() }
    )
}

@Composable
fun LoginScreenContent(
    uiState: LoginUIState = LoginUIState(),
    onBack: () -> Unit = {},
) {
    ShishkaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                SimpleButton(text = "Go to first fragment") {
                    onBack()
                }
                SimpleButton(text = "Fetch random joke from API") {
                }
                if (uiState.isLoading) CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreenContent() {
    LoginScreenContent()
}