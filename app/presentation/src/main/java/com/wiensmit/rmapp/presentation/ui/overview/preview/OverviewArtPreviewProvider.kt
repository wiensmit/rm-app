package com.wiensmit.rmapp.presentation.ui.overview.preview

import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview

object OverviewArtPreviewProvider {

    fun provideOverviewArtItem() = MuseumArtOverview("1234", "Nice Art Number 1", "Wien Smit", "https://image.com")
}