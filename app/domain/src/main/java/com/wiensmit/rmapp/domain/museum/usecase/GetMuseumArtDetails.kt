package com.wiensmit.rmapp.domain.museum.usecase

import com.wiensmit.rmapp.domain.museum.MuseumRepository
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import javax.inject.Inject

/**
 * supplies the MuseumArtDetails from the repository
 *
 * version 1.0
 */
class GetMuseumArtDetails @Inject constructor(
    private val museumRepository: MuseumRepository,
) {

    suspend operator fun invoke(objectNumber: String): Result<MuseumArtDetails> {
        return museumRepository.getArtDetails(objectNumber)
    }
}
