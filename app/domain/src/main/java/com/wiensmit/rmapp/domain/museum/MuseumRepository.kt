package com.wiensmit.rmapp.domain.museum

import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview

interface MuseumRepository {

    suspend fun getOverviews(page: Int, pageSize: Int): Result<List<MuseumArtOverview>>

    suspend fun getArtDetails(objectNumber: String): Result<MuseumArtDetails>
}