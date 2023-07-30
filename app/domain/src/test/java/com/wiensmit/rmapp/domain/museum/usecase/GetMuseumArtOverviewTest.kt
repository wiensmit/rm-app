package com.wiensmit.rmapp.domain.museum.usecase

import com.wiensmit.rmapp.domain.museum.MuseumRepository
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetMuseumArtOverviewTest {

    @Test
    fun `when invoked, the overviews should be fetched from the repository`() {
        // given
        val reponse = Result.success<List<MuseumArtOverview>>(mockk())
        val repository = mockk<MuseumRepository> {
            coEvery { getOverviews(1, 20) } returns reponse
        }
        val getOverviews = GetMuseumArtOverviewFeed(repository)

        // when
        val result = runBlocking { getOverviews(1, 20) }

        // then
        coVerify { repository.getOverviews(any(), any()) }
        Assert.assertEquals(reponse, result)
    }
}