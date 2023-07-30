package com.wiensmit.rmapp.presentation.ui.artdetails.ui

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.placeholder.material.placeholder
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetailMaker
import com.wiensmit.rmapp.presentation.R
import com.wiensmit.rmapp.presentation.components.compose.theme.AppDimension
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme
import com.wiensmit.rmapp.presentation.components.extensions.setImageUrlSize
import com.wiensmit.rmapp.presentation.components.extensions.toColorStops
import com.wiensmit.rmapp.presentation.ui.artdetails.model.ArtDetailsDisplay
import com.wiensmit.rmapp.presentation.ui.artdetails.preview.ArtDetailsPreviewProvider

/**
 * Art Details content with optional loading state
 * version 1.0
 */
@Composable
fun ArtDetailsContent(
    display: ArtDetailsDisplay.Data,
    isLoading: Boolean,
) {
    val brush = display.details.colors?.toColorStops()?.let {
        Brush.verticalGradient(*(it.toTypedArray()))
    } ?: Brush.verticalGradient(listOf(AppTheme.color.surface, AppTheme.color.neutral))
    val imageSizePx = LocalDensity.current.run { LocalConfiguration.current.screenWidthDp.dp.roundToPx() }

    Column(
        modifier = Modifier
            .background(AppTheme.color.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenWidthDp.dp / 1.3f)
        ) {
            if (!isLoading) {
                Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                    drawRect(brush)
                })
            }
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(AppDimension.Spacing.spacing24)
                    .placeholder(isLoading),
                painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .placeholder(R.drawable.ic_placeholder)
                        .data(display.details.imageUrl?.setImageUrlSize(imageSizePx))
                        .size(Size(imageSizePx, imageSizePx))
                        .build()
                ),
                contentDescription = stringResource(R.string.app_name) + display.details.title,
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppDimension.Spacing.spacing24, vertical = AppDimension.Spacing.spacing12),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .placeholder(isLoading),
                text = display.details.title,
                style = AppTheme.typography.subtitle,
                color = AppTheme.color.onBackground,
            )
            display.details.description?.let {
                Text(
                    modifier = Modifier
                        .padding(top = AppDimension.Spacing.spacing12)
                        .align(CenterHorizontally)
                        .placeholder(isLoading),
                    text = it,
                    style = AppTheme.typography.body,
                    color = AppTheme.color.onBackground,
                )
            }
            Spacer(modifier = Modifier.height(AppDimension.Spacing.spacing12))
            display.details.presentingDate?.let {
                ArtDetailsContentList(stringResource(id = R.string.presenting_date), listOf(it), isLoading)
            }
            display.details.historicalPersons?.let {
                ArtDetailsContentList(stringResource(id = R.string.historical_persons), it, isLoading)
            }
            display.details.objectTypes?.let {
                ArtDetailsContentList(stringResource(id = R.string.object_types), it, isLoading)
            }
            display.details.objectCollection?.let {
                ArtDetailsContentList(stringResource(id = R.string.object_collection), it, isLoading)
            }
            display.details.materials?.let {
                ArtDetailsContentList(stringResource(id = R.string.materials), it, isLoading)
            }
            display.details.techniques?.let {
                ArtDetailsContentList(stringResource(id = R.string.techniques), it, isLoading)
            }
            if (display.details.makers.isNullOrEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(top = AppDimension.Spacing.spacing12)
                        .align(CenterHorizontally)
                        .placeholder(isLoading),
                    text = stringResource(id = R.string.maker_unknown),
                    style = AppTheme.typography.body,
                    color = AppTheme.color.onBackground,
                )
            } else {
                Text(
                    modifier = Modifier
                        .padding(top = AppDimension.Spacing.spacing12)
                        .align(CenterHorizontally)
                        .placeholder(isLoading),
                    text = stringResource(id = R.string.makers),
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.color.onBackground,
                )
                display.details.makers?.forEach {
                    ArtDetailsContentMaker(maker = it, isLoading)
                }
                Spacer(modifier = Modifier.height(AppDimension.Spacing.spacing24))
            }
        }
    }
}

@Composable
private fun ArtDetailsContentList(
    type: String,
    details: List<String?>,
    isLoading: Boolean,
) {
    Row() {
        Text(
            modifier = Modifier
                .padding(end = AppDimension.Spacing.spacing4)
                .placeholder(isLoading),
            text = type,
            style = AppTheme.typography.body,
            color = AppTheme.color.onBackground,
        )
        Row() {
            Text(
                modifier = Modifier
                    .padding(end = AppDimension.Spacing.spacing4)
                    .placeholder(isLoading),
                text = details.filterNotNull().joinToString(separator = ", "),
                style = AppTheme.typography.bodyBold,
                color = AppTheme.color.onBackground,
            )
        }
    }
}

@Composable
private fun ArtDetailsContentMaker(
    maker: MuseumArtDetailMaker,
    isLoading: Boolean,
) {
    Row(modifier = Modifier.padding(vertical = AppDimension.Spacing.spacing24)) {
        Text(
            modifier = Modifier
                .padding(end = AppDimension.Spacing.spacing4)
                .placeholder(isLoading),
            text = maker.name,
            style = AppTheme.typography.bodyBold,
            color = AppTheme.color.onBackground,
        )
    }
    maker.dateOfBirth?.let {
        ArtDetailsContentList(stringResource(id = R.string.place_of_birth), listOf(it, maker.placeOfBirth), isLoading)
    }
    maker.nationality?.let {
        ArtDetailsContentList(stringResource(id = R.string.nationality), listOf(it), isLoading)
    }
    maker.dateOfDeath?.let {
        ArtDetailsContentList(stringResource(id = R.string.date_of_death), listOf(it), isLoading)
    }
    maker.occupation?.let {
        ArtDetailsContentList(stringResource(id = R.string.occupation), it, isLoading)
    }
    maker.biography?.let {
        ArtDetailsContentList(stringResource(id = R.string.biography), listOf(it), isLoading)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingLight() =
    ArtDetailsContentPreview(ArtDetailsPreviewProvider.artDetailsPreview(), false)

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoadingDark() =
    ArtDetailsContentPreview(ArtDetailsPreviewProvider.artDetailsPreview(), true)

@Composable
private fun ArtDetailsContentPreview(display: ArtDetailsDisplay.Data, isLoading: Boolean) {
    AppTheme {
        Surface() {
            ArtDetailsContent(display, isLoading)
        }
    }
}