package com.octocavern.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.octocavern.auth.IsUserAuthorizedUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }

    @Inject
    lateinit var isUserAuthorizedUseCase: IsUserAuthorizedUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (isUserAuthorizedUseCase()) navController.navigate(NavRequests.PROJECTS_SCREEN)

        return ComposeView(requireActivity()).apply {
            setContent {
                LoginScreen(
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }
    }
}