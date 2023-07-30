package com.wiensmit.rmapp.presentation.components.compose.theme

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiensmit.rmapp.presentation.R

internal object TypographyToken {

    private val ChaletNewYork60 = FontFamily(Font(R.font.chalet_new_york_sixty))
    private val ChaletLondon60 = FontFamily(Font(R.font.chalet_london_sixty))

    val header = TextStyle(
        fontFamily = ChaletNewYork60,
        fontSize = 30.sp
    )

    val subtitle = TextStyle(
        fontFamily = ChaletNewYork60,
        fontSize = 20.sp
    )

    val body = TextStyle(
        fontFamily = ChaletLondon60,
        fontSize = 16.sp,
        lineHeight = 26.sp
    )

    val bodyBold = TextStyle(
        fontFamily = ChaletNewYork60,
        fontSize = 16.sp,
        lineHeight = 26.sp
    )

    val button = TextStyle(
        fontFamily = ChaletNewYork60,
        fontSize = 16.sp
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun PreviewTypographyLight() = Typography()

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewTypographyDark() = Typography()

@Composable
private fun Typography() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = AppTheme.color.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text("Heading", color = AppTheme.color.onBackground, style = AppTheme.typography.heading)
                Text("Subtitle", color = AppTheme.color.onBackground, style = AppTheme.typography.subtitle)
                Text("Body", color = AppTheme.color.onBackground, style = AppTheme.typography.body)
                Text("Body Bold", color = AppTheme.color.onBackground, style = AppTheme.typography.bodyBold)
                Text("Button", color = AppTheme.color.onBackground, style = AppTheme.typography.button)
            }
        }
    }

}
