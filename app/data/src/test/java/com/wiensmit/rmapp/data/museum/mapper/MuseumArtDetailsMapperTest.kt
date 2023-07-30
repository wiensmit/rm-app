package com.wiensmit.rmapp.data.museum.mapper

import com.wiensmit.rmapp.data.museum.api.model.MuseumArtDetailsEntity
import com.wiensmit.rmapp.data.museum.api.model.MuseumArtObjectEntity
import com.wiensmit.rmapp.data.museum.api.model.MuseumArtObjectMakerEntity
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetailMaker
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MuseumArtDetailsMapperTest {

    private val mapper = MuseumArtDetailsMapper()

    val entity = MuseumArtDetailsEntity(
        MuseumArtObjectEntity(
            objectNumber = "1234",
            title = "1232",
            webImage = null,
            colors = null,
            description = "test",
            objectTypes = emptyList(),
            objectCollection = emptyList(),
            principalMakers = listOf(
                MuseumArtObjectMakerEntity(
                    name = "charles",
                    placeOfBirth = null,
                    dateOfBirth = null,
                    dateOfDeath = null,
                    occupation = emptyList(),
                    nationality = null,
                    biography = null
                )
            ),
            materials = emptyList(),
            techniques = emptyList(),
            dating = null,
            historicalPersons = emptyList()
        )
    )

    @Test
    fun `map detail entity, when lists are empty return them as null`() {
        // when
        val result = runBlocking { mapper.map(entity) }

        // then
        Assert.assertEquals(null, result.objectTypes)
        Assert.assertEquals(null, result.objectCollection)
        Assert.assertEquals(null, result.materials)
        Assert.assertEquals(null, result.techniques)
        Assert.assertEquals(null, result.historicalPersons)
        Assert.assertEquals(null, result.makers!!.first().occupation)
    }

    @Test
    fun `map detail entity, when a maker has no name ignore it`() {
        // when
        val result = runBlocking {
            mapper.map(
                entity.copy(
                    artObject = entity.artObject.copy(
                        principalMakers = listOf(
                            entity.artObject.principalMakers.first().copy(name = null)
                        )
                    )
                )
            )
        }

        // then
        Assert.assertEquals(emptyList<MuseumArtDetailMaker>(), result.makers)
    }
}