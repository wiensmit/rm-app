package com.wiensmit.rmapp.data.museum.api.model

data class MuseumArtFeedEntity(
    val artObjects: List<MuseumFeedArtObjectEntity>,
)

data class MuseumFeedArtObjectEntity(
    val objectNumber: String,
    val title: String,
    val principalOrFirstMaker: String,
    val webImage: MuseumArtImageEntity?,
)