package com.wiensmit.rmapp.presentation.ui.overview.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.wiensmit.rmapp.presentation.components.compose.theme.AppDimension
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme

/**
 * Art overview screen sticky header item
 *
 * version 1.0
 */
@Composable
fun OverviewHeaderItem(
    title: String,
) {
    Column(
        modifier = Modifier
            .background(AppTheme.color.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppDimension.Spacing.spacing8)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = AppDimension.Spacing.spacing16),
                text = title,
                style = AppTheme.typography.subtitle,
                color = AppTheme.color.onBackground
            )
        }
        Divider()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingLight() =
    OverviewHeaderItemPreview("Johannes Charles the third von buckenstein")

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoadingDark() =
    OverviewHeaderItemPreview("Johannes Charles the third von buckenstein")

@Composable
private fun OverviewHeaderItemPreview(display: String) {
    AppTheme {
        Surface() {
            OverviewHeaderItem(display)
        }
    }
}