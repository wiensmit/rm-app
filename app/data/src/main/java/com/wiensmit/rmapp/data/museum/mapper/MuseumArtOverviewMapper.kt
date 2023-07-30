package com.wiensmit.rmapp.data.museum.mapper

import com.wiensmit.rmapp.data.museum.api.model.MuseumArtFeedEntity
import com.wiensmit.rmapp.data.museum.api.model.MuseumFeedArtObjectEntity
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import javax.inject.Inject

/**
 * mapper to convert a overview art object to its domain layer equivalent
 *
 * version 1.0
 */
class MuseumArtOverviewMapper @Inject constructor() {

    fun map(entity: MuseumArtFeedEntity): List<MuseumArtOverview> {
        return entity.artObjects.map { mapObjectEntity(it) }
    }

    private fun mapObjectEntity(entity: MuseumFeedArtObjectEntity): MuseumArtOverview {
        return MuseumArtOverview(
            objectNumber = entity.objectNumber,
            artTitle = entity.title,
            creatorName = entity.principalOrFirstMaker,
            imageUrl = entity.webImage?.url
        )
    }
}