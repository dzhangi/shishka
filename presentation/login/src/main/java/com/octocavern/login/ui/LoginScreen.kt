package com.octocavern.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.octocavern.login.ui.LoginViewModel
import com.octocavern.ui.component.SimpleButton
import com.octocavern.ui.theme.ShishkaTheme

@Composable
fun SecondScreen(
    viewModel: LoginViewModel,
    navController: NavController,
//    args: LoginFragmentArgs,
) {
    val uiState by viewModel.state.collectAsState()

    ShishkaTheme {
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