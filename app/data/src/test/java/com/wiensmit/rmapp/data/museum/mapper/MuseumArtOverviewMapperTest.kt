package com.wiensmit.rmapp.data.museum.mapper

import com.wiensmit.rmapp.data.museum.api.model.MuseumArtFeedEntity
import com.wiensmit.rmapp.data.museum.api.model.MuseumArtImageEntity
import com.wiensmit.rmapp.data.museum.api.model.MuseumFeedArtObjectEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MuseumArtOverviewMapperTest {

    private val mapper = MuseumArtOverviewMapper()

    val entity = MuseumArtFeedEntity(
        listOf(
            MuseumFeedArtObjectEntity(
                objectNumber = "1234",
                title = "test title",
                principalOrFirstMaker = "jan",
                webImage = null
            ),
            MuseumFeedArtObjectEntity(
                objectNumber = "1234",
                title = "test title",
                principalOrFirstMaker = "jan",
                webImage = MuseumArtImageEntity("https://test.com")
            )
        )
    )

    @Test
    fun `map overview entity, data is mapped to their correct fields`() {
        // when
        val result = runBlocking { mapper.map(entity) }

        // then
        Assert.assertEquals(null, result.first().imageUrl)
        Assert.assertEquals("test title", result.first().artTitle)
        Assert.assertEquals("1234", result.first().objectNumber)
        Assert.assertEquals("jan", result.first().creatorName)
        Assert.assertEquals("https://test.com", result.last().imageUrl)
    }
}