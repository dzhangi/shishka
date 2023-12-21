package com.octocavern.auth.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.octocavern.ui.theme.ShishkaTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController,
) {
    val uiState by viewModel.state.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetState()
        }
    }

    LoginScreenContent(
        uiState = uiState,
        onLogin = { login, pass -> viewModel.signIn(login, pass) },
        onSignUp = { viewModel.signUp() },
        onSuccessLogin = { navController.navigate(NavRequests.PROJECTS_SCREEN) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    uiState: LoginUIState,
    onLogin: (String, String) -> Unit = { _, _ -> },
    onSignUp: () -> Unit = { },
    onSuccessLogin: () -> Unit = { },
) {
    val login = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    if (uiState.isSuccess) onSuccessLogin()

    ShishkaTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Login",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 180.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Please sign in to continue",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = login.value,
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    onValueChange = { login.value = it },
                    label = { if (!uiState.isLoginError) Text("login") },
                    isError = uiState.isLoginError,
                    supportingText = { if (uiState.isLoginError) Text(uiState.loginErrorMessage) },
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    value = password.value,
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    onValueChange = { password.value = it },
                    label = { if (!uiState.isPasswordError) Text("password") },
                    isError = uiState.isPasswordError,
                    supportingText = { if (uiState.isPasswordError) Text(uiState.passwordErrorMessage) }
                )
                Button(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .align(Alignment.End)
                        .width(128.dp),
                    onClick = { onLogin(login.value, password.value) },
                ) {
                    if (!uiState.isLoading) Text(text = "sign in")
                    if (uiState.isLoading) CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.2.dp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Don't have an account?")
                    Text(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .clickable { onSignUp() },
                        text = "Sign up",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreenContent() {
    LoginScreenContent(
        uiState = LoginUIState()
    )
}