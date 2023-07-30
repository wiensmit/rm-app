package com.wiensmit.rmapp.presentation.ui.artdetails.model

import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails

/**
 * Art Details display with screen states
 * version 1.0
 */
sealed class ArtDetailsDisplay {

    data class Data(
        val details: MuseumArtDetails,
    ) : ArtDetailsDisplay()

    data class Loading(
        val skeleton: Data,
    ) : ArtDetailsDisplay()

    object Error : ArtDetailsDisplay()
}