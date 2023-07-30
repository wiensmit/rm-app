package com.wiensmit.rmapp.presentation.ui.artdetails.model

import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetailMaker
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import javax.inject.Inject

/**
 * Art details screen loading state skeleton
 * version 1.0
 */
class ArtDetailsSkeletonProvider @Inject constructor() {

    fun provideSkeleton() = ArtDetailsDisplay.Loading(
        ArtDetailsDisplay.Data(
            MuseumArtDetails(
                objectNumber = string(12),
                title = string(12),
                imageUrl = null,
                colors = null,
                description = string(200),
                objectTypes = listOf(string(8), string(8), string(8)),
                objectCollection = listOf(string(8), string(8), string(8)),
                materials = listOf(string(8), string(8), string(8)),
                techniques = listOf(string(8), string(8), string(8)),
                presentingDate = string(12),
                historicalPersons = listOf(string(12), string(12), string(12)),
                makers = listOf(createMaker(), createMaker())
            )
        )
    )

    private fun createMaker() = MuseumArtDetailMaker(
        name = string(12),
        placeOfBirth = string(8),
        dateOfBirth = string(8),
        dateOfDeath = string(8),
        occupation = listOf(string(8)),
        nationality = string(8),
        biography = string(100),
    )

    fun string(size: Int): String = (0..size).joinToString("") { "*" }
}