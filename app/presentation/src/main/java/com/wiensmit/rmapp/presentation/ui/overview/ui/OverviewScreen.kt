package com.wiensmit.rmapp.presentation.ui.overview.ui


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.wiensmit.rmapp.presentation.R
import com.wiensmit.rmapp.presentation.components.compose.theme.AppDimension
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme
import com.wiensmit.rmapp.presentation.components.compose.ui.RMTopBar
import com.wiensmit.rmapp.presentation.components.compose.ui.RetryError
import com.wiensmit.rmapp.presentation.components.extensions.buttonPress
import com.wiensmit.rmapp.presentation.ui.overview.OverviewViewModel

/**
 * Art overview screen state composable, listens to data from [OverviewPager]. handles loading and error states supplied by the pager
 *
 * version 1.0
 */
@ExperimentalFoundationApi
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel,
    onItemClicked: (String) -> Unit,
) {
    val display = viewModel.overviews.collectAsLazyPagingItems()
    var lastCreatorName: String? by remember { mutableStateOf(null) }
    val iconSizePx = LocalDensity.current.run { AppDimension.Spacing.spacing52.roundToPx() }

    Scaffold(
        topBar = { RMTopBar(stringResource(R.string.app_name)) },
        content = {
            LazyColumn(Modifier.padding(it)) {
                if (display.loadState.refresh == LoadState.Loading) {
                    item {
                        Column(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(color = AppTheme.color.onBackground)
                        }
                    }
                }

                for (index in 0 until display.itemCount) {
                    // Gets item without notifying Paging of the item access,
                    // which would otherwise trigger page loads
                    val peekArt = display.peek(index)
                    val creatorName = peekArt?.creatorName

                    if (peekArt !== null && creatorName != lastCreatorName) {
                        stickyHeader(key = peekArt.objectNumber + creatorName) {
                            creatorName?.let {
                                OverviewHeaderItem(it)
                            }
                        }
                    }

                    item(key = peekArt?.objectNumber) {
                        // Gets item, triggering page loads if needed
                        display[index]?.let {
                            OverviewArtItem(
                                modifier = Modifier.buttonPress { onItemClicked(it.objectNumber) },
                                display = it,
                                iconSizePx = iconSizePx
                            )
                        }
                    }

                    lastCreatorName = creatorName
                }

                if (display.loadState.append == LoadState.Loading) {
                    item {
                        CircularProgressIndicator(
                            color = AppTheme.color.onBackground,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .padding(vertical = AppDimension.Spacing.spacing12)
                        )
                    }
                }
                if (display.loadState.append is LoadState.Error || display.loadState.refresh is LoadState.Error) {
                    item {
                        RetryError(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = AppDimension.Spacing.spacing12),
                            onRetryClicked = { display.retry() }
                        )
                    }
                }
            }
        }
    )
}