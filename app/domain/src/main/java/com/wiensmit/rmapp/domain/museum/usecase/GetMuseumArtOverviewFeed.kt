package com.wiensmit.rmapp.domain.museum.usecase

import com.wiensmit.rmapp.domain.museum.MuseumRepository
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import javax.inject.Inject

/**
 * supplies the MuseumArtOverview from the repository
 *
 * version 1.0
 */
class GetMuseumArtOverviewFeed @Inject constructor(
    private val museumRepository: MuseumRepository,
) {

    suspend operator fun invoke(page: Int, pageSize: Int): Result<List<MuseumArtOverview>> {
        return museumRepository.getOverviews(page, pageSize)
    }
}
