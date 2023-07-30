package com.wiensmit.rmapp.presentation.ui.artdetails.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wiensmit.rmapp.presentation.R
import com.wiensmit.rmapp.presentation.components.compose.ui.RMTopBar
import com.wiensmit.rmapp.presentation.components.compose.ui.RetryError
import com.wiensmit.rmapp.presentation.ui.artdetails.ArtDetailsViewModel
import com.wiensmit.rmapp.presentation.ui.artdetails.model.ArtDetailsDisplay

/**
 * Art Details screen state composable
 * version 1.0
 */
@Composable
fun ArtDetailsScreen(
    viewModel: ArtDetailsViewModel,
    onCloseClicked: () -> Unit,
) {
    val display by viewModel.display.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { RMTopBar(stringResource(R.string.details_screen), onCloseClicked) },
        content = {
            Surface(Modifier.padding(it)) {
                when (val subscriptionDisplay = display) {
                    is ArtDetailsDisplay.Loading -> ArtDetailsContent(
                        display = subscriptionDisplay.skeleton,
                        isLoading = true
                    )

                    is ArtDetailsDisplay.Data -> ArtDetailsContent(
                        display = subscriptionDisplay,
                        isLoading = false
                    )

                    is ArtDetailsDisplay.Error -> RetryError(
                        modifier = Modifier.fillMaxSize(),
                        onRetryClicked = { viewModel.retry() }
                    )
                }
            }
        }
    )
}