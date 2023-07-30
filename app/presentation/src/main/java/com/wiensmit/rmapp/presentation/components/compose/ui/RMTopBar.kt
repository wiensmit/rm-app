package com.wiensmit.rmapp.presentation.components.compose.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wiensmit.rmapp.presentation.R
import com.wiensmit.rmapp.presentation.components.compose.theme.AppDimension
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme
import com.wiensmit.rmapp.presentation.components.extensions.buttonPress

/**
 * app bar with optional back button
 * version 1.0
 */
@Composable
fun RMTopBar(
    title: String,
    onCloseClicked: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = CenterVertically) {
                onCloseClicked?.let {
                    Image(
                        modifier = Modifier
                            .size(AppDimension.Spacing.spacing44)
                            .padding(end = AppDimension.Spacing.spacing8)
                            .buttonPress { onCloseClicked() }
                            .testTag("back_button"),
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(AppTheme.color.onInverseBackground),
                    )
                }
                Text(
                    text = title,
                    style = AppTheme.typography.heading,
                    color = AppTheme.color.onInverseBackground
                )
            }
        },
        backgroundColor = AppTheme.color.inverseBackground,
        elevation = 2.dp
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingLight() =
    RMTopBarPreview("app bar")

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoadingDark() =
    RMTopBarPreview("app bar", {})

@Composable
private fun RMTopBarPreview(display: String, onCloseClicked: (() -> Unit)? = null) {
    AppTheme {
        Surface() {
            RMTopBar(display, onCloseClicked)
        }
    }
}