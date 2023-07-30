package com.wiensmit.rmapp.presentation.components.compose.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wiensmit.rmapp.presentation.R
import com.wiensmit.rmapp.presentation.components.compose.theme.AppDimension
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme

/**
 * generic error with retry button
 * version 1.0
 */
@Composable
fun RetryError(
    onRetryClicked: (() -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(AppTheme.color.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = AppDimension.Spacing.spacing24)
                .testTag("error_message"),
            text = stringResource(R.string.error_message),
            style = AppTheme.typography.subtitle,
            color = AppTheme.color.onBackground,
        )
        Button(
            modifier = Modifier
                .padding(top = AppDimension.Spacing.spacing12)
                .testTag("error_button"),
            colors = ButtonDefaults.buttonColors(containerColor = AppTheme.color.inverseBackground),
            onClick = onRetryClicked
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = AppDimension.Spacing.spacing24),
                text = stringResource(R.string.error_retry),
                style = AppTheme.typography.subtitle,
                color = AppTheme.color.onInverseBackground,
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingLight() =
    RetryErrorPreview({})

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoadingDark() =
    RetryErrorPreview({})

@Composable
private fun RetryErrorPreview(onRetryClicked: (() -> Unit)) {
    AppTheme {
        Surface() {
            RetryError(onRetryClicked)
        }
    }
}