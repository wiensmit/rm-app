package com.wiensmit.rmapp.data.museum

import com.wiensmit.rmapp.common.extensions.safeRunAsync
import com.wiensmit.rmapp.data.components.config.DataConfig
import com.wiensmit.rmapp.data.museum.api.provider.MuseumApiProvider
import com.wiensmit.rmapp.data.museum.mapper.MuseumArtDetailsMapper
import com.wiensmit.rmapp.data.museum.mapper.MuseumArtOverviewMapper
import com.wiensmit.rmapp.domain.museum.MuseumRepository
import com.wiensmit.rmapp.domain.localization.usecase.GetLocale
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import javax.inject.Inject

/**
 * repository to receive data from the rijks museum api
 *
 * version 1.0
 */
class MuseumDataRepository @Inject constructor(
    apiProvider: MuseumApiProvider,
    private val getLocale: GetLocale,
    private val dataConfig: DataConfig,
    private val overviewMapper: MuseumArtOverviewMapper,
    private val detailsMapper: MuseumArtDetailsMapper,
) : MuseumRepository {

    private val api by lazy { apiProvider.museumApi }

    override suspend fun getOverviews(page: Int, pageSize: Int): Result<List<MuseumArtOverview>> {
        return Result.safeRunAsync {
            api.getOverviewFeed(
                language = getLocale(),
                apiKey = dataConfig.museumApiToken,
                pageNumber = page,
                pageSize = pageSize
            )
        }.map {
            //preform an extra sorting on creatorName because the api doesn't always sort properly
            overviewMapper.map(it).sortedBy { it.creatorName }
        }
    }

    override suspend fun getArtDetails(objectNumber: String): Result<MuseumArtDetails> {
        return Result.safeRunAsync {
            api.getArtDetails(
                language = getLocale(),
                apiKey = dataConfig.museumApiToken,
                objectNumber = objectNumber
            )
        }.map {
            detailsMapper.map(it)
        }
    }
}
