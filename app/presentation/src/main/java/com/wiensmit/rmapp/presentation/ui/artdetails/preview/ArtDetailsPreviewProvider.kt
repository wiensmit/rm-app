package com.wiensmit.rmapp.presentation.ui.artdetails.preview

import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetailColors
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetailMaker
import com.wiensmit.rmapp.domain.museum.model.MuseumArtDetails
import com.wiensmit.rmapp.presentation.ui.artdetails.model.ArtDetailsDisplay
import javax.inject.Inject

object ArtDetailsPreviewProvider {

    fun artDetailsPreview() = ArtDetailsDisplay.Data(
        MuseumArtDetails(
            objectNumber = "122344",
            title = "Nice Artwork",
            imageUrl = null,
            colors = listOf(
                MuseumArtDetailColors(6, "#FBFAF7"),
                MuseumArtDetailColors(13, "#95907F"),
                MuseumArtDetailColors(13, "#95907F"),
                MuseumArtDetailColors(21, "#E6E0C7"),
                MuseumArtDetailColors(35, "#686454")
            ),
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            objectTypes = listOf("one", "two", "three"),
            objectCollection = listOf("one", "two", "three"),
            materials = listOf("one", "two", "three"),
            techniques = listOf("one", "two", "three"),
            presentingDate = "1935 - 1939",
            historicalPersons = listOf("one", "two", "three"),
            makers = listOf(createMaker(), createMaker())
        )
    )

    private fun createMaker() = MuseumArtDetailMaker(
        name = "Some One",
        placeOfBirth = "village",
        dateOfBirth = "14-09-1876",
        dateOfDeath = "23-08-1921",
        occupation = listOf("painter", "something"),
        nationality = "dutch",
        biography = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ",
    )
}