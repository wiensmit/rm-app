package com.wiensmit.rmapp.data.museum.mapper

import com.wiensmit.rmapp.common.extensions.nullIfEmpty
import com.wiensmit.rmapp.data.museum.api.model.MuseumArtDetailsEntity
import com.wiensmit.rmapp.data.museum.api.model.MuseumArtObjectColorEntity
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetailColors
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetailMaker
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import javax.inject.Inject

/**
 * mapper to convert a detailed art object to its domain layer equivalent
 *
 * version 1.0
 */
class MuseumArtDetailsMapper @Inject constructor() {

    fun map(entity: MuseumArtDetailsEntity): MuseumArtDetails {
        return with(entity.artObject) {
            MuseumArtDetails(
                objectNumber = objectNumber!!,
                title = title!!,
                imageUrl = webImage?.url,
                colors = colors?.let { mapColors(it) },
                description = description,
                objectTypes = objectTypes.nullIfEmpty(),
                objectCollection = objectCollection.nullIfEmpty(),
                materials = materials.nullIfEmpty(),
                techniques = techniques.nullIfEmpty(),
                presentingDate = dating?.presentingDate,
                historicalPersons = historicalPersons.nullIfEmpty(),
                makers = principalMakers.mapNotNull {
                    MuseumArtDetailMaker(
                        name = it.name ?: return@mapNotNull null,
                        placeOfBirth = it.placeOfBirth,
                        dateOfBirth = it.dateOfBirth,
                        dateOfDeath = it.dateOfDeath,
                        occupation = it.occupation.nullIfEmpty(),
                        nationality = it.nationality,
                        biography = it.biography
                    )
                }
            )
        }
    }

    private fun mapColors(colors: List<MuseumArtObjectColorEntity>): List<MuseumArtDetailColors>? {
        return if (colors.size > 1) {
            colors.map { MuseumArtDetailColors(it.percentage, it.hex.trim()) }
        } else null
    }
}