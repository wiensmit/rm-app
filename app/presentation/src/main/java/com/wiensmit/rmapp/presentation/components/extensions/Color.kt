package com.wiensmit.rmapp.presentation.components.extensions

import androidx.compose.ui.graphics.Color
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetailColors
import timber.log.Timber

fun List<MuseumArtDetailColors>.toColorStops(): List<Pair<Float, Color>>? {
    return try {
        map {
            it.percentage.toFloat() / 10 to Color(android.graphics.Color.parseColor(it.hex))
        }
    } catch (e: IllegalArgumentException) {
        Timber.e(e, this.map { it.hex }.joinToString { "$it, " })
        null
    }
}