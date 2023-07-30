package com.wiensmit.rmapp.data.museum.api.provider

import com.wiensmit.rmapp.data.BuildConfig
import com.wiensmit.rmapp.data.components.retrofit.RetrofitFactory
import com.wiensmit.rmapp.data.museum.api.MuseumApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * api provider for the Rijks Museum api
 *
 * version 1.0
 */
@Singleton
class MuseumApiProvider @Inject constructor(
    retrofitFactory: RetrofitFactory,
) {

    val museumApi: MuseumApi by lazy {
        retrofitFactory.buildWithUrl(
            BuildConfig.RM_API_URL,
            BuildConfig.DEBUG
        ).create(MuseumApi::class.java)
    }
}