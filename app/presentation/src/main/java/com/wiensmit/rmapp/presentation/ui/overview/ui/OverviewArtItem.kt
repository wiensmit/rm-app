package com.wiensmit.rmapp.presentation.ui.overview.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.placeholder.material.placeholder
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import com.wiensmit.rmapp.presentation.R
import com.wiensmit.rmapp.presentation.components.compose.theme.AppDimension
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme
import com.wiensmit.rmapp.presentation.components.extensions.setImageUrlSize
import com.wiensmit.rmapp.presentation.ui.overview.preview.OverviewArtPreviewProvider

/**
 * Art overview screen art list item
 *
 * version 1.0
 */
@Composable
fun OverviewArtItem(
    display: MuseumArtOverview,
    iconSizePx: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppDimension.Spacing.spacing16,
                    vertical = AppDimension.Spacing.spacing8
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = AppDimension.Spacing.spacing8),
                text = display.artTitle,
                style = AppTheme.typography.bodyBold,
                color = AppTheme.color.onBackground,
            )
            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(AppDimension.Spacing.spacing52)
                    .placeholder(false),
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .placeholder(R.drawable.ic_placeholder)
                        .data(display.imageUrl?.setImageUrlSize(iconSizePx))
                        .size(Size(iconSizePx, iconSizePx))
                        .build()
                ),
                contentDescription = stringResource(R.string.app_name) + display.artTitle,
            )
        }
        Divider(color = AppTheme.color.surface)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingLight() =
    OverviewArtItem(OverviewArtPreviewProvider.provideOverviewArtItem())

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoadingDark() =
    OverviewArtItem(OverviewArtPreviewProvider.provideOverviewArtItem())

@Composable
private fun OverviewArtItem(display: MuseumArtOverview) {
    val iconSizePx = LocalDensity.current.run { AppDimension.Spacing.spacing52.roundToPx() }
    AppTheme {
        Surface(color = AppTheme.color.background) {
            OverviewArtItem(display, iconSizePx)
        }
    }
}