package com.wiensmit.rmapp.presentation.components.extensions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.buttonPress(onClick: () -> Unit) = buttonPress(onClick, true, null)

fun Modifier.buttonPress(
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) = composed {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val translation by animateFloatAsState(if (pressed && enabled) 1.5f else 0f, tween(100))
    this
        .clickable(
            interactionSource = source,
            indication = null,
            onClick = onClick,
            enabled = enabled
        )
        .graphicsLayer(translationX = translation, translationY = translation)
}