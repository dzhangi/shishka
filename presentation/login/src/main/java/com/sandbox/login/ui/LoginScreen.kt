package com.sandbox.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.sandbox.ui.component.SimpleButton
import com.sandbox.ui.theme.ModularSandboxTheme

@Composable
fun SecondScreen(
    viewModel: LoginViewModel,
    navController: NavController,
//    args: LoginFragmentArgs,
) {
    val uiState by viewModel.state.collectAsState()

    ModularSandboxTheme {
        Surface {
            Column {
//                Text(text = args.testArg.toString())
                SimpleButton(text = "Go to first fragment") {
                    navController.navigateUp()
                }
                SimpleButton(text = "Fetch random joke from API") {
                    viewModel.fetchRandomJoke()
                }
                if (uiState.isLoading) CircularProgressIndicator()
                Text(text = uiState.joke)
            }
        }
    }
}