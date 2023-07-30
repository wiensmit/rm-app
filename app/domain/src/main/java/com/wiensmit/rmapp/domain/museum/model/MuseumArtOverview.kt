package com.wiensmit.rmapp.domain.museum.model

data class MuseumArtOverview(
    val objectNumber: String,
    val artTitle: String,
    val creatorName: String,
    val imageUrl: String?,
)