package com.wiensmit.rmapp.data.museum.api

import com.wiensmit.rmapp.data.museum.api.model.MuseumArtDetailsEntity
import com.wiensmit.rmapp.data.museum.api.model.MuseumArtFeedEntity
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MuseumApi {

    @POST("{language}/collection")
    suspend fun getOverviewFeed(
        @Path("language") language: String,
        @Query("key") apiKey: String,
        @Query("p") pageNumber: Int,
        @Query("ps") pageSize: Int,
        @Query("s") sortedBy: String = "artist",
        @Query("imgonly") imageOnly: Boolean = true,
    ): MuseumArtFeedEntity

    @POST("{language}/collection/{objectNumber}")
    suspend fun getArtDetails(
        @Path("language") language: String,
        @Path("objectNumber") objectNumber: String,
        @Query("key") apiKey: String,
    ): MuseumArtDetailsEntity
}