package com.wiensmit.rmapp.data.museum

import com.wiensmit.rmapp.data.components.config.DataConfig
import com.wiensmit.rmapp.data.museum.api.MuseumApi
import com.wiensmit.rmapp.data.museum.api.provider.MuseumApiProvider
import com.wiensmit.rmapp.data.museum.mapper.MuseumArtDetailsMapper
import com.wiensmit.rmapp.data.museum.mapper.MuseumArtOverviewMapper
import com.wiensmit.rmapp.domain.localization.usecase.GetLocale
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MuseumDataRepositoryTest {

    private val api = mockk<MuseumApi>()
    private val apiProvider = mockk<MuseumApiProvider> {
        every { museumApi } returns this@MuseumDataRepositoryTest.api
    }
    private val getLocale = mockk<GetLocale>(relaxed = true)
    private val dataConfig = mockk<DataConfig>(relaxed = true)
    private val overviewMapper = mockk<MuseumArtOverviewMapper>(relaxed = true)
    private val detailsMapper = mockk<MuseumArtDetailsMapper>(relaxed = true)

    private val repository = MuseumDataRepository(apiProvider, getLocale, dataConfig, overviewMapper, detailsMapper)

    @Test
    fun `get the art details from the api and map them with the details mapper`() {
        //given
        val response = mockk<MuseumArtDetails>()
        coEvery { dataConfig.museumApiToken } returns "abc"
        coEvery { api.getArtDetails(any(), "1234", "abc") } returns mockk()
        coEvery { detailsMapper.map(any()) } returns response

        // when
        val result = runBlocking { repository.getArtDetails("1234") }

        // then
        Assert.assertEquals(Result.success(response), result)
    }

    @Test
    fun `get the art details from the api but it throws and error`() {
        //given
        val response = Exception()
        coEvery { dataConfig.museumApiToken } returns "abc"
        coEvery { api.getArtDetails(any(), "1234", "abc") } throws response

        // when
        val result = runBlocking { repository.getArtDetails("1234") }

        // then
        Assert.assertEquals(Result.failure<MuseumArtDetails>(response), result)
    }

    @Test
    fun `get the art overview from the api and map them with the details mapper`() {
        //given
        val response = mockk<List<MuseumArtOverview>>(relaxed = true)
        coEvery { dataConfig.museumApiToken } returns "abc"
        coEvery { api.getOverviewFeed(any(), "abc", 1, 20) } returns mockk()
        coEvery { overviewMapper.map(any()) } returns response

        // when
        val result = runBlocking { repository.getOverviews(1, 20) }

        // then
        Assert.assertEquals(Result.success(response.sortedBy { it.creatorName }), result)
    }

    @Test
    fun `get the art overview from the api but it throws and error`() {
        //given
        val response = Exception()
        coEvery { dataConfig.museumApiToken } returns "abc"
        coEvery { api.getOverviewFeed(any(), "abc", 1, 20) } throws response

        // when
        val result = runBlocking { repository.getOverviews(1, 20) }

        // then
        Assert.assertEquals(Result.failure<MuseumArtDetails>(response), result)
    }

}