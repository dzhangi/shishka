package com.octocavern.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.octocavern.ui.component.SimpleButton
import com.octocavern.ui.theme.ShishkaTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [TestSplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TestSplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setContent {
                ShishkaTheme {
                    Surface {
                        SimpleButton(text = "Go to second fragment") {
                            val request = NavDeepLinkRequest.Builder
                                .fromUri("android-app://com.octocavern.app/test_login_fragment".toUri())
                                .build()
                            findNavController().navigate(request)
                        }
                    }
                }
            }
        }
    }
}