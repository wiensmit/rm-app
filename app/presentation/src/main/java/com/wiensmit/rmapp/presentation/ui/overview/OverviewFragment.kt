package com.wiensmit.rmapp.presentation.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme
import com.wiensmit.rmapp.presentation.ui.overview.ui.OverviewScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Art overview fragment, used as a wrapper for its compose screen
 *
 * version 1.0
 */
@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private val overviewVM: OverviewViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(requireActivity().lifecycle)
            )
            setContent {
                AppTheme {
                    OverviewScreen(
                        viewModel = overviewVM,
                        onItemClicked = ::navigateDetails
                    )
                }
            }
        }
    }

    private fun navigateDetails(objectNumber: String) {
        findNavController().navigate(OverviewFragmentDirections.actionDashboardToAddExtras(objectNumber))
    }
}
