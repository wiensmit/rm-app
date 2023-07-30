package com.wiensmit.rmapp.data.museum.api.model

data class MuseumArtDetailsEntity(
    val artObject: MuseumArtObjectEntity,
)

data class MuseumArtObjectEntity(
    val objectNumber: String?,
    val title: String?,
    val webImage: MuseumArtImageEntity?,
    val colors: List<MuseumArtObjectColorEntity>?,
    val description: String?,
    val objectTypes: List<String>?,
    val objectCollection: List<String>?,
    val principalMakers: List<MuseumArtObjectMakerEntity>,
    val materials: List<String>?,
    val techniques: List<String>?,
    val dating: MuseumArtObjectDatingEntity?,
    val historicalPersons: List<String>?,
)

data class MuseumArtObjectDatingEntity(
    val presentingDate: String?,
)

data class MuseumArtObjectMakerEntity(
    val name: String?,
    val placeOfBirth: String?,
    val dateOfBirth: String?,
    val dateOfDeath: String?,
    val occupation: List<String>?,
    val nationality: String?,
    val biography: String?,
)

data class MuseumArtObjectColorEntity(
    val percentage: Int,
    val hex: String,
)