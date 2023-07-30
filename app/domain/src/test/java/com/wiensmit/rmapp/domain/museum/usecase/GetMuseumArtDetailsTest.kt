package com.wiensmit.rmapp.domain.museum.usecase

import com.wiensmit.rmapp.domain.museum.MuseumRepository
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetMuseumArtDetailsTest {

    @Test
    fun `when invoked, the details should be fetched from the repository`() {
        // given
        val reponse = Result.success<MuseumArtDetails>(mockk())
        val repository = mockk<MuseumRepository> {
            coEvery { getArtDetails("123") } returns reponse
        }
        val getDetails = GetMuseumArtDetails(repository)

        // when
        val result = runBlocking { getDetails("123") }

        // then
        coVerify { repository.getArtDetails(any()) }
        Assert.assertEquals(reponse, result)
    }
}