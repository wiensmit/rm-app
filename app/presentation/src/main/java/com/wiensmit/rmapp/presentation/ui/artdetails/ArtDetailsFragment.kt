package com.wiensmit.rmapp.presentation.ui.artdetails

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
import androidx.navigation.fragment.navArgs
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme
import com.wiensmit.rmapp.presentation.ui.artdetails.ui.ArtDetailsScreen
import com.wiensmit.rmapp.presentation.ui.overview.ui.OverviewScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Art details fragment, used as a wrapper for its compose screen
 *
 * version 1.0
 */
@AndroidEntryPoint
class ArtDetailsFragment : Fragment() {

    private val detailsVM: ArtDetailsViewModel by viewModels()
    private val args by navArgs<ArtDetailsFragmentArgs>()

    @ExperimentalFoundationApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme {
                    ArtDetailsScreen(
                        viewModel = detailsVM,
                        onCloseClicked = ::close
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsVM.initVm(args.objectNumber)
    }

    private fun close() {
        findNavController().popBackStack()
    }
}
