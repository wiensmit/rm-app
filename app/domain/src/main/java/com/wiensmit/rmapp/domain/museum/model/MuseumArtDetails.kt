package com.wiensmit.rmapp.domain.museum.model

data class MuseumArtDetails(
    val objectNumber: String,
    val title: String,
    val imageUrl: String?,
    val colors: List<MuseumArtDetailColors>?,
    val description: String?,
    val objectTypes: List<String>?,
    val objectCollection: List<String>?,
    val materials: List<String>?,
    val techniques: List<String>?,
    val presentingDate: String?,
    val historicalPersons: List<String>?,
    val makers: List<MuseumArtDetailMaker>?,
)

data class MuseumArtDetailColors(
    val percentage: Int,
    val hex: String,
)

data class MuseumArtDetailMaker(
    val name: String,
    val placeOfBirth: String?,
    val dateOfBirth: String?,
    val dateOfDeath: String?,
    val occupation: List<String>?,
    val nationality: String?,
    val biography: String?,
)